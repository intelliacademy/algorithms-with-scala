package com.intellibucket.lessons
package cp0

import scala.annotation.tailrec

object Chapter_Zero extends App{
  @tailrec
  def printRecursive(value: String, count: Int): Unit = {
    println(value)
    if (count != 0) printRecursive(value, count - 1)
  }

  @tailrec
  def factorial(n: Int, acc: Int = 1): Int = {
    if (n == 0) acc
    else factorial(n - 1, n * acc)
  }


  def fibonacci(n: Int): Int = {
    if (n <= 1) 1
    else fibonacci(n-1) + fibonacci(n-2)
  }

  println(fibonacci(8))
}
