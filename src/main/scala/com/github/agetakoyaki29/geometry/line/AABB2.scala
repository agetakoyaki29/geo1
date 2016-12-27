package com.github.agetakoyaki29.geometry.line

import com.github.agetakoyaki29.geometry.dim2.Dim2


object AABB2 {
  val WHOLE = new AABB2(Point2(Double.NegativeInfinity, Double.NegativeInfinity), Size2.INFINITY) {
    override def contain(op: Point2) = true
  }
}

case class AABB2(val sp: Point2, val size: Size2) {
  
  def +(pt: Point2): AABB2 = AABB2((sp+pt).asInstanceOf[Point2], size)
  def -(pt: Point2): AABB2 = AABB2((sp-pt).asInstanceOf[Point2], size)
  
  def *(d: Double): AABB2 = d match {
    case d if d<0 => this.invert * -d
    case d => AABB2((sp*d).asInstanceOf[Point2], (size*d).asInstanceOf[Size2])
  }
  def /(d: Double): AABB2 = d match {
    case d if d<0 => this.invert / -d
    case d => AABB2((sp/d).asInstanceOf[Point2], (size/d).asInstanceOf[Size2])
  }
  
  def invert = new AABB2((-sp-size).asInstanceOf[Point2], size)
  
  def contain(pt: Point2) = size.contain((pt-sp).asInstanceOf[Point2])
  
  def containWithDelta(pt: Point2) = size.containWithDelta((pt-sp).asInstanceOf[Point2])
  
}
