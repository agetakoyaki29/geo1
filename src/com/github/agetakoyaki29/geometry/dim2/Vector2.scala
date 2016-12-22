package com.github.agetakoyaki29.geometry.dim2


object Vector2 {
  def apply(d: Double) = new Vector2(Dim2(d, d))
  def apply(x: Double, y: Double) = new Vector2(Dim2(x, y))
  def apply(dim2: Dim2) = new Vector2(dim2)
}

class Vector2 protected(override protected val wrapped: Dim2) extends Dim2Wrapper(wrapped) with Dim2 {
  override def mapD2(f: Double => Double) = Vector2(super[Dim2].mapD2(f))
  
  def unary_+(): Vector2 = this
  def unary_-(): Vector2 = mapD2(-_)
  
  def abs: Vector2 = mapD2(Math.abs(_))
  
  def +(op: Dim2): Vector2 = Vector2(x+op.x, y+op.y)
  def -(op: Dim2): Vector2 = Vector2(x-op.x, y-op.y)
  
  def *(d: Double): Vector2 = Vector2(x*d, y*d)
  def /(d: Double): Vector2 = Vector2(x/d, y/d)
  
  def dot(op: Dim2) = x*op.x + y*op.y

  def cross(op: Dim2) = x*op.y - y*op.x
  
  def norm: Double = Math.sqrt(sqrNorm)
  def sqrNorm: Double = x*x + y*y
}
