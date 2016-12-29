package com.github.agetakoyaki29.geometry.dim2.circle

import com.github.agetakoyaki29.geometry.dim2.Dim2Factory
import com.github.agetakoyaki29.geometry.dim2.Dim2
import com.github.agetakoyaki29.geometry.dim2.Point
import com.github.agetakoyaki29.geometry.Delta


object Range extends Dim2Factory[Range] {
  def apply(d: Double) = new Range(Dim2(d, d))
  def apply(x: Double, y: Double) = new Range(Dim2(x, y))
  def apply(dim2: Dim2) = new Range(dim2)
}

class Range protected(override protected val wrapped: Dim2) extends Point(wrapped) {

  override def factory: Dim2Factory[_ <: Range] = Range

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
