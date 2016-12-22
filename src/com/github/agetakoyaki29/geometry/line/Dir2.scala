package com.github.agetakoyaki29.geometry.line

import com.github.agetakoyaki29.geometry.dim2.Dim2
import com.github.agetakoyaki29.geometry.dim2.Vector2


object Dir2 {
  def apply(d: Double) = new Dir2(Dim2(d, d))
  def apply(x: Double, y: Double) = new Dir2(Dim2(x, y))
  def apply(dim2: Dim2) = new Dir2(dim2)
}

class Dir2 protected(override protected val wrapped: Dim2) extends Vector2(wrapped) with Dim2 {
  override def mapD2(f: Double => Double) = Dir2(super[Dim2].mapD2(f))
    
}
