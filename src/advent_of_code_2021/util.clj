(ns advent-of-code-2021.util
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defn read-day-input-rows
  [n]
  (as-> n x
    (format "input/day%02d.txt" x)
    (io/resource x)
    (slurp x)
    (str/split x #"\n")))

(defn binary->decimal
  [binary-seq]
  (->> binary-seq 
       reverse
       (map (fn [x n] (* n (Math/pow 2 x))) (range))
       (reduce +)
       int))

(defn- common-helper
  [f coll]
  (let [sorted-frequences (->> coll frequencies (sort-by second) f)]
    (if (empty? sorted-frequences)
      nil
      (->> sorted-frequences
           (take-while #(= (second %) (second (first sorted-frequences))))
           (map first)
           (into #{})))))

(defn most-common
  "Return a set of the most common items in `col`"
  [col]
  (common-helper reverse col))

(defn least-common
  "Return a set of the last common items in `col`"
  [col]
  (common-helper identity col))
