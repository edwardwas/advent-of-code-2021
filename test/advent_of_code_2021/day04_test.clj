(ns advent-of-code-2021.day04-test
  (:require
   [advent-of-code-2021.day04 :as sut]
   [clojure.test :refer [deftest is]]))

(deftest part1-test
  (is
   (= (sut/part1 sut/test-input)
      4512)))

(deftest part2-test
  (is
   (= (sut/part2 sut/test-input)
      1924)))
