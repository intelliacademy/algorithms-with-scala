package com.intellibucket.lessons
package cp7

import scala.annotation.tailrec

trait Tree[T: Ordering] {
  def isEmpty: Boolean

  def insert(x: T): Unit

  def contains(x: T): Boolean

  def remove(x: T): Unit

  def print: Unit
}

class BTree[T: Ordering](val t: Int) extends Tree[T] {
  private val ord = implicitly[Ordering[T]]
  private var root: Node[T] = Node(isLeaf = true)
  
  def this() = this(2)

  override def isEmpty: Boolean = root.keys.isEmpty

  override def insert(x: T): Unit = {
    if (root.keys.size == 2 * t - 1) {
      val s = Node[T](isLeaf = false)
      s.children = List(root)
      splitChild(s, 0, root)
      root = s
    }
    insertNonFull(root, x)
  }

  override def contains(x: T): Boolean = search(root, x) != null

  override def remove(x: T): Unit = {
    if (!isEmpty) {
      delete(root, x)
      if (root.keys.isEmpty) {
        root = if (root.isLeaf) null else root.children.head
      }
    }
  }

  override def print: Unit = printNode(root, "")

  private def printNode(node: Node[T], indent: String): Unit = {
    println(indent + node.keys.mkString(", "))
    if (!node.isLeaf) {
      node.children.foreach(child => printNode(child, indent + "    "))
    }
  }

  @tailrec
  private def search(node: Node[T], key: T): Node[T] = {
    var i = 0
    while (i < node.keys.size && ord.lt(node.keys(i), key)) {
      i += 1
    }
    if (i < node.keys.size && ord.equiv(node.keys(i), key)) {
      node
    } else if (node.isLeaf) {
      null
    } else {
      search(node.children(i), key)
    }
  }

  @tailrec
  private def insertNonFull(node: Node[T], key: T): Unit = {
    var i = node.keys.size - 1
    if (node.isLeaf) {
      node.keys = node.keys :+ key
      node.keys = node.keys.sorted(ord)
    } else {
      while (i >= 0 && ord.lt(key, node.keys(i))) {
        i -= 1
      }
      i += 1
      if (node.children(i).keys.size == 2 * t - 1) {
        splitChild(node, i, node.children(i))
        if (ord.gt(key, node.keys(i))) {
          i += 1
        }
      }
      insertNonFull(node.children(i), key)
    }
  }

  private def splitChild(parent: Node[T], i: Int, child: Node[T]): Unit = {
    val newNode = Node[T](isLeaf = child.isLeaf)
    newNode.keys = child.keys.drop(t)
    if (!child.isLeaf) {
      newNode.children = child.children.drop(t)
    }
    child.keys = child.keys.take(t - 1)
    child.children = child.children.take(t)
    parent.children = parent.children.patch(i + 1, Seq(newNode), 0)
    parent.keys = parent.keys.patch(i, Seq(child.keys(t - 1)), 0)
  }

  private def delete(node: Node[T], key: T): Unit = {
    // Implement B-Tree delete operation
    // This function is left as an exercise to the reader
  }
  

  case class Node[T: Ordering](var keys: List[T] = List.empty[T], var children: List[Node[T]] = List.empty[Node[T]], val isLeaf: Boolean)
}
