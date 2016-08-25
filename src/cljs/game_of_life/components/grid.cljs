(ns game-of-life.components.grid)

(defn cn [conditionals]
  "combine conditional css classes"
  (apply str 
    (interpose " " 
      (map 
        (fn [[class condition?]] (when condition? class)) 
        conditionals))))

(defn row [row-key row]
  [:tr {:key row-key}
   (map-indexed
     (fn [key alive?]
       [:td
        {:key key
         :class (cn {"tile" true 
                     "tile--alive" alive?})}])
     row)])

(defn grid-component [board]
  [:table.grid
   [:tbody
    (map-indexed row board)]])

