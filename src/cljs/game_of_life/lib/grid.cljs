(ns game-of-life.lib.grid
  (:require [game-of-life.lib.game :refer [will-live?]]))

(defn make-grid [size fill-with]
  (let [xs (range size)]
    (mapv
      #(mapv fill-with xs)
      xs)))



(defn new-key [key size]
  (cond 
    (= key -1) (dec size)
    (= key size) 0
    :else key))

(defn count-neighbours [board [y x]]
  "gets the amount of neighbour cells alive for a given cell"
  (let [offset [-1 0 1]
        size (count board)]
    (count
      (filter true?
        (mapcat
          (fn [y']
            (map
              (fn [x']
                (when-not
                  (every? zero? [y' x'])
                  (get-in board
                    (map #(new-key % size) [(+ y y') (+ x x')]))))
              offset))
          offset)))))

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

