package com.intellibucket.lessons
package cp0

object FunctionalProgramming_Playground extends App {

  var exFunction: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(v1: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(v2: Int): Int = v1 + v2
    }
  }
  
  val supFunction: Function1[Int, Function1[Int, Function1[Int,Int]]] = new Function1[Int, Function1[Int, Function1[Int, Int]]] {
    override def apply(v1: Int): Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
      override def apply(v2: Int): Function1[Int, Int] = new Function1[Int, Int] {
        override def apply(v3: Int): Int = v1 + v2 + v3
      }
    }
  }
  
  var lambdaFunc1: Int => Int = x => x + 1
  var lambdaFunc2: (Int, Int) => Int = (x, y) => x + y
  var lambdaFunc3: (Int, Int, Int) => Int = (x, y, z) => x + y + z
  
  var lambdaFunc4 = (x: Int, y: Int) => x + y
  
  
  var noParamsLambda = () => println("No Params")
  
  
  var curlyBraceLambda = {
    (str: String) => println(str) 
  }
  
  //MOAR syntactic sugar
  var lambdaFunc5 = (x: Int) => x + 1
  var moarSyntaxSugar: Int => Int = _ + 1
  var moadAdder: (Int, Int) => Int = _ + _
  
  println(lambdaFunc1(5))

  println(supFunction(5)(6)(4))

}
