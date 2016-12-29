package com.github.agetakoyaki29.geometry

import com.github.agetakoyaki29.geometry.dim2.Complex
import com.github.agetakoyaki29.geometry.dim2.Dim2
import com.github.agetakoyaki29.geometry.dim2.Vector2
import com.github.agetakoyaki29.geometry.dim2.line.Point
import com.github.agetakoyaki29.geometry.dim2.line.Dir
import com.github.agetakoyaki29.geometry.dim2.line.Size


object Main {
  def main(args: Array[String]): Unit = {
    println(Double.MinPositiveValue)
    println("geo")
    val dim2 = Dim2(1, -1)
    println(dim2)
    println(Vector2(dim2))
    println(~Complex(dim2))
    println(Vector2(dim2).abs)
    println(Math.toDegrees(Dir(1,1).angle))
    println(Dir(dim2).normalize)
    println(Size.ZERO)
    println(Size.INFINITY)
  }
}