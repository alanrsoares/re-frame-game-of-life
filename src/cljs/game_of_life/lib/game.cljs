(ns game-of-life.lib.game)

(defn will-live? [alive? neighbours]
  "tells whether a cell will live or die based on its
   living neighbours"
  (if alive?
    (<= 2 neighbours 3)
    (== 3 neighbours)))

