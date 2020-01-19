OS_NAME := $(shell uname -s | tr A-Z a-z)
PROTOC_PATH := $(ifeq $(OS_NAME,linux), dependencies/protobuf_linux/bin/protoc, dependencies/protobuf_osx/bin/protoc)

build:
	mkdir -p ebi
	cd dependencies/gpb/ && make && cd ../../../
	chmod +x dependencies/gpb/bin/protoc-erl
	chmod +x dependencies/gpb/bin/protoc-erl
	chmod +x dependencies/chumak/rebar3
	dependencies/gpb/bin/protoc-erl -I. -maps -o src/frontend/ src/protos/*.proto
	$(PROTOC_PATH) --java_out=src/protos src/protos/*.proto 
	sed -i '1s/^/package protos;\n/' src/protos/Proto*.java
	cd dependencies/jiffy/ && make && cd ../../../
	cp dependencies/jiffy/src/*.erl src/frontend/
	cp -rf dependencies/jiffy/priv ebin/
	erlc -I dependencies/gpb/include -o ebin/ src/frontend/*.erl
	dependencies/chumak/rebar3 compile && cp dependencies/chumak/ebin/* ebin/

clean:
	rm src/frontend/jiffy.erl src/frontend/jiffy_utf8.erl src/frontend/proto_*.erl src/protos/*.java
	rm -rf ebin
	rm rebar.lock








