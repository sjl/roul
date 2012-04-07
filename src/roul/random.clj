(ns roul.random
  (:refer-clojure :exclude [rand-int rand rand-nth]))


(defn rand
  "Return a random float between start (inclusive) and end (exclusive).

  start defaults to 0
  end defaults to 1"
  ([] (clojure.core/rand))
  ([end] (clojure.core/rand end))
  ([start end] (+ start (clojure.core/rand (- end start)))))

(defn rand-int
  "Return a random int between start (inclusive) and end (exclusive).

  start defaults to 0"
  ([end] (clojure.core/rand-int end))
  ([start end] (+ start (clojure.core/rand-int (- end start)))))

(defn rand-nth
  "Return a random element of the collection.  Delegates to the built-in
  rand-nth for now."
  [coll]
  (clojure.core/rand-nth coll))

(defn rand-nth-weighted
  "Return a random element from the weighted collection.

  A weighted collection can be any seq of [choice, weight] elements.  The
  weights can be arbitrary numbers -- they do not need to add up to anything
  specific.

  Examples:

  (rand-nth-weighted [[:a 0.50] [:b 0.20] [:c 0.30]])
  (rand-nth-weighted {:a 10 :b 200})"
  [coll]
  (let [total (reduce + (map second coll))]
    (loop [i (rand total)
           [[choice weight] & remaining] (seq coll)]
      (if (>= weight i)
        choice
        (recur (- i weight) remaining)))))

