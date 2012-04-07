#!/usr/bin/env bash

rm -rf ./build
~/lib/virtualenvs/d/bin/d
hg -R ~/src/sjl.bitbucket.org pull -u
rsync --delete -a ./build/ ~/src/sjl.bitbucket.org/roul
hg -R ~/src/sjl.bitbucket.org commit -Am 'roul: Update site.'
hg -R ~/src/sjl.bitbucket.org push

