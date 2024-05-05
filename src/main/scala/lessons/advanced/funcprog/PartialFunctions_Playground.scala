package com.intellibucket.lessons
package lessons.advanced.funcprog

import scala.util.Try

object PartialFunctions_Playground extends App {
  println("Partial Functions")

  var aFunction: Int => Int = x => x + 1
  var bFunction: (Int) => Int = x => x + 1
  var cFunction :Function1[Int,Int] = x => x + 1


  val aPartialFunction: PartialFunction[Int,Int] = {
    case 1 => 10
    case 2 => 20
    case 3 => 30
  }

  val result:Int = Try[Int]{
    aPartialFunction(99)
  }.getOrElse(990)

  println(result)
}
