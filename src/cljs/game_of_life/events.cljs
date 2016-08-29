(ns game-of-life.events
  (:require [re-frame.core   :refer [reg-event-db path trim-v after debug]]
            [game-of-life.lib.grid :refer [next-gen]]
            [game-of-life.store :refer [default-db
                                        blank-board
                                        randomize]]))


(reg-event-db
  :initialize-db
  (fn []
    default-db))

(reg-event-db
  :randomize
  (path [:board])
  (fn []
    (randomize)))

(reg-event-db
  :reset
  (path [:board])
  (fn []
    blank-board))

(reg-event-db
  :tick
  (path [:profiler])
  (fn [profiler _]
    (update profiler :ticks inc)))

(reg-event-db
  :toggle-cell
  (path [:board])
  (fn [board [_ [y x]]]
    (update-in board [y x] not)))

(reg-event-db
  :next-gen
  (path [:board])
  next-gen)
  
