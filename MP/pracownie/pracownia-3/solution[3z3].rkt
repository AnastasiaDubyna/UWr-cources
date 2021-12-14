#lang racket

(provide cons car cdr null null? pair?)

(define (cons a b)
  (lambda (x) (cond [(= 0 x) a]
                    [(= 1 x) b])))

(define (car p)
  (p 0))

(define (cdr p)
  (p 1))

(define null '())

(define (null? p)
  (if (eq? null p)
      #t
      #f))

(define (pair? p)
  (if (eq? null p)
      #f
      #t))

(define (list-sum xs)
  (if (null? xs)
      0
      (+ (car xs)
         (list-sum (cdr xs)))))

;Testy
;> (define p (cons 5 6))
;> (car p)
;5
;> (cdr p)
;6
;> (pair? p)
;#t
;> (pair? null)
;#f
;> (define n null)
;> (pair? n)
;#f
;> (null? n)
;#t
;> (null? p)
;#f
;> (list-sum (cons 2 (cons 3 null)))
;5
;> (list-sum (cons -2 (cons 4 (cons 8 (cons 10 null)))))
;20
                    