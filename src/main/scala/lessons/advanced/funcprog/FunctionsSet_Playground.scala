package com.intellibucket.lessons
package lessons.advanced.funcprog

import scala.annotation.{tailrec, targetName}
import scala.language.postfixOps

object FunctionsSet_Playground extends App {
  println()
  println("Function Sets")
  println()

  var values = MySet(1, 2, 3, 4, 5)
  values + 5 ++ MySet(1,2,3) + 4 + 4 map (x =>x * 3) filter (x => x % 2 != 0) flatMap (x => MySet(x + 1,x + 2)) foreach println

  //println(values contains 1)
  values - 1
  println(values contains 1)
}
trait MySet[A] extends (A => Boolean):
  override def apply(v1: A): Boolean = contains(v1)
  def contains(value: A): Boolean
  @targetName("add")
  def +(value: A): MySet[A]
  @targetName("concat")
  def ++(other: MySet[A]): MySet[A]
  def map[B](f: A => B): MySet[B]
  def flatMap[B](f: A => MySet[B]): MySet[B]
  def filter(filter: A => Boolean): MySet[A]
  def foreach(consumer: A => Unit): Unit
  @targetName("remove")
  def -(value: A) : MySet[A]
  @targetName("intersection")
  def &(other: MySet[A]) : MySet[A]
  @targetName("difference")
  def --(other: MySet[A]) : MySet[A]
end MySet


class ConsSet[A](val head:A, tail: MySet[A]) extends MySet[A]:

  @targetName("add")
  override def +(value: A): MySet[A] =
    if (this contains value) this
    else ConsSet(value,this)
  end +
  

  override def flatMap[B](f: A => MySet[B]): MySet[B] = (this.tail flatMap f) ++ f(this.head)

  /*
  [1,2,3] ++ [4,5,6] =
  [1,2] ++ [4,5,6] + 3 =>
  [1] ++ [4,5,6] + 3 + 2 =>
  [] ++ [4,5,6] + 3 + 2 + 1 ===>
  [1,2,3,4,5,6]
   */
  @targetName("concat")
  override def ++(other: MySet[A]): MySet[A] = this.tail ++ other + this.head

  override def foreach(consumer: A => Unit): Unit =
    consumer(head)
    this.tail foreach consumer
  end foreach


  override def map[B](f: A => B): MySet[B] = (this.tail map f) + f(this.head)

  override def filter(predicate: A => Boolean): MySet[A]  =
    val filteredTail = this.tail filter predicate
    if (predicate(head)) filteredTail + head
    else filteredTail
  end filter


  override def contains(value: A): Boolean = value == head || (tail contains value)

  @targetName("remove")
  override def -(value: A): MySet[A] = {
    @tailrec def removeElement(bef: A, tail: MySet[A]): MySet[A] = {
      if (this.head == value) {
        this.tail + bef
      }else{
        removeElement(this.head,this.tail);
      }
    }
    removeElement(this.head,this.tail)
  }

  @targetName("intersection")
  override def &(other: MySet[A]): MySet[A] = ???

  @targetName("difference")
  override def --(other: MySet[A]): MySet[A] = ???


end ConsSet

object ConsSet:
  def apply[A](a: A, mySet: MySet[A]): MySet[A] = new ConsSet[A](a, mySet)
end ConsSet


object MySet:
  def apply[A](args: A* ): MySet[A] =
    @tailrec def build(valSeq: Set[A], acc: MySet[A]): MySet[A] =
      if (valSeq.isEmpty) acc
      else build(valSeq.tail, acc + valSeq.head)
    end build
    build(args.toSet, new EmptyMySet[A])
  end apply
end MySet


class EmptyMySet[A] extends MySet[A]:

  @targetName("remove")
  override def -(value: A): MySet[A] = new EmptyMySet[A]

  @targetName("difference")
  override def --(other: MySet[A]): MySet[A] = this

  @targetName("intersection")
  override def &(other: MySet[A]): MySet[A] = this

  override def contains(a: A): Boolean = false

  @targetName("add")
  override def +(a: A): MySet[A] = new ConsSet[A](a,this)

  @targetName("concat")
  override def ++(other: MySet[A]): MySet[A] = other

  override def map[B](f: A => B): MySet[B] = new EmptyMySet[B]

  override def flatMap[B](f: A => MySet[B]): MySet[B] = new EmptyMySet[B]

  override def foreach(consumer: A => Unit): Unit = ()

  override def filter(filter: A => Boolean): MySet[A] = this

end EmptyMySet

object EmptyMySet

