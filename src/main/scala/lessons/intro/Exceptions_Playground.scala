package com.intellibucket.lessons
package lessons.intro

object Exceptions_Playground extends App {
  
  private class MyException extends Exception
  
  private def throwMyException(): Nothing = {
    throw new MyException
  }

  try {
    throwMyException()
  } catch {
    case e: MyException => println("Caught MyException")
  }
}
