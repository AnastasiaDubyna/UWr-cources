#lang racket
(require rackunit)

(define (maximum xs)
  (define (helper curr-max xs)
    (cond
      [(null? xs) curr-max]
      [(< curr-max (car xs)) (helper (car xs) (cdr xs))]
      [else (helper curr-max (cdr xs))]))
  (helper -inf.0 xs))


(check-equal? (maximum (list 1 2 3 4)) 4)
(check-equal? (maximum (list 11 2 3 4)) 11)
(check-equal? (maximum (list 1 -2 3 -4)) 3)
(check-equal? (maximum (list)) -inf.0)
