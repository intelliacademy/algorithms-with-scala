package com.intellibucket.lessons
package cp6

trait Tree[T: Ordering] {
  def isEmpty: Boolean

  def insert(x: T): Unit

  def contains(x: T): Boolean

  def remove(x: T): Unit

  def print: Unit
}

class SplayTree[T: Ordering]() extends Tree[T] {
  private val ord = implicitly[Ordering[T]]

  var root: Node[T] = _

  override def contains(x: T): Boolean = {
    root = splay(root, x)
    root != null && ord.equiv(root.value, x)
  }

  override def insert(x: T): Unit = {
    if (root == null) {
      root = Node(x)
    } else {
      root = splay(root, x)
      if (!ord.equiv(root.value, x)) {
        val newNode = Node(x)
        if (ord.lt(x, root.value)) {
          newNode.left = root.left
          newNode.right = root
          root.left = null
        } else {
          newNode.right = root.right
          newNode.left = root
          root.right = null
        }
        root = newNode
      }
    }
  }

  override def remove(x: T): Unit = {
    if (root != null) {
      root = splay(root, x)
      if (ord.equiv(root.value, x)) {
        if (root.left == null) {
          root = root.right
        } else {
          val tmp = root.right
          root = root.left
          root = splay(root, x)
          root.right = tmp
        }
      }
    }
  }

  override def print: Unit = {
    def printHelper(node: Node[T], indent: String): Unit = {
      if (node != null) {
        printHelper(node.right, indent + "   ")
        println(indent + node.value)
        printHelper(node.left, indent + "   ")
      }
    }
    printHelper(root, "")
  }

  override def isEmpty: Boolean = root == null

  private def rightRotate(x: Node[T]): Node[T] = {
    val y = x.left
    x.left = y.right
    y.right = x
    y
  }

  private def leftRotate(x: Node[T]): Node[T] = {
    val y = x.right
    x.right = y.left
    y.left = x
    y
  }

  private def splay(current: Node[T], x: T): Node[T] = {
    var h = current
    if (h == null) return null
    if (ord.lt(x, h.value)) {
      if (h.left == null) return h
      if (ord.lt(x, h.left.value)) {
        h.left.left = splay(h.left.left, x)
        if (h.left.left != null) h = rightRotate(h)
      } else if (ord.gt(x, h.left.value)) {
        h.left.right = splay(h.left.right, x)
        if (h.left.right != null) h.left = leftRotate(h.left)
      }
      if (h.left == null) h
      else rightRotate(h)
    } else if (ord.gt(x, h.value)) {
      if (h.right == null) return h
      if (ord.gt(x, h.right.value)) {
        h.right.right = splay(h.right.right, x)
        if (h.right.right != null) h = leftRotate(h)
      } else if (ord.lt(x, h.right.value)) {
        h.right.left = splay(h.right.left, x)
        if (h.right.left != null) h.right = rightRotate(h.right)
      }
      if (h.right == null)  h
      else leftRotate(h)
    } else h
  }
}

class Node[T: Ordering](var value: T, var left: Node[T], var right: Node[T]) {
  def setLeft(node: Node[T]): Unit = this.left = node
  def setRight(node: Node[T]): Unit = this.right = node

  override def toString: String = value.toString
}

object Node {
  def apply[T](value: T)(using ordering$T$0: Ordering[T]): Node[T] = new Node[T](value, null, null)
}

class Nil[T: Ordering] extends Node[T](null.asInstanceOf[T], null, null)

object Nil
