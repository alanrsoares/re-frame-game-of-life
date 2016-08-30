(ns game-of-life.lib.grid
  (:require [game-of-life.lib.game :refer [will-live?]]))

(defn make-grid [size fill-with]
  (let [xs (range size)]
    (mapv
      #(mapv fill-with xs)
      xs)))

(defn new-key [size key]
  (cond 
    (= key -1) (dec size)
    (= key size) 0
    :else key))

(defn new-keys [size keys]
  (map (partial new-key size) keys))

(defn combine-position [[y x]]
  (let [offset [-1 0 1]]
    (->> offset
         (mapcat
           (fn [y']
             (map
               (fn [x']
                 [(+ y y')
                  (+ x x')])
               offset)))
         (remove #{[y x]}))))

(defn count-neighbours [board [y x]]
  "gets the amount of neighbour cells alive for a given cell"
  (let [s (count board)]
    (->> (combine-position [y x])
         (map #(get-in board (new-keys s %)))
         (filter true?)
         (count))))

(defn next-gen [board]
  "calculates board's next generation"
  (let [mapv-indexed (comp vec map-indexed)]
    (mapv-indexed
      (fn [y row]
        (mapv-indexed
          (fn [x cell]
            (will-live? cell (count-neighbours board [y x])))
          row))
      board)))

