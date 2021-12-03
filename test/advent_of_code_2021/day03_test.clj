(ns advent-of-code-2021.day03-test
  (:require
   [advent-of-code-2021.day03 :as sut]
   [clojure.test :refer [deftest is]]))

(deftest part1-test
  (is
   (= (sut/part1 sut/test-input)
      198)))

(deftest part2-test
  (is
   (= (sut/part2 sut/test-input)
      230)))
