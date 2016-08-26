(ns game-of-life.containers.main
    (:require [game-of-life.components.grid :refer [grid-component]]
              [re-frame.core :refer [subscribe
                                     dispatch]]))

(defonce tick 
  (js/setInterval
    #(dispatch [:next-gen]) 100))

(defn main-container []
  (let [board (subscribe [:board])]
    (fn []
      (grid-component @board))))
