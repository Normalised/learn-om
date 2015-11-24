(ns hello-world.core
  (:require
    [goog.dom :as gdom]
    [om.next :as om :refer-macros [defui]]
    [om.dom :as dom]
    [sablono.core :as sab :include-macros true])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]))

(enable-console-print!)

(println "Figwheel. Om-Next. DevCards")

;; An om-next component interface
(defui HelloWorld
  Object
  (render [this]
    (dom/div nil (get (om/props this) :title))))

;; An om-next component interface
(defui SecondComponent
  Object
  (render [this] (dom/div nil "Second Component")))

;; To create an Om component we must first produce a factory from the component class. #
;; The function returned by om.next/factory has the same signature as pure React components
;; with the exception that the first argument is usually an immutable data structure.

(def hello (om/factory HelloWorld))
(def c2 (om/factory SecondComponent))

;; Some plain devcards
(defcard
  "# Om Next Testing
   Testing Om Next in DevCards.")

(defcard first-card
  (hello {:title "Passed in property"}))

(defcard second-card (c2))

(defn main []
  ;; conditionally start the app based on wether the #main-app-area
  ;; node is on the page
  (if-let [node (gdom/getElement "main-app-area")]
    (js/ReactDOM.render (sab/html [:div "This is working"]) node)))

(main)
