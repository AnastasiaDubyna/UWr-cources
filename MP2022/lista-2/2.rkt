#lang racket


(define-struct matrix (a b c d) #:transparent)

(define (matrix-mult m n)
  (make-matrix (+ (* (matrix-a m) (matrix-a n)) (* (matrix-b m) (matrix-c n)))
               (+ (* (matrix-a m) (matrix-b n)) (* (matrix-b m) (matrix-d n)))
               (+ (* (matrix-c m) (matrix-a n)) (* (matrix-d m) (matrix-c n)))
               (+ (* (matrix-c m) (matrix-b n)) (* (matrix-d m) (matrix-d n)))))

(define (matrix-expt m k)
  (define (iter k matrix-acc)
    (cond
      [(< k 0) (raise (error "Error: only natural exponents"))]
      [(= k 0) matrix-id]
      [(= k 1) matrix-acc]
      [else (iter (- k 1) (matrix-mult matrix-acc m))]))
  (iter k m))

(define matrix-m (make-matrix 1 2 3 4))
(define matrix-n (make-matrix 5 6 7 8))
(define matrix-id (make-matrix 1 0 0 1))

(matrix-mult matrix-m matrix-n)
(matrix-mult matrix-m matrix-id)

;(matrix-expt matrix-m -1)
(matrix-expt matrix-m 0)
(matrix-expt matrix-m 5)


(define (fib-matrix n)
  (matrix-b (matrix-expt (make-matrix 1 1 1 0) n)))

(fib-matrix 10)
(fib-matrix 13)

  
