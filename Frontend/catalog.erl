-module(catalog).
-export([catalog/1]).

catalog(Catalog) ->
  receive
    {sell, Manufacturer, ProductName, MinQuantity, MaxQuantity, MinPrice, OfferTimeout} ->
      
      io:format("Received an offer from a producer.");
    {bid, Importer, Manufacturer, ProductName, Quantity, Price} ->
      io:format("Received a bid from an importer.")
  end.