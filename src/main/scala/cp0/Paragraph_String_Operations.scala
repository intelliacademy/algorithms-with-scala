package com.intellibucket.lessons
package cp0

object Paragraph_String_Operations extends App {
  var str: String = "Hello, World!, I am a string and I am a sequence of characters"
  println()
  println(str)
  println("Length of the string: " + str.length)
  println("First character of the string: " + str(0))
  println("Last character of the string: " + str(str.length - 1))
  println("First 5 characters of the string: " + str.substring(0, 5))
  println("Last 5 characters of the string: " + str.substring(str.length - 5, str.length))
  println("First 5 characters of the string: " + str.take(5))
  println("Last 5 characters of the string: " + str.takeRight(5))
  println("Drop first 5 characters of the string: " + str.drop(5))
  println("Drop last 5 characters of the string: " + str.dropRight(5))
  println("Index of the first occurrence of 'a': " + str.indexOf('a'))
  println("Index of the last occurrence of 'a': " + str.lastIndexOf('a'))
  println("Index of the first occurrence of 'a' after 10th character: " + str.indexOf('a', 10))
  println("Index of the last occurrence of 'a' before 10th character: " + str.lastIndexOf('a', 10))
  println("Index of the first occurrence of 'a' after 10th character: " + str.indexOfSlice("am", 10))
  println("Index of the last occurrence of 'a' before 10th character: " + str.lastIndexOfSlice("am", 10))
  println("String contains 'World': " + str.contains("World"))
  println("Split the string by ',': " + str.split(",").mkString("Array(", ", ", ")"))
  println("Starts with 'Hello': " + str.startsWith("Hello"))
  println("Ends with 'characters': " + str.endsWith("characters"))
  println("Replace 'a' with 'A': " + str.replace('a', 'A'))
  println("Replace 'a' with 'A' only first 2 occurrences: " + str.replaceFirst("a", "A"))
  println("Replace 'a' with 'A' all occurrences: " + str.replaceAll("a", "A"))
  println("Lower case: " + str.toLowerCase)
  println("Upper case: " + str.toUpperCase)
}
