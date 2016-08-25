(ns game-of-life.events
  (:require [game-of-life.db  :refer [default-db 
                                      next-gen 
                                      randomize
                                      blank-board]]
                                          
            [re-frame.core    :refer [reg-event-db path trim-v after debug]]))
           

(reg-event-db
  :initialize-db
  (fn [_ _]
    default-db))

(reg-event-db
  :randomize
  (fn [_ _]
    (randomize)))

(reg-event-db
  :reset-board
  (path :board)
  (fn [_ _]
    blank-board))

(reg-event-db
  :next-gen
  (path [:board])
  next-gen)
  
