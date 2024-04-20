package com.intellibucket.lessons
package cp0

import scala.annotation.tailrec

object HigherOrderedFunctions_Playground extends App {
  println("Higher Ordered Functions Playground")

  // HOFs are functions that take other functions as arguments or return functions as results
  // They are a powerful tool for abstraction and composition

  // Curries functions are a special case of HOFs
  // They are functions that return other functions

//  var function0: (Int,(String,(Int => Int))) => (Int => Int) = ??? // This is a curried function
  //var function1: (Int,(String,(Int => Int))) => Int = (a,b) => b._2(b._1)

  @tailrec
  private def nTimesExecutingFunction(f: Int => Int, n: Int, x: Int): Int = {
    if (n <= 0) x
    else nTimesExecutingFunction(f, n - 1, f(x))
  }


  private def nTimesBetterFunction(f: Int => Int, n: Int): Int => Int = {
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetterFunction(f, n - 1)(f(x))
  }

  println(nTimesExecutingFunction(x => x + 1, 10, 1))
  var res: Int => Int = nTimesBetterFunction(x => x + 1, 10);
  println(res(1))

  //curried functions
  var currierFunction0 : Int => (Int => Int) = (a: Int) => (b: Int) => a + b
  var currierFunction1 : Int => Int => Int = (a: Int) => (b: Int) => a + b
  var resCurrierFunction0 = currierFunction0(5) // y = 5 + y
  println(resCurrierFunction0(2))


  def currentFormatter(format: String)(number: Double): String = format.format(number)
  val standardFormatter: Double => String = currentFormatter("%4.2f")
  val preciseFormatter: Double => String = currentFormatter("%10.8f")

  println(s"Standard Formatter: ${standardFormatter(Math.PI)}")
  println(s"Precise Formatter: ${preciseFormatter(Math.PI)}")
}
