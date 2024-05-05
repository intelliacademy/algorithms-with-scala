package com.intellibucket.lessons
package lessons.advanced.taste

object AdvancePatternMatching_1 extends App {
  println("Advance Pattern Matching 1")


  case class Person(name: String, age: Int)
  object Person:
    def unapply(arg: Person): Option[(String, Int)] = if (arg.age == 33 ) Some("Johnes",arg.age + 1) else None

  var examplePerson = Person("John",33)

  var result = examplePerson match
    case Person(name,age) => s"Name is $name and age $age"
    case _ => "does't matter"

  //println(result)


  var value: Int = 4;

  var result2 = value match
    case val1 if val1 % 2 == 0 => s"This is even $val1"
    case _ => s"not even"

  //println(result2)


  var result3 = value match
    case val1 if val1 % 2 == 0 => s"This is even $val1"
    case _ => s"not even"

  //println(result3)

  object EventCondition:
    def unapply(arg: Int): Option[Boolean] = if (arg % 2 == 0) Some(true) else None

  var result4 = value match
    case EventCondition(val1) => s"This is even $val1"
    case _ => s"not even"

  //println(result4)


  object SimpleEventCondition:
    def unapply(arg: Int): Boolean = arg % 2 == 0

  var result5 = value match
    case SimpleEventCondition() => s"This is even"
    case _ => s"not even"

  println(result5)
}
