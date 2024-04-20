package com.intellibucket.lessons
package cp0

object HOFsAndCurriesExercises extends App {
  println("HOFs and Curries Exercises")

  // Expand CustomList to support map, filter, and reduce
  /*
  1- foreach method A => Unit
     [1, 2, 3].foreach(x => println(x))
  
  2- map method A => B
     [1, 2, 3].map(x => x * 2)
  
  3- filter method A => Boolean
     [1, 2, 3].filter(x => x % 2 == 0)
  
  4- reduce method (A, A) => A
     [1, 2, 3].reduce((x, y) => x + y)
  
  5- flatMap method A => CustomList[B]
     [1, 2, 3].flatMap(x => CustomList(x, x * 2))
  
  6- zip method (CustomList[A], CustomList[B]) => CustomList[(A, B)]
     [1, 2, 3].zip([4, 5, 6]) => [(1, 4), (2, 5), (3, 6)]
  
  7- foldLeft method (B, (B, A) => B) => B
     [1, 2, 3].foldLeft(0, (acc, x) => acc + x)
  
  8- foldRight method (B, (A, B) => B) => B
     [1, 2, 3].foldRight(0, (x, acc) => acc + x)
  
  9- take method Int => CustomList[A]
     [1, 2, 3].take(2) => [1, 2]
  
  10- takeWhile method A => Boolean => CustomList[A]
      [1, 2, 3].takeWhile(x => x < 3) => [1, 2]
  
  11- drop method Int => CustomList[A]
      [1, 2, 3].drop(2) => [3]
  
  12- dropWhile method A => Boolean => CustomList[A]
      [1, 2, 3].dropWhile(x => x < 3) => [3]
  
  13- exists method A => Boolean
      [1, 2, 3].exists(x => x == 2) => true
  
  14- zipWith method (CustomList[A], CustomList[B], (A, B) => C) => CustomList[C]
      [1, 2, 3].zipWith([4, 5, 6], (x, y) => x + y) => [5, 7, 9]
   */
  
  var unsortedValues = Array(72, 1, 3, 5, 7, 9, 2, 4, 6, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19)
  var list:XList[Int] = XNil
  for (value <- unsortedValues) {
    list = list + value
  }
  list.foreach(println)
}


trait XList[+A] {
  def +[B >: A](element: B): XList[B] = XCons(element, this)
  def foreach(f: A => Unit): Unit
  def map[B](f: A => B): XList[B]
  def filter(f: A => Boolean): XList[A]
  def reduce[B >: A](f: (B, A) => B): B
  def flatMap[B](f: A => XList[B]): XList[B]
  def zip[B](that: XList[B]): XList[(A, B)]
  def foldLeft[B](z: B)(f: (B, A) => B): B
  def foldRight[B](z: B)(f: (A, B) => B): B
  def take(n: Int): XList[A]
  def takeWhile(f: A => Boolean): XList[A]
  def drop(n: Int): XList[A]
  def dropWhile(f: A => Boolean): XList[A]
  def exists(f: A => Boolean): Boolean
  def zipWith[B, C](that: XList[B], f: (A, B) => C): XList[C]
}

case object XNil extends XList[Nothing] {
  override def +[B >: Nothing](element: B): XList[B] = XCons(element, this)
  
  override def foreach(f: Nothing => Unit): Unit = ()

  override def map[B](f: Nothing => B): XList[B] = XNil

  override def filter(f: Nothing => Boolean): XList[Nothing] = XNil

  override def reduce[B >: Nothing](f: (B, Nothing) => B): B = throw new NoSuchElementException

  override def flatMap[B](f: Nothing => XList[B]): XList[B] = XNil

  override def zip[B](that: XList[B]): XList[(Nothing, B)] = XNil

  override def foldLeft[B](z: B)(f: (B, Nothing) => B): B = z

  override def foldRight[B](z: B)(f: (Nothing, B) => B): B = z

  override def take(n: Int): XList[Nothing] = XNil

  override def takeWhile(f: Nothing => Boolean): XList[Nothing] = XNil

  override def drop(n: Int): XList[Nothing] = XNil

  override def dropWhile(f: Nothing => Boolean): XList[Nothing] = XNil

  override def exists(f: Nothing => Boolean): Boolean = false

  override def zipWith[B, C](that: XList[B], f: (Nothing, B) => C): XList[C] = XNil
}

case object XCons {
  def apply[A](head: A, tail: XList[A]): XList[A] = new XCons(head, tail)
  def apply[A](head: A): XList[A] = new XCons(head, XNil)
  def apply[A](): XList[A] = XNil
}

case class XCons[+A](head: A, tail: XList[A]) extends XList[A] {
  override def foreach(f: A => Unit): Unit = {
    f(head)
    tail.foreach(f)
  }

  override def map[B](f: A => B): XList[B] = {
    XCons(f(head), tail.map(f))
  }

  override def filter(f: A => Boolean): XList[A] = {
    if (f(head)) XCons(head, tail.filter(f))
    else tail.filter(f)
  }

  override def reduce[B >: A](f: (B, A) => B): B = {
    //tail.reduce(f(head, _))
    null.asInstanceOf[B]
  }

  override def flatMap[B](f: A => XList[B]): XList[B] = {
//    f(head) match {
//      case XCons(h, t) => XCons(h, t.flatMap(f).zip(tail.flatMap(f)))
//      case _ => tail.flatMap(f)
//    }
    null.asInstanceOf[XList[B]]
  }

  override def zip[B](that: XList[B]): XList[(A, B)] = {
    that match {
      case XCons(h, t) => XCons((head, h), tail.zip(t))
      case _ => XNil
    }
  }

  override def foldLeft[B](z: B)(f: (B, A) => B): B = {
    tail.foldLeft(f(z, head))(f)
  }

  override def foldRight[B](z: B)(f: (A, B) => B): B = {
    f(head, tail.foldRight(z)(f))
  }

  override def take(n: Int): XList[A] = {
    if (n > 0) XCons(head, tail.take(n - 1))
    else XNil
  }

  override def takeWhile(f: A => Boolean): XList[A] = {
    if (f(head)) XCons(head, tail.takeWhile(f))
    else XNil
  }

  override def drop(n: Int): XList[A] = {
    if (n > 0) tail.drop(n - 1)
    else this
  }

  override def dropWhile(f: A => Boolean): XList[A] = {
    if (f(head)) tail.dropWhile(f)
    else this
  }

  override def exists(f: A => Boolean): Boolean = {
    f(head) || tail.exists(f)
  }
  
  override def zipWith[B, C](that: XList[B], f: (A, B) => C): XList[C] = {
    that match {
      case XCons(h, t) => XCons(f(head, h), tail.zipWith(t, f))
      case _ => XNil
    }
  }
}