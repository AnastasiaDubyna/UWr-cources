#lang racket
(provide (contract-out
            [with-labels with-labels/c]
            [foldr-map foldr-map/c]
            [pair-from pair-from/c]))
(provide with-labels/c foldr-map/c pair-from/c )

(define with-labels/c
  (parametric->/c [a b] (-> (-> a b) (listof a) (listof (*list/c b a)))))

(define foldr-map/c
  (parametric->/c [a b c] (-> (-> a b (cons/c c b)) b (listof a) (cons/c (listof c) b))))

(define pair-from/c
  (parametric->/c [a b c] (-> (-> a b) (-> a c) (-> a (cons/c b c)))))

(define (with-labels f xs)
  with-labels/c
  (if (null? xs)
      null
      (cons (list (f (car xs)) (car xs)) (with-labels f (cdr xs)))))

(define (test-1)
  (with-labels number->string (list 1 2 3)))


(define (foldr-map f a xs)
  foldr-map/c
  (define (it a xs ys)
    (if (null? xs)
        (cons ys a)
        (let [(p (f (car xs) a))]
          (it (cdr p)
              (cdr xs)
              (cons (car p) ys)))))
  (it a (reverse xs) null))

(define (test-2)
  (foldr-map (lambda (x a) (cons a (+ a x))) 0 '(1 2 3)))


(define (pair-from f g)
  pair-from/c
  (lambda (x)
    (cons (f x) (g x))))

(define (test-3)
  ((pair-from (lambda (x) (+ x 1)) (lambda (x) (* x 2)))2))
  


