build:
	mkdir EBin
	Dependencies/gpb/bin/protoc-erl -I. -maps -o Frontend/ Protos/*.proto
	cp Dependencies/jiffy-1.0.1/src/*.erl Frontend/
	cp -rf Dependencies/jiffy-1.0.1/priv EBin/
	erlc -I Dependencies/gpb/include -o EBin/ Frontend/*.erl

clean:
	rm -rf EBin