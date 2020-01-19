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
      %EncodedJSON = jiffy:encode(#{<<"manufacturer">> => Manufacturer,
        %<<"product">> => ProductName,
        %<<"min_quantity">> => MinQuantity,
        %<<"max_quantity">> => MaxQuantity,
        %<<"min_price">> => MinPrice,
        %<<"offer_timeout">> => OfferTimeout}),
      %DecodedJSON = jiffy:decode(EncodedJSON, [return_maps]),
      %io:format(DecodedJSON),
      %inets:start(),
      %{ok, {{_, 200, _}, _, Response}} = httpc:request("http://localhost:8000/test"),
      %inets:stop(),
      %io:format(Response),
      %DecodedJSON = jiffy:decode(Response, [return_maps]),
      %io:format(DecodedJSON),
      %catalog(Catalog)
      case Operation of
        'MAKE_BID' ->
          io:format("MAKE BID!\n"),
          DecodedJSON = makeBid(Socket, DecodedMap),
          io:format("~p~n",[DecodedJSON]);
        'LIST_MANUFACTURERS' -> io:format("LIST MANUFACTURERS!\n");
        'LIST_PRODUCTS' ->      io:format("LIST PRODUCTS OF MANUFACTURER!\n");
        'LIST_BIDS' ->          io:format("LIST BIDS OF A PRODUCT OF A MANUFACTURER!\n");
        'CHECK_HISTORY' ->      io:format("CHECK HISTORY!")
      end,
      importer(Catalog, Socket)
  end.

listManufacturers(DecodedProto) ->
  .

makeBid (Socket, DecodedProto) ->
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
  %{ok, {{_, HttpResponse, _}, _, Response}} = httpc:request("http://localhost:8080/" ++ Manufacturer ++ "/addBid/" ++ Product, put, [], EncodedJSON),
  inets:stop(),
  jiffy:decode(Response, [return_maps]).
  %DecodedJSON = jiffy:decode(EncodedJSON, [return_maps]),
  %io:format(DecodedJSON),
  %inets:start(),
  %{ok, {{_, 200, _}, _, Response}} = httpc:request("http://localhost:8000/test"),
