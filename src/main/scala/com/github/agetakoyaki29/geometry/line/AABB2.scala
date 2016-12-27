package com.github.agetakoyaki29.geometry.line

import com.github.agetakoyaki29.geometry.dim2.Dim2


object AABB2 {
  val WHOLE = new AABB2(Point2(Double.NegativeInfinity, Double.NegativeInfinity), Size2.INFINITY) {
    override def contain(op: Point2) = true
  }
}

case class AABB2(val sp: Point2, val size: Size2) {
  
  def contain(pt: Point2) = size.contain((pt-sp).asInstanceOf[Point2])
  
  def containWithDelta(pt: Point2) = size.containWithDelta((pt-sp).asInstanceOf[Point2])
  
}
