package com.github.agetakoyaki29.geometry.line

import com.github.agetakoyaki29.geometry.dim2.Dim2


object AABB2 {
  val WHOLE = new AABB2(Point2(Double.NegativeInfinity, Double.NegativeInfinity), Size2.INFINITY) {
    override def contain(op: Point2) = true
  }
}

case class AABB2(val pt: Point2, val size: Size2) {
  
  def contain(op: Point2) = size.contain((op-pt).asInstanceOf[Point2])
  
  def containWithDelta(op: Point2) = size.containWithDelta((op-pt).asInstanceOf[Point2])
  
}
