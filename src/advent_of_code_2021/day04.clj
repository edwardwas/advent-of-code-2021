(ns advent-of-code-2021.day04
  (:require
   [advent-of-code-2021.util :as util]
   [clojure.string :as str]))

(def grid-size 5)

(defn- parse-grid
  [rows]
  (mapv
   (fn [row] (->> (str/split row #"\s+")
                  (remove #(= "" %))
                  (mapv (fn [s] {:number (Integer/parseInt s)
                                 :marked false})))) 
   rows))

(defn- parse-input
  [input]
  (let [[numbers _ & rows] input]
    {:numbers (map #(Integer/parseInt %) (str/split numbers #","))
     :boards (->> rows
                (iterate (partial drop (inc grid-size)))
                (map (partial take grid-size))
                (take-while (complement empty?))
                (map parse-grid))})) 

(defn- board-wins?
  [board]
  (letfn [(row-wins? [row]
            (every? :marked row))]
    (or (some row-wins? board)
        (some row-wins? (apply map vector board)))))

(defn- mark-number
  [number board]
  (mapv
   (partial
    mapv (fn [cell]
           (if (= number (:number cell))
             (assoc cell :marked true)
             cell)))
   board))

(defn- generate-winning-board
  [{:keys [numbers boards]}]
  (loop [numbers numbers boards boards]
    (let [n (first numbers)
          new-boards (map (partial mark-number n) boards)
          winner (->> new-boards (filter board-wins?) first)]
      (if winner
        [n winner]
        (recur (rest numbers) new-boards)))))

(defn- generate-last-winning-board
  [{:keys [numbers boards]}]
  (loop [numbers numbers boards boards]
    (let [n (first numbers)
          new-boards (->> boards
                          (map (partial mark-number n))
                          (remove board-wins?))]
      (if (= 1 (count new-boards))
        [(second numbers) (mark-number (second numbers) (first new-boards))]
        (recur (rest numbers) new-boards)))))

(defn- board-score
  [board]
  (->> board
       (map (fn [row] (->> row (filter (complement :marked)) (map :number) (apply +))))
       (apply +)))

(defn part1
  [input]
  (let [[final-n winner-board] (generate-winning-board (parse-input input))]
    (* final-n (board-score winner-board))))

(defn part2
  [input]
  (let [[final-n winner-board] (generate-last-winning-board (parse-input input))]
    (* final-n (board-score winner-board))))

(def input
  (util/read-day-input-rows 4 "input"))

(def test-input
  (util/read-day-input-rows 4 "test"))

(def info
  {:part1 part1 :part2 part2 :input input})
