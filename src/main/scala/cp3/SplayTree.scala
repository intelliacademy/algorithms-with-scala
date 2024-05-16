package com.intellibucket.lessons
package cp3

import cp1.Tree


case class SplayTree[T <: Comparable[T]]() extends Tree[T]:
  override def contains(x: T): Boolean = true

  override def insert(x: T): Unit = {}

  override def remove(x: T): Unit = {}

  override def print: Unit = {}

  override def isEmpty: Boolean = true
end SplayTree

class Node[T <: Comparable[T]](var value:T,var left: Node[T],var right: Node[T]) extends Comparable[Node[T]]:
  override def compareTo(o: Node[T]): Int = -1
end Node


class Nil[T <: Comparable[T]] extends Node[T](null.asInstanceOf[T],null,null):
end Nil

object Nil:
end Nil



