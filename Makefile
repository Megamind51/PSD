build:
	mkdir -p EBin
	cd Dependencies/gpb/ && make && cd ../../
	Dependencies/gpb/bin/protoc-erl -I. -maps -o Frontend/ Protos/*.proto
	cd Dependencies/jiffy/ && make && cd ../../
	cp Dependencies/jiffy/src/*.erl Frontend/
	cp -rf Dependencies/jiffy/priv EBin/
	erlc -I Dependencies/gpb/include -o EBin/ Frontend/*.erl

clean:
	rm Frontend/jiffy.erl Frontend/jiffy_utf8.erl Frontend/proto_auth.erl
	rm -rf EBin