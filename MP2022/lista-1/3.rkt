#lang racket

;(* (+ 2 2)   5) -> 20
;(* (+ 2 2) (5)) -> błąd w (5)
;Racket oczekuje na procedurę lub formę specjalną, którą można zaaplikować do argumentów
;(*(+(2 2) 5)) -> błąd w (2 2)
;application: not a procedure; expected a procedure that can be applied to arguments
;(*(+ 2
;   2)5) -> 20
;(5 * 4) -> błąd. racket ma prefiksową składnie.
;Na pierwszym miejscu musi być procedura lub forma specjalna
;(5 * (2 + 2)) -> same błąd
;((+ 2 3)) -> (5) -> błąd
;+ -> #<procedure:+>
;(define + (* 2 3))
; + -> 6
;(* 2 +) -> 12, bo + ma wartość 6
;(define (five) 5) -> zdefiniowaliśmy 0-argumentową procedurę
;(define four 4) -> zdefiniowaliśmy zmienną four
;(five) -> 5
;four -> 4
;five -> #<procedure:five>
;(four) -> application: not a procedure;