package com.intellibucket.lessons
package cp0

import scala.util.Random

object PatternMatching extends App {
  var random: Random = new Random()
  val x: Int = random.nextInt(10)

  val result = x match {
    case 1 => "Ten"
    case 2 => "Twenty"
    case 4 => "Three"
    case _ => "Unknown"
  }

  println(result)


  case class Person(name: String, age: Int)

  val person = Person("Joh", 30)

  person match {
    case Person("John", 30)  => println("John is 30 years old")
    case Person("John", _) => println("John is not 30 years old")
    case Person(name, age) if age < 10 => println(s"$name is $age years old")
    case _ => println(s"Unknown person")
  }

}
