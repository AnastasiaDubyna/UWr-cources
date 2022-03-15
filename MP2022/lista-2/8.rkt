#lang racket
(define (select xs)
  (define (find-min curr-min xs)
    (cond
      [(null? xs) curr-min]
      [(< (car xs) curr-min) (find-min (car xs) (cdr xs))]
      [else (find-min curr-min (cdr xs))]))
  (define (retrieve-min min xs)
    (cond
      [(null? (car xs)) null]
      [(equal? min (car xs)) (cdr xs)]
      [else (cons (car xs) (retrieve-min min (cdr xs)))]))
  (define min-val (find-min (car xs) (cdr xs)))
  (cons min-val (retrieve-min min-val xs)))

;(select (list 4 3 1 2 0 5))

(define (select-sort xs)
  (define selected-list (select xs))
  (cond
    [(null? (cdr xs)) (cons (car xs) null)]
    [else (cons (car selected-list) (select-sort (cdr selected-list)))]))

(select-sort (list 1 5 0 9 11 7 1 4 1 0))


    