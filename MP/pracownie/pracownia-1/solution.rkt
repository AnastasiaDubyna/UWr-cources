#lang racket

(provide cube-root)

(define (cube-root x)
  (define (good-enough? guess)
    (< (abs (- (expt guess 3) x)) 0.001))
  (define (improve guess)
    (/(+(/ x (* guess guess))(* 2 guess)) 3))
  (define (cube-root-iter guess)
    (if (good-enough? guess)
        guess
        (cube-root-iter (improve guess))))
  (cond ((= x 0) 0)
        ((> x 0)(cube-root-iter 1.0))
        ((< x 0)(write "invalid value"))))

#|Dla wartości mniejszych od 0 funkcja zwraca "invalid value"|#
#|Dla wartości 1< np. 27 : 3.0000005410641766 |#
#|Dla wartości 0< i <1 np. 0.5 : 0.7937048480708166 |#
#|Dla x = 0: 0|#