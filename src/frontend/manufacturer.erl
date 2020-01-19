-module(manufacturer).
-export([manufacturerStart/1]).

manufacturerStart(ManufacturerQueuePort) ->
  {ok, Socket} = chumak:socket(push),
  {ok, _BindPid} = chumak:connect(Socket, tcp, "localhost", ManufacturerQueuePort),
  manufacturer(Socket).

manufacturer(Socket) ->
  receive
    {Sock, TcpHandler, EncodedData} ->
      io:format("BREAKPOINT 15.~n"),
      DecodedMap = proto_manufacturer:decode_msg(EncodedData, 'ManufacturerRequest'),
      io:format("~p~n", [DecodedMap]),
      publishProduct(DecodedMap),
      ok = chumak:send(Socket, EncodedData),
      manufacturer(Socket)
  end.

publishProduct(DecodedProto) ->
  Manufacturer = maps:get(manufacturer, DecodedProto),
  Product = maps:get(product, DecodedProto),
  MinQuantity = maps:get(min_quantity, DecodedProto),
  MaxQuantity = maps:get(max_quantity, DecodedProto),
  Price = maps:get(min_price, DecodedProto),
  Seconds = maps:get(seconds, DecodedProto),
  EncodedJSON = jiffy:encode(#{<<"q_min">> => MinQuantity,
                              <<"q_max">> => MaxQuantity,
                              <<"p_min">> => Price,
                               <<"time">> => Seconds,
                               <<"name">> => list_to_binary(Product)}),
  inets:start(),
  {ok, {{_, HttpResponse, _}, _, Response}} = httpc:request(put, {"http://localhost:8080/manufacturers/" ++ Manufacturer ++ "/addItem/", [], "application/json", EncodedJSON}, [], []),
  _ = inets:stop(),
  jiffy:decode(Response, [return_maps]).