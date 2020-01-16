-module(frontend).
-import(auth, [auth/2]).
-import(catalog, [catalog/1]).
-export([start/1]).

start(Port) ->
  CatalogManager = spawn(fun() -> catalog(#{}) end),
  LoginManager = spawn(fun() -> auth(#{"m0" => {'MANUFACTURER', "pm0"}, "i0" => {'IMPORTER', "pi0"}}, CatalogManager) end),
  {ok, LSock} = gen_tcp:listen(Port, [binary, {packet, 0}, {reuseaddr, true}, {active, true}]),
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
      Pid ! {Sock, self(), EncodedData};
    {tcp_closed, _} ->
      io:format("Connection closed.\n");
    {tcp_error, _, _} ->
      io:format("TCP Error ocurred.\n")
  end.