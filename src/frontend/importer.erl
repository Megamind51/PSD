-module(importer).
-export([importerStart/2]).

importerStart(Catalog, ImporterMappingPort) ->
  {ok, Socket} = chumak:socket(pub),
  {ok, _BindPid} = chumak:connect(Socket, tcp, "localhost", ImporterMappingPort),
  importer(Catalog, Socket).

importer(Catalog, Socket) ->
  receive
    {Sock, TcpHandler, EncodedData} ->
      ok = chumak:send(Socket, EncodedData),
      DecodedMap = proto_importer:decode_msg(EncodedData, 'ImporterRequest'),
      Operation = maps:get(operation, DecodedMap),
      case Operation of
        'MAKE_BID' ->           io:format("MAKE BID!\n");
        'LIST_MANUFACTURERS' -> io:format("LIST MANUFACTURERS!\n");
        'LIST_PRODUCTS' ->      io:format("LIST PRODUCTS OF MANUFACTURER!\n");
        'LIST_BIDS' ->          io:format("LIST BIDS OF A PRODUCT OF A MANUFACTURER!\n");
        'CHECK_HISTORY' ->      io:format("CHECK HISTORY!")
      end,
      importer(Catalog, Socket)
  end.