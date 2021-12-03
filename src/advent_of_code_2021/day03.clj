(ns advent-of-code-2021.day03
  (:require
   [advent-of-code-2021.util :as util]))

(def input
  (->> (util/read-day-input-rows 3)
       (map (partial map #(Integer/parseInt (str %))))))

(def test-input
  (->> ["00100"
        "11110"
        "10110"
        "10111"
        "10101"
        "01111"
        "00111"
        "11100"
        "10000"
        "11001"
        "00010"
        "01010"]
       (map (partial map #(Integer/parseInt (str %))))))

(defn part1
  [input]
  (let [transposed (apply map vector input)
        gamma (util/binary->decimal
               (map (comp first util/most-common) transposed))
        epsilon (util/binary->decimal
                 (map (comp first util/least-common) transposed))]
    (* gamma epsilon)))

(defn- life-support-helper
  [f tie-break col]
  (loop [col col i 0]
    (if (= 1 (count col))
      (util/binary->decimal (first col))
      (let [bits (f (map #(get % i) col))
            bit-mask (if (= 1 (count bits)) 
                       (first bits) 
                       tie-break)
            new-col (filter #(= bit-mask (get % i)) col)] 
        (recur new-col (inc i))))))

(defn- calculate-oxygen-generator-rating
  [col]
  (life-support-helper util/most-common 1 (mapv #(into [] %) col)))

(defn- calculate-co2-scrubber-rating
  [col]
  (life-support-helper util/least-common 0 (mapv #(into [] %) col)))

(defn part2
  [input]
  (* (calculate-oxygen-generator-rating input)
     (calculate-co2-scrubber-rating input)))

(def info
  {:part1 part1 :part2 part2 :input input})
