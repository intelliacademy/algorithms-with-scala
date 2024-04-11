package com.intellibucket.lessons
package cp1.concretes

trait Tree[T <: Comparable[T]] {
  def isEmpty: Boolean

  def insert(x: T): Unit

  def contains(x: T): Boolean

  def remove(x: T): Unit

  def print: Unit
}

class BinarySearchTree[T <: Comparable[T]] extends Tree[T]{
  var root: Node[T] = NilNode[T]

  override def remove(x: T): Unit = ???
  override def contains(x: T): Boolean = ???

  override def insert(x: T): Unit = {
    if (root.isInstanceOf[NilNode[T]])
      this.root = Node[T](x)
    else
      this.root + Node[T](x)
  }

  override def isEmpty: Boolean = ???

  override def print: Unit = println(root.toString)

}
