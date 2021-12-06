(ns advent-of-code-2021.util
  (:require
   [clojure.java.io :as io]
   [clojure.spec.alpha :as s]
   [clojure.string :as str]
   [orchestra.core :refer [defn-spec]]
   [orchestra.spec.test :as st]))

(defn-spec read-day-input-rows (s/coll-of string?)
  ([n int?]
   (read-day-input-rows n "input"))
  ([n int? fp string?]
   (as-> n x
     (format "%s/day%02d.txt" fp x)
     (io/resource x)
     (slurp x)
     (str/split x #"\n"))))

(defn-spec binary->decimal int?
  "Convert a collection of 0s and 1s to a decimal number"
  [binary-seq (s/coll-of #(and (int? %) (#{0 1} %))
                         :min-count 1)]
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

(st/instrument)
