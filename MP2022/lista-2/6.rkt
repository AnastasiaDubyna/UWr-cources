#lang racket

(define (suffixes xs)
  (if (null? xs)
      (list null)
      (cons xs (suffixes (cdr xs)))))

(suffixes (list 1 2 3 4))
(suffixes (list 1 2 3 4 5))
