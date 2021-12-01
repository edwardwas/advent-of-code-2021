(ns advent-of-code-2021.core
  (:require
   [advent-of-code-2021.day01 :as day01])
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
                 1 day01/info)]
      (run-for-day (assoc info :number a)))))