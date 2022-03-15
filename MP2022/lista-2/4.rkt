#lang racket

(require rackunit)

(define (elem? el ls)
  (cond
    [(null? (cdr ls)) (equal? el (car ls))]
    [(equal? el (car ls)) #t]
    [else (elem? el (cdr ls))]))

(check-true (elem? 2 (list 1 2 3 4)))
(check-false (elem? 2 (list 1 5 3 4)))
(check-true (elem? 2 (list 1 5 3 2)))
(check-true (elem? 2 (list 2 1 3 )))

      
  