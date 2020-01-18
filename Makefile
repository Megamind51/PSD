build:
	mkdir -p ebin
	cd src/dependencies/gpb/ && make && cd ../../../
	src/dependencies/gpb/bin/protoc-erl -I. -maps -o src/frontend/ src/protos/*.proto
	src/dependencies/protobuf/bin/protoc --java_out=src/protos src/protos/proto_auth.proto src/protos/proto_bid.proto src/protos/proto_product.proto
	cd src/dependencies/jiffy/ && make && cd ../../../
	cp src/dependencies/jiffy/src/*.erl src/frontend/
	cp -rf src/dependencies/jiffy/priv ebin/
	erlc -I src/dependencies/gpb/include -o ebin/ src/frontend/*.erl

clean:
	rm src/frontend/jiffy.erl src/frontend/jiffy_utf8.erl src/frontend/proto_auth.erl
	rm -rf ebin
