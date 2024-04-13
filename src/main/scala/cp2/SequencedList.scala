package com.intellibucket.lessons
package cp2

trait GPredicate[-A] {
  def test(a: A): Boolean
}

object GPredicate {
  def and[A](p1: GPredicate[A], p2: GPredicate[A]): GPredicate[A] =
    (a: A) => p1.test(a) && p2.test(a)

  def or[A](p1: GPredicate[A], p2: GPredicate[A]): GPredicate[A] =
    (a: A) => p1.test(a) || p2.test(a)
}

trait GFunction[-A, +B] {
  def apply(a: A): B
}

object GFunction {
  def andThan[A, B, C](f: GFunction[A, B], g: GFunction[B, C]): GFunction[A, C] =
    (a: A) => g(f(a))
}

trait GConsumer[-A] {
  def apply(a: A): Unit
}

object GConsumer {
  def apply[A](f: A => Unit): GConsumer[A] = (a: A) => f(a)
}


trait GList[+A] {
  def isEmpty: Boolean
  def head: A
  def tail: GList[A]
  def prepend[B >: A](elem: B): SequencedList[B]
  def filter(p: GPredicate[A]): GList[A]
  def map[B](f: GFunction[A, B]): GList[B]
  def forEach(f: GConsumer[A]): Unit
}

case class SequencedList[+A](value: A,next: GList[A]) extends GList[A] {

  def this(value: A) = this(value, EmptyList)

  def this() = this(null.asInstanceOf[A], EmptyList)

  def this(next: GList[A]) = this(null.asInstanceOf[A], next)

  override def isEmpty: Boolean = false

  override def head: A = this.value

  override def tail: GList[A] = this.next

  override def prepend[B >: A](elem: B): SequencedList[B] = SequencedList(elem, this)

  override def map[B](f: GFunction[A, B]): GList[B] = {
    val mappedValue = f(this.value)
    val mappedNext = this.next.map(f)
    mappedNext.prepend(mappedValue)
  }

  override def filter(p: GPredicate[A]): GList[A] = {
    if (p.test(this.value)) {
      val filteredNext = this.next.filter(p)
      filteredNext.prepend(this.value)
    } else {
      this.next.filter(p)
    }
  }

  final override def forEach(f: GConsumer[A]): Unit = {
    f(this.value)
    this.next.forEach(f)
  }

}

case object SequencedList {
  def apply[A]: SequencedList[A] = EmptyList.asInstanceOf[SequencedList[A]]
  def apply[A](value: A): SequencedList[A] = new SequencedList(value)
}

case object EmptyList extends GList[Nothing] {
  override def isEmpty: Boolean = true

  override def head: Nothing = throw new NoSuchElementException("head of empty list")

  override def tail: GList[Nothing] = throw new NoSuchElementException("tail of empty list")

  override def prepend[B >: Nothing](elem: B): SequencedList[B] = SequencedList(elem)

  override def map[B](f: GFunction[Nothing, B]): GList[B] = this

  override def forEach(f: GConsumer[Nothing]): Unit = ()

  override def filter(p: GPredicate[Nothing]): GList[Nothing] = this
}

case class EmptyNodeException(message: String) extends Exception(message)