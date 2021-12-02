(ns advent-of-code-2021.day02
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defn- parse-line
  [l]
  (let [[tag n-str] (str/split l #" ")]
    [(keyword tag) (Integer/parseInt n-str)]))

(defonce input
  (as-> "input/day02.txt" x
    (io/resource x)
    (slurp x)
    (str/split x #"\n")
    (map parse-line x)))

(def start-submarine
  {:depth 0
   :aim 0
   :horiz 0})

(defn- update-submarine-with-command-for-part-1
  [sub [tag amount]]
  (case tag
    :up (update sub :depth #(- % amount))
    :down (update sub :depth + amount)
    :forward (update sub :horiz + amount)
    (throw (ex-info "Unknown command"
                    {:tag tag}))))

(defn- update-submarine-with-command-for-part-2
  [sub [tag amount]]
  (case tag
    :up (update sub :aim #(- % amount))
    :down (update sub :aim + amount)
    :forward (-> sub
                 (update :horiz + amount)
                 (update :depth + (* amount (:aim sub))))))

(defn part1
  [input]
  (let [{:keys [depth horiz]}
        (reduce update-submarine-with-command-for-part-1 start-submarine input)]
    (* depth horiz)))

(defn part2
  [input]
  (let [{:keys [depth horiz]}
        (reduce update-submarine-with-command-for-part-2 start-submarine input)]
    (* depth horiz)))

(def info
  {:part1 part1 :part2 part2 :input input})
