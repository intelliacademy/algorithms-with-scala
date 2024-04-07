package com.intellibucket.lessons
package cp0

import scala.annotation.tailrec

object Paragraph_Recursion extends App {
  def factorial(n: Int): Int = {
    @tailrec
    def recFactorial(n: Int, acc: Int) : Int = {
      if (n == 1) acc
      else recFactorial(n - 1, n * acc)
    }
    recFactorial(n, 1)
  }

  /*
  n = 5 -> recFactorial(5, 1) -> recFactorial(4, 5) -> recFactorial(3, 20) -> recFactorial(2, 60) -> recFactorial(1, 120) -> 120
  n = 4 -> recFactorial(4, 1) -> recFactorial(3, 4) -> recFactorial(2, 12) -> recFactorial(1, 24) -> 24
   */

  println(factorial(5))
}
