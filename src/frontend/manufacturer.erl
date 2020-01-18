-module(manufacturer).
-export([manufacturerStart/2]).

manufacturerStart(Catalog, ManufacturerQueuePort) ->
  ok = application:start(chumak),
  {ok, Socket} = chumak:socket(push),
  {ok, _BindPid} = chumak:connect(Socket, tcp, "localhost", ManufacturerQueuePort),
  manufacturer(Catalog, Socket).

manufacturer(Catalog, Socket) ->
  receive
    {Sock, TcpHandler, EncodedData} ->
      io:format("BREAKPOINT 15.~n"),
      DecodedMap = proto_product:decode_msg(EncodedData, 'Product'),
      io:format("~p~n", [DecodedMap]),
      ok = chumak:send(Socket, EncodedData),
      manufacturer(Catalog, Socket)
  end.