(ns game-of-life.db)

(def board-size 20)

(defn random-fill [] 
  (< 0.8 (rand)))

(defn make-board [size fill-with]
  (let [xs (range size)]
    (mapv
      #(mapv fill-with xs)
      xs)))

(def blank-board
  (make-board board-size (fn [] false)))

(defn randomize []
  (make-board board-size random-fill))

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

(defn will-live? [alive? neighbours]
  "tells whether a cell will live or die based on its
   living neighbours"
  (if alive?
    (<= 2 neighbours 3)
    (= 3 neighbours)))


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

(def default-db
  {:board (randomize)})


