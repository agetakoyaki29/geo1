package com.github.agetakoyaki29.geometry.line

import com.github.agetakoyaki29.geometry.dim2.Dim2
import com.github.agetakoyaki29.geometry.dim2.Dim2Factory
import com.github.agetakoyaki29.geometry.Delta


object Dir2 extends Dim2Factory[Dir2] {
  def apply(d: Double) = new Dir2(Dim2(d, d))
  def apply(x: Double, y: Double) = new Dir2(Dim2(x, y))
  def apply(dim2: Dim2) = new Dir2(dim2)
}

class Dir2 protected(override protected val wrapped: Dim2) extends Point2(wrapped) {

  override def factory: Dim2Factory[_ <: Dir2] = Dir2
  
  /**
   * distance
   * this sinTo pt * pt.norm
   */
  override def distance(pt: Point2): Double = this cross pt / this.norm
  override def distanceSqr(pt: Point2): Double = Math.pow(this cross pt, 2) / this.normSqr
  
  /**
   * nearest point
   * pt + distance * this.normal.normalize
   * @param pt
   * @return
   */
  def nearest(pt: Point2): Point2 = (pt + this.normal.normalize * -(this distance pt)).asInstanceOf[Point2]  // FIXME
  
  def isOn(pt: Point2): Boolean = this isParallel Dir2(pt)    // FIXME
  def isOnWithDelta(pt: Point2): Boolean = this isParallelWithDelta Dir2(pt)
  
  def normalize: Dir2 = (this/norm).asInstanceOf[Dir2]
  
  def normal: Dir2 = factory(-y, x)
  
  def isNormal(op: Dir2): Boolean = dot(op) == 0
  def isNormalWithDelta(op: Dir2): Boolean = Delta.eq0(dot(op))  // TODO fix eq0
  
  def isParallel(op: Dir2): Boolean = cross(op) == 0
  def isParallelWithDelta(op: Dir2): Boolean = Delta.eq0(cross(op))

  /**
   * get angle
   * -pi ~ pi
   * @return angle, if zero then NaN
   */
  def angle: Double = if(isZero) Double.NaN else Math.atan2(y, x)
  
  /**
   * get angle between two dir
   * @param op
   * @return angle, if zero then NaN
   */
  def angleTo(op: Dir2): Double = op.angle - this.angle
  
  def cosTo(op: Dir2): Double = {
    if(this.isZero) Double.NaN
    else if(op.isZero) Double.NaN
    else this dot op / this.norm / op.norm
  }
  
  def sinTo(op: Dir2): Double = {
    if(this.isZero) Double.NaN
    else if(op.isZero) Double.NaN
    else this cross op / this.norm / op.norm
  }
  
}



object Dir2Chomp extends Dim2Factory[Dir2Chomp] {
  def apply(d: Double) = new Dir2Chomp(Dim2(d, d))
  def apply(x: Double, y: Double) = new Dir2Chomp(Dim2(x, y))
  def apply(dim2: Dim2) = new Dir2Chomp(dim2)
}

class Dir2Chomp protected(override protected val wrapped: Dim2) extends Dir2(wrapped) {

  override def factory: Dim2Factory[_ <: Dir2Chomp] = Dir2Chomp
  
  override def distance(op: Point2): Double = ???
  override def distanceSqr(op: Point2): Double = ???
  
  override def nearest(pt: Point2): Point2 = ???
  
  override def isOn(pt: Point2): Boolean = ???
  override def isOnWithDelta(pt: Point2): Boolean = ???
  
}

