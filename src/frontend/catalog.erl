-module(catalog).
-export([catalog/1]).

catalog(Catalog) ->
  receive
    {sell, Manufacturer, ProductName, MinQuantity, MaxQuantity, MinPrice, OfferTimeout} ->
      io:format("Received an offer from a manufacturer.\n"),
      EncodedJSON = jiffy:encode(#{<<"manufacturer">> => Manufacturer,
                                   <<"product">> => ProductName,
                                   <<"min_quantity">> => MinQuantity,
                                   <<"max_quantity">> => MaxQuantity,
                                   <<"min_price">> => MinPrice,
                                   <<"offer_timeout">> => OfferTimeout}),
      %DecodedJSON = jiffy:decode(EncodedJSON, [return_maps]),
      %io:format(DecodedJSON),
      %inets:start(),
      %{ok, {{_, 200, _}, _, Response}} = httpc:request("http://localhost:8000/test"),
      %inets:stop(),
      %io:format(Response),
      %DecodedJSON = jiffy:decode(Response, [return_maps]),
      %io:format(DecodedJSON),
      catalog(Catalog);
    {bid, Importer, Manufacturer, ProductName, Quantity, Price} ->
      io:format("Received a bid from an importer.\n"),
      catalog(Catalog)
  end.