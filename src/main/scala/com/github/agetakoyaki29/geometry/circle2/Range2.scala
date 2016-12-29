package com.github.agetakoyaki29.geometry.circle2

import com.github.agetakoyaki29.geometry.dim2.Dim2Factory
import com.github.agetakoyaki29.geometry.dim2.Dim2
import com.github.agetakoyaki29.geometry.dim2.line.Point
import com.github.agetakoyaki29.geometry.Delta


object Range2 extends Dim2Factory[Range2] {
  def apply(d: Double) = new Range2(Dim2(d, d))
  def apply(x: Double, y: Double) = new Range2(Dim2(x, y))
  def apply(dim2: Dim2) = new Range2(dim2)
}

class Range2 protected(override protected val wrapped: Dim2) extends Point(wrapped) {

  override def factory: Dim2Factory[_ <: Range2] = Range2

  override def validate = {
    super.validate
    if(x == 0 && y == 0) throw new IllegalArgumentException("Not Zero")
  }

  override def validateElement(d: Double) = d match {
    case Double.PositiveInfinity | Double.NegativeInfinity =>
      new IllegalArgumentException("Not Infinity")
    case d => super.validateElement(d)
  }

}
