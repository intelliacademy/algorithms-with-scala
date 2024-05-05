package com.intellibucket.lessons
package cp2.p1

trait FuncGList[+A] {
  def isEmpty: Boolean
  def head: A
  def tail: FuncGList[A]
  def prepend[B >: A](elem: B): FuncSequencedList[B]
  def filter(p: A => Boolean): FuncGList[A]
  def map[B](f: A => B): FuncGList[B]
  def flatMap[B](f: A => FuncGList[B]): FuncGList[B]
  def forEach(f: A => Unit): Unit
}

case class FuncSequencedList[+A](value: A,next: FuncGList[A]) extends FuncGList[A] {

  def this(value: A) = this(value, FuncEmptyList)

  def this() = this(null.asInstanceOf[A], FuncEmptyList)

  def this(next: FuncGList[A]) = this(null.asInstanceOf[A], next)
  
  override def isEmpty: Boolean = false

  override def head: A = this.value

  override def tail: FuncGList[A] = this.next

  override def prepend[B >: A](elem: B): FuncSequencedList[B] = FuncSequencedList(elem, this)

  override def map[B](f: A => B): FuncGList[B] = {
    val mappedValue = f(this.value)
    val mappedNext = this.next.map(f)
    mappedNext.prepend(mappedValue)
  }

  override def flatMap[B](f: A => FuncGList[B]): FuncGList[B] = {
    val mappedValue = f(this.value)
    val mappedNext = this.next.flatMap(f)
    mappedNext.prepend(mappedValue.head)
  }

  override def filter(p: A => Boolean): FuncGList[A] = {
    if (p(this.value)) {
      val filteredNext = this.next.filter(p)
      filteredNext.prepend(this.value)
    } else {
      this.next.filter(p)
    }
  }

  final override def forEach(f: A => Unit): Unit = {
    f(this.value)
    this.next.forEach(f)
  }

}

case object FuncSequencedList {
  def apply[A]: FuncSequencedList[A] = new FuncSequencedList[A]
  def apply[A](value: A): FuncSequencedList[A] = new FuncSequencedList(value)
  def apply[A](list: List[A]): FuncSequencedList[A] = {
    list.foldRight(FuncSequencedList[A])((elem, acc) => acc.prepend(elem))
  }
}

case object FuncEmptyList extends FuncGList[Nothing] {
  override def isEmpty: Boolean = true

  override def head: Nothing = throw new NoSuchElementException("head of empty list")

  override def tail: FuncGList[Nothing] = throw new NoSuchElementException("tail of empty list")

  override def prepend[B >: Nothing](elem: B): FuncSequencedList[B] = FuncSequencedList(elem)

  override def map[B](f: Nothing => B): FuncGList[B] = this

  override def flatMap[B](f: Nothing => FuncGList[B]): FuncGList[B] = this

  override def forEach(f: Nothing => Unit): Unit = ()

  override def filter(p: Nothing => Boolean): FuncGList[Nothing] = this
}

case class FuncEmptyNodeException(message: String) extends Exception(message)

