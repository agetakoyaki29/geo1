package com.github.agetakoyaki29.geometry.dim2.figure

import com.github.agetakoyaki29.geometry.dim2.Dim2Factory
import com.github.agetakoyaki29.geometry.dim2.Dim2
import com.github.agetakoyaki29.geometry.Delta
import com.github.agetakoyaki29.geometry.dim2.Point


object Size extends Dim2Factory[Size] {
  def apply(d: Double) = new Size(Dim2(d, d))
  def apply(x: Double, y: Double) = new Size(Dim2(x, y))
  def apply(dim2: Dim2) = new Size(dim2)
  
  val ZERO: Size = new Size(Dim2.ZERO)
  val INFINITY: Size = new Size(Dim2.INFINITY)
}

class Size protected(override protected val wrapped: Dim2) extends Point(wrapped) {

  override def factory: Dim2Factory[_ <: Size] = Size
  
  override def validateElement(d: Double) = d match {
    case d if d < 0 => throw new IllegalArgumentException("Not minas")
    case d => super.validateElement(d)
  }
  
//  def unary_-
  
//  def *
//  def /
  
  def contain(pt: Point) = 0 <= pt.x && pt.x <= x &&
                            0 <= pt.y && pt.y <= y
  
  def containWithDelta(pt: Point) = {
    import Delta.ge
    ge(0, pt.x) && ge(pt.x, x) &&
    ge(0, pt.y) && ge(pt.y, y)
  }
  
}