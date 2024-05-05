package com.intellibucket.lessons
package lessons.advanced.funcprog

import scala.util.Try

object PartialFunctions_Playground extends App {
  println("Partial Functions")

  var aFunction: Int => Int = x => x + 1
  var bFunction: (Int) => Int = x => x + 1
  var cFunction :Function1[Int,Int] = x => x + 1

  val aNiceFussyFunction = (x: Int) => x match
    case 3 => 30

  val bNiceFussyFunction: Int => Int = (x: Int) => x match
    case 3 => 30


  val aPartialFunction: PartialFunction[Int,Int] = {
    case 1 => 10
    case 2 => 20
    case 3 => 30
  }

  val result:Int = Try[Int]{
    aPartialFunction(99)
  }.getOrElse(990)

  println(result)
  println(aPartialFunction.isDefinedAt(99))
  var liftedFunc = aPartialFunction.lift
  println(s"lifted value 1 match ${liftedFunc(1).getOrElse(100)}")
  println(s"lifted value 99 match ${liftedFunc(99).getOrElse(101)}")

  var chainPFunction = aPartialFunction.orElse[Int,Int]({
    case 99 => {
      println("Or Else function executing")
      990
    }
  })

  println(s"Or Else Partial Function ${chainPFunction(99)}")
}
