package com.intellibucket.lessons
package cp3

import cp1.{Person, Tree}

import scala.language.postfixOps

object SplayTree_Playground extends App {
  
  println("SplayTree")

  var tree: SplayTree[Person] =  new SplayTree[Person]

  var personArrays = Array(
    new Person("A", 10),
    new Person("B", 90),
    new Person("C", 30),
    new Person("D", 70),
    new Person("E", 60),
    new Person("F", 50),
    new Person("G", 40),
    new Person("H", 80),
    new Person("I", 20),
    new Person("J", 100)
  )

  for (person <- personArrays) {
    tree.insert(person)
  }

  tree print
}
