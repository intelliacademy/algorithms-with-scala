package com.intellibucket.lessons
package cp2

trait FuncGPredicate[-A] {
  def test(a: A): Boolean
  //def or[B <: A](that: GPredicate[B]): GPredicate[A] = (a: A) => this.test(a) || that.test(a)
  //def and[B <: A](that: GPredicate[B]): GPredicate[A] = (a: A) => this.test(a) && that.test(a)
}

trait FuncGFunction[-A, B] {
  def map(a: A): B
  def andThen[C](that: FuncGFunction[B, C]): FuncGFunction[A, C] = (a: A) => that.map(this.map(a))
}


trait FuncGConsumer[-A] {
  def apply(a: A): Unit
//  def andThen[B <: A](that: GConsumer[B]): GConsumer[A] = (a: A) => {
//    this(a)
//    that(a)
//  }
}


trait FuncGList[+A] {
  def isEmpty: Boolean
  def head: A
  def tail: FuncGList[A]
  def prepend[B >: A](elem: B): FuncSequencedList[B]
  def filter(p: FuncGPredicate[A]): FuncGList[A]
  def map[B](f: FuncGFunction[A, B]): FuncGList[B]
  def forEach(f: FuncGConsumer[A]): Unit
}

case class FuncSequencedList[+A](value: A,next: FuncGList[A]) extends FuncGList[A] {

  def this(value: A) = this(value, FuncEmptyList)

  def this() = this(null.asInstanceOf[A], FuncEmptyList)

  def this(next: FuncGList[A]) = this(null.asInstanceOf[A], next)

  override def isEmpty: Boolean = false

  override def head: A = this.value

  override def tail: FuncGList[A] = this.next

  override def prepend[B >: A](elem: B): FuncSequencedList[B] = FuncSequencedList(elem, this)

  override def map[B](f: FuncGFunction[A, B]): FuncGList[B] = {
    val mappedValue = f.map(this.value)
    val mappedNext = this.next.map(f)
    mappedNext.prepend(mappedValue)
  }

  override def filter(p: FuncGPredicate[A]): FuncGList[A] = {
    if (p.test(this.value)) {
      val filteredNext = this.next.filter(p)
      filteredNext.prepend(this.value)
    } else {
      this.next.filter(p)
    }
  }

  final override def forEach(f: FuncGConsumer[A]): Unit = {
    f(this.value)
    this.next.forEach(f)
  }

}

case object FuncSequencedList {
  def apply[A]: FuncSequencedList[A] = EmptyList.asInstanceOf[FuncSequencedList[A]]
  def apply[A](value: A): FuncSequencedList[A] = new FuncSequencedList(value)
}

case object FuncEmptyList extends FuncGList[Nothing] {
  override def isEmpty: Boolean = true

  override def head: Nothing = throw new NoSuchElementException("head of empty list")

  override def tail: FuncGList[Nothing] = throw new NoSuchElementException("tail of empty list")

  override def prepend[B >: Nothing](elem: B): FuncSequencedList[B] = FuncSequencedList(elem)

  override def map[B](f: FuncGFunction[Nothing, B]): FuncGList[B] = this

  override def forEach(f: FuncGConsumer[Nothing]): Unit = ()

  override def filter(p: FuncGPredicate[Nothing]): FuncGList[Nothing] = this
}

case class FuncEmptyNodeException(message: String) extends Exception(message)

