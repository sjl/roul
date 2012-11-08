(ns roul.random
  (:refer-clojure :exclude [rand-int rand rand-nth]))
 

(def generator (java.util.Random.))

(defn rand
  "Return a random float between start (inclusive) and end (exclusive).

  start defaults to 0
  end defaults to 1

  "
  ([] (clojure.core/rand))
  ([end] (clojure.core/rand end))
  ([start end] (+ start (clojure.core/rand (- end start)))))

(defn rand-int
  "Return a random int between start (inclusive) and end (exclusive).

  start defaults to 0

  "
  ([end] (clojure.core/rand-int end))
  ([start end] (+ start (clojure.core/rand-int (- end start)))))

(defn rand-nth
  "Return a random element of the collection.

  Delegates to the built-in rand-nth for now.

  "
  [coll]
  (clojure.core/rand-nth coll))

(defn rand-nth-weighted
  "Return a random element from the weighted collection.

  A weighted collection can be any seq of [choice, weight] elements.  The
  weights can be arbitrary numbers -- they do not need to add up to anything
  specific.

  Examples:

  (rand-nth-weighted [[:a 0.50] [:b 0.20] [:c 0.30]])
  (rand-nth-weighted {:a 10 :b 200})

  "
  [coll]
  (let [total (reduce + (map second coll))]
    (loop [i (rand total)
           [[choice weight] & remaining] (seq coll)]
      (if (>= weight i)
        choice
        (recur (- i weight) remaining)))))

(defn rand-bool
  "Return true or false with a percent chance of being true.

  percent defaults to 50.0

  "
  ([]
   (rand-bool 50.0))
  ([percent]
   (< (rand 100) percent)))

(defn rand-gaussian
  "Return a random float.

  Floats are generated from a Gaussian distribution with the given mean and
  standard deviation.

  A lower and upper bound can be specified if desired, which will clamp the
  output of this function to those bounds.  Note that this clamping does NOT
  adjust the distribution, so if you clamp too tightly you'll get
  a disproportionate number of the boundary values.  It's just here to give you
  a way to prevent garbage values.

  mean defaults to 0.
  standard-deviation defaults to 1.

  "
  ([]
   (.nextGaussian generator))
  ([mean standard-deviation]
   (-> (rand-gaussian)
     (* standard-deviation)
     (+ mean)))
  ([mean standard-deviation lower-bound upper-bound]
   (-> (rand-gaussian mean standard-deviation)
     (max lower-bound)
     (min upper-bound))))

(defn rand-gaussian-int
  "Return a random integer.

  Integers are generated from a Gaussian distribution with the given mean and
  standard deviation.

  A lower and upper bound can be specified if desired, which will clamp the
  output of this function to those bounds.  Note that this clamping does NOT
  adjust the distribution, so if you clamp too tightly you'll get
  a disproportionate number of the boundary values.  It's just here to give you
  a way to prevent garbage values.

  mean defaults to 0.
  standard-deviation defaults to 1.

  "
  ([]
   (int (rand-gaussian)))
  ([mean standard-deviation]
   (int (rand-gaussian mean standard-deviation)))
  ([mean standard-deviation lower-bound upper-bound]
   (-> (rand-gaussian-int mean standard-deviation)
     (max lower-bound)
     (min upper-bound))))

