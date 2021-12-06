(ns advent-of-code-2021.day06
  (:require
   [advent-of-code-2021.util :as util]
   [clojure.string :as str]))

(defn- parse-input
  [s]
  (map #(Integer/parseInt %) (str/split s #",")))

(defn- list->counts
  [start]
  (->> start
       (map (fn [x] {x 1}))
       (apply merge-with +)))

(defn- advance-generation
  [count-map]
  (let [zero-count (or (get count-map 0) 0)]
    (as-> count-map c
      (dissoc c 0)
      (map (fn [[k v]] [(- k 1) v]) c)
      (into {} c)
      (merge-with + c {6 zero-count 8 zero-count}))))

(defn- population-count
  [count-map]
  (->> count-map (map second) (apply +)))

(defn- total-count-after-days
  [days input]
  (->> input
       list->counts
       (iterate advance-generation)
       (drop days)
       first
       population-count))

(defn part1
  [input]
  (total-count-after-days 80 input))

(defn part2
  [input]
  (total-count-after-days 256 input))

(def info
  {:part1 part1
   :part2 part2
   :input (parse-input (first (util/read-day-input-rows 6 "input")))})
