(ns pong.core)

(def canvas (. js/document getElementById "tutorial"))

(def ctx (. canvas getContext "2d"))

(defn draw-background
  []
  (aset ctx "fillStyle" "rgb(0, 0, 0)")
  (. ctx fillRect 0, 0, (aget canvas "height"), (aget canvas "width")))

(defn clear-background
  []
  (. ctx clearRect 0, 0, (aget canvas "height"), (aget canvas "width")))

(def x-coord (atom 100))
(def y-coord (atom 100))

(def direction (atom [-0.5 -0.4]))

(defn negate
  [x]
  (if (< x 0)
    (Math/abs x)
    (- x)))

(defn draw-ball
  []
  (if (or (<= @x-coord 0) (>= @x-coord (aget canvas "width")))
    (swap! direction update-in [0] negate))
  (if (or (<= @y-coord 0) (>= @y-coord (aget canvas "height")))
    (swap! direction update-in [1] negate))
  (do
    (swap! x-coord #(+ % (* 10 (first @direction))))
    (swap! y-coord #(+ % (* 10 (second @direction)))))
  (clear-background)
  (draw-background)
  (. ctx beginPath)
  (. ctx arc @x-coord, @y-coord, 15, 0, (* 2 Math/PI))
  (aset ctx "fillStyle" "rgb(200, 0, 0)")
  (. ctx fill)
  (. ctx stroke))

(defn init
  []
  (draw-background)
  (. js/window setInterval #(draw-ball) 50))

(init)

(comment
  ;; code for players
  (defn move
        [e]
        (case (aget e "keyCode")
              37 (swap! x-coord #(- % 5))
              38 (swap! y-coord #(- % 5))
              39 (swap! x-coord #(+ % 5))
              40 (swap! y-coord #(+ % 5)))
        (draw-ball)))