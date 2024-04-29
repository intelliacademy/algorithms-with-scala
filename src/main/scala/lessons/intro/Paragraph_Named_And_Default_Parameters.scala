package com.intellibucket.lessons
package lessons.intro

import scala.annotation.tailrec

object Paragraph_Named_And_Default_Parameters extends App {
  def save(name: String, width: Int = 800, height: Int = 600): Unit = {
    println(s"Saving $name with width $width and height $height")
  }

  save("image1", 640, 480)
  save("image2")
  save("image3", height = 480)
  save("image4", width = 320)
  save("image5", height = 480, width = 320)
}
