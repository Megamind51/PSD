-module(importer).
-export([importer/1]).

importer(Catalog) ->
  io:format("I'm an importer!\n").