(ns game-of-life.events
  (:require [re-frame.core   :refer [reg-event-db path trim-v after debug]]
            [game-of-life.lib.grid :refer [next-gen]]
            [game-of-life.db :refer [default-db
                                     blank-board
                                     randomize]]))


(reg-event-db
  :initialize-db
  (fn []
    default-db))

(reg-event-db
  :randomize
  (fn []
    (randomize)))

(reg-event-db
  :reset-board
  (path :board)
  (fn []
    blank-board))

(reg-event-db
  :next-gen
  (path [:board])
  next-gen)
  
