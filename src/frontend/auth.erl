-module(auth).
-import(manufacturer, [manufacturerStart/2]).
-import(bot, [manufacturerBot/1]).
-import(importer, [importerStart/2]).
-export([auth/4]).

auth (Credentials, Catalog, ManufacturerQueuePort, ImporterMappingPort) ->
  receive
    {Sock, TcpHandler, EncodedData} ->
      io:format("BREAKPOINT 0.\n"),
      DecodedMap = proto_auth:decode_msg(EncodedData, 'Auth'),
      io:format("BREAKPOINT 0.5.\n"),
      Operation = maps:get(operation, DecodedMap),
      io:format("BREAKPOINT 1.\n"),
      Username = maps:get(username, DecodedMap),
      Password = maps:get(password, DecodedMap),
      case Operation of
        % LOGIN OPERATION
        'LOGIN' ->
          io:format("LOGIN OPERATION.\n"),
          case login(Credentials, Username, Password) of
            % AUTHENTICATED
            {login_granted, Role} ->
              io:format("LOGIN GRANTED.\n"),
              case Role of
                  'MANUFACTURER' -> NextPID = spawn(fun() -> manufacturerStart(Catalog, ManufacturerQueuePort) end);
                  'IMPORTER' -> NextPID = spawn(fun() -> importerStart(Catalog, ImporterMappingPort) end)
              end,
              TcpHandler ! {pid, NextPID},
              ResponseMap = proto_auth:encode_msg(#{username => Username, password => Password, authenticated => 0, type => Role, operation => Operation}, 'Auth'),
              gen_tcp:send(Sock, ResponseMap);
            % PASSWORDS DON'T MATCH
            {login_denied} ->
              io:format("LOGIN DENIED.\n"),
              ResponseMap = proto_auth:encode_msg(#{username => Username, password => Password, authenticated => 2, operation => Operation}, 'Auth'),
              gen_tcp:send(Sock, ResponseMap);
            % USERNAME NOT FOUND
            {login_username_missing} ->
              io:format("LOGIN USERNAME MISSING.\n"),
              ResponseMap = proto_auth:encode_msg(#{username => Username, password => Password, authenticated => 1, operation => Operation}, 'Auth'),
              gen_tcp:send(Sock, ResponseMap),
              io:format("BREAKPOINT 6.\n")
          end
        % LOGOUT OPERATION
        %'LOGOUT' ->
        %;
        % REGISTER OPERATION
        %'REGISTER' ->
      end,
  auth(Credentials, Catalog, ManufacturerQueuePort, ImporterMappingPort)
  end.

login (Credentials, Username, Password) ->
  case maps:is_key(Username, Credentials) of
    true ->
      {Role, StoredPassword} = maps:get(Username, Credentials),
      case Password == StoredPassword of
        true -> {login_granted, Role};
        false -> {login_denied}
      end;
    false -> {login_username_missing}
  end.