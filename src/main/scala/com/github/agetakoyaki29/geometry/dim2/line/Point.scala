package com.github.agetakoyaki29.geometry.dim2.line

import com.github.agetakoyaki29.geometry.Delta
import com.github.agetakoyaki29.geometry.dim2.Dim2
import com.github.agetakoyaki29.geometry.dim2.Vector2
import com.github.agetakoyaki29.geometry.dim2.Dim2Factory


object Point extends Dim2Factory[Point] {
  def apply(d: Double) = new Point(Dim2(d, d))
  def apply(x: Double, y: Double) = new Point(Dim2(x, y))
  def apply(dim2: Dim2) = new Point(dim2)
}

class Point protected(override protected val wrapped: Dim2) extends Vector2(wrapped) {

  override def factory: Dim2Factory[_ <: Point] = Point

  def distance(op: Point) = Math.sqrt(distanceSqr(op))
  def distanceSqr(op: Point) = (this-op).normSqr

	def sameWithDelta(op: Point): Boolean = Delta.eq(x, op.x) && Delta.eq(y, op.y)

}
