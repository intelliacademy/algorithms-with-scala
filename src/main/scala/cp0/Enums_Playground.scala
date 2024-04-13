package com.intellibucket.lessons
package cp0

object Enums_Playground extends App{

  println(Permissions.READ_WRITE)
}

object ColorEnum extends Enumeration {
  val RED, GREEN, BLUE = Value
}


enum Permissions {
  case READ, WRITE, EXECUTE, NONE;
  case READ_WRITE extends Permissions
  def isAllowed(permission: Permissions): Boolean = {
    this match {
      case READ => permission == READ || permission == WRITE || permission == EXECUTE
      case WRITE => permission == WRITE || permission == EXECUTE
      case EXECUTE => permission == EXECUTE
      case NONE => false
    }
  }

  def somethingDoIt(): Unit = {}
}