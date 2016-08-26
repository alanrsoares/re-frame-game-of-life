(ns game-of-life.containers.main
    (:require [game-of-life.components.grid :refer [grid-component]]
              [re-frame.core :refer [subscribe
                                     dispatch]]))
((defn tick []
  (js/requestAnimationFrame
    (fn []
      (do
        (dispatch [:next-gen])
        (tick))))))       

(defn main-container []
  (let [board (subscribe [:board])]
    (fn []
      (grid-component @board))))
