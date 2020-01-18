build:
	mkdir -p ebin
	cd dependencies/gpb/ && make && cd ../../../
	dependencies/gpb/bin/protoc-erl -I. -maps -o src/frontend/ src/protos/*.proto
	dependencies/protobuf/bin/protoc --java_out=src/protos src/protos/proto_auth.proto src/protos/proto_bid.proto src/protos/proto_product.proto
	sed -i '1s/^/package protos;\n/' src/protos/ProtoAuth.java src/protos/ProtoBid.java src/protos/ProtoProduct.java
	cd dependencies/jiffy/ && make && cd ../../../
	cp dependencies/jiffy/src/*.erl src/frontend/
	cp -rf dependencies/jiffy/priv ebin/
	erlc -I dependencies/gpb/include -o ebin/ src/frontend/*.erl
	dependencies/chumak/rebar3 compile && cp dependencies/chumak/ebin/* ebin/

clean:
	rm src/frontend/jiffy.erl src/frontend/jiffy_utf8.erl src/frontend/proto_auth.erl
	rm -rf ebin
