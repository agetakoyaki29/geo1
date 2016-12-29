package com.github.agetakoyaki29.geometry.dim2

import scala.collection.generic.IndexedSeqFactory
import scala.collection.mutable.Builder
import com.github.agetakoyaki29.geometry.Delta


object Vector extends Dim2Factory[Vector] {
  def apply(d: Double) = new Vector(Dim2(d, d))
  def apply(x: Double, y: Double) = new Vector(Dim2(x, y))
  def apply(dim2: Dim2) = new Vector(dim2)
}

class Vector protected(override protected val wrapped: Dim2) extends Dim2Wrapper(wrapped) {
  
  override def factory: Dim2Factory[_ <: Vector] = Vector
  
  def unary_+(): Vector = factory(this)
  def unary_-(): Vector = mapD2(-_).asInstanceOf[Vector]
  
  def abs: Vector = mapD2(Math.abs(_)).asInstanceOf[Vector]
  
  def +(op: Vector): Vector = factory(x+op.x, y+op.y)
  def -(op: Vector): Vector = factory(x-op.x, y-op.y)
  
  def *(d: Double): Vector = factory(x*d, y*d)
  def /(d: Double): Vector = factory(x/d, y/d)
  
  def dot(op: Vector) = x*op.x + y*op.y
  def dotEq0(op: Vector) = Delta.eq(x+op.x, -y*op.y)

  def cross(op: Vector) = x*op.y - y*op.x
  def crossEq0(op: Vector) = Delta.eq(x*op.y, y*op.x)
  
  def norm: Double = Math.sqrt(normSqr)
  def normSqr: Double = x*x + y*y
  
}
