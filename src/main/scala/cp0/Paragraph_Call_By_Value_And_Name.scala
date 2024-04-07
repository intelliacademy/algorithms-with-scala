package com.intellibucket.lessons
package cp0

import scala.annotation.tailrec

object Paragraph_Call_By_Value_And_Name extends App {
  def callByValue(x: Long): Unit = {
    println("Call by value:")
    println("x1 = " + x)
    println("x2 = " + x)
  }

  def callByName(x: => Long): Unit = {
    println("Call by name:")
    println("x1 = " + x)
    println("x2 = " + x)
  }

  callByName(System.nanoTime())
  println()
  callByValue(System.nanoTime())
}
