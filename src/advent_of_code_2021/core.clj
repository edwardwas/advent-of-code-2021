(ns advent-of-code-2021.core
  (:require
   [advent-of-code-2021.day01 :as day01]
   [advent-of-code-2021.day02 :as day02]
   [advent-of-code-2021.day03 :as day03]
   [advent-of-code-2021.day04 :as day04]
   [advent-of-code-2021.day05 :as day05]
   [advent-of-code-2021.day06 :as day06])
  (:gen-class))

(defn run-for-day
  [info]
  (let [{:keys [number part1 part2 input]} info]
    (println "Day" number)
    (print "Part 1: ")
    (flush)
    (println (part1 input))
    (print "Part 2: ")
    (flush)
    (println (part2 input))))

(def info-maps
  [day01/info day02/info day03/info day04/info day05/info day06/info])

(defn -main
  [& args]
  (if (get (into #{} args) "all")
    (doseq [[n i] (map vector (range) info-maps)]
      (run-for-day (assoc i :number (inc n))))
    (doseq [a (map #(Integer/parseInt %) args)]
      (let [info (get info-maps (- a 1))] 
        (run-for-day (assoc info :number a))))))
