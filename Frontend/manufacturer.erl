-module(manufacturer).
-export([manufacturer/1]).

manufacturer(Catalog) ->
  receive
    {Sock, TcpHandler, EncodedData} ->
        io:format("BREAKPOINT 0.\n"),
        DecodedMap = auth:decode_msg(EncodedData, 'NOVOPROTOCOLO'),
        io:format("I'm a manufacturer!\n"),
        manufacturer(Catalog)
  end.