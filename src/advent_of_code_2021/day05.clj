(ns advent-of-code-2021.day05
  (:require
   [advent-of-code-2021.util :as util]))

(defn- parse-line-segment
  [s]
  (let [[_ x1 y1 x2 y2] (re-matches #"(\d+),(\d+) -> (\d+),(\d+)" s)]
    [[(Integer/parseInt x1) (Integer/parseInt y1)] [(Integer/parseInt x2) (Integer/parseInt y2)]]))

(defn- horiz-or-vertical?
  [[[x1 y1] [x2  y2]]]
  (or (= x1 x2) (= y1 y2)))

(defn- diag-line-locations
  [[[x1 y1] [x2 y2]]]
  (let [xs (if (> x1 x2)
             (reverse (range x2 (+ 1 x1)))
             (range x1 (+ 1 x2)))
        ys (if (> y1 y2)
             (reverse (range y2 (+ 1 y1)))
             (range y1 (+ 1 y2)))] 
    (map vector xs ys)))

(diag-line-locations [[9 7] [7 9]])

(defn- locations
  [[[x1 y1] [x2 y2]]]
  (cond
    (= x1 x2)
    (map (fn [y] [x1 y]) (range (min y1 y2) (+ 1 (max y1 y2))))
    (= y1 y2)
    (map (fn [x] [x y1]) (range (min x1 x2) (+ 1 (max x1 x2))))
    :else
    (diag-line-locations [[x1 y1] [x2 y2]])))

(defn parts-shared
  [filter-func input]
  (->> input
       (map parse-line-segment)
       (filter filter-func)
       (mapcat locations)
       (reduce (fn [m pos]
                 (if (get m pos)
                   (update m pos inc)
                   (assoc m pos 1)))
               {})
       (filter #(>= (second %) 2))
       count))

(defn part1
  [input]
  (parts-shared horiz-or-vertical? input))

(defn part2
  [input]
  (parts-shared (constantly true) input))

(def info
  {:part1 part1 :part2 part2 :input (util/read-day-input-rows 5)})
