package com.intellibucket.lessons
package cp2

object SequencedList_Playground extends App{

  var unsortedList = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

  var list : GList[Int] = new SequencedList[Int]()

  for (i <- unsortedList) {
    list = list.prepend(i)
  }

  list.map((x: Int) => x * 2)
    .forEach((x: Int) => println(x))
}
