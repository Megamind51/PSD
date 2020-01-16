-module(importer).
-export([importer/1]).

importer(Catalog) ->
  receive
    {} ->
      io:format("I'm an importer!\n"),
      importer(Catalog)
  end.