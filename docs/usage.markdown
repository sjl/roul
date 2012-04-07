Usage
=====

Roul currently contains a few functions in one namespace.  It'll expand in the
future, but for now that's all it is.

[TOC]

roul.random
-----------

The `roul.random` namespace contains wrappers around some of Clojure's built-in
random functions to make them more user friendly, as well as some uniqe
functions of its own.

The recommended way is to `require` this namespace into your own instead of
overwriting Clojure's builtins:

    :::clojure
    (ns foo.core
      (:require [roul.random :as rr]))

    ; or

    (require '[roul.random :as rr])

### rand

    :::clojure
    (rand)           ; return a float in [0, 1)
    (rand end)       ; return a float in [0, end)
    (rand start end) ; return a float in [start, end)

A wrapper around the built-in `rand`.  Returns a random floating point number
between `start` (inclusive) and `end` (exclusive).

If not given, `start` defaults to `0` and `end` defaults to `1`.

### rand-int

    :::clojure
    (rand-int end)       ; return an int in [0, end)
    (rand-int start end) ; return an int in [start, end)

A wrapper around the built-in `rand-int`.  Returns a random integer between
`start` (inclusive) and `end` (exclusive).

If not given, `start` defaults to `0`.

### rand-nth

    :::clojure
    (rand-nth coll) ; return a random element of coll

A wrapper around the built-in `rand-nth`.  Returns a random element of the given
collection.

This is a transparent wrapper around the builtin, included only for
completeness.  Unlike the other wrappers no new functionality has been added
(yet).

### rand-nth-weighted

    :::clojure
    (rand-nth-weighted coll) ; return a random element of coll

Returns a random element of a weighted collection.

A weighted collection can be any seq of `[choice, weight]` elements.  The
weights can be arbitrary numbers -- they do not need to add up to anything
specific.

    :::clojure
    ; Returns coffee roughly 80% of the time, tea 15%, and soda 5%.
    (rr/rand-nth-weighted {:coffee 0.80, :tea 0.15, :soda 0.05})

    ; Returns cats roughly twice as often as boots.
    (rr/rand-nth-weighted [[:boots 14]
                           [:cats 28]])
