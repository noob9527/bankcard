#!/usr/bin/env bash
DIR="$(cd "$(dirname "${BASH_SOURCE:-$0}")" && pwd)"
PROJECT_DIR=$(dirname "$DIR")

for lib in $PROJECT_DIR/libs/*/; do
    cd $lib
    if [[ -s $lib/makefile ]]; then
        make test
    fi
done