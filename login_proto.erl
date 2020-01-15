-module(login_proto).
-export([start/1]).

start(Port) ->
  SessionManager = spawn(fun() -> ok end),
  LoginManager = spawn(fun() -> auth(#{"m0" => {'MANUFACTURER', "pm0"}, "i0" => {'IMPORTER', "pi0"}}, SessionManager) end),
  {ok, LSock} = gen_tcp:listen(Port, [binary, {packet, 0}, {reuseaddr, true}, {active, true}]),
  acceptor(LSock, LoginManager).

acceptor(LSock, LoginManager) ->
  {ok, Sock} = gen_tcp:accept(LSock),
  spawn(fun() -> acceptor(LSock, LoginManager) end),
  tcp_handler(Sock, LoginManager).

tcp_handler(Sock, Pid) ->
  receive
    {pid, NewPid} ->
      tcp_handler(Sock, NewPid);
    {tcp, Sock, EncodedData} ->
      DecodedData = auth:decode_msg(EncodedData, 'Auth'),
      Pid ! {Sock, self(), DecodedData};
    {tcp_closed, _} ->
      io:format("Connection closed.\n");
    {tcp_error, _, _} ->
      io:format("TCP Error ocurred.\n")
  end.

auth (Credentials, SessionManager) ->
  receive
    {Sock, TcpHandler, DecodedMap} ->
      io:format("BREAKPOINT 0.\n"),
      DecodedMap,
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
              TcpHandler ! {pid, SessionManager},
              ResponseMap = auth:encode_msg(#{username => Username, password => Password, authenticated => 0, type => Role, operation => Operation}, 'Auth'),
              gen_tcp:send(Sock, ResponseMap);
            % PASSWORDS DON'T MATCH
            {login_denied} ->
              io:format("LOGIN DENIED.\n"),
              ResponseMap = auth:encode_msg(#{username => Username, password => Password, authenticated => 2, operation => Operation}, 'Auth'),
              gen_tcp:send(Sock, ResponseMap);
            % USERNAME NOT FOUND
            {login_username_missing} ->
              io:format("LOGIN USERNAME MISSING.\n"),
              ResponseMap = auth:encode_msg(#{username => Username, password => Password, authenticated => 1, operation => Operation}, 'Auth'),
              gen_tcp:send(Sock, ResponseMap),
              io:format("BREAKPOINT 6.\n")
          end
        % LOGOUT OPERATION
        %'LOGOUT' ->
        %;
        % REGISTER OPERATION
        %'REGISTER' ->
      end
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

session (Roles) ->
  ok.



	
