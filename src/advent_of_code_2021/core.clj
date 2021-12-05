(ns advent-of-code-2021.core
  (:require
   [advent-of-code-2021.day01 :as day01]
   [advent-of-code-2021.day02 :as day02]
   [advent-of-code-2021.day03 :as day03]
   [advent-of-code-2021.day04 :as day04]
   [advent-of-code-2021.day05 :as day05])
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

(defn -main
  [& args]
  (doseq [a (map #(Integer/parseInt %) args)]
    (let [info (case a
                 1 day01/info
                 2 day02/info
                 3 day03/info
                 4 day04/info
                 5 day05/info)]
      (run-for-day (assoc info :number a)))))
