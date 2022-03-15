#lang racket

(require rackunit)

(define (fib-rec n)
  (cond
    [(= n 0) 0]
    [(= n 1) 1]
    [else (+ (fib-rec (- n 1)) (fib-rec (- n 2)))]))

(define (fib-iter n)
  (define (iter n val-n-1 val-n-2)
    (cond
      [(= n 0) val-n-2]
      [(= n 1) val-n-1]
      [else (iter (- n 1) (+ val-n-1 val-n-2) val-n-1)]))
  (iter n 1 0))

;(time (fib-rec 10))
;(time (fib-iter 10))

;(time (fib-rec 15))
;(time (fib-iter 15))

(time (fib-rec 35))
(time (fib-iter 35))
    


; n = 0 -> val-n-2
; n = 1 -> val-n-1
; n = 2 -> (iter (- n 1) (+ val-n-1 val-n-2) val-n-1)

; n = 3 -> (iter 3 1 0) -> (iter 2 1 1) -> (iter 1 2 1) -> 2
; n = 5 -> (iter 5 1 0) -> (iter 4 1 1) -> (iter 3 2 1)
    ;-> (iter 2 3 2) -> (iter 1 5 3)


;n = 3
;fib-rec
;(fib-rec 3) ->
;(+ (fib-rec 2) (fib-rec 1)) ->
;(+ (+ (fib-rec 1) (fib-rec 0)) (fib-rec 1)) ->
;(+ (+ 1 0) 1) ->
;(+ 1 1) ->
;2

;fib-iter
;(fib-iter 3) ->
;(iter 3 1 0) ->
;(iter 2 1 1) ->
;(iter 1 2 1) ->
;2

    
    