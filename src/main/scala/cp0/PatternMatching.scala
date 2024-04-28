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


  sealed class Animal
  case class Dog(name: String) extends Animal
  case class Cat(name: String) extends Animal
  case class Bird(name: String) extends Animal

  val animal: Animal = Dog("Buddy")

  animal match {
    case Dog(name) => println(s"Dog name is $name")
    case Cat(name) => println(s"Cat name is $name")
    case _ => println("Unknown animal")
  }


  trait Expreression
  case class Number(value: Int) extends Expreression
  case class Sum(e1: Expreression, e2: Expreression) extends Expreression
  case class Prod(e1: Expreression, e2: Expreression) extends Expreression

  def eval(e: Expreression): Int = e match {
    case Number(value) => value
    case Sum(e1, e2) => eval(e1) + eval(e2)
    case Prod(e1, e2) => {
      def evalProd(e: Expreression): Int = e match {
        case Number(value) => value
        case Prod(e1, e2) => evalProd(e1) * evalProd(e2)
        case _ => throw new IllegalArgumentException("Invalid expression")
      }
      evalProd(e1) * evalProd(e2)
    }
  }
}
