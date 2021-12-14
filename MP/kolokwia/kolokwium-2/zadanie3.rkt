#lang racket

(define first-contract
  (parametric->/c [a b] (-> (-> a b) a b)))

(define second-contract
  (parametric->/c [a b c] (-> (-> a b c)
                              (-> (cons/c a b) c))))

(define third-contract 
  (parametric->/c [a b] (-> (listof (-> a b))
                            (listof a)
                            (listof b))))

(define fourth-contract
  (parametric->/c [a b] (-> (-> b (or/c false/c (cons/c a b)))
                            b
                            (listof a))))

(define fifth-contract
  (parametric->/c [a] (-> (-> a boolean?)
                          (listof a)
                          (cons/c (listof a) (listof a)))))

;; Napisz poniżej implementacje procedur spełniające powyższe kontrakty

(define/contract (first a->b a)
  first-contract
  (a->b a))

(define/contract (second a-b->c a)
  second-contract
  (lambda (ab) (a-b->c (car ab) (cdr ab))))

; iloczyn kartezjański - efekt działania wszystkich funkcji na wszystkich wartościach
(define/contract (third list_fn list_a)
  third-contract
  (append-map (λ (a) (map (λ (a->b) (a->b a))
                          list_fn))
              list_a))

; funkcja ta wykorzystuje fn i b do budowania listy
; jeżeli (fn b) zwróci fałsz, to budowanie list jest zakańczane
; jeżeli zwróci a i b, to a jest traktowane jako głowa, zaś z b jest budowana reszta listy
(define/contract (fourth fn b)
  fourth-contract
  (define res (fn b))
  (if res
      (cons (car res) (fourth fn (cdr res)))
      null))

; przykład użycia funkcji fourth do obliczenia ciągu liczb (skonwertowanych na stringi)
; dla podanej liczby w problemie collatza
(fourth (λ(x) (cond [(= x 1) false]
                    [(even? x) (let ([res (/ x 2)]) (cons (number->string res) res ))]
                    [else (let ([res (+ (* 3 x) 1)]) (cons (number->string res) res))]))
       6)

; partition - podział listy na dwie listy
; pierwsza zawiera elementy spełdniające predykat
; druga niespełniające
(define/contract (fifth pred as)
  fifth-contract
  (cons (filter pred as)
        (filter (λ(x) (not (pred x))) as)))

