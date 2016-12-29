package com.github.agetakoyaki29.geometry.dim2.line

import com.github.agetakoyaki29.geometry.dim2.Dim2


object AABB {
  val WHOLE = new AABB(Point(Double.NegativeInfinity, Double.NegativeInfinity), Size.INFINITY) {
    override def contain(op: Point) = true
  }
}

case class AABB(val sp: Point, val size: Size) {
  
  def +(pt: Point): AABB = AABB((sp+pt).asInstanceOf[Point], size)
  def -(pt: Point): AABB = AABB((sp-pt).asInstanceOf[Point], size)
  
  def *(d: Double): AABB = d match {
    case d if d<0 => this.invert * -d
    case d => AABB((sp*d).asInstanceOf[Point], (size*d).asInstanceOf[Size])
  }
  def /(d: Double): AABB = d match {
    case d if d<0 => this.invert / -d
    case d => AABB((sp/d).asInstanceOf[Point], (size/d).asInstanceOf[Size])
  }
  
  def invert = new AABB((-sp-size).asInstanceOf[Point], size)
  
  def contain(pt: Point) = size.contain((pt-sp).asInstanceOf[Point])
  
  def containWithDelta(pt: Point) = size.containWithDelta((pt-sp).asInstanceOf[Point])
  
}
