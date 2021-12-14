#lang racket

;; ZAD 3.

(define (map f xs)
  (if (null? xs)
      null
      (cons (f (car xs))
            (map f (cdr xs)))))

(define (sum xs)
  (if (null? xs)
      0
      (+ (car xs)
         (sum (cdr xs)))))

(define (map-sum f xs)
  (define (iter xs acc)
    (if (null? xs)
        acc
        (iter (cdr xs)
              (+ (f (car xs)) acc))))
  (iter xs 0))

;; Udowodnij, że dla każdej procedury f i listy xs zachodzi (map-sum f xs) ≡ (sum (map f xs)).


#|
Najpierw pokażę przez indukcję, że dla każdej procedury f, listy xs oraz liczby n zachodzi:
    (iter xs n) === (+ n (sum (map f xs)))


Podstawa indukcji (xs == null):
    Weźmy dowolną liczbę n:

    L ===
    (iter xs n) ===
    (iter null n) ===   { z def. iter dla pustej listy }
    n

    R ===
    (+ n (sum (map f xs))) ===
    (+ n (sum (map f null))) ===   { z def. map dla pustej list }
    (+ n (sum null)) ===   { z def. sum dla pustej list }
    (+ n 0) ===   { z tego jak działa + }
    n

    L === R

Krok indukcyjny (xs == (cons head tail)):
    Zakładamy, że dla dowolnego m:
        (iter tail m) === (+ m (sum (map f tail)))

    Weźmy dowolną liczbę n:

    L ===
    (iter (cons head tail) n) ===   {z def. iter dla niepustej list }
    (iter tail (+ (f head) n)) ===   { z zał. ind. dla m === (+ (f head) n) }
    (+ (+ (f head) n) (sum (map f tail))) ===   { z tego jak działa + }
    (+ n (f head) (sum (map f tail)))

    R ===
    (+ n (sum (map f xs))) ===
    (+ n (sum (map f (cons head tail)))) === {z def. map dla niepustej list }
    (+ n (sum (cons (f head) (map f tail)))) === {z def. sum dla niepustej list }
    (+ n (+ (f head) (sum (map f tail)))) === { z tego jak działa + }
    (+ n (f head) (sum (map f tail)))

    L === R

Na mocy zasady indukcji dla każdej procedury f, listy xs oraz liczby n zachodzi:
    (iter xs n) === (+ n (sum (map f xs)))

Więc w szczególności dla n = 0:
    (iter xs 0) === (+ 0 (sum (map f xs)))

    (map-sum xs) === (sum (map f xs))

    Co należało udowodnić
|#