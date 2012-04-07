(ns roul.random_test
  (:use clojure.test)
  (:require [clojure.test.generative.generators :as gen])
  (:use [clojure.test.generative :only [defspec]])
  (:require [roul.random :as rr]))


(defn abs [i]
  (if (< i 0)
    (- i)
    i))


(defn random-smallish-int []
  (gen/uniform -2000 2000))

(defspec abs-is-never-negative
  abs
  [^{:tag `random-smallish-int} n]
  (assert (not (neg? %))))


(defn all-within [actual expected tolerance]
  (letfn [(is-bad [[i p]]
            (<= (abs (- p (expected i)))
                tolerance))]
    (empty? (remove identity (map is-bad actual)))))

(defn percentquencies [data]
  (let [freqs (frequencies data)
        total (reduce + (map second freqs))]
    (into {} (map (juxt first #(-> % second (/ total) float))
                  freqs))))


(defn random-data [] (gen/vec gen/int))
(defspec percentquencies-are-sane
  percentquencies
  [^{:tag `random-data} data]
  (assert (= (set (keys %)) (set data))))


(deftest test-rand-nth-weighted
  (testing "Values should be selected approximately according to their weights."
    (dotimes [_ 10]
      (let [data (repeatedly 10000 #(rr/rand-nth-weighted {:a 0.5 :b 0.2 :c 0.3}))]
        (is (all-within (percentquencies data) {:a 0.5 :b 0.2 :c 0.3} 0.05))))
    (dotimes [_ 10]
      (let [data (repeatedly 10000 #(rr/rand-nth-weighted {:a 100 :b 900}))]
        (is (all-within (percentquencies data) {:a 0.10 :b 0.9} 0.05))))))
