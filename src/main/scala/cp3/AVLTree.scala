package com.intellibucket.lessons
package cp3

trait Tree[T: Ordering] {
  def isEmpty: Boolean

  def insert(x: T): Unit

  def contains(x: T): Boolean

  def remove(x: T): Unit

  def print: Unit
}


case class AVLTree[T: Ordering]() extends Tree[T]:
  private val ord = implicitly[Ordering[T]]

  var root: Node[T] = _

  override def contains(x: T): Boolean = true

  override def insert(x: T): Unit = {
    if (root == null){
      root = Node(x)
    }else {
      this.root.insert(x)
    }
  }

  override def remove(x: T): Unit = {}

  override def print: Unit = {}

  override def isEmpty: Boolean = true

  private def rightRotate(x: Node[T]) : Node[T] = {
    val y  = x.left
    x setLeft y.right
    y setRight x
    y
  }

  private def leftRotate(x: Node[T]): Node[T] = {
    val y = x.right
    x setRight y.left
    y setLeft x
    y
  }

end AVLTree


case class Node[T: Ordering](var value:T,var left: Node[T],var right: Node[T]):

  def insert(value: T): Unit ={
    var node = Node(value)


  }


  def setLeft(node: Node[T]) : Unit = this.left = node
  def setRight(node: Node[T]) : Unit = this.right = node

end Node

case object Node :
  def apply[T](value: T)(using ordering$T$0: Ordering[T]): Node[T] = new Node[T](value,null,null)
end Node


class Nil[T: Ordering] extends Node[T](null.asInstanceOf[T],null,null):
end Nil

object Nil:
end Nil
