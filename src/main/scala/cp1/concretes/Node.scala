package com.intellibucket.lessons
package cp1.concretes

import java.lang.Comparable

class Node[T <: Comparable[T]](val value:T,var left: Node[T],var right: Node[T]) extends Comparable[Node[T]] {
  
  def this(value:T) = this(value,null,null)
  
  override def compareTo(o: Node[T]): Int = this.value.compareTo(o.value)
  
}

class NilNode[T <: Comparable[T]] extends Node[T](null.asInstanceOf[T],null,null) {
  
  override def compareTo(o: Node[T]): Int = {
    if(o.isInstanceOf[NilNode[T]]) 0
    else -1
  }
  
}

object NilNode {
  def apply[T <: Comparable[T]] = new NilNode[T]
}
