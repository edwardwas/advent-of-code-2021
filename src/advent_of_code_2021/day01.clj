(ns advent-of-code-2021.day01
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(def input
  (as-> "input/day01.txt" x
    (io/resource x)
    (slurp x)
    (str/split x #"\n")
    (map #(Integer/parseInt %) x)))

(defn part1
  [xs]
  (->> xs rest (map < xs) (filter identity) count))

(defn part2
  [xs]
  (part1 (map + xs (rest xs) (rest (rest xs)))))

(def info
  {:part1 part1 :part2 part2 :input input})
