#lang racket
(define (foo a b c)
  (cond [(> a b) (+ (* a a) (if (> b c) (* b b) (* c c)))]
        [else    (+ (* b b) (if (> a c) (* a a) (* c c)))]))

(foo 1 2 3)
(foo 7 4 5)
    