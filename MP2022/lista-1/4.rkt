#lang racket
(let ([x 3])
  (+ x y))
; [x 3] wiąży x w (+ x y)
; y jest wolny

(let ([x 1]
      [y (+ x 2)])
  (+ x y))
; [x 1] wiąży x w (+ x y)
; [y (+ x 2)] wiąży y w (+ x y)
; x w (+ x 2) jest wolny

(let ([x 1])
  (let ([y (+ x 2)])
    (* x y)))
; [x 1] wiąży x w (+ x 2) oraz (* x y)
; y (+ x 2) wiąży y w (* x y)

(define (f x y)
  (* x y z))
;wiadomo. z: undefined;

(define (f x)
  (define (g y z)
    (* x y z))
  (f x x x))

;f: arity mismatch;
 ;the expected number of arguments does not match the given number
  ;expected: 1
  ;given: 3