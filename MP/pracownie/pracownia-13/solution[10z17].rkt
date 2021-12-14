#lang plait

(define-type-alias Subst (Listof (Symbol * S-Exp)))
(define-type-alias Eq (S-Exp * S-Exp))
(define-type-alias Eqs (Listof Eq))

(define (eq-l eq)
  (fst eq))
(define (eq-r eq)
  (snd eq))

(define (occurs id eq)
  (cond
    [(s-exp-list? eq) (member id (s-exp->list eq))]
    [(and (s-exp-symbol? eq) (equal? eq id)) #t]
    [else #f]))

(define (substitute-var id val eqs)
  (cond
    [(empty? eqs) empty]
    [(equal? (snd (first eqs)) id)
     (cons (pair (fst (first eqs)) val) (substitute-var id val (rest eqs)))]
    [else (cons (first eqs) (substitute-var id val (rest eqs)))]))

(define (append-pair eqs p)
  (append eqs (list p)))

(define (find-var var ls)
  (cond
    [(empty? ls) (none)]
    [(equal? (fst (first ls)) var) (some (snd (first ls)))]
    [else (find-var var (rest ls))]))

(define (is-found? var ls)
  (cond
    [(empty? ls) #f]
    [(equal? (fst (first ls)) var) #t]
    [else (is-found? var (rest ls))]))
      

(define (is-there-unsolved? wait-ls res-ls)
  (type-case Eqs wait-ls
    [empty #f]
    [(cons eq eqs)
     (let* ([eql (fst eq)]
            [eqr (snd eq)]
            [res-val (find-var (s-exp->symbol eql) res-ls)])
       (cond
         [(and (s-exp-symbol? eql) (s-exp-symbol? eqr)) #t]
         [(none? res-val) (is-there-unsolved? eqs res-ls)]
         [(not (equal? (some-v res-val) eqr)) #t]
         [else (is-there-unsolved? eqs res-ls)]))]))

(define (concat-lists ls1 ls2)
  (if (empty? ls1)
      ls2
      (cons (first ls1) (concat-lists (rest ls1) ls2))))

(define (convert-to-symb wait-ls)
  (map (lambda (p) (pair (s-exp->symbol (fst p)) (snd p))) wait-ls))

(define (unify [eqs-main : Eqs]) : (Optionof Subst)
  (local
    [(define (iter eqs result-list waiting-list)
       (type-case Eqs eqs
         [empty (if (is-there-unsolved? waiting-list result-list)
                    (none)
                    (some (concat-lists result-list (convert-to-symb waiting-list))))]
         
         [(cons eq eqs1)
          (let ([eql (fst eq)]
                [eqr (snd eq)])
            (cond
              [(s-exp-symbol? eql)
               (cond
                 [(s-exp-list? eqr)
                  (if (occurs eql eqr)
                      (none)
                      (iter eqs1
                            (cons (pair (s-exp->symbol eql) eqr) result-list)
                            (substitute-var eql eqr waiting-list)))]
                 [(or (s-exp-number? eqr)
                      (s-exp-boolean? eqr))
                  (let ([eql-val (find-var (s-exp->symbol eql) result-list)])
                    (cond
                      [(none? eql-val) (iter eqs1
                                              (cons (pair (s-exp->symbol eql) eqr) result-list)
                                              (substitute-var eql eqr waiting-list))]
                      [(equal? (some-v eql-val) eqr) (iter eqs1
                                                           result-list
                                                           (substitute-var eql eqr waiting-list))]
                      [else (none)]))]
                 [(s-exp-symbol? eqr)
                  (let ([eqr-val (find-var (s-exp->symbol eqr) result-list)])
                    (cond
                      [(occurs eql eqr) (none)]
                      [(is-found? (s-exp->symbol eql) result-list) (none)]
                      [(none? eqr-val) (iter eqs1 result-list (cons eq waiting-list))]
                      [else (iter eqs1
                                  (cons (pair (s-exp->symbol eql) (some-v eqr-val)) result-list)
                                  (substitute-var eql (some-v eqr-val) waiting-list))]))])]

              ; usunięcie
              [(and (s-exp-number? eql)
                    (s-exp-number? eqr)
                    (equal? eql eqr))
               (iter eqs1 result-list waiting-list)]
              [(and (s-exp-boolean? eql)
                    (s-exp-boolean? eqr)
                    (equal? eql eqr))
               (iter eqs1 result-list waiting-list)]
              [(and (s-exp-list? eql)
                    (s-exp-list? eqr)
                    (equal? eql eqr))
               (iter eqs1 result-list waiting-list)]
              
              ; dekompozycja
              [(and (s-exp-list? eql)
                    (s-exp-list? eqr))     
               (iter (cons
                      (pair (second (s-exp->list eql)) (second (s-exp->list eqr)))
                      (cons (pair (first (s-exp->list eql)) (first (s-exp->list eqr))) eqs1)) result-list waiting-list)]
              
              ; zamiana
              [(s-exp-symbol? eqr)
               (iter (cons (pair eqr eql) eqs1) result-list waiting-list)]
              ; konflikt
              
              [else (none)]))]))]
    (iter eqs-main empty empty)))
;(iter eqs1 (cons (values (s-exp->symbol eql) eqr) helper-list))



(unify (list (pair `x `y)));<--- uważaj, pętla
(unify (list (pair `x `y) (pair `z `4))) ;pętla

(unify (list (pair `x `1) (pair `x `x)))

(unify (list (pair `x `1)))
;(some (list (values 'x ‘1)))
(unify (list (pair `3 `x) (pair `y `#t) (pair `(z q) `(x y)) (pair `3 `z)))
(unify (list (pair `x `1) (pair `y `#t) (pair `(z q) `(x y)) (pair `(1 2) `(1 2)) (pair `p `5)))
(unify (list (pair `(z q) `(x y)) (pair `x `1) (pair `y `#t)))
;(some (list (values 'q ‘#t ) (values 'z ‘1) (values 'y ‘#t) (values 'x ‘1)))
(unify (list (pair `x `1) (pair `y `#t) (pair `(z z) `(x y))))
;(none)

(define (test1) (unify (list (pair `x `1))))
(define (test2) (unify (list (pair `x `1) (pair `y `#t) (pair `(z q) `(x y)))))
(define (test3) (unify (list (pair `x `1) (pair `y `#t) (pair `(z z) `(x y)))))
(define (test4) (unify (list (pair `x `x) (pair `x `y) (pair `y `5))))
(define (test5) (unify (list (pair `x `y) (pair `y `x))))
(define (test6) (unify (list (pair `y `(y 1)))))
(define (test7) (unify (list (pair `(x y) `(a b)))))
(define (test9) (unify (list (pair `5 `x))))
(define (test10) (unify (list (pair `1 `1))))
(define (test11) (unify (list (pair `#t `#f))))
(define (test12) (unify (list (pair `1 `3))))
(define (test13) (unify (list (pair `(1 2) `(2 1)))))
(define (test14) (unify (list (pair `(1 2) `(1 2)))))
(define (test15) (unify (list (pair `x `(1 2 3)))))

