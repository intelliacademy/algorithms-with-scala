package com.intellibucket.lessons
package cp1.concretes

import java.lang.Comparable
import scala.annotation.{tailrec, targetName}

object Node {
  def apply[T <: Comparable[T]](value:T) = if (value == null) NilNode[T] else new Node[T](value)
}

class Node[T <: Comparable[T]](var value:T,var left: Node[T],var right: Node[T]) extends Comparable[Node[T]] {

  def this(value:T) = this(value,NilNode[T],NilNode[T])

  override def compareTo(o: Node[T]): Int = this.value.compareTo(o.value)

  def max: Node[T] = if(this.hasRight) this.right.max else this

  def min: Node[T] = if(this.hasLeft) this.left.min else this

  def isLeaf: Boolean = left.isInstanceOf[NilNode[T]] && right.isInstanceOf[NilNode[T]]

  def isHalf: Boolean = !left.isInstanceOf[NilNode[T]] ^ !right.isInstanceOf[NilNode[T]]

  def isNil: Boolean = this.isInstanceOf[NilNode[T]]

  def hasLeft: Boolean = !left.isInstanceOf[NilNode[T]]

  def hasRight: Boolean = !right.isInstanceOf[NilNode[T]]

  def hasBoth: Boolean = !left.isInstanceOf[NilNode[T]] && !right.isInstanceOf[NilNode[T]]

  def isGreaterThan(o: Node[T]): Boolean = this.compareTo(o) > 0

  def isLessThan(o: Node[T]): Boolean = this.compareTo(o) < 0

  def isEquals(o: Node[T]): Boolean = this.compareTo(o) == 0

  def isGreaterOrEquals(o: Node[T]): Boolean = this.compareTo(o) >= 0

  def isLessOrEquals(o: Node[T]): Boolean = this.compareTo(o) <= 0

  def isLeafOrHalf: Boolean = isLeaf || isHalf

  override def toString(): String = {
    s"Node($value) ->  Left(${left.value}) Right(${right.value}) "
  }

  @targetName("insertNode")
  def +(node: Node[T]): Unit = {
    if (node.isGreaterThan(this)) {
      if (this.hasLeft)  this.left + node
      else this.left = node
    }
    else  {
      if (this.hasRight)  this.right + node
      else this.right = node
    }
  }

  @targetName("deleteNode")
  def -(node: Node[T]): Node[T] = {
    if(this.isNil) this
    else if(this.isEquals(node)) {
      if(this.isLeaf) NilNode[T]
      else if(this.hasBoth) {
        val successor = this.right.min
        this.value = successor.value
        this.right = this.right - successor
      }
      else if(this.hasLeft) this.left
      else this.right
    }
    else if(this.isGreaterThan(node)) {
      this.left = this.left - node
    }
    else if(this.isLessThan(node)) {
      this.right = this.right - node
    }
    this
  }



}

class NilNode[T <: Comparable[T]] extends Node[T](null.asInstanceOf[T],null,null) {

  override def compareTo(o: Node[T]): Int = {
    if(o == NilNode[T]) 0
    else -1
  }

  override def isNil: Boolean = true

  override def isLeaf: Boolean = true

  override def isHalf: Boolean = false

  override def hasLeft: Boolean = false

  override def hasRight: Boolean = false

  override def hasBoth: Boolean = false

  override def isGreaterThan(o: Node[T]): Boolean = false

  override def isLessThan(o: Node[T]): Boolean = if (o.isInstanceOf[NilNode[T]]) false else true

  override def isEquals(o: Node[T]): Boolean = o.isInstanceOf[NilNode[T]]

  override def isGreaterOrEquals(o: Node[T]): Boolean = o.isInstanceOf[NilNode[T]]

  override def isLessOrEquals(o: Node[T]): Boolean = true

  override def toString(): String = "NilNode"
}

object NilNode {
  def apply[T <: Comparable[T]] = new NilNode[T]
}
