package com.intellibucket.lessons
package cp2.p1

import cp2.p1.*


object FuncSequencedList_Playground extends App{

  var unsortedList = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  var unsortedList2 = List(11, 12, 13, 14, 15, 16, 17, 18, 19, 20)

  var list : FuncGList[Int] = new FuncSequencedList[Int]()
  var list2 : FuncGList[Int] = new FuncSequencedList[Int]()

  for (i <- unsortedList) {
    list = list.prepend(i)
  }

  for (i <- unsortedList2) {
    list2 = list2.prepend(i)
  }

  var specialFunction : Function[Int, Function[Int, Function[Int, Int]]] =
    (x: Int) => (y: Int) => (z: Int) => x + y + z

  list
    .map(specialFunction(1)(2)(_))
    .map(_ * 3)
    .forEach((x: Int) => println(x))

}
