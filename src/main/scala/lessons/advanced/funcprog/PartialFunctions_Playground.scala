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
  
  //Turns this partial function into a plain function returning an Option result.
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

  println()

  val aTotalFunction: Int => Int = {
    case 1 => 111
    case 2 => 222
    case 3 => 333
  }

  var multiplyPFunction: PartialFunction[Int,Int] = {
    case a if a < 30  => a * 10
  }

  var chainPfFunction:Int => Int = multiplyPFunction.orElse({
    case b if b >= 3 => b * 20
  })

  val mappedList = List(1,2,3,4,5).map({
    case 1 => 10
    case 2 => 20
    case x => x * 10
  }).map(chainPfFunction)

  println(s"Mapped list $mappedList")


  case class CustomPartialFunction() extends PartialFunction[Int,Int]:
    override def apply(v1: Int): Int = v1 match
      case 1 => 10
      case 2 => 20
      case x => x * 100

    override def isDefinedAt(x: Int): Boolean = {
      this.productElementNames.exists((y:String) => y.equals(x))
    }
  end CustomPartialFunction
  case object CustomPartialFunction

  println("Write something")
  scala.io.Source
    .stdin
    .getLines()
    .foreach(line => print(s"You said: ${CustomPartialFunction()(line.toInt)}"))
  println("Okey")

}
