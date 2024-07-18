package com.intellibucket.lessons
package cp7

import cp1.Person

object BTree_Playground extends App {
  println("SplayTree")

  var tree: Tree[Person] =  new BTree[Person]

  var personArrays = Array(
    new Person("A", 10),
    new Person("B", 21),
    new Person("C", 3),
    new Person("D", 70),
    new Person("F", 56),
    new Person("G", 69),
    new Person("H", 30),
    new Person("I", 20),
    new Person("J", 100),
    new Person("E", 60)
  )

  for (person <- personArrays) {
    tree.insert(person)
  }

  tree
}
