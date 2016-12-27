package com.github.agetakoyaki29.geometry.line


class Line2(val sp: Point2, val dir: Dir2) {
  
//  override def factory: Line2Factory[_ <: Line2] = ???
  
//  def normalize: Dir2 = (this/norm).asInstanceOf[Dir2]
//  
//  def normal: Dir2 = factory(-y, x)
//  
//  def isNormal(op: Dir2): Boolean = dot(op) == 0
//  def isNormalWithDelta(op: Dir2): Boolean = Delta.eq0(dot(op))  // TODO fix eq0
//  
//  def isParallel(op: Dir2): Boolean = cross(op) == 0
//  def isParallelWithDelta(op: Dir2): Boolean = Delta.eq0(cross(op))
  
  def distance(pt: Point2): Double = dir distance (pt-sp).asInstanceOf[Point2]
  def distanceSqr(pt: Point2): Double = dir distanceSqr (pt-sp).asInstanceOf[Point2]
  
  def nearest(pt: Point2): Point2 = ???
  
  def isOn(pt: Point2): Boolean = dir isOn (pt-sp).asInstanceOf[Point2]
  def isOnWithDelta(pt: Point2): Boolean = dir isOnWithDelta (pt-sp).asInstanceOf[Point2]
  
  //
  
  def same(op: Any) = ???
  
  def aabb: AABB2 = dir.aabb + sp
  
  def isIntersect(op: Line2): Boolean = ???
  
  def intersect(op: Line2): Seq[Point2] = ???
  
}