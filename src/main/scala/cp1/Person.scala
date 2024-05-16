package com.intellibucket.lessons
package cp1

class Person(val name: String, val age: Int) extends Comparable[Person] {
  override def compareTo(o: Person): Int = {
    if (o != null){
      if (this.age > o.age) 1
      else if (this.age < o.age) -1
      else 0
    }else -1
  }

  override def toString: String = {
    s"Person{name=$name, age=$age}"
  }
}