package com.github.agetakoyaki29.geometry.line

import com.github.agetakoyaki29.geometry.Delta
import com.github.agetakoyaki29.geometry.dim2.Dim2
import com.github.agetakoyaki29.geometry.dim2.Vector2
import com.github.agetakoyaki29.geometry.dim2.Dim2Factory


object Point2 extends Dim2Factory[Point2] {
  def apply(d: Double) = new Point2(Dim2(d, d))
  def apply(x: Double, y: Double) = new Point2(Dim2(x, y))
  def apply(dim2: Dim2) = new Point2(dim2)
}

class Point2 protected(override protected val wrapped: Dim2) extends Vector2(wrapped) {

  override def factory: Dim2Factory[_ <: Point2] = Point2

  def distance(op: Point2) = Math.sqrt(distanceSqr(op))
  def distanceSqr(op: Point2) = (this-op).normSqr

	def sameWithDelta(op: Point2): Boolean = Delta.eq(x, op.x) && Delta.eq(y, op.y)

}
