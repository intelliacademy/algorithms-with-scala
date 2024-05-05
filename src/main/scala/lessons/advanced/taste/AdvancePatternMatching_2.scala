package com.intellibucket.lessons
package lessons.advanced.taste

import scala.+:

object AdvancePatternMatching_2 extends App {
  println("Advance Pattern Matching 2")
  println()

  var singleItemList = List(1)
  var twoItemList = List(1,2)
  var multiItemList = List(4,2,3,4,5)

  var result = singleItemList match
    case head :: Nil => s"This is single item list"
    case _ => s"This is multi item list"

  //println(result)


  var result2 = twoItemList match
    case head :: Nil => s"This is single item list"
    //case ::(head,Nil) => s"This is single item list" // equivalent is head :: Nil
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

  //println(result3)

  var result4 = multiItemList match
    case List(1,2,_*)=>s"List starting with 1 and 2"
    case list => s"list  not start with 1 and 2 , this is $list"

  //println(result4)

  abstract class XSeq[+A]{
    def head:A = ???
    def tail:XSeq[A] = ???
  }
  case object EmptyXSeq$ extends XSeq[Nothing]

  object XSeq{
    def empty: XSeq[Nothing] = EmptyXSeq$

     def unapplySeq[A](arg: XSeq[A]): Option[Seq[A]] = {
      if (arg == EmptyXSeq$ ) Some(Seq.empty)
      else unapplySeq(arg.tail).map(arg.head +: _)
    }
  }

  case class ConXSeq[+A](override val head:A, override val tail:XSeq[A]) extends XSeq[A]


  val myList:XSeq[Int] = ConXSeq(1,ConXSeq(2,ConXSeq(3,ConXSeq(4,XSeq.empty))))

  var resultOfMyList = myList match
    case XSeq(1,2,_*)=>s"Start with 1,2"
    case list => s"else ${list}"

  //println(resultOfMyList)

  case class Person(name:String)

  abstract class Wrapper[T]:
    def isEmpty:Boolean
    def get:T
  end Wrapper

  object PersonWrapper{
    def unapply(arg: Person): Wrapper[String] = new Wrapper[String]:
      override def get: String = arg.name

      override def isEmpty: Boolean = false
  }

  val exPerson = Person("Vugar")

  var personWrapperResult = exPerson match
    case PersonWrapper(va1)=>s"Person name is $va1"
    case _ => "else"

  println(personWrapperResult)
}
