-module(frontend).
-import(auth, [auth/4]).
-import(catalog, [catalog/1]).
-export([start/4]).

start(Port, ManufacturerQueuePort, ImporterMapperPort, NotificationPort) ->
  LoginManager = spawn(fun() -> auth(#{"m0" => {'MANUFACTURER', "pm0"},
                                       "m1" => {'MANUFACTURER', "pm1"},
                                       "i0" => {'IMPORTER', "pi0"},
                                       "i1" => {'IMPORTER', "pi1"},
                                       "i2" => {'IMPORTER', "pi2"},
                                       "i3" => {'IMPORTER', "pi3"},
                                       "i4" => {'IMPORTER', "pi4"}}, ManufacturerQueuePort, ImporterMapperPort, NotificationPort) end),
  {ok, LSock} = gen_tcp:listen(Port, [binary, {packet, 0}, {reuseaddr, true}, {active, true}]),
  application:start(chumak),
  acceptor(LSock, LoginManager).

acceptor(LSock, LoginManager) ->
  {ok, Sock} = gen_tcp:accept(LSock),
  spawn(fun() -> acceptor(LSock, LoginManager) end),
  tcp_handler(Sock, LoginManager).

tcp_handler(Sock, Pid) ->
  receive
    {pid, NewPid} ->
      tcp_handler(Sock, NewPid);
    {tcp, Sock, EncodedData} ->
      Pid ! {Sock, self(), EncodedData},
      tcp_handler(Sock, Pid);
    {tcp_closed, _} ->
      io:format("Connection closed.~n");
    {tcp_error, _, _} ->
      io:format("TCP Error ocurred.~n"),
      tcp_handler(Sock, Pid)
  end.