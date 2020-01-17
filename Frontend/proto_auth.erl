%% -*- coding: utf-8 -*-
%% @private
%% Automatically generated, do not edit
%% Generated by gpb_compile version 4.11.0
-module(proto_auth).

-export([encode_msg/2, encode_msg/3]).
-export([decode_msg/2, decode_msg/3]).
-export([merge_msgs/3, merge_msgs/4]).
-export([verify_msg/2, verify_msg/3]).
-export([get_msg_defs/0]).
-export([get_msg_names/0]).
-export([get_group_names/0]).
-export([get_msg_or_group_names/0]).
-export([get_enum_names/0]).
-export([find_msg_def/1, fetch_msg_def/1]).
-export([find_enum_def/1, fetch_enum_def/1]).
-export([enum_symbol_by_value/2, enum_value_by_symbol/2]).
-export(['enum_symbol_by_value_Auth.Operation'/1, 'enum_value_by_symbol_Auth.Operation'/1]).
-export(['enum_symbol_by_value_Auth.Type'/1, 'enum_value_by_symbol_Auth.Type'/1]).
-export([get_service_names/0]).
-export([get_service_def/1]).
-export([get_rpc_names/1]).
-export([find_rpc_def/2, fetch_rpc_def/2]).
-export([fqbin_to_service_name/1]).
-export([service_name_to_fqbin/1]).
-export([fqbins_to_service_and_rpc_name/2]).
-export([service_and_rpc_name_to_fqbins/2]).
-export([fqbin_to_msg_name/1]).
-export([msg_name_to_fqbin/1]).
-export([fqbin_to_enum_name/1]).
-export([enum_name_to_fqbin/1]).
-export([get_package_name/0]).
-export([uses_packages/0]).
-export([source_basename/0]).
-export([get_all_source_basenames/0]).
-export([get_all_proto_names/0]).
-export([get_msg_containment/1]).
-export([get_pkg_containment/1]).
-export([get_service_containment/1]).
-export([get_rpc_containment/1]).
-export([get_enum_containment/1]).
-export([get_proto_by_msg_name_as_fqbin/1]).
-export([get_proto_by_service_name_as_fqbin/1]).
-export([get_proto_by_enum_name_as_fqbin/1]).
-export([get_protos_by_pkg_name_as_fqbin/1]).
-export([gpb_version_as_string/0, gpb_version_as_list/0]).


%% enumerated types
-type 'Auth.Operation'() :: 'LOGIN' | 'LOGOUT' | 'REGISTER'.
-type 'Auth.Type'() :: 'MANUFACTURER' | 'IMPORTER'.
-export_type(['Auth.Operation'/0, 'Auth.Type'/0]).

%% message types
-type 'Auth'() ::
      #{operation               => 'LOGIN' | 'LOGOUT' | 'REGISTER' | integer(), % = 1, enum Auth.Operation
        type                    => 'MANUFACTURER' | 'IMPORTER' | integer(), % = 2, enum Auth.Type
        username                => iodata(),        % = 3
        password                => iodata(),        % = 4
        authenticated           => integer()        % = 5, 32 bits
       }.

-export_type(['Auth'/0]).

-spec encode_msg('Auth'(), atom()) -> binary().
encode_msg(Msg, MsgName) when is_atom(MsgName) ->
    encode_msg(Msg, MsgName, []).

-spec encode_msg('Auth'(), atom(), list()) -> binary().
encode_msg(Msg, MsgName, Opts) ->
    case proplists:get_bool(verify, Opts) of
      true -> verify_msg(Msg, MsgName, Opts);
      false -> ok
    end,
    TrUserData = proplists:get_value(user_data, Opts),
    case MsgName of
      'Auth' ->
	  encode_msg_Auth(id(Msg, TrUserData), TrUserData)
    end.


encode_msg_Auth(Msg, TrUserData) ->
    encode_msg_Auth(Msg, <<>>, TrUserData).


encode_msg_Auth(#{} = M, Bin, TrUserData) ->
    B1 = case M of
	   #{operation := F1} ->
	       begin
		 TrF1 = id(F1, TrUserData),
		 if TrF1 =:= 'LOGIN'; TrF1 =:= 0 -> Bin;
		    true ->
			'e_enum_Auth.Operation'(TrF1, <<Bin/binary, 8>>,
						TrUserData)
		 end
	       end;
	   _ -> Bin
	 end,
    B2 = case M of
	   #{type := F2} ->
	       begin
		 TrF2 = id(F2, TrUserData),
		 if TrF2 =:= 'MANUFACTURER'; TrF2 =:= 0 -> B1;
		    true ->
			'e_enum_Auth.Type'(TrF2, <<B1/binary, 16>>, TrUserData)
		 end
	       end;
	   _ -> B1
	 end,
    B3 = case M of
	   #{username := F3} ->
	       begin
		 TrF3 = id(F3, TrUserData),
		 case is_empty_string(TrF3) of
		   true -> B2;
		   false ->
		       e_type_string(TrF3, <<B2/binary, 26>>, TrUserData)
		 end
	       end;
	   _ -> B2
	 end,
    B4 = case M of
	   #{password := F4} ->
	       begin
		 TrF4 = id(F4, TrUserData),
		 case is_empty_string(TrF4) of
		   true -> B3;
		   false ->
		       e_type_string(TrF4, <<B3/binary, 34>>, TrUserData)
		 end
	       end;
	   _ -> B3
	 end,
    case M of
      #{authenticated := F5} ->
	  begin
	    TrF5 = id(F5, TrUserData),
	    if TrF5 =:= 0 -> B4;
	       true ->
		   e_type_int32(TrF5, <<B4/binary, 40>>, TrUserData)
	    end
	  end;
      _ -> B4
    end.

'e_enum_Auth.Operation'('LOGIN', Bin, _TrUserData) ->
    <<Bin/binary, 0>>;
'e_enum_Auth.Operation'('LOGOUT', Bin, _TrUserData) ->
    <<Bin/binary, 1>>;
'e_enum_Auth.Operation'('REGISTER', Bin, _TrUserData) ->
    <<Bin/binary, 2>>;
'e_enum_Auth.Operation'(V, Bin, _TrUserData) ->
    e_varint(V, Bin).

'e_enum_Auth.Type'('MANUFACTURER', Bin, _TrUserData) ->
    <<Bin/binary, 0>>;
'e_enum_Auth.Type'('IMPORTER', Bin, _TrUserData) ->
    <<Bin/binary, 1>>;
'e_enum_Auth.Type'(V, Bin, _TrUserData) ->
    e_varint(V, Bin).

-compile({nowarn_unused_function,e_type_sint/3}).
e_type_sint(Value, Bin, _TrUserData) when Value >= 0 ->
    e_varint(Value * 2, Bin);
e_type_sint(Value, Bin, _TrUserData) ->
    e_varint(Value * -2 - 1, Bin).

-compile({nowarn_unused_function,e_type_int32/3}).
e_type_int32(Value, Bin, _TrUserData)
    when 0 =< Value, Value =< 127 ->
    <<Bin/binary, Value>>;
e_type_int32(Value, Bin, _TrUserData) ->
    <<N:64/unsigned-native>> = <<Value:64/signed-native>>,
    e_varint(N, Bin).

-compile({nowarn_unused_function,e_type_int64/3}).
e_type_int64(Value, Bin, _TrUserData)
    when 0 =< Value, Value =< 127 ->
    <<Bin/binary, Value>>;
e_type_int64(Value, Bin, _TrUserData) ->
    <<N:64/unsigned-native>> = <<Value:64/signed-native>>,
    e_varint(N, Bin).

-compile({nowarn_unused_function,e_type_bool/3}).
e_type_bool(true, Bin, _TrUserData) ->
    <<Bin/binary, 1>>;
e_type_bool(false, Bin, _TrUserData) ->
    <<Bin/binary, 0>>;
e_type_bool(1, Bin, _TrUserData) -> <<Bin/binary, 1>>;
e_type_bool(0, Bin, _TrUserData) -> <<Bin/binary, 0>>.

-compile({nowarn_unused_function,e_type_string/3}).
e_type_string(S, Bin, _TrUserData) ->
    Utf8 = unicode:characters_to_binary(S),
    Bin2 = e_varint(byte_size(Utf8), Bin),
    <<Bin2/binary, Utf8/binary>>.

-compile({nowarn_unused_function,e_type_bytes/3}).
e_type_bytes(Bytes, Bin, _TrUserData)
    when is_binary(Bytes) ->
    Bin2 = e_varint(byte_size(Bytes), Bin),
    <<Bin2/binary, Bytes/binary>>;
e_type_bytes(Bytes, Bin, _TrUserData)
    when is_list(Bytes) ->
    BytesBin = iolist_to_binary(Bytes),
    Bin2 = e_varint(byte_size(BytesBin), Bin),
    <<Bin2/binary, BytesBin/binary>>.

-compile({nowarn_unused_function,e_type_fixed32/3}).
e_type_fixed32(Value, Bin, _TrUserData) ->
    <<Bin/binary, Value:32/little>>.

-compile({nowarn_unused_function,e_type_sfixed32/3}).
e_type_sfixed32(Value, Bin, _TrUserData) ->
    <<Bin/binary, Value:32/little-signed>>.

-compile({nowarn_unused_function,e_type_fixed64/3}).
e_type_fixed64(Value, Bin, _TrUserData) ->
    <<Bin/binary, Value:64/little>>.

-compile({nowarn_unused_function,e_type_sfixed64/3}).
e_type_sfixed64(Value, Bin, _TrUserData) ->
    <<Bin/binary, Value:64/little-signed>>.

-compile({nowarn_unused_function,e_type_float/3}).
e_type_float(V, Bin, _) when is_number(V) ->
    <<Bin/binary, V:32/little-float>>;
e_type_float(infinity, Bin, _) ->
    <<Bin/binary, 0:16, 128, 127>>;
e_type_float('-infinity', Bin, _) ->
    <<Bin/binary, 0:16, 128, 255>>;
e_type_float(nan, Bin, _) ->
    <<Bin/binary, 0:16, 192, 127>>.

-compile({nowarn_unused_function,e_type_double/3}).
e_type_double(V, Bin, _) when is_number(V) ->
    <<Bin/binary, V:64/little-float>>;
e_type_double(infinity, Bin, _) ->
    <<Bin/binary, 0:48, 240, 127>>;
e_type_double('-infinity', Bin, _) ->
    <<Bin/binary, 0:48, 240, 255>>;
e_type_double(nan, Bin, _) ->
    <<Bin/binary, 0:48, 248, 127>>.

-compile({nowarn_unused_function,e_varint/3}).
e_varint(N, Bin, _TrUserData) -> e_varint(N, Bin).

-compile({nowarn_unused_function,e_varint/2}).
e_varint(N, Bin) when N =< 127 -> <<Bin/binary, N>>;
e_varint(N, Bin) ->
    Bin2 = <<Bin/binary, (N band 127 bor 128)>>,
    e_varint(N bsr 7, Bin2).

is_empty_string("") -> true;
is_empty_string(<<>>) -> true;
is_empty_string(L) when is_list(L) ->
    not string_has_chars(L);
is_empty_string(B) when is_binary(B) -> false.

string_has_chars([C | _]) when is_integer(C) -> true;
string_has_chars([H | T]) ->
    case string_has_chars(H) of
      true -> true;
      false -> string_has_chars(T)
    end;
string_has_chars(B)
    when is_binary(B), byte_size(B) =/= 0 ->
    true;
string_has_chars(C) when is_integer(C) -> true;
string_has_chars(<<>>) -> false;
string_has_chars([]) -> false.


decode_msg(Bin, MsgName) when is_binary(Bin) ->
    decode_msg(Bin, MsgName, []).

decode_msg(Bin, MsgName, Opts) when is_binary(Bin) ->
    TrUserData = proplists:get_value(user_data, Opts),
    decode_msg_1_catch(Bin, MsgName, TrUserData).

-ifdef('OTP_RELEASE').
decode_msg_1_catch(Bin, MsgName, TrUserData) ->
    try decode_msg_2_doit(MsgName, Bin, TrUserData)
    catch Class:Reason:StackTrace -> error({gpb_error,{decoding_failure, {Bin, MsgName, {Class, Reason, StackTrace}}}})
    end.
-else.
decode_msg_1_catch(Bin, MsgName, TrUserData) ->
    try decode_msg_2_doit(MsgName, Bin, TrUserData)
    catch Class:Reason ->
        StackTrace = erlang:get_stacktrace(),
        error({gpb_error,{decoding_failure, {Bin, MsgName, {Class, Reason, StackTrace}}}})
    end.
-endif.

decode_msg_2_doit('Auth', Bin, TrUserData) ->
    id(decode_msg_Auth(Bin, TrUserData), TrUserData).



decode_msg_Auth(Bin, TrUserData) ->
    dfp_read_field_def_Auth(Bin, 0, 0,
			    id('LOGIN', TrUserData),
			    id('MANUFACTURER', TrUserData), id([], TrUserData),
			    id([], TrUserData), id(0, TrUserData), TrUserData).

dfp_read_field_def_Auth(<<8, Rest/binary>>, Z1, Z2,
			F@_1, F@_2, F@_3, F@_4, F@_5, TrUserData) ->
    d_field_Auth_operation(Rest, Z1, Z2, F@_1, F@_2, F@_3,
			   F@_4, F@_5, TrUserData);
dfp_read_field_def_Auth(<<16, Rest/binary>>, Z1, Z2,
			F@_1, F@_2, F@_3, F@_4, F@_5, TrUserData) ->
    d_field_Auth_type(Rest, Z1, Z2, F@_1, F@_2, F@_3, F@_4,
		      F@_5, TrUserData);
dfp_read_field_def_Auth(<<26, Rest/binary>>, Z1, Z2,
			F@_1, F@_2, F@_3, F@_4, F@_5, TrUserData) ->
    d_field_Auth_username(Rest, Z1, Z2, F@_1, F@_2, F@_3,
			  F@_4, F@_5, TrUserData);
dfp_read_field_def_Auth(<<34, Rest/binary>>, Z1, Z2,
			F@_1, F@_2, F@_3, F@_4, F@_5, TrUserData) ->
    d_field_Auth_password(Rest, Z1, Z2, F@_1, F@_2, F@_3,
			  F@_4, F@_5, TrUserData);
dfp_read_field_def_Auth(<<40, Rest/binary>>, Z1, Z2,
			F@_1, F@_2, F@_3, F@_4, F@_5, TrUserData) ->
    d_field_Auth_authenticated(Rest, Z1, Z2, F@_1, F@_2,
			       F@_3, F@_4, F@_5, TrUserData);
dfp_read_field_def_Auth(<<>>, 0, 0, F@_1, F@_2, F@_3,
			F@_4, F@_5, _) ->
    #{operation => F@_1, type => F@_2, username => F@_3,
      password => F@_4, authenticated => F@_5};
dfp_read_field_def_Auth(Other, Z1, Z2, F@_1, F@_2, F@_3,
			F@_4, F@_5, TrUserData) ->
    dg_read_field_def_Auth(Other, Z1, Z2, F@_1, F@_2, F@_3,
			   F@_4, F@_5, TrUserData).

dg_read_field_def_Auth(<<1:1, X:7, Rest/binary>>, N,
		       Acc, F@_1, F@_2, F@_3, F@_4, F@_5, TrUserData)
    when N < 32 - 7 ->
    dg_read_field_def_Auth(Rest, N + 7, X bsl N + Acc, F@_1,
			   F@_2, F@_3, F@_4, F@_5, TrUserData);
dg_read_field_def_Auth(<<0:1, X:7, Rest/binary>>, N,
		       Acc, F@_1, F@_2, F@_3, F@_4, F@_5, TrUserData) ->
    Key = X bsl N + Acc,
    case Key of
      8 ->
	  d_field_Auth_operation(Rest, 0, 0, F@_1, F@_2, F@_3,
				 F@_4, F@_5, TrUserData);
      16 ->
	  d_field_Auth_type(Rest, 0, 0, F@_1, F@_2, F@_3, F@_4,
			    F@_5, TrUserData);
      26 ->
	  d_field_Auth_username(Rest, 0, 0, F@_1, F@_2, F@_3,
				F@_4, F@_5, TrUserData);
      34 ->
	  d_field_Auth_password(Rest, 0, 0, F@_1, F@_2, F@_3,
				F@_4, F@_5, TrUserData);
      40 ->
	  d_field_Auth_authenticated(Rest, 0, 0, F@_1, F@_2, F@_3,
				     F@_4, F@_5, TrUserData);
      _ ->
	  case Key band 7 of
	    0 ->
		skip_varint_Auth(Rest, 0, 0, F@_1, F@_2, F@_3, F@_4,
				 F@_5, TrUserData);
	    1 ->
		skip_64_Auth(Rest, 0, 0, F@_1, F@_2, F@_3, F@_4, F@_5,
			     TrUserData);
	    2 ->
		skip_length_delimited_Auth(Rest, 0, 0, F@_1, F@_2, F@_3,
					   F@_4, F@_5, TrUserData);
	    3 ->
		skip_group_Auth(Rest, Key bsr 3, 0, F@_1, F@_2, F@_3,
				F@_4, F@_5, TrUserData);
	    5 ->
		skip_32_Auth(Rest, 0, 0, F@_1, F@_2, F@_3, F@_4, F@_5,
			     TrUserData)
	  end
    end;
dg_read_field_def_Auth(<<>>, 0, 0, F@_1, F@_2, F@_3,
		       F@_4, F@_5, _) ->
    #{operation => F@_1, type => F@_2, username => F@_3,
      password => F@_4, authenticated => F@_5}.

d_field_Auth_operation(<<1:1, X:7, Rest/binary>>, N,
		       Acc, F@_1, F@_2, F@_3, F@_4, F@_5, TrUserData)
    when N < 57 ->
    d_field_Auth_operation(Rest, N + 7, X bsl N + Acc, F@_1,
			   F@_2, F@_3, F@_4, F@_5, TrUserData);
d_field_Auth_operation(<<0:1, X:7, Rest/binary>>, N,
		       Acc, _, F@_2, F@_3, F@_4, F@_5, TrUserData) ->
    {NewFValue, RestF} = {id('d_enum_Auth.Operation'(begin
						       <<Res:32/signed-native>> =
							   <<(X bsl N +
								Acc):32/unsigned-native>>,
						       id(Res, TrUserData)
						     end),
			     TrUserData),
			  Rest},
    dfp_read_field_def_Auth(RestF, 0, 0, NewFValue, F@_2,
			    F@_3, F@_4, F@_5, TrUserData).

d_field_Auth_type(<<1:1, X:7, Rest/binary>>, N, Acc,
		  F@_1, F@_2, F@_3, F@_4, F@_5, TrUserData)
    when N < 57 ->
    d_field_Auth_type(Rest, N + 7, X bsl N + Acc, F@_1,
		      F@_2, F@_3, F@_4, F@_5, TrUserData);
d_field_Auth_type(<<0:1, X:7, Rest/binary>>, N, Acc,
		  F@_1, _, F@_3, F@_4, F@_5, TrUserData) ->
    {NewFValue, RestF} = {id('d_enum_Auth.Type'(begin
						  <<Res:32/signed-native>> =
						      <<(X bsl N +
							   Acc):32/unsigned-native>>,
						  id(Res, TrUserData)
						end),
			     TrUserData),
			  Rest},
    dfp_read_field_def_Auth(RestF, 0, 0, F@_1, NewFValue,
			    F@_3, F@_4, F@_5, TrUserData).

d_field_Auth_username(<<1:1, X:7, Rest/binary>>, N, Acc,
		      F@_1, F@_2, F@_3, F@_4, F@_5, TrUserData)
    when N < 57 ->
    d_field_Auth_username(Rest, N + 7, X bsl N + Acc, F@_1,
			  F@_2, F@_3, F@_4, F@_5, TrUserData);
d_field_Auth_username(<<0:1, X:7, Rest/binary>>, N, Acc,
		      F@_1, F@_2, _, F@_4, F@_5, TrUserData) ->
    {NewFValue, RestF} = begin
			   Len = X bsl N + Acc,
			   <<Utf8:Len/binary, Rest2/binary>> = Rest,
			   {id(unicode:characters_to_list(Utf8, unicode),
			       TrUserData),
			    Rest2}
			 end,
    dfp_read_field_def_Auth(RestF, 0, 0, F@_1, F@_2,
			    NewFValue, F@_4, F@_5, TrUserData).

d_field_Auth_password(<<1:1, X:7, Rest/binary>>, N, Acc,
		      F@_1, F@_2, F@_3, F@_4, F@_5, TrUserData)
    when N < 57 ->
    d_field_Auth_password(Rest, N + 7, X bsl N + Acc, F@_1,
			  F@_2, F@_3, F@_4, F@_5, TrUserData);
d_field_Auth_password(<<0:1, X:7, Rest/binary>>, N, Acc,
		      F@_1, F@_2, F@_3, _, F@_5, TrUserData) ->
    {NewFValue, RestF} = begin
			   Len = X bsl N + Acc,
			   <<Utf8:Len/binary, Rest2/binary>> = Rest,
			   {id(unicode:characters_to_list(Utf8, unicode),
			       TrUserData),
			    Rest2}
			 end,
    dfp_read_field_def_Auth(RestF, 0, 0, F@_1, F@_2, F@_3,
			    NewFValue, F@_5, TrUserData).

d_field_Auth_authenticated(<<1:1, X:7, Rest/binary>>, N,
			   Acc, F@_1, F@_2, F@_3, F@_4, F@_5, TrUserData)
    when N < 57 ->
    d_field_Auth_authenticated(Rest, N + 7, X bsl N + Acc,
			       F@_1, F@_2, F@_3, F@_4, F@_5, TrUserData);
d_field_Auth_authenticated(<<0:1, X:7, Rest/binary>>, N,
			   Acc, F@_1, F@_2, F@_3, F@_4, _, TrUserData) ->
    {NewFValue, RestF} = {begin
			    <<Res:32/signed-native>> = <<(X bsl N +
							    Acc):32/unsigned-native>>,
			    id(Res, TrUserData)
			  end,
			  Rest},
    dfp_read_field_def_Auth(RestF, 0, 0, F@_1, F@_2, F@_3,
			    F@_4, NewFValue, TrUserData).

skip_varint_Auth(<<1:1, _:7, Rest/binary>>, Z1, Z2,
		 F@_1, F@_2, F@_3, F@_4, F@_5, TrUserData) ->
    skip_varint_Auth(Rest, Z1, Z2, F@_1, F@_2, F@_3, F@_4,
		     F@_5, TrUserData);
skip_varint_Auth(<<0:1, _:7, Rest/binary>>, Z1, Z2,
		 F@_1, F@_2, F@_3, F@_4, F@_5, TrUserData) ->
    dfp_read_field_def_Auth(Rest, Z1, Z2, F@_1, F@_2, F@_3,
			    F@_4, F@_5, TrUserData).

skip_length_delimited_Auth(<<1:1, X:7, Rest/binary>>, N,
			   Acc, F@_1, F@_2, F@_3, F@_4, F@_5, TrUserData)
    when N < 57 ->
    skip_length_delimited_Auth(Rest, N + 7, X bsl N + Acc,
			       F@_1, F@_2, F@_3, F@_4, F@_5, TrUserData);
skip_length_delimited_Auth(<<0:1, X:7, Rest/binary>>, N,
			   Acc, F@_1, F@_2, F@_3, F@_4, F@_5, TrUserData) ->
    Length = X bsl N + Acc,
    <<_:Length/binary, Rest2/binary>> = Rest,
    dfp_read_field_def_Auth(Rest2, 0, 0, F@_1, F@_2, F@_3,
			    F@_4, F@_5, TrUserData).

skip_group_Auth(Bin, FNum, Z2, F@_1, F@_2, F@_3, F@_4,
		F@_5, TrUserData) ->
    {_, Rest} = read_group(Bin, FNum),
    dfp_read_field_def_Auth(Rest, 0, Z2, F@_1, F@_2, F@_3,
			    F@_4, F@_5, TrUserData).

skip_32_Auth(<<_:32, Rest/binary>>, Z1, Z2, F@_1, F@_2,
	     F@_3, F@_4, F@_5, TrUserData) ->
    dfp_read_field_def_Auth(Rest, Z1, Z2, F@_1, F@_2, F@_3,
			    F@_4, F@_5, TrUserData).

skip_64_Auth(<<_:64, Rest/binary>>, Z1, Z2, F@_1, F@_2,
	     F@_3, F@_4, F@_5, TrUserData) ->
    dfp_read_field_def_Auth(Rest, Z1, Z2, F@_1, F@_2, F@_3,
			    F@_4, F@_5, TrUserData).

'd_enum_Auth.Operation'(0) -> 'LOGIN';
'd_enum_Auth.Operation'(1) -> 'LOGOUT';
'd_enum_Auth.Operation'(2) -> 'REGISTER';
'd_enum_Auth.Operation'(V) -> V.

'd_enum_Auth.Type'(0) -> 'MANUFACTURER';
'd_enum_Auth.Type'(1) -> 'IMPORTER';
'd_enum_Auth.Type'(V) -> V.

read_group(Bin, FieldNum) ->
    {NumBytes, EndTagLen} = read_gr_b(Bin, 0, 0, 0, 0, FieldNum),
    <<Group:NumBytes/binary, _:EndTagLen/binary, Rest/binary>> = Bin,
    {Group, Rest}.

%% Like skipping over fields, but record the total length,
%% Each field is <(FieldNum bsl 3) bor FieldType> ++ <FieldValue>
%% Record the length because varints may be non-optimally encoded.
%%
%% Groups can be nested, but assume the same FieldNum cannot be nested
%% because group field numbers are shared with the rest of the fields
%% numbers. Thus we can search just for an group-end with the same
%% field number.
%%
%% (The only time the same group field number could occur would
%% be in a nested sub message, but then it would be inside a
%% length-delimited entry, which we skip-read by length.)
read_gr_b(<<1:1, X:7, Tl/binary>>, N, Acc, NumBytes, TagLen, FieldNum)
  when N < (32-7) ->
    read_gr_b(Tl, N+7, X bsl N + Acc, NumBytes, TagLen+1, FieldNum);
read_gr_b(<<0:1, X:7, Tl/binary>>, N, Acc, NumBytes, TagLen,
          FieldNum) ->
    Key = X bsl N + Acc,
    TagLen1 = TagLen + 1,
    case {Key bsr 3, Key band 7} of
        {FieldNum, 4} -> % 4 = group_end
            {NumBytes, TagLen1};
        {_, 0} -> % 0 = varint
            read_gr_vi(Tl, 0, NumBytes + TagLen1, FieldNum);
        {_, 1} -> % 1 = bits64
            <<_:64, Tl2/binary>> = Tl,
            read_gr_b(Tl2, 0, 0, NumBytes + TagLen1 + 8, 0, FieldNum);
        {_, 2} -> % 2 = length_delimited
            read_gr_ld(Tl, 0, 0, NumBytes + TagLen1, FieldNum);
        {_, 3} -> % 3 = group_start
            read_gr_b(Tl, 0, 0, NumBytes + TagLen1, 0, FieldNum);
        {_, 4} -> % 4 = group_end
            read_gr_b(Tl, 0, 0, NumBytes + TagLen1, 0, FieldNum);
        {_, 5} -> % 5 = bits32
            <<_:32, Tl2/binary>> = Tl,
            read_gr_b(Tl2, 0, 0, NumBytes + TagLen1 + 4, 0, FieldNum)
    end.

read_gr_vi(<<1:1, _:7, Tl/binary>>, N, NumBytes, FieldNum)
  when N < (64-7) ->
    read_gr_vi(Tl, N+7, NumBytes+1, FieldNum);
read_gr_vi(<<0:1, _:7, Tl/binary>>, _, NumBytes, FieldNum) ->
    read_gr_b(Tl, 0, 0, NumBytes+1, 0, FieldNum).

read_gr_ld(<<1:1, X:7, Tl/binary>>, N, Acc, NumBytes, FieldNum)
  when N < (64-7) ->
    read_gr_ld(Tl, N+7, X bsl N + Acc, NumBytes+1, FieldNum);
read_gr_ld(<<0:1, X:7, Tl/binary>>, N, Acc, NumBytes, FieldNum) ->
    Len = X bsl N + Acc,
    NumBytes1 = NumBytes + 1,
    <<_:Len/binary, Tl2/binary>> = Tl,
    read_gr_b(Tl2, 0, 0, NumBytes1 + Len, 0, FieldNum).

merge_msgs(Prev, New, MsgName) when is_atom(MsgName) ->
    merge_msgs(Prev, New, MsgName, []).

merge_msgs(Prev, New, MsgName, Opts) ->
    TrUserData = proplists:get_value(user_data, Opts),
    case MsgName of
      'Auth' -> merge_msg_Auth(Prev, New, TrUserData)
    end.

-compile({nowarn_unused_function,merge_msg_Auth/3}).
merge_msg_Auth(PMsg, NMsg, _) ->
    S1 = #{},
    S2 = case {PMsg, NMsg} of
	   {_, #{operation := NFoperation}} ->
	       S1#{operation => NFoperation};
	   {#{operation := PFoperation}, _} ->
	       S1#{operation => PFoperation};
	   _ -> S1
	 end,
    S3 = case {PMsg, NMsg} of
	   {_, #{type := NFtype}} -> S2#{type => NFtype};
	   {#{type := PFtype}, _} -> S2#{type => PFtype};
	   _ -> S2
	 end,
    S4 = case {PMsg, NMsg} of
	   {_, #{username := NFusername}} ->
	       S3#{username => NFusername};
	   {#{username := PFusername}, _} ->
	       S3#{username => PFusername};
	   _ -> S3
	 end,
    S5 = case {PMsg, NMsg} of
	   {_, #{password := NFpassword}} ->
	       S4#{password => NFpassword};
	   {#{password := PFpassword}, _} ->
	       S4#{password => PFpassword};
	   _ -> S4
	 end,
    case {PMsg, NMsg} of
      {_, #{authenticated := NFauthenticated}} ->
	  S5#{authenticated => NFauthenticated};
      {#{authenticated := PFauthenticated}, _} ->
	  S5#{authenticated => PFauthenticated};
      _ -> S5
    end.


verify_msg(Msg, MsgName) when is_atom(MsgName) ->
    verify_msg(Msg, MsgName, []).

verify_msg(Msg, MsgName, Opts) ->
    TrUserData = proplists:get_value(user_data, Opts),
    case MsgName of
      'Auth' -> v_msg_Auth(Msg, [MsgName], TrUserData);
      _ -> mk_type_error(not_a_known_message, Msg, [])
    end.


-compile({nowarn_unused_function,v_msg_Auth/3}).
-dialyzer({nowarn_function,v_msg_Auth/3}).
v_msg_Auth(#{} = M, Path, TrUserData) ->
    case M of
      #{operation := F1} ->
	  'v_enum_Auth.Operation'(F1, [operation | Path],
				  TrUserData);
      _ -> ok
    end,
    case M of
      #{type := F2} ->
	  'v_enum_Auth.Type'(F2, [type | Path], TrUserData);
      _ -> ok
    end,
    case M of
      #{username := F3} ->
	  v_type_string(F3, [username | Path], TrUserData);
      _ -> ok
    end,
    case M of
      #{password := F4} ->
	  v_type_string(F4, [password | Path], TrUserData);
      _ -> ok
    end,
    case M of
      #{authenticated := F5} ->
	  v_type_int32(F5, [authenticated | Path], TrUserData);
      _ -> ok
    end,
    lists:foreach(fun (operation) -> ok;
		      (type) -> ok;
		      (username) -> ok;
		      (password) -> ok;
		      (authenticated) -> ok;
		      (OtherKey) ->
			  mk_type_error({extraneous_key, OtherKey}, M, Path)
		  end,
		  maps:keys(M)),
    ok;
v_msg_Auth(M, Path, _TrUserData) when is_map(M) ->
    mk_type_error({missing_fields, [] -- maps:keys(M),
		   'Auth'},
		  M, Path);
v_msg_Auth(X, Path, _TrUserData) ->
    mk_type_error({expected_msg, 'Auth'}, X, Path).

-compile({nowarn_unused_function,'v_enum_Auth.Operation'/3}).
-dialyzer({nowarn_function,'v_enum_Auth.Operation'/3}).
'v_enum_Auth.Operation'('LOGIN', _Path, _TrUserData) ->
    ok;
'v_enum_Auth.Operation'('LOGOUT', _Path, _TrUserData) ->
    ok;
'v_enum_Auth.Operation'('REGISTER', _Path,
			_TrUserData) ->
    ok;
'v_enum_Auth.Operation'(V, Path, TrUserData)
    when is_integer(V) ->
    v_type_sint32(V, Path, TrUserData);
'v_enum_Auth.Operation'(X, Path, _TrUserData) ->
    mk_type_error({invalid_enum, 'Auth.Operation'}, X,
		  Path).

-compile({nowarn_unused_function,'v_enum_Auth.Type'/3}).
-dialyzer({nowarn_function,'v_enum_Auth.Type'/3}).
'v_enum_Auth.Type'('MANUFACTURER', _Path,
		   _TrUserData) ->
    ok;
'v_enum_Auth.Type'('IMPORTER', _Path, _TrUserData) ->
    ok;
'v_enum_Auth.Type'(V, Path, TrUserData)
    when is_integer(V) ->
    v_type_sint32(V, Path, TrUserData);
'v_enum_Auth.Type'(X, Path, _TrUserData) ->
    mk_type_error({invalid_enum, 'Auth.Type'}, X, Path).

-compile({nowarn_unused_function,v_type_sint32/3}).
-dialyzer({nowarn_function,v_type_sint32/3}).
v_type_sint32(N, _Path, _TrUserData)
    when -2147483648 =< N, N =< 2147483647 ->
    ok;
v_type_sint32(N, Path, _TrUserData)
    when is_integer(N) ->
    mk_type_error({value_out_of_range, sint32, signed, 32},
		  N, Path);
v_type_sint32(X, Path, _TrUserData) ->
    mk_type_error({bad_integer, sint32, signed, 32}, X,
		  Path).

-compile({nowarn_unused_function,v_type_int32/3}).
-dialyzer({nowarn_function,v_type_int32/3}).
v_type_int32(N, _Path, _TrUserData)
    when -2147483648 =< N, N =< 2147483647 ->
    ok;
v_type_int32(N, Path, _TrUserData) when is_integer(N) ->
    mk_type_error({value_out_of_range, int32, signed, 32},
		  N, Path);
v_type_int32(X, Path, _TrUserData) ->
    mk_type_error({bad_integer, int32, signed, 32}, X,
		  Path).

-compile({nowarn_unused_function,v_type_string/3}).
-dialyzer({nowarn_function,v_type_string/3}).
v_type_string(S, Path, _TrUserData)
    when is_list(S); is_binary(S) ->
    try unicode:characters_to_binary(S) of
      B when is_binary(B) -> ok;
      {error, _, _} ->
	  mk_type_error(bad_unicode_string, S, Path)
    catch
      error:badarg ->
	  mk_type_error(bad_unicode_string, S, Path)
    end;
v_type_string(X, Path, _TrUserData) ->
    mk_type_error(bad_unicode_string, X, Path).

-compile({nowarn_unused_function,mk_type_error/3}).
-spec mk_type_error(_, _, list()) -> no_return().
mk_type_error(Error, ValueSeen, Path) ->
    Path2 = prettify_path(Path),
    erlang:error({gpb_type_error,
		  {Error, [{value, ValueSeen}, {path, Path2}]}}).


-compile({nowarn_unused_function,prettify_path/1}).
-dialyzer({nowarn_function,prettify_path/1}).
prettify_path([]) -> top_level;
prettify_path(PathR) ->
    list_to_atom(lists:append(lists:join(".",
					 lists:map(fun atom_to_list/1,
						   lists:reverse(PathR))))).


-compile({nowarn_unused_function,id/2}).
-compile({inline,id/2}).
id(X, _TrUserData) -> X.

-compile({nowarn_unused_function,v_ok/3}).
-compile({inline,v_ok/3}).
v_ok(_Value, _Path, _TrUserData) -> ok.

-compile({nowarn_unused_function,m_overwrite/3}).
-compile({inline,m_overwrite/3}).
m_overwrite(_Prev, New, _TrUserData) -> New.

-compile({nowarn_unused_function,cons/3}).
-compile({inline,cons/3}).
cons(Elem, Acc, _TrUserData) -> [Elem | Acc].

-compile({nowarn_unused_function,lists_reverse/2}).
-compile({inline,lists_reverse/2}).
'lists_reverse'(L, _TrUserData) -> lists:reverse(L).
-compile({nowarn_unused_function,'erlang_++'/3}).
-compile({inline,'erlang_++'/3}).
'erlang_++'(A, B, _TrUserData) -> A ++ B.


get_msg_defs() ->
    [{{enum, 'Auth.Operation'},
      [{'LOGIN', 0}, {'LOGOUT', 1}, {'REGISTER', 2}]},
     {{enum, 'Auth.Type'},
      [{'MANUFACTURER', 0}, {'IMPORTER', 1}]},
     {{msg, 'Auth'},
      [#{name => operation, fnum => 1, rnum => 2,
	 type => {enum, 'Auth.Operation'},
	 occurrence => optional, opts => []},
       #{name => type, fnum => 2, rnum => 3,
	 type => {enum, 'Auth.Type'}, occurrence => optional,
	 opts => []},
       #{name => username, fnum => 3, rnum => 4,
	 type => string, occurrence => optional, opts => []},
       #{name => password, fnum => 4, rnum => 5,
	 type => string, occurrence => optional, opts => []},
       #{name => authenticated, fnum => 5, rnum => 6,
	 type => int32, occurrence => optional, opts => []}]}].


get_msg_names() -> ['Auth'].


get_group_names() -> [].


get_msg_or_group_names() -> ['Auth'].


get_enum_names() -> ['Auth.Operation', 'Auth.Type'].


fetch_msg_def(MsgName) ->
    case find_msg_def(MsgName) of
      Fs when is_list(Fs) -> Fs;
      error -> erlang:error({no_such_msg, MsgName})
    end.


fetch_enum_def(EnumName) ->
    case find_enum_def(EnumName) of
      Es when is_list(Es) -> Es;
      error -> erlang:error({no_such_enum, EnumName})
    end.


find_msg_def('Auth') ->
    [#{name => operation, fnum => 1, rnum => 2,
       type => {enum, 'Auth.Operation'},
       occurrence => optional, opts => []},
     #{name => type, fnum => 2, rnum => 3,
       type => {enum, 'Auth.Type'}, occurrence => optional,
       opts => []},
     #{name => username, fnum => 3, rnum => 4,
       type => string, occurrence => optional, opts => []},
     #{name => password, fnum => 4, rnum => 5,
       type => string, occurrence => optional, opts => []},
     #{name => authenticated, fnum => 5, rnum => 6,
       type => int32, occurrence => optional, opts => []}];
find_msg_def(_) -> error.


find_enum_def('Auth.Operation') ->
    [{'LOGIN', 0}, {'LOGOUT', 1}, {'REGISTER', 2}];
find_enum_def('Auth.Type') ->
    [{'MANUFACTURER', 0}, {'IMPORTER', 1}];
find_enum_def(_) -> error.


enum_symbol_by_value('Auth.Operation', Value) ->
    'enum_symbol_by_value_Auth.Operation'(Value);
enum_symbol_by_value('Auth.Type', Value) ->
    'enum_symbol_by_value_Auth.Type'(Value).


enum_value_by_symbol('Auth.Operation', Sym) ->
    'enum_value_by_symbol_Auth.Operation'(Sym);
enum_value_by_symbol('Auth.Type', Sym) ->
    'enum_value_by_symbol_Auth.Type'(Sym).


'enum_symbol_by_value_Auth.Operation'(0) -> 'LOGIN';
'enum_symbol_by_value_Auth.Operation'(1) -> 'LOGOUT';
'enum_symbol_by_value_Auth.Operation'(2) -> 'REGISTER'.


'enum_value_by_symbol_Auth.Operation'('LOGIN') -> 0;
'enum_value_by_symbol_Auth.Operation'('LOGOUT') -> 1;
'enum_value_by_symbol_Auth.Operation'('REGISTER') -> 2.

'enum_symbol_by_value_Auth.Type'(0) -> 'MANUFACTURER';
'enum_symbol_by_value_Auth.Type'(1) -> 'IMPORTER'.


'enum_value_by_symbol_Auth.Type'('MANUFACTURER') -> 0;
'enum_value_by_symbol_Auth.Type'('IMPORTER') -> 1.


get_service_names() -> [].


get_service_def(_) -> error.


get_rpc_names(_) -> error.


find_rpc_def(_, _) -> error.



-spec fetch_rpc_def(_, _) -> no_return().
fetch_rpc_def(ServiceName, RpcName) ->
    erlang:error({no_such_rpc, ServiceName, RpcName}).


%% Convert a a fully qualified (ie with package name) service name
%% as a binary to a service name as an atom.
-spec fqbin_to_service_name(_) -> no_return().
fqbin_to_service_name(X) ->
    error({gpb_error, {badservice, X}}).


%% Convert a service name as an atom to a fully qualified
%% (ie with package name) name as a binary.
-spec service_name_to_fqbin(_) -> no_return().
service_name_to_fqbin(X) ->
    error({gpb_error, {badservice, X}}).


%% Convert a a fully qualified (ie with package name) service name
%% and an rpc name, both as binaries to a service name and an rpc
%% name, as atoms.
-spec fqbins_to_service_and_rpc_name(_, _) -> no_return().
fqbins_to_service_and_rpc_name(S, R) ->
    error({gpb_error, {badservice_or_rpc, {S, R}}}).


%% Convert a service name and an rpc name, both as atoms,
%% to a fully qualified (ie with package name) service name and
%% an rpc name as binaries.
-spec service_and_rpc_name_to_fqbins(_, _) -> no_return().
service_and_rpc_name_to_fqbins(S, R) ->
    error({gpb_error, {badservice_or_rpc, {S, R}}}).


fqbin_to_msg_name(<<"Auth">>) -> 'Auth';
fqbin_to_msg_name(E) -> error({gpb_error, {badmsg, E}}).


msg_name_to_fqbin('Auth') -> <<"Auth">>;
msg_name_to_fqbin(E) -> error({gpb_error, {badmsg, E}}).


fqbin_to_enum_name(<<"Auth.Operation">>) -> 'Auth.Operation';
fqbin_to_enum_name(<<"Auth.Type">>) -> 'Auth.Type';
fqbin_to_enum_name(E) ->
    error({gpb_error, {badenum, E}}).


enum_name_to_fqbin('Auth.Operation') -> <<"Auth.Operation">>;
enum_name_to_fqbin('Auth.Type') -> <<"Auth.Type">>;
enum_name_to_fqbin(E) ->
    error({gpb_error, {badenum, E}}).


get_package_name() -> undefined.


%% Whether or not the message names
%% are prepended with package name or not.
uses_packages() -> false.


source_basename() -> "proto_auth.proto".


%% Retrieve all proto file names, also imported ones.
%% The order is top-down. The first element is always the main
%% source file. The files are returned with extension,
%% see get_all_proto_names/0 for a version that returns
%% the basenames sans extension
get_all_source_basenames() -> ["proto_auth.proto"].


%% Retrieve all proto file names, also imported ones.
%% The order is top-down. The first element is always the main
%% source file. The files are returned sans .proto extension,
%% to make it easier to use them with the various get_xyz_containment
%% functions.
get_all_proto_names() -> ["proto_auth"].


get_msg_containment("proto_auth") -> ['Auth'];
get_msg_containment(P) ->
    error({gpb_error, {badproto, P}}).


get_pkg_containment("proto_auth") -> undefined;
get_pkg_containment(P) ->
    error({gpb_error, {badproto, P}}).


get_service_containment("proto_auth") -> [];
get_service_containment(P) ->
    error({gpb_error, {badproto, P}}).


get_rpc_containment("proto_auth") -> [];
get_rpc_containment(P) ->
    error({gpb_error, {badproto, P}}).


get_enum_containment("proto_auth") ->
    ['Auth.Operation', 'Auth.Type'];
get_enum_containment(P) ->
    error({gpb_error, {badproto, P}}).


get_proto_by_msg_name_as_fqbin(<<"Auth">>) -> "proto_auth";
get_proto_by_msg_name_as_fqbin(E) ->
    error({gpb_error, {badmsg, E}}).


-spec get_proto_by_service_name_as_fqbin(_) -> no_return().
get_proto_by_service_name_as_fqbin(E) ->
    error({gpb_error, {badservice, E}}).


get_proto_by_enum_name_as_fqbin(<<"Auth.Type">>) ->
    "proto_auth";
get_proto_by_enum_name_as_fqbin(<<"Auth.Operation">>) ->
    "proto_auth";
get_proto_by_enum_name_as_fqbin(E) ->
    error({gpb_error, {badenum, E}}).


-spec get_protos_by_pkg_name_as_fqbin(_) -> no_return().
get_protos_by_pkg_name_as_fqbin(E) ->
    error({gpb_error, {badpkg, E}}).



gpb_version_as_string() ->
    "4.11.0".

gpb_version_as_list() ->
    [4,11,0].
