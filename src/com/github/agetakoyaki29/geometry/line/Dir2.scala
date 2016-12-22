package com.github.agetakoyaki29.geometry.line

import com.github.agetakoyaki29.geometry.dim2.Dim2
import com.github.agetakoyaki29.geometry.dim2.Dim2Factory


object Dir2 extends Dim2Factory[Dir2] {
  def apply(d: Double) = new Dir2(Dim2(d, d))
  def apply(x: Double, y: Double) = new Dir2(Dim2(x, y))
  def apply(dim2: Dim2) = new Dir2(dim2)
}

class Dir2 protected(override protected val wrapped: Dim2) extends Point2(wrapped) with Dim2 {

  override def factory: Dim2Factory[_ <: Dir2] = Dir2
  
  override def dist(op: Dim2) = ???
  override def sqrDist(op: Dim2) = ???
  
  def normalize: Dir2 = (this/norm).asInstanceOf[Dir2]

  // normalize normal isParallel isNormal getAABB isOn nearest
  
}
