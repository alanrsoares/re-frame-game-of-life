(ns game-of-life.components.grid-controls)

(defn grid-controls-component [profiler actions]
  (let [{:keys [ticks 
                frame-id]} profiler
        {:keys [reset
                tick
                randomize]} actions]
    [:div.grid-controls
     [:div.btn-group {:role "group"
                      :style {:margin-botton "20px"}}
      [:button.btn.btn-danger {:on-click reset}
       "RESET"]
      [:button.btn.btn-success {:on-click randomize}
       "RANDOMIZE"]
      [:button.btn.btn-default {:on-click tick}
       [:i.fa.fa-fast-forward.fa-lg]
       " "
       "NEXT"]]]))
     
    
    
    
