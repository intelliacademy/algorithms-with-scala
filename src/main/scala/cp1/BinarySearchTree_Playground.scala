package com.intellibucket.lessons.cp1

import concretes.BinarySearchTree

import scala.language.postfixOps

object BinarySearchTree_Playground extends App {

  class Person(val name: String, val age: Int) extends Comparable[Person] {
    override def compareTo(o: Person): Int = {
      if (o != null){
        if (this.age > o.age) 1
        else if (this.age < o.age) -1
        else 0
      }else -1
    }

    override def toString: String = {
      s"Person{name=$name, age=$age}"
    }
  }

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

  var binarySearchTree = new BinarySearchTree[Person]()

  for (person <- personArrays) {
    binarySearchTree.insert(person)
  }

  binarySearchTree print




}


