-module(bot).
-export([manufacturerBot/1]).

manufacturerBot (Catalog) ->
  io:format("I was called~n"),
  receive
    _ -> io:format("I'm a bot manufacturer and I received something!~n")
    %Catalog ! {sell, "Bot", "Product0", 100, 200, 100, 7200},
    %Catalog ! {sell, "Bot", "Product1", 50, 100, 10, 60},
    %Catalog ! {sell, "Bot", "Product2", 1, 2, 1000, 3600}
  end.