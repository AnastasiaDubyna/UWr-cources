#lang plait

; wyrażenia

(define-type ArithExpr
  (const [val : Number])
  (binop [op : Symbol] [l : ArithExpr] [r : ArithExpr])
  (var-expr [id : Symbol])
  (let-expr [id : Symbol] [e1 : ArithExpr] [e2 : ArithExpr])
  (if-expr [eb : ArithExpr] [et : ArithExpr] [ef : ArithExpr]))

(define-type Type
  (number-type)
  (boolean-type))

(define (typecheck-aux e t-env) : (Optionof Type)
  (type-case ArithExpr e
    [(const e) (some (number-type))]
    [(binop op l r)
     (let ([lt (typecheck-aux l t-env)]
           [rt (typecheck-aux r t-env)])
       (cond
         [(and (equal? (some (number-type))lt)
               (equal? (some (number-type)) rt))
          (cond
            [(member op '(+ * / -)) (some (number-type))]
            [(member op '(< > =)) (some (boolean-type))]
            [else (none)])]
         [(and (equal? (some (boolean-type)) lt)
               (equal? (some (boolean-type)) rt)
               (member op '(and or)))
          (some (boolean-type))]          
         [else (none)]))]
    [(var-expr id) (t-env-lookup id t-env)]
    [(let-expr id e1 e2)
     (let* ([e1t (typecheck-aux e1 t-env)]
            [e2t (typecheck-aux e2 (t-env-add id e1t t-env))])
       (if (equal? e1t (none))
           (none)
           e2t))]
    [(if-expr eb et ef)
     (let ([ebt (typecheck-aux eb t-env)]
           [ett (typecheck-aux et t-env)]
           [eft (typecheck-aux ef t-env)])
       (cond
         [(and (equal? (some (boolean-type)) ebt)
               (equal? ett eft))
          ett]
         [else (none)]))]))

(define (typecheck e) : (Optionof Type)
  (typecheck-aux e t-env-empty))
          
     

(define (binary-operator? op)
  (member op '(+ - * / = < > and or)))

(define (parse q)
  (cond
    [(s-exp-number? q) (const (s-exp->number q))]
    [(s-exp-symbol? q) (var-expr (s-exp->symbol q))]
    [(s-exp-list? q)
     (let ([ql (s-exp->list q)])
       (cond
         [(and (= (length ql) 3)
               (equal? (first ql) `let))
          (let ([ll (s-exp->list (second ql))])
            (let-expr (s-exp->symbol (first ll))
                      (parse (second ll))
                      (parse (third ql))))]
         [(and (= (length ql) 4)
               (equal? (first ql) `if))
          (if-expr (parse (second ql))
                   (parse (third ql))
                   (parse (fourth ql)))]
         [(and (= (length ql) 3)
               (binary-operator? (s-exp->symbol (first ql))))
          (binop (s-exp->symbol (first ql))
                 (parse (second ql))
                 (parse (third ql)))]))]))

; środowiska
(define-type-alias (T-Env 'a) (Listof (Symbol * 'a)))

(define t-env-empty empty)
(define (t-env-add x t t-env)
  (cons (pair x t) t-env))

(define (t-env-lookup x t-env)
  (type-case (T-Env (Optionof Type)) t-env
    [empty (none)]
    [(cons p ps)
     (if (eq? (fst p) x)
         (snd p)
         (t-env-lookup x (rest t-env)))]))
  

(define-type-alias (Env 'a) (Listof (Symbol * 'a)))

(env-empty : (Env 'a))
(define env-empty empty)

(define (env-add x v env) (cons (pair x v) env))

(define (env-lookup x env)
  (type-case (Env 'a) env
    [empty (error 'env-lookup (string-append "Unknown identifier "
                                             (to-string x)))]
    [(cons p ps)
     (if (eq? (fst p) x)
         (snd p)
         (env-lookup x (rest env)))]))

; ewaluacja

(define-type Value
  (number-val [v : Number])
  (boolean-val [v : Boolean]))

(define (arith-op f)
  (lambda (x y)
    (number-val (f (number-val-v x) (number-val-v y)))))

(define (comp-op f)
  (lambda (x y)
    (boolean-val (f (number-val-v x) (number-val-v y)))))

(define (bool-op f)
  (lambda (x y)
    (boolean-val (f (boolean-val-v x) (boolean-val-v y)))))

(define (op->proc op)
  (case op
    ['+ (arith-op +)]
    ['- (arith-op -)]
    ['* (arith-op *)]
    ['/ (arith-op /)]
    ['= (comp-op =)]
    ['< (comp-op <)]
    ['and (bool-op (lambda (x y) (and x y)))]
    ['or (bool-op (lambda (x y) (or x y)))]))

(define (eval-env e env)
  (type-case ArithExpr e
    [(const n) (number-val n)]
    [(binop op l r) ((op->proc op) (eval-env l env) (eval-env r env))]
    [(var-expr x) (env-lookup x env)]
    [(let-expr x e1 e2) (eval-env e2 (env-add x (eval-env e1 env) env))]
    [(if-expr eb et ef)
     (if (boolean-val-v (eval-env eb env))
         (eval-env et env)
         (eval-env ef env))]))

(define (eval e) (eval-env e env-empty))

#|

(typecheck-aux (parse `(let [x 5] (if (= x 5) 1 0))) t-env-empty)

(typecheck-aux (parse `(let [x 5] (if (+ x 5) 1 0))) t-env-empty)
(typecheck-aux (parse `(let [x 5] (if (= x 5) (< 2 3) 0))) t-env-empty)
(typecheck-aux (parse `(let [x (< 2 3)] (if (= x 5) 1 0))) t-env-empty)
(typecheck-aux (parse `(if (let [x 5] (if (= x 5) 1 0)) 1 0)) t-env-empty)

(typecheck-aux (parse `(and (< 2 3) (< 4 5))) t-env-empty)
(typecheck-aux (parse `(< 2 3)) t-env-empty) |#

(typecheck (parse `(if (let [x 5] (if (= x 5) (< 2 3) (> 3 5))) 1 0)))
(typecheck (parse `(let [x 5] (if (= x 5) (< 2 3) (> 3 5)))))

(typecheck (parse `(let [x 5] (if (= x 5) (< (< x 4) 3) (> 3 x)))))
(typecheck (parse `(let [x 5] (if (= x 6) (< 4 3) (> 3 x)))))
(typecheck (parse `(let [x 5] (if (+ x 6) (< 4 3) (> 3 x)))))
(typecheck (parse `(let [x (< 3 4)] (if (= x 6) (< 4 3) (> 3 x)))))
(typecheck (parse `(let [x (< 3 4)] (if x (< 4 3) (> 3 5)))))
(typecheck (parse `(let [x (< 3 4)] (if x x (> 3 5)))))
(typecheck (parse `(if (< 5 (let [x (let [y 6] (< 2 y))] (if x 1 0))) 1 2)))
(typecheck (parse `(if (and (< 5 (let [x (let [y 6] (< 2 y))] (if x 1 0)))
                            (> 3 2))
                       1
                       2)))

(typecheck (parse `(if (or (< 2 3) (> 5 6))
                       (+ 2 3)
                       (- 4 5))))

(typecheck (parse `(if (or (< 2 3) (> 5 6))
                       (+ 2 3)
                       (> 4 5))))

(typecheck (parse `(if (= (< 2 3) (> 5 6))
                       (< 2 3)
                       (> 4 5))))


(typecheck (parse `(let [x (< (< 2 3) 5)] (if (= 5 5) 1 2))))










;(eval (parse `(let [x 5] (if (= x 5) 1 0))))