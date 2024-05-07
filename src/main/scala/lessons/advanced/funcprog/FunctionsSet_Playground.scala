package com.intellibucket.lessons
package lessons.advanced.funcprog

object FunctionsSet_Playground extends App {
  println()
  println("Function Sets")
  println()

  var values = Set(1,2,3,4,5)

  trait MySet[A] extends (A => Boolean):
    override def apply(v1: A): Boolean = contains(v1)
    def contains(value: A): Boolean
    def +(value: A): MySet[A]
    def ++[B <: A](other: MySet[B]): MySet[A]
    def map[B](f: A => B): MySet[B]
    def flatMap[B](f: A => MySet[B]): MySet[B]
    def filter[B <: A](filter: B => Boolean): MySet[B]
    def foreach[B <: A](consumer: B => Unit): Unit
  end MySet

  class ConsSet[A](val head:A, tail: MySet[A]) extends MySet[A]:

    override def +(value: A): MySet[A] =
      if (this contains value) this
      else ConsSet(value,this.tail)

    override def flatMap[B](f: A => MySet[B]): MySet[B] = (this.tail flatMap f) ++ f(this.head)

    /*
    [1,2,3] ++ [4,5,6] =
    [1,2] ++ [4,5,6] + 3 =>
    [1] ++ [4,5,6] + 3 + 2 =>
    [] ++ [4,5,6] + 3 + 2 + 1 =>
    [1,2,3,4,5,6]
     */
    override def ++[B <: A](other: MySet[B]): MySet[A] = this.tail ++ other + this.head

    override def foreach[B <: A](consumer: B => Unit): Unit = ???

    //Reconstruction
    override def map[B](f: A => B): MySet[B] = (this.tail map f) + f(this.head)

    override def filter[B <: A](filter: B => Boolean): MySet[B] = ???

    override def contains(a: A): Boolean = a == this.head || this.contains(a)
  end ConsSet

  object ConsSet:
    def apply[A](a: A, mySet: MySet[A]) : MySet[A] = new ConsSet[A](a,mySet)
  end ConsSet


  class EmptyMySet extends MySet[Nothing]:
    override def contains(a: Nothing): Boolean = false

    override def +(a: Nothing): MySet[Nothing] = new ConsSet[Nothing](a,this)

    override def ++[B <: Nothing](other: MySet[B]): MySet[Nothing] = other

    override def map[B](f: Nothing => B): MySet[B] = ???
    
    override def flatMap[B](f: Nothing => MySet[B]): MySet[B] = ???

    override def foreach[B <: Nothing](consumer: B => Unit): Unit = ()

    override def filter[B <: Nothing](filter: B => Boolean): MySet[B] = ???
  end EmptyMySet




}
