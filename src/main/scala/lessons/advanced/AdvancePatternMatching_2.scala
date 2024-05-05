package com.intellibucket.lessons
package lessons.advanced

object AdvancePatternMatching_2 extends App {
  println("Advance Pattern Matching 2")
  println()

  var singleItemList = List(1)
  var twoItemList = List(1,2)
  var multiItemList = List(1,2,3,4,5)

  var result = singleItemList match
    case head :: Nil => s"This is single item list"
    case _ => s"This is multi item list"

  //println(result)


  var result2 = twoItemList match
    case head :: Nil => s"This is single item list"
    case head :: 2 :: Nil => s"This is only two item list"
    case _ => s"This is multi item list"

  //println(result2)

  def calculateLength[A](list: List[A]):Int = {
    list match
      case Nil => 0
      case head :: tail => 1 + calculateLength(tail)
  }

  //println(calculateLength(multiItemList))


  //infix pattern

  case class Or[A,B](a: A,b: B)
  val orValue = Or(2,"Two")
  var result3 = orValue match
    case number Or string => s"$number is $string as string"
    //case Or(number,string) => s"$number is $string as string"

  println(result3)
}
