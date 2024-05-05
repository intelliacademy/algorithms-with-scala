package com.intellibucket.lessons
package lessons.advanced.funcprog

object FunctionsSet_Playground extends App {
  println()
  println("Function Sets")
  println()

  var values = Set(1,2,3,4,5)

  trait MySet[A] extends (A => Boolean):
    override def apply(v1: A): Boolean = contains(v1)
    def contains(a: A): Boolean
    def +(a: A): MySet[A]
    def ++[B <: A](other: MySet[B]): MySet[A]
    def map[B](f: A => B): MySet[B]
    def flatMap[B,C <: A](f: C => MySet[B]): MySet[B]
    def filter[B <: A](filter: B => Boolean): MySet[B]
    def foreach[B <: A](consumer: B => Unit): Unit
  end MySet

  class ConsSet[A](val a:A, mySet: MySet[A]) extends MySet[A]:
    override def +(a: A): MySet[A] = ???

    override def flatMap[B, C <: A](f: C => MySet[B]): MySet[B] = ???

    override def ++[B <: A](other: MySet[B]): MySet[A] = ???

    override def foreach[B <: A](consumer: B => Unit): Unit = ???

    override def map[B](f: A => B): MySet[B] = ???

    override def filter[B <: A](filter: B => Boolean): MySet[B] = ???

    override def contains(a: A): Boolean = ???
  end ConsSet

  object ConsSet

  class EmptyMySet extends MySet[Nothing]:
    override def contains(a: Nothing): Boolean = false

    override def +(a: Nothing): MySet[Nothing] = new ConsSet[Nothing](a,this)

    override def ++[B <: Nothing](other: MySet[B]): MySet[Nothing] = other

    override def map[B](f: Nothing => B): MySet[B] = ???
    
    override def flatMap[B, C <: Nothing](f: C => MySet[B]): MySet[B] = ???

    override def foreach[B <: Nothing](consumer: B => Unit): Unit = ()

    override def filter[B <: Nothing](filter: B => Boolean): MySet[B] = ???
  end EmptyMySet




}
