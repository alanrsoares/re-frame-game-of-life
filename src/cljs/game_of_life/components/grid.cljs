(ns game-of-life.components.grid)

(defn cn [conditionals]
  "combine conditional css classes"
  (apply str 
    (interpose " " 
      (map 
        (fn [[class condition?]] (when condition? class)) 
        conditionals))))

(defn row [on-tile-click row-key cells]
  [:tr {:key row-key}
   (map-indexed
     (fn [key alive?]
       [:td
        {:key key
         :class (cn {"tile" true 
                     "tile--alive" alive?})
         :on-click #(on-tile-click [row-key key])}])
     cells)])

(defn grid-component [board actions]
  (let [{:keys [toggle-cell]} actions]
    [:table.grid
     [:tbody
      (map-indexed 
        (partial row toggle-cell) 
        board)]]))

