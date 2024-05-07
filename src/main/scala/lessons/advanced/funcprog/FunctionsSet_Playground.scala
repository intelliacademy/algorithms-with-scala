package com.intellibucket.lessons
package lessons.advanced.funcprog

import scala.annotation.tailrec

object FunctionsSet_Playground extends App {
  println()
  println("Function Sets")
  println()

  var values = Set(1,2,3,4,5)

  trait MySet[A] extends (A => Boolean):
    override def apply(v1: A): Boolean = contains(v1)
    def contains(value: A): Boolean
    def +(value: A): MySet[A]
    def ++(other: MySet[A]): MySet[A]
    def map[B](f: A => B): MySet[B]
    def flatMap[B](f: A => MySet[B]): MySet[B]
    def filter(filter: A => Boolean): MySet[A]
    def foreach(consumer: A => Unit): Unit
  end MySet


  private class ConsSet[A](val head:A, tail: MySet[A]) extends MySet[A]:

    override def +(value: A): MySet[A] =
      if (this contains value) this
      else ConsSet(value,this.tail)
    end +


    override def flatMap[B](f: A => MySet[B]): MySet[B] = (this.tail flatMap f) ++ f(this.head)

    /*
    [1,2,3] ++ [4,5,6] =
    [1,2] ++ [4,5,6] + 3 =>
    [1] ++ [4,5,6] + 3 + 2 =>
    [] ++ [4,5,6] + 3 + 2 + 1 ===>
    [1,2,3,4,5,6]
     */
    override def ++(other: MySet[A]): MySet[A] = this.tail ++ other + this.head

    override def foreach(consumer: A => Unit): Unit =
      consumer(head)
      tail foreach consumer
    end foreach


    override def map[B](f: A => B): MySet[B] = (this.tail map f) + f(this.head)

    override def filter(predicate: A => Boolean): MySet[A]  =
      val filteredTail = this.tail filter predicate
      if (predicate(head)) then (filteredTail + head)
      filteredTail
    end filter


    override def contains(a: A): Boolean = a == this.head || this.contains(a)

  end ConsSet

  private object ConsSet:
    def apply[A](a: A, mySet: MySet[A]) : MySet[A] = new ConsSet[A](a,mySet)

    def apply[A](args: A*) : MySet[A] =
      @tailrec def build(valSeq: Set[A], acc: MySet[A]): MySet[A] =
        if (valSeq.isEmpty) then return acc
        build(valSeq.tail,acc + valSeq.head)
      end build
      build(args.toSet,new EmptyMySet[A])
    end apply

  end ConsSet


  private class EmptyMySet[A] extends MySet[A]:

    override def contains(a: A): Boolean = false

    override def +(a: A): MySet[A] = new ConsSet[A](a,this)

    override def ++(other: MySet[A]): MySet[A] = other

    override def map[B](f: A => B): MySet[B] = new EmptyMySet[B]
    
    override def flatMap[B](f: A => MySet[B]): MySet[B] = new EmptyMySet[B]

    override def foreach(consumer: A => Unit): Unit = ()

    override def filter(filter: A => Boolean): MySet[A] = this

  end EmptyMySet

  private object EmptyMySet:
  end EmptyMySet




}
