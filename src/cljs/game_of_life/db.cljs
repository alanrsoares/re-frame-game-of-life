(ns game-of-life.db
  (:require [game-of-life.lib.grid :refer [make-grid]]))


(def board-size 20)

(defn random-fill [] 
  (< 0.8 (rand)))

(def blank-board
  (make-grid board-size (fn [] false)))

(defn randomize []
  (make-grid board-size random-fill))

(def default-db
  {:board (randomize)
   :profiler {:frame-id nil
              :started-at nil
              :ticks 0
              :frame-rate nil}})


