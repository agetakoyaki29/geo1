package com.github.agetakoyaki29.geometry.line

import com.github.agetakoyaki29.geometry.dim2.Dim2
import com.github.agetakoyaki29.geometry.dim2.Vector2
import com.github.agetakoyaki29.geometry.dim2.Dim2Factory


object Point2 extends Dim2Factory[Point2] {
  def apply(d: Double) = new Point2(Dim2(d, d))
  def apply(x: Double, y: Double) = new Point2(Dim2(x, y))
  def apply(dim2: Dim2) = new Point2(dim2)
}

class Point2 protected(override protected val wrapped: Dim2)
extends Vector2(wrapped) with Dim2 {
  
  override def factory: Dim2Factory[_ <: Point2] = Point2
  
  def dist(op: Dim2) = Math.sqrt(sqrDist(op))
  def sqrDist(op: Dim2) = (this-op).sqrNorm

  /**
   * -pi ~ pi
   * @return angle
   */
  def angle: Double = {
    if(isZero) throw new RuntimeException("zero point doesn't have angle")
    Math.atan2(y, x)
  }
  
}
