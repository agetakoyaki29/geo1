package com.github.agetakoyaki29.geometry.line

import com.github.agetakoyaki29.geometry.dim2.Dim2


object AABB2 {
  val INFINITY = new AABB2(Point2(Dim2.ZERO), Size2.INFINITY)
}

case class AABB2(val pt: Point2, val size: Size2) {
  
  def contain(op: Point2) = size.contain((op-pt).asInstanceOf[Point2])
  
}
