package com.github.agetakoyaki29.geometry.dim2


//trait CanFact[T <: Dim2] {  
//  def factory: Dim2Factory[_ <: T]
//}


abstract class Dim2Factory[T <: Dim2] {
  def apply(d: Double): T
  def apply(x: Double, y: Double): T
  def apply(dim2: Dim2): T
}

