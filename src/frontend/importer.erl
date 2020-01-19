-module(importer).
-export([importerStart/2]).

importerStart(Catalog, ImporterMappingPort) ->
  application:start(chumak),
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
        'MAKE_BID' ->           io:format("MAKE BID!");
        'LIST_MANUFACTURERS' -> io:format("LIST MANUFACTURERS!");
        'LIST_PRODUCTS' ->      io:format("LIST PRODUCTS OF MANUFACTURER!");
        'LIST_BIDS' ->          io:format("LIST BIDS OF A PRODUCT OF A MANUFACTURER!");
        'CHECK_HISTORY' ->      io:format("CHECK HISTORY!")
      end,
      importer(Catalog, Socket)
  end.