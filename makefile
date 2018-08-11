dist/banks-%.json:
	node ./scripts/concat-banks.js

build: dist/banks-%.json
	./scripts/build.sh

clean:
	rm -rf ./dist

.PHONY: test build clean
