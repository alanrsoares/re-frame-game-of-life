(ns game-of-life.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
  :board
  :board)

(reg-sub
  :profiler
  :profiler)
