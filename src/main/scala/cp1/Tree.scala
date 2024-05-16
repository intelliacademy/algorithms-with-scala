package com.intellibucket.lessons
package cp1

trait Tree[T <: Comparable[T]] {
  def isEmpty: Boolean

  def insert(x: T): Unit

  def contains(x: T): Boolean

  def remove(x: T): Unit

  def print: Unit
}