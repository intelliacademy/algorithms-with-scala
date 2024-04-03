package com.intellibucket.lessons
package cp1.concretes

import java.lang.Comparable

class Node[T <: Comparable[T]] extends Comparable[Node[T]] {

  def this(value: T) = {
    this()
  }
  
  
  def compareTo(o: Node[T]): Int = {
    65
  }
}


class NilNode extends Node[Nothing] {
  override def compareTo(o: Node[Nothing]): Int = 0
}