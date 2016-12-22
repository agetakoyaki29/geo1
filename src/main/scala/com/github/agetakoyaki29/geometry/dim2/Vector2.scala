package com.github.agetakoyaki29.geometry.dim2

import scala.collection.generic.IndexedSeqFactory
import scala.collection.mutable.Builder


object Vector2 extends Dim2Factory[Vector2] {
  def apply(d: Double) = new Vector2(Dim2(d, d))
  def apply(x: Double, y: Double) = new Vector2(Dim2(x, y))
  def apply(dim2: Dim2) = new Vector2(dim2)
}

class Vector2 protected(override protected val wrapped: Dim2) extends Dim2Wrapper(wrapped) {
  
  override def factory: Dim2Factory[_ <: Vector2] = Vector2
  
  def unary_+(): Vector2 = factory(this)
  def unary_-(): Vector2 = mapD2(-_).asInstanceOf[Vector2]
  
  def abs: Vector2 = mapD2(Math.abs(_)).asInstanceOf[Vector2]
  
  def +(op: Vector2): Vector2 = factory(x+op.x, y+op.y)
  def -(op: Vector2): Vector2 = factory(x-op.x, y-op.y)
  
  def *(d: Double): Vector2 = factory(x*d, y*d)
  def /(d: Double): Vector2 = factory(x/d, y/d)
  
  def dot(op: Vector2) = x*op.x + y*op.y

  def cross(op: Vector2) = x*op.y - y*op.x
  
  def norm: Double = Math.sqrt(normSqr)
  def normSqr: Double = x*x + y*y
  
}
