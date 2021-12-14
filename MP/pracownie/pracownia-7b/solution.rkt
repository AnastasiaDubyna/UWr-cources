(define (find-dead-vars e)
  (define (rec expr dict) ; ten dict mógłby być setem/listą, ale już tak zostało XD
    (match expr
      [(const n) (cons expr dict)]
      [(binop op l r) (let* ([pr   (rec r dict)]
                             [r    (car pr)]
                             [dict (cdr pr)]
                             [pl   (rec l dict)]
                             [l    (car pl)]
                             [dict (cdr pl)])
                        (cons (binop op l r) dict))]
      [(var-expr x) (if (dict-has-key? dict x)
                        (cons expr dict)
                        (cons (var-dead x) (dict-set dict x true)))]
      [(let-expr x e1 e2) (let* ([p2    (rec e2 (dict-remove dict x))]
                                 [e2    (car p2)]
                                 [dict2 (dict-remove (cdr p2) x)]
                                 [dict3 (if (dict-has-key? dict x)
                                            (dict-set dict2 x true)
                                            dict2)]
                                 [p1    (rec e1 dict3)]
                                 [e1    (car p1)]
                                 [dict4 (cdr p1)])
                            (cons (let-expr x e1 e2) dict4))]))
  (car (rec e null)))