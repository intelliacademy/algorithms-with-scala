package com.intellibucket.lessons
package lessons.advanced.funcprog

object PartialFunctions_Playground extends App {
  println("Partial Functions")

  var aFunction: Int => Int = x => x + 1
  var bFunction: (Int) => Int = x => x + 1
  var cFunction :Function1[Int,Int] = x => x + 1


}
