-module(importer).
-export([importerStart/1]).

importerStart(ImporterMappingPort) ->
  {ok, Socket} = chumak:socket(pub),
  {ok, _BindPid} = chumak:connect(Socket, tcp, "localhost", ImporterMappingPort),
  importer(Socket).

importer(Socket) ->
  receive
    {Sock, TcpHandler, EncodedData} ->
      DecodedMap = proto_importer:decode_msg(EncodedData, 'ImporterRequest'),
      Manufacturer = maps:get(manufacturer, DecodedMap),
      ProductName = maps:get(product, DecodedMap),
      Topic = Manufacturer ++ "_" ++ ProductName ++ "/",
      Operation = maps:get(operation, DecodedMap),
      case Operation of
        'MAKE_BID' ->
          %DataMaisBontiaToSend = [list_to_binary(Topic), EncodedData],
          Cena2 = list_to_binary(Topic),
          Cena = <<Cena2/binary, EncodedData/binary>>,
          ok = chumak:send(Socket, Cena),
          io:format("MAKE BID!\n");
        'LIST_MANUFACTURERS' ->
          io:format("LIST MANUFACTURERS!\n"),
          Reply = listManufacturers(DecodedMap),
          gen_tcp:send(Sock, Reply),
          io:format("~p~n",[Reply]);
        'LIST_PRODUCTS' ->
          io:format("LIST PRODUCTS OF MANUFACTURER!\n"),
          Reply = listProducts(DecodedMap),
          gen_tcp:send(Sock, Reply),
        io:format("~p~n",[Reply]);
        'LIST_BIDS' ->
          io:format("LIST BIDS OF A PRODUCT OF A MANUFACTURER!\n"),
          Reply = listBids(DecodedMap),
          gen_tcp:send(Sock, Reply),
        io:format("~p~n",[Reply]);
        'CHECK_HISTORY' ->
          io:format("CHECK HISTORY!"),
          Reply = checkHistory(DecodedMap),
          gen_tcp:send(Sock, Reply),
        io:format("~p~n",[Reply])
      end,
      importer(Socket)
  end.

listManufacturers(DecodedProto) ->
  io:format("~p~n",[DecodedProto]),
  inets:start(),
  {ok, {{_, 200, _}, _, Response}} = httpc:request("http://localhost:8080/manufacturers/"),
  _ = inets:stop(),
  DecodedJSON = jiffy:decode(Response, [return_maps]),
  io:format("~p~n",[DecodedJSON]),
  Response.

listProducts(DecodedProto) ->
  io:format("~p~n",[DecodedProto]),
  Manufacturer = maps:get(manufacturer, DecodedProto),
  inets:start(),
  {ok, {{_, 200, _}, _, Response}} = httpc:request("http://localhost:8080/manufacturers/" ++ Manufacturer),
  _ = inets:stop(),
  DecodedJSON = jiffy:decode(Response, [return_maps]),
  io:format("~p~n",[DecodedJSON]),
  Response.

listBids(DecodedProto) ->
  io:format("~p~n",[DecodedProto]),
  Manufacturer = maps:get(manufacturer, DecodedProto),
  Product = maps:get(product, DecodedProto),
  inets:start(),
  {ok, {{_, 200, _}, _, Response}} = httpc:request("http://localhost:8080/manufacturers/" ++ Manufacturer ++ "/" ++ Product),
  _ = inets:stop(),
  DecodedJSON = jiffy:decode(Response, [return_maps]),
  io:format("~p~n",[DecodedJSON]),
  Response.

checkHistory(DecodedProto) ->
  io:format("~p~n",[DecodedProto]),
  Manufacturer = maps:get(manufacturer, DecodedProto),
  Product = maps:get(product, DecodedProto),
  inets:start(),
  {ok, {{_, 200, _}, _, Response}} = httpc:request("http://localhost:8080/manufacturers/" ++ Manufacturer ++ "/history/" ++ Product),
  _ = inets:stop(),
  DecodedJSON = jiffy:decode(Response, [return_maps]),
  io:format("~p~n",[DecodedJSON]),
  Response.

makeBid (DecodedProto) ->
  io:format("~p~n",[DecodedProto]),
  Manufacturer = maps:get(manufacturer, DecodedProto),
  Importer = maps:get(importer, DecodedProto),
  io:format("|"++ Importer ++ "|\n"),
  Product = maps:get(product, DecodedProto),
  Quantity = maps:get(quantity, DecodedProto),
  Price = maps:get(price, DecodedProto),
  EncodedJSON = jiffy:encode(#{<<"nameUser">> => list_to_binary(Importer),
                               <<"itemAmount">> => Quantity,
                               <<"itemPrice">> => Price}),
  inets:start(),
  {ok, {{_, HttpResponse, _}, _, Response}} = httpc:request(put, {"http://localhost:8080/manufacturers/" ++ Manufacturer ++ "/addBid/" ++ Product, [], "application/json", EncodedJSON}, [], []),
  _ = inets:stop(),
  jiffy:decode(Response, [return_maps]).
