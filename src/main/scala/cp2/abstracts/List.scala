package com.intellibucket.lessons
package cp2.abstracts

trait List[-T] {
  def isEmpty: Boolean
  def add(elem: T): List[T]
  def remove(elem: T): List[T]
}
