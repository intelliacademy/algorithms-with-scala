package com.intellibucket.lessons
package lessons.intro

object AllThePatterns extends App{

  // 1. Constants

  val x: Any = "Scala"

  x match {
    case 1 => "One"
    case "Scala" => "The Scala Programming Language"
    case _ => "Something else"
  }

  // 2. Wildcard
  // The wildcard pattern _ matches any value.
  // It can be used to ignore the value of a variable.

  x match {
    case _ => "Something"
  }

  // 3. Variable

  x match {
    case something => s"Something: $something"
  }

  // 4.  Tuple

  val y: Any = (1, 2)

  var aTuple = y match {
    case (4,_) => "Something"
    case (a, b) => s"Tuple: $a, $b"
  }


  // 5. Constructor Pattern

  val z: Any = List(1, 2, 3)

  z match {
    case List(12,_*) => "List starting with 12"
    case List(1, 2, 3) => "List starting with 1, 2, 3"
    case _ => "Something else"
  }

  // 6. Sequence Pattern

  val seq: Any = List(1, 2, 3)

  seq match {
    case List(1, _*) => "List starting with 1"
    case _ => "Something else"
  }

  // 7. Type Pattern

  val a: Any = 1

  a match {
    case _: Int => "An integer"
    case _ => "Something else"
  }

  // 8. Pattern Guards

  val b: Any = 1

  b match {
    case x: Int if x > 0 => "A positive integer"
    case x: Int if x < 0 => "A negative integer"
    case _ => "Something else"
  }

  // 9. Pattern Overlap

  val c: Any = 1

  c match {
    case _: Int => "An integer"
    case _ => "Something else"
  }

  // 10. Nested Patterns

  val d: Any = List(1, 2, 3)

  d match {
    case List(1, List(2, 3)) => "List starting with 1, followed by List(2, 3)"
    case _ => "Something else"
  }

  // 11. List Patterns

  val e: Any = List(1, 2, 3)

  e match {
    case List(1, 2, 3) => "List starting with 1, 2, 3"
    case List(1, _*) => "List starting with 1"
    case 1 :: 2 :: 3 :: Nil => "List starting with 1, 2, 3"
    case 1 :: _ => "List starting with 1"
    case List(1, 2, 3, _*) => "List starting with 1, 2, 3"
    case _ => "Something else"
  }

  // 12. Name Binding

  val f: Any = List(1, 2, 3)

  f match {
    case list @ List(1, 2, 3) => s"List: $list"
    case _ => "Something else"
  }


  // 13. Multiple Patterns

  val g: Any = 1

  g match {
    case 1 | 2 | 3 => "1, 2 or 3"
    case _ => "Something else"
  }
}
