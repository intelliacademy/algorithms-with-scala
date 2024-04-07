package com.intellibucket.lessons
package cp0

import scala.annotation.tailrec

object Paragraph_Recursion extends App{
  def factorial(n: Int): Int = {
    @tailrec
    def recFactorial(n: Int, acc: Int) : Int = {
      if (n == 1) acc
      else recFactorial(n - 1, n * acc)
    }
    recFactorial(n, 1)
  }


  println(factorial(5))
}
