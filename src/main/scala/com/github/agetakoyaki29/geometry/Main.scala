package com.github.agetakoyaki29.geometry

import com.github.agetakoyaki29.geometry.dim2.Complex
import com.github.agetakoyaki29.geometry.dim2.Dim2
import com.github.agetakoyaki29.geometry.dim2.Vector2
import com.github.agetakoyaki29.geometry.line.Point2
import com.github.agetakoyaki29.geometry.line.Dir2
import com.github.agetakoyaki29.geometry.line.Size2


object Main {
  def main(args: Array[String]): Unit = {
    println(Double.MinPositiveValue)
    println("geo")
    val dim2 = Dim2(1, -1)
    println(dim2)
    println(Vector2(dim2))
    println(~Complex(dim2))
    println(Vector2(dim2).abs)
    println(Math.toDegrees(Dir2(1,1).angle))
    println(Dir2(dim2).normalize)
    println(Size2.ZERO)
    println(Size2.INFINITY)
  }
}