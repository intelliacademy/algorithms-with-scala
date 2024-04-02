package com.intellibucket.lessons
package cp1.concretes

import java.lang.Comparable

class Node[T <: Comparable[T]] extends Comparable[Node[T]] {

  def this(value: T) {
    this()
    this.value = value
  }
  
  
  def compareTo(o: Node[T]): Int = {
    
  }
}


class NilNode extends Node[Nothing] {
  override def compareTo(o: Node[Nothing]): Int = 0
}