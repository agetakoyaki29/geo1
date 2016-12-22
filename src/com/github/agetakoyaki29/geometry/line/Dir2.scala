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
  
  override def dist(op: Point2): Double = ???
  override def sqrDist(op: Point2): Double = ???
  
  def nearest(pt: Point2): Point2 = ???
  
  def isOn(pt: Point2): Boolean = ???
  def isOnWithDelta(pt: Point2): Boolean = ???
  
  def normalize: Dir2 = (this/norm).asInstanceOf[Dir2]
  
  def normal: Dir2 = factory(-y, x)
  
  def isNormal(op: Dir2): Boolean = dot(op) == 0
  def isNormalWithDelta(op: Dir2): Boolean = Delta.eq0(dot(op))
  
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
  
}



object Dir2Chomp extends Dim2Factory[Dir2Chomp] {
  def apply(d: Double) = new Dir2Chomp(Dim2(d, d))
  def apply(x: Double, y: Double) = new Dir2Chomp(Dim2(x, y))
  def apply(dim2: Dim2) = new Dir2Chomp(dim2)
}

class Dir2Chomp protected(override protected val wrapped: Dim2) extends Dir2(wrapped) {

  override def factory: Dim2Factory[_ <: Dir2Chomp] = Dir2Chomp
  
  override def dist(op: Point2): Double = ???
  override def sqrDist(op: Point2): Double = ???
  
  override def nearest(pt: Point2): Point2 = ???
  
  override def isOn(pt: Point2): Boolean = ???
  override def isOnWithDelta(pt: Point2): Boolean = ???
  
}

