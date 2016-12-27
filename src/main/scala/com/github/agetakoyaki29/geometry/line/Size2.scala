package com.github.agetakoyaki29.geometry.line

import com.github.agetakoyaki29.geometry.dim2.Dim2Factory
import com.github.agetakoyaki29.geometry.dim2.Dim2


object Size2 extends Dim2Factory[Size2] {
  def apply(d: Double) = new Size2(Dim2(d, d))
  def apply(x: Double, y: Double) = new Size2(Dim2(x, y))
  def apply(dim2: Dim2) = new Size2(dim2)
  
  val ZERO: Size2 = new Size2(Dim2.ZERO)
  val INFINITY: Size2 = new Size2(Dim2.INFINITY)
}

class Size2 protected(override protected val wrapped: Dim2) extends Point2(wrapped) {

  override def factory: Dim2Factory[_ <: Size2] = Size2
  
  override def validateElement(d: Double) = d match {
    case d if d < 0 => throw new IllegalArgumentException("Not minas")
    case d => super.validateElement(d)
  }
  
//  def unary_-
  
//  def *
//  def /
  
  def contain(pt: Point2) = 0 <= pt.x && pt.x <= x &&
                            0 <= pt.y && pt.y <= y
  
  def containWithDelta(pt: Point2) = 0 <= pt.x && pt.x <= x &&    // FIXME delta
                                     0 <= pt.y && pt.y <= y
  
}