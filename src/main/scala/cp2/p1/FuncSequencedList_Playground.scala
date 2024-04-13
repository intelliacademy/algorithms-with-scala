package com.intellibucket.lessons
package cp2.p1

import cp2.p1.*


object FuncSequencedList_Playground extends App{

  var unsortedList = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  var unsortedList2 = List(11, 12, 13, 14, 15, 16, 17, 18, 19, 20)

  var list : FuncGList[Int] = new FuncSequencedList[Int]()

  for (i <- unsortedList) {
    list = list.prepend(i)
  }

  list
    .flatMap((x: Int) => FuncSequencedList(List(x, x + 1)))
    .forEach((x: Int) => println(x))
}
