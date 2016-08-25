(ns game-of-life.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 :board
 (fn [db _]
   (:board db)))
