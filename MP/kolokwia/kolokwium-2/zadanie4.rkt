#lang plait

;; Typ drzew prefiksowych

(define-type (PrefixTree 'a)
    (node [exists : Boolean] [children : (Listof ('a * (PrefixTree 'a)))]))

;; Przykładowe drzewo prefiksowe z treści zadania

(define example-tree
  (node #f (list
    (pair 1 (node #t (list
      (pair 2 (node #f (list
        (pair 3 (node #t empty))
        (pair 4 (node #t empty))))))))
    (pair 3 (node #f (list
      (pair 5 (node #t empty))))))))

;; Napisz implementacje procedur poniżej

(lookup : ((Listof 'a) (PrefixTree 'a) -> Boolean))
(define (lookup xs t)
  (type-case (Listof 'a) xs
    [empty (node-exists t)]
    [(cons x xs)
     (foldr (λ (p acc)
              (or acc
                  (and (equal? x (fst p))
                       (lookup xs (snd p)))))
            #f
            (node-children t))]))

(insert : ((Listof 'a) (PrefixTree 'a) -> (PrefixTree 'a)))
(define (insert xs t)
  (local [(define (list-contains (ls : (Listof 'b)) (l : 'b))
            (type-case (Listof 'a) ls
              [empty #f]
              [(cons x xs) (or (equal? x l)
                               (list-contains xs l))]))]
    (type-case (Listof 'a) xs
      [empty (node #t (node-children t))]
      [(cons x xs)
       (node (node-exists t)
             (if (list-contains (map fst (node-children t)) x)
                 (map (λ (p) (if (equal? x (fst p))
                                 (pair (fst p) (insert xs (snd p)))
                                 p))
                      (node-children t))
                 (cons (pair x (insert xs (node #t empty)))
                       (node-children t))))])))

(remove : ((Listof 'a) (PrefixTree 'a) -> (PrefixTree 'a)))
(define (remove xs t)
  (type-case (Listof 'a) xs
    [empty (node #f (node-children t))]
    [(cons x xs) (node (node-exists t)
                       (map (λ (p) (if (equal? (fst p) x)
                                       (pair (fst p) (remove xs (snd p)))
                                       p))
                              (node-children t)))]))
