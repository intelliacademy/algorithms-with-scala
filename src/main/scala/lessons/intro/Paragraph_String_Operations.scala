package com.intellibucket.lessons
package lessons.intro

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
  println("Trim: " + "  Hello, World!  ".trim)
  println("Reverse: " + str.reverse)
  println("Concatenate: " + str.concat(" I am concatenated"))
  println("Concatenate: " + str + " I am concatenated")



  var numberString: String = "12345"
  var numberExp: Int = numberString.toInt
  println('a' +: numberString :+ 'z') // prepend and append operations


  println("-" * 50)
  println("Scala Specific : String Interpolation and Formatting")
  println("-" * 50)
  println("S - String Interpolation")
  var exampleString: String = "Hello World from Scala Programming Language !"
  var exmapleNumber: Int = 100
  println(s"String: $exampleString")
  println(s"String toUpperCase ${exampleString.toUpperCase}")
  println(s"String take 5 ${exampleString.take(5)}")
  println(s"String drop 5 ${exampleString.drop(5)}")
  println(s"String dropRight 5 ${exampleString.dropRight(5)}")
  println(s"String indexOf 'W' ${exampleString.indexOf('W')}")
  println(s"String indexOfSlice 'World' ${exampleString.indexOfSlice("World")}")
  println(s"String number ${exmapleNumber + 100}")

  println("-" * 50)
  println("Scala Specific : String Interpolation and Formatting")
  println("-" * 50)
  println("F - String Interpolation")
  var exampleStringF: String = "Hello World from Scala Programming Language !"
  var exmapleNumberF: Double = 100.123456
  println(f"String: $exampleStringF%s")
  println(f"Number: $exmapleNumberF%.3f")
  printf("Print F Number: %.3f\n", exmapleNumberF)


  println("-" * 50)
  println("Scala Specific : String Interpolation and Formatting")
  println("-" * 50)
  println("Raw - String Interpolation")
  var exampleStringRaw: String = "Hello World \n from Scala Programming Language !"
  println(raw"This is a \n new line")
  println(s"$exampleStringRaw")
  println(raw"$exampleStringRaw")
}
