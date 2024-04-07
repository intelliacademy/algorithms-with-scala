package com.intellibucket.lessons
package cp0

import scala.annotation.tailrec

object Paragraph_Basic_Function extends App{
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


  //1 1 2 3 5 8 13 21
  def fibonacci(n: Int): Int = {
    if (n <= 2) 1
    else fibonacci(n-1) + fibonacci(n-2)
  }

  def isPrime(n: Int): Boolean = {
    if (n <= 1) false
    else if (n == 2) true
    else !(2 until n).exists(x => n % x == 0)
  }

  def isPrimeTailRec(n: Int): Boolean = {
    @tailrec
    def isPrimeUntil(t: Int): Boolean = {
      if (t <= 1) true
      else  (n % t != 0) && isPrimeUntil(t - 1)
    }
    isPrimeUntil(n / 2)
  }

  println(isPrimeTailRec(3700000))
}
