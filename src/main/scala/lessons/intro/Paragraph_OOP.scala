package com.intellibucket.lessons
package lessons.intro

import java.util.UUID
import scala.annotation.targetName
import scala.language.postfixOps

object Paragraph_OOP extends App {

    val task1 = new Task
    var task2 = Task()
    var task3 = Task("New", 1)
    var task4 = new Task("Lin", 5)
    task4.priority = 1
    //task3.name = "Jane Doe"

    task4 walkTo "the park"
    task4()
    (task4 + task3) walkTo "the park"
    task1.+ (task3) walkTo "the park"

  println(1.+(2))
   println(!task2)
   println( task2 isFree)
    println(task2)
    task4.execute()
   task4.someDoIt("Lin", "the park")


  class Task(val name: String, var priority: Int) {
    def this() = this("Task".concat("-").concat(UUID.randomUUID().toString), 5)
    def this(name: String) = this(name, 5)

    def walkTo(destination: String): Unit = {
      println(s"$name is walking to $destination")
    }

    def apply(): Unit = {
      println(s"Name: ${this.name}, Priority: ${this.priority}")
    }

    @targetName("concatTask")
    def +(other: Task): Task = {
      new Task(this.name.concat(" && ").concat(other.name), this.priority + other.priority)
    }

    def isFree: Boolean = {
      true
    }

    @targetName("incrementPriority")
    def unary_++ : Task = {
      new Task(this.name, this.priority + 1)
    }


    @targetName("decrementPriority")
    def unary_-- : Task = {
      new Task(this.name, this.priority - 1)
    }

    @targetName("isFreePlus")
    def unary_!+ : Boolean = {
      !isFree
    }

    @targetName("isNotFree")
    def unary_! : Boolean = !isFree

    def execute(): Unit = this walkTo "execute"
    
    def someDoIt(name: String, target: String): Unit = {
      println(s"$name is walking to $target")
    }
  }


}
