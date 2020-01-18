-module(importer).
-export([importerStart/2]).

importerStart(Catalog, ImporterMappingPort) ->
  application:start(chumak),
  {ok, Socket} = chumak:socket(pub),
  {ok, _BindPid} = chumak:bind(Socket, tcp, "localhost", ImporterMappingPort),
  importer(Catalog, Socket).


importer(Catalog, Socket) ->
  receive
    {Sock, TcpHandler, EncodedData} ->
      ok = chumak:send(Socket, EncodedData),
      importer(Catalog, Socket)
  end.