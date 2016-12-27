package com.github.agetakoyaki29.geometry.line

import com.github.agetakoyaki29.geometry.dim2.Dim2
import com.github.agetakoyaki29.geometry.dim2.Dim2Factory
import com.github.agetakoyaki29.geometry.Delta
import org.scalactic.TypeCheckedTripleEquals._
import com.github.agetakoyaki29.geometry.dim2.Vector2


object Dir2 extends Dim2Factory[Dir2] {
  def apply(d: Double) = new Dir2(Dim2(d, d))
  def apply(x: Double, y: Double) = new Dir2(Dim2(x, y))
  def apply(dim2: Dim2) = new Dir2(dim2)
}

/**
 * not Zero, not Infinity
 */
class Dir2 protected(override protected val wrapped: Dim2) extends Point2(wrapped) {

  override def factory: Dim2Factory[_ <: Dir2] = Dir2

  override def validate = {
    super.validate
    if(x == 0 && y == 0) throw new IllegalArgumentException("Not Zero")
  }

  override def validateElement(d: Double) = d match {
  case d if d.isInfinite => throw new IllegalArgumentException("Not Infinite")
    case _ => super.validateElement(d)
  }

  // override def isZero = false
  // override def isInfinite = false

  override def *(d: Double): Dir2 = d match {
    case d if d == 0 => throw new IllegalArgumentException("Not Zero")
    case d if d.isInfinite => throw new IllegalArgumentException("Not Infinite")
    case _ => super.*(d).asInstanceOf[Dir2]
  }

  override def /(d: Double): Dir2 = d match {
    case d if d == 0 => throw new IllegalArgumentException("Not Zero")
    case d if d.isInfinite => throw new IllegalArgumentException("Not Infinite")
    case _ => super./(d).asInstanceOf[Dir2]
  }

  //

  /**
   * distance, non abs
   * this sinTo pt * pt.norm
   */
  override def distance(pt: Point2): Double = this cross pt / this.norm
  override def distanceSqr(pt: Point2): Double = Math.pow(this cross pt, 2) / this.normSqr

  /**
   * nearest point
   * this.normalize * this cosTo pt
   * pt + this.normal.normalize * -distance
   * @param pt
   * @return
   */
  def nearest(pt: Point2): Point2 = (this * ((this dot pt) / this.normSqr / pt.norm)).asInstanceOf[Point2]  // FIXME

  def isOn(pt: Point2): Boolean = this isParallel Dir2(pt)
  def isOnWithDelta(pt: Point2): Boolean = this isParallelWithDelta Dir2(pt)

  //

  def sameWithDelta(op: Dir2): Boolean = this isParallelWithDelta op

  def sameWithDelta(line: Line2): Boolean = (this isOnWithDelta line.sp) && (this isParallelWithDelta line.dir)

  def aabb: AABB2 = AABB2.WHOLE

  def intersect(line: Line2): Seq[Point2] = ???

  def isIntersect(line: Line2): Boolean = ???

  //

  def inRegion1(pt: Point2) = Delta.gt(this dot pt, 0)
  def inRegion2(pt: Point2) = Delta.gt(-this dot pt-this, 0)

  def normalize: Dir2 = (this/norm).asInstanceOf[Dir2]

  def normal: Dir2 = factory(-y, x)

  def isNormal(op: Dir2): Boolean = dot(op) == 0
  def isNormalWithDelta(op: Dir2): Boolean = this dotEq0 op

  def isParallel(op: Dir2): Boolean = cross(op) == 0
  def isParallelWithDelta(op: Dir2): Boolean = this crossEq0 op

  /**
   * get angle
   * -pi ~ pi
   * @return angle
   */
  def angle: Double = Math.atan2(y, x)

  /**
   * get angle between two dir
   * @param op
   * @return angle
   */
  def angleTo(op: Dir2): Double = op.angle - this.angle

  def cosTo(op: Dir2): Double = this dot op / this.norm / op.norm

  def sinTo(op: Dir2): Double = this cross op / this.norm / op.norm

}



object Dir2Chomp extends Dim2Factory[Dir2Chomp] {
  def apply(d: Double) = new Dir2Chomp(Dim2(d, d))
  def apply(x: Double, y: Double) = new Dir2Chomp(Dim2(x, y))
  def apply(dim2: Dim2) = new Dir2Chomp(dim2)
}

class Dir2Chomp protected(override protected val wrapped: Dim2) extends Dir2(wrapped) {

  override def factory: Dim2Factory[_ <: Dir2Chomp] = Dir2Chomp

  //

  override def distance(pt: Point2): Double = {
    if(!this.inRegion1(pt)) pt.norm
    else if(!this.inRegion2(pt)) (pt-this).norm
    else super.distance(pt)
  }
  override def distanceSqr(pt: Point2): Double = {
    if(!this.inRegion1(pt)) pt.normSqr
    else if(!this.inRegion2(pt)) (pt-this).normSqr
    else super.distanceSqr(pt)
  }

  override def nearest(pt: Point2): Point2 = {
    if(!this.inRegion1(pt)) Dir2(Dim2.ZERO)
    else if(!this.inRegion2(pt)) this
    else super.nearest(pt)
  }

  override def isOn(pt: Point2): Boolean = super.isOn(pt) && aabb.contain(pt)
  override def isOnWithDelta(pt: Point2): Boolean = super.isOnWithDelta(pt) && aabb.containWithDelta(pt)

  //

  override def sameWithDelta(op: Dir2): Boolean = asInstanceOf[Point2].sameWithDelta(op)			// TODO check isChomp

  override def sameWithDelta(line: Line2): Boolean = {
    (line.sp sameWithDelta Point2(Dim2.ZERO)) && (line.dir sameWithDelta this) ||
    (line.sp sameWithDelta this) && ((-line.dir).asInstanceOf[Point2] sameWithDelta this)		// FIXME
	}

  override def aabb: AABB2 = {
    val min = this.mapD2(Math.min(0, _)).asInstanceOf[Point2]
    val max = this.mapD2(Math.max(0, _)).asInstanceOf[Vector2]
    new AABB2(min, Size2(max-min))
  }

  override def intersect(line: Line2): Seq[Point2] = ???

  override def isIntersect(line: Line2): Boolean = ???

}
