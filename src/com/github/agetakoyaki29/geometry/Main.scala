package com.github.agetakoyaki29.geometry

import com.github.agetakoyaki29.geometry.dim2.Dim2
import com.github.agetakoyaki29.geometry.dim2.Vector2
import com.github.agetakoyaki29.geometry.dim2.Complex


object Main {
  def main(args: Array[String]): Unit = {
    println("geo")
    val dim2 = Dim2(1, -1)
    println(dim2)
    println(Vector2(dim2))
    println(~Complex(dim2))
    println(Vector2(dim2).abs)
  }
}