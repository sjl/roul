roul
====

Roul (from "roulette") is a tiny Clojure library for working with random numbers
more easily.

For example:

    (require '[roul.random :as rr])

    ; Get a random integer between 0 and 10
    (rand-int 10)    ; Default Clojure
    (rr/rand-int 10) ; Roul

    ; Get a random integer between 10 and 20
    (+ 10 (rand-int 10)) ; Default Clojure
    (rr/rand-int 10 20)  ; Roul

    ; Get a random element from a weighted collection

    ; Returns coffee roughly 80% of the time, tea 15%, and soda 5%.
    (rr/rand-nth-weighted {:coffee 0.80 :tea 0.15 :soda 0.05})

    ; Returns cats roughly twice as often as boots.
    (rr/rand-nth-weighted [[:boots 14] [:cats 28]])

Installation
------------

You need Leiningen 2 to use this.  Sorry.

    [roul "0.2.0"]

Usage
-----

Read the [documentation](http://sjl.bitbucket.org/roul/).

License
-------

Copyright © 2012 Steve Losh and Contributors

MIT/X11 Licensed
