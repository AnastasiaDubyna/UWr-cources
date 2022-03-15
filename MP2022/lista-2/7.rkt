#lang racket

(require rackunit)

(define (sorted? xs)
  (cond
    [(null? (cdr xs)) #t]
    [(<= (car xs) (cadr xs)) (sorted? (cdr xs))]
    [else #f]))

(check-true (sorted? (list 1 2 3 4 5)))
(check-true (sorted? (list 2 4 7 8)))
(check-true (sorted? (list 1 4 4 4 8)))
(check-false (sorted? (list 5 1 3 6 7)))
(check-false (sorted? (list 9 8 7 6)))