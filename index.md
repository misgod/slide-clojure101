---
title       : Clojure 101
subtitle    : Lisp + Functional + Dynamic = Fun
author      : SzuHsien Lee
job         : A software engineer on the street
license     : by-nc-sa
framework   : io2012        # {io2012, html5slides, shower, dzslides, ...}
highlighter : highlight.js  # {highlight.js, prettify, highlight}
hitheme     : solarized_light     # 
widgets     : []  # {mathjax, quiz, bootstrap}       
mode        : selfcontained # {standalone, draft}
logo        : icon.png
---
<style>
.dark q {
  color: white;
}

g {
  color: green;
}

r {
  color: red;
}



</style>



## Outline

* Overview

* Clojure Fundamentals

* Functional Programming in Clojure

* Code Example

* Q & A


--- bg:#CCC
### Alan J. Perlis (Epigrams on Programming)

<q>"A language that doesn't affect the way you think about programming, is not worth knowing. " </q>

---

|Year | Language   |Year | Language                             
|-----|------------|-----|----------                  
|1957 |FORTRAN     |1993 | Ruby
|1958 |LISP        |1993 | Lua
|1959 |COBOL       |1995 | JavaScript
|1964 |BASIC       |1995 | Java
|1970 |Forth       |1995 | PHP
|1972 |C           |2000 | ActionScript
|1972 |Smalltalk   |2001 | C#  
|1983 |C++         |2001 | Visual Basic .NET
|1984 |Common Lisp |2002 | F#
|1986 |Objective-C |2003 | Groovy    
|1986 |Erlang      |2003 | Scala
|1987 |Perl        |2007 | <r>Clojure</r>
|1990 |Haskell     |2009 | Go
|1991 |Python      |2011 | Dart
|1991 |Visual Basic|2012 | Rust


--- .segue .dark

## Overview

---
## Clojure Status

* started in 2007
* Original author: Rich Hickey
* Latest Stable Release: 1.6.0 (April, 2014)

---
## What is Clojure?

> * Lisp dialect (Code as data)
> * Dynamic 
> * Functional (immutability)
> * General-purpose 
> * Compiled
> * Hosted on JVM, also CLR and JavaScript
> * Open Source under Eclipse Public License

---
## Why use Clojure

> * More fun & less code
> * Focus on problem
> * Supporting concurrency by default
> * Interactive programming
> * Robust, practical & high-performance
> * Java ecosystem

---
## Development environment

* REPL

* Light Table (newbie)

* Eclipse with Counterclockwise

* Intellij IDEA with La Clojure

* Emacs with emacs-live (recommend)

--- .segue .dark

## Clojure Fundamentals

---
## Hello World

```clojure 
(defn hello
  "Say hello world!"
  [] (println "Hello World!"))

```

```clojure
(hello)
;Hello World!
;=> nil
```

---
## Hello World with parameter

```clojure
(defn hello
  "Say hello to someone!"
  ([] (println "Hello World"))
  ([someone] (println "Hello" someone)))

```

```clojure
(hello)
;Hello World!
;=> nil

(hello "Sam")
;Hello Sam
;=> nil

```


---
## Actomic Data Types

* Boolean:  <g> true false </g>

* Number:  <g> 1234567890876544321, 1e65, 2r1010, 1.23, 25/7 </g>

* String:  <g> "hello" </g>

* Character:  <g> \c \b \a \space \newline \tab </g>

* Null:  <g> nil </g>

* Symbol:  <g> def read zero? +  </g>

* Keyword:  <g> :fred :ethel </g>

* Regex pattern:  <g> #"...pattern..." </g>

---
## Some Demo

```clojure
(+ 1 2) ;=> 3
(* 1 3) ;=> 3
(- 3 2) ;=> 1 
(/ 8 2) ;=> 4
(+ 1 2 3) ;=> 6
(/ 3 5) ;=> 3/5
(* 5 (/ 3 5)) ;=> 3N

(not true) ;=> false
(= "aa" (str "a" "a") ) ;=> true
(= 1 1) ;=> true

(str "hello" "-" "world" "!") ;=> hello-world!

(identical? "aa" (str "a" "a")) ;=> false
(nil? nil) ;=> true 

(class "hello") ;=> java.lang.String
(class 1) ;=> java.lang.Long
```

--- bg:#CCC
### Alan J. Perlis (Epigrams on Programming)

<q>"It is better to have 100 functions operate on one data structure than to have 10 functions operate on 10 data structures." </q>

---
## Clojure Data Structures

* List: <g> (1 2 3 4 5), ("abc" "bac" "aaa"), (list 1 2 3) </g>

* Vector: <g> [1 2 3 4 5], [Mon Tue Wen Thu] </g>

* Map: <g> {:a 1, :b 2, :c 3}, {1 "one" 2 "two"} </g>

* Set: <g> #{"foo" "bar" "baz"} </g>

---
## Some Code 

```clojure
(class [1 2 3]) ;=> clojure.lang.PersistentVector
(class '(1 2 3)) ;=> clojure.lang.PersistentList
(class #{1 2 3}) ;=> clojure.lang.PersistentHashSet
(class {:k1 1 :k2 2}) ;=> clojure.lang.PersistentArrayMap

(str 2 3) ;=> 23
'(str 2 3) ;=> (str 2 3)
(eval '(str 2 3)) ;=>23

(coll? [1 2 3]) ;=> true
(coll? '(1 2 3)) ;=> true
(coll? #{1 2 3}) ;=> true
(coll? {1 2 3 4}) ;=> true

;;Seqs are an interface for logical lists, which can be lazy.
(seq? [1 2 3]) ;=> false
(seq? '(1 2 3)) ;=> true
(seq? #{1 2 3}) ;=> false
(seq? {1 2 3 4}) ;=> false

```

---
## Some Code (cont.)

```clojure
(range 5) ;=> (0 1 2 3 4)
(range) ;=> (0 1 2 3 4 5 ...∞) ;lazy
(take 5 (range)) ;=> (0 1 2 3 4)
(nth (range) 3) ;=> 3

;;Use cons to add an item to the beginning of a list or vector
(cons 4 [1 2 3]) ; => (4 1 2 3)
(cons 4 '(1 2 3)) ; => (4 1 2 3)
;;Add to the beginning of a list, or the end of a vector
(conj [1 2 3] 4) ; => [1 2 3 4]
(conj '(1 2 3) 4) ; => (4 1 2 3)

(concat [1 2] '(3 4)) ;=> (1 2 3 4)

(first '(1 2 3)) ;=> 1
(rest '(1 2 3))  ;=> (2 3)
(rest ()) ;=> ()
(empty? ()) ;=> true

```

---
## Some Code (cont.)

```clojure
(apply + [1 2 3 4]) ;=> 10

(count [0 1 2 3]) ;=> 4

(def mymap {:g "Google" :a "Apple" :h "htc" :s "Samsung"}) ;=> #'user/mymap

(count mymap) ;=> 4

(mymap :a) ;=> "Apple"
(:g mymap) ;=> "Google"

(= mymap (hash-map :g "Google" :a "Apple" :h "htc" :s "Samsung")) ;=> true

(assoc mymap "o" "Oracle") ;=> {:g "Google" :a "Apple" :o "Oracle :h "htc" :s "Samsung"}
(dissoc mymap :s :h :a) ;=> {:g "Google"}

mymap ;=> {:g "Google" :a "Apple" :h "htc" :s "Samsung"}  ;; immutable

```

--- .segue .dark
## Functional programming in Clojure

---
## What is Functional programming  

<q>
**Functional programming** is a style of building the structure and elements of computer programs, that treats computation as the evaluation of mathematical functions and avoids state and mutable data. </q>

---
## Functional Progamming Features

* First-class functions

* Immutability

* Pure functions (no side effect)

* Recursion

* Expression

* Compose functions

* Lazy evaluation


---
## First-class functions

### Passing functions as arguments
```clojure 
(def square (fn [x] (* x x))) ;=> #'user/square

(def add2 (fn [x] (+ 2 x))) ;=> #'user/add2

(defn sum-by [x f] 
  (apply + (map f x))) ;=> #'user/sum-by

(sum-by [1 2 3] square) ;=> 14

(sum-by [1 2 3] add2) ;=> 12
```

---
## First-class functions (Cont.)


### Returning function as value
```clojure 
(defn power [base]
  "Return a power function based on passing argument"
  (let [b base]
    (fn [exp] (Math/pow  b exp))))
    
(def base2 (power 2))

(def base5 (power 5))


(base2 10) ;=> 1024

(base5 2) ;=> 25
```

--- &twocol w1:40% w2:60%
## Recursion 

A recursive factorial sample


*** =left

![](http://mitpress.mit.edu/sicp/full-text/sicp/book/chapter-1/figs/fact-shape.gif)


*** =right

```Clojure 
(defn factorial [n]
  (if (= 1 n)
    1
    (* n (factorial (dec n)))))
```

```clojure 
(factorial 6) ;=> 720

(factorial 10000N) ;=> StackOverFlowError
```

---
## Tail-recursion

```clojure
(def factorial2
  (fn [n]
    (loop [i n acc 1]
       (if (zero? i)
            acc
            (recur (dec i) (* acc i))))))
```

```clojure 
(factorial 6) ;=> 720

(factorial 10000N) ;=> ...
```

### But ... 

> * JVM does not support tail-call optimization.
> * So **recur** is the only non-stack-consuming looping construct in Clojure.

---
## Comp and Partial

### Partial functions
```clojure
(def add5 (partial + 5)) ;=> #'user/add5

(add5 5) ;=> 10

((partial + 5) 5) ;=> 10
```

### Compose functions
```clojure
(def not-zero? (comp not zero?)) 

(not-zero? 1) ;=> true
(not-zero? 0) ;=> false

((comp second reverse) '("a" 2 3 "b")) ;=> 3
```

---
## Map, Reduce and Filter

```Clojure
(def data '(1 2 3 4 5))

(defn square [x] (* x x))

(map square data) ;=> (1 4 9 16 25)
(map #(* % %) data) ;=> (1 4 9 16 25)

(filter odd? data) ;=> (1 3 5)
(filter #(> % 5) data) ;=> ()
(filter (partial < 3) data) ;=> (4 5)

(reduce * data) ;=> 120
(reduce str data) ;=> "12345"
(reduce #(str %2 %1) data) ;=> "54321"

(take 5 (filter odd? (range))) ;=> (1 3 5 7 9)
(take 5 (map #(+ % %) (range))) ;=> (0 2 4 6 8)

(reduce + (range)) ;=> non-stop ..
```

---
## Fibonacci Series Example

### Simple recursive version

```clojure
(defn fib1
  "simple version"
  [n]
  (if (< n 2)
    n
    (+ (fib1 (- n 2)) (fib1 (- n 1)))))
```
```clojure
(fib 7) ;=> 13

(take 10 (map fib1 (range))) 
;=> (0 1 1 2 3 5 8 13 21 34)

```

> * Very slow

---
## Fibonacci Series Example (Cont.)

### Memoized recursive version

```clojure
(defn fib2
  "memo version"
  [n]
  (if (< n 2)
    n
    (+' (mfib (- n 2)) (mfib (- n 1)))))

(def mfib (memoize fib2))
```
```clojure
(mfib 1000) ;=> StackOverflowError

(take 10000 (map mfib (range))) ;=> (0 1 1 ...)
```

> * Speed up but StackOverflowError

---
## Fibonacci Series Example (Cont.)

### Tail recursion version
```clojure
(defn fib3
  "Tail Recursion"
  [n]
  (let [f (fn [i p acc]
            (if (zero? i)
              p
              (recur (dec i) acc (+' acc p))))]
    (f n 0 1)))
```

> * complicate....

---
## Fibonacci Series Example (Cont.)


### Lazy version -1
```clojure
(defn fib4 []
  (letfn [(f [p c]
            (cons c (lazy-seq (f c (+' p c)))))]
    (f 0 1)))

```

```clojure
(fib4)  ;=> (0 1 1 2 3 5 8 ...)

(nth (fib4) 100000) ;=> (A very long number...)

```

---
## Fibonacci Series Example (Cont.)

### Lazy version -2

```clojure
(def fib5 (lazy-cat [0 1] (map +' fib5 (rest fib5))))
```

```clojure
fib5  ;=> (0 1 1 2 3 5 8 ...)

(take 10 fib5) ;=> (0 1 1 2 3 5 8 13 21 34)
```

--- .segue  .dark
## Live Demo

--- .segue .dark
## Q & A


--- 
## Some Learning Material

* Online
  - Try Clojure (http://tryclj.com/)
  - Clojure Koans (http://clojurekoans.com/)
  - 4clojure (https://www.4clojure.com/) (good!)
  - ClojureTV on Youtube
  
* Books
  - Clojure Programming (http://www.clojurebook.com/)
  
  - The Joy of Clojure (Not for beginner)
  
* Toolbox 
  - http://www.clojure-toolbox.com/
  
* Cheatsheet
  - http://clojure.org/cheatsheet
