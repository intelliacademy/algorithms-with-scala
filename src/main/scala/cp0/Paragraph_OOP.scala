package com.intellibucket.lessons
package cp0

object Paragraph_OOP extends App {

    val person1 = new Person
    var person2 = Person()
    var person3 = Person("John Doe", 30)
    var person4 = new Person("Jane Doe", 25)
    person4.age = 26
    //person3.name = "Jane Doe"



  class Person(val name: String, var age: Int) {
    def this() = this("John Doe", 30)
    def this(name: String) = this(name, 30)
  }
}
