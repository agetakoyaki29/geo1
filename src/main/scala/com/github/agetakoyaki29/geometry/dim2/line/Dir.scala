package com.github.agetakoyaki29.geometry.dim2.line



import com.github.agetakoyaki29.geometry.Delta
import com.github.agetakoyaki29.geometry.dim2.Dim2
import com.github.agetakoyaki29.geometry.dim2.Dim2Factory
import com.github.agetakoyaki29.geometry.dim2.Point
import com.github.agetakoyaki29.geometry.dim2.Vector
import com.github.agetakoyaki29.geometry.dim2.figure.AABB
import com.github.agetakoyaki29.geometry.dim2.figure.Size


object Dir extends Dim2Factory[Dir] {
  def apply(d: Double) = new Dir(Dim2(d, d))
  def apply(x: Double, y: Double) = new Dir(Dim2(x, y))
  def apply(dim2: Dim2) = new Dir(dim2)
}

/**
 * not Zero, not Infinity
 */
class Dir protected(override protected val wrapped: Dim2) extends Point(wrapped) {

  override def factory: Dim2Factory[_ <: Dir] = Dir

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

  override def *(d: Double): Dir = d match {
    case d if d == 0 => throw new IllegalArgumentException("Not Zero")
    case d if d.isInfinite => throw new IllegalArgumentException("Not Infinite")
    case _ => super.*(d).asInstanceOf[Dir]
  }

  override def /(d: Double): Dir = d match {
    case d if d == 0 => throw new IllegalArgumentException("Not Zero")
    case d if d.isInfinite => throw new IllegalArgumentException("Not Infinite")
    case _ => super./(d).asInstanceOf[Dir]
  }

  //

  /**
   * distance, non abs
   * this sinTo pt * pt.norm
   */
  override def distance(pt: Point): Double = this cross pt / this.norm
  override def distanceSqr(pt: Point): Double = Math.pow(this cross pt, 2) / this.normSqr

  /**
   * nearest point
   * this.normalize * this cosTo pt
   * pt + this.normal.normalize * -distance
   * @param pt
   * @return
   */
  def nearest(pt: Point): Point = (this * ((this dot pt) / this.normSqr / pt.norm)).asInstanceOf[Point]  // FIXME

  def isOn(pt: Point): Boolean = this isParallel Dir(pt)
  def isOnWithDelta(pt: Point): Boolean = this isParallelWithDelta Dir(pt)

  //

  def sameWithDelta(op: Dir): Boolean = this isParallelWithDelta op

  def sameWithDelta(line: Line): Boolean = (this isOnWithDelta line.sp) && (this isParallelWithDelta line.dir)

  def aabb: AABB = AABB.WHOLE

  def intersect(line: Line): Seq[Point] = {
		if(this isParallelWithDelta line.dir) Nil		// TODO be strict?
		else {
  		val t = (line.sp cross line.dir) / (this cross line.dir)
  		println(this isParallelWithDelta line.dir)
  		println(this crossEq0 line.dir)
  		println(this cross line.dir)
  		println(t)
  		Seq((Point(this) * t).asInstanceOf[Point])
		}
	}

  def isIntersect(line: Line): Boolean = !(this isParallelWithDelta line.dir)

  //

  def inRegion1(pt: Point) = Delta.gt(this dot pt, 0)
  def inRegion2(pt: Point) = Delta.gt(-this dot pt-this, 0)

  def normalize: Dir = (this/norm).asInstanceOf[Dir]

  def normal: Dir = factory(-y, x)

  def isNormal(op: Dir): Boolean = dot(op) == 0
  def isNormalWithDelta(op: Dir): Boolean = this dotEq0 op

  def isParallel(op: Dir): Boolean = cross(op) == 0
  def isParallelWithDelta(op: Dir): Boolean = this crossEq0 op

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
  def angleTo(op: Dir): Double = op.angle - this.angle

  def cosTo(op: Dir): Double = this dot op / this.norm / op.norm

  def sinTo(op: Dir): Double = this cross op / this.norm / op.norm

}



object DirChomp extends Dim2Factory[DirChomp] {
  def apply(d: Double) = new DirChomp(Dim2(d, d))
  def apply(x: Double, y: Double) = new DirChomp(Dim2(x, y))
  def apply(dim2: Dim2) = new DirChomp(dim2)
}

class DirChomp protected(override protected val wrapped: Dim2) extends Dir(wrapped) {

  override def factory: Dim2Factory[_ <: DirChomp] = DirChomp

  //

  override def distance(pt: Point): Double = {
    if(!this.inRegion1(pt)) pt.norm
    else if(!this.inRegion2(pt)) (pt-this).norm
    else super.distance(pt)
  }
  override def distanceSqr(pt: Point): Double = {
    if(!this.inRegion1(pt)) pt.normSqr
    else if(!this.inRegion2(pt)) (pt-this).normSqr
    else super.distanceSqr(pt)
  }

  override def nearest(pt: Point): Point = {
    if(!this.inRegion1(pt)) Dir(Dim2.ZERO)
    else if(!this.inRegion2(pt)) this
    else super.nearest(pt)
  }

  override def isOn(pt: Point): Boolean = super.isOn(pt) && aabb.contain(pt)
  override def isOnWithDelta(pt: Point): Boolean = super.isOnWithDelta(pt) && aabb.containWithDelta(pt)

  //

  override def sameWithDelta(op: Dir): Boolean = asInstanceOf[Point].sameWithDelta(op)			// TODO check isChomp

  override def sameWithDelta(line: Line): Boolean = {
    (line.sp sameWithDelta Point(Dim2.ZERO)) && (line.dir sameWithDelta this) ||
    (line.sp sameWithDelta this) && ((-line.dir).asInstanceOf[Point] sameWithDelta this)		// FIXME
	}

  override def aabb: AABB = {
    val min = this.mapD2(Math.min(0, _)).asInstanceOf[Point]
    val max = this.mapD2(Math.max(0, _)).asInstanceOf[Vector]
    new AABB(min, Size(max-min))
  }

  override def intersect(line: Line): Seq[Point] = ???

  override def isIntersect(line: Line): Boolean = ???

}
