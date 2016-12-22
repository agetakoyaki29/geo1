package com.github.agetakoyaki29.geometry.dim2


abstract class Dim2WrapperComp {
  def apply(d: Double): Dim2Wrapper
  def apply(x: Double, y: Double): Dim2Wrapper
  def apply(dim2: Dim2): Dim2Wrapper
}

abstract class Dim2Wrapper(protected val wrapped: Dim2) extends Dim2 {
  override def x = wrapped.x
  override def y = wrapped.y
  
  override def mapD2(f: Double => Double) = comp(super[Dim2].mapD2(f))
  
  override def toString = this.getClass.getSimpleName + s"(${x}, ${y})"

  def atom: Dim2 = wrapped match {
    case wrapper: Dim2Wrapper => wrapper.atom
    case _ => wrapped
  }
  
  def comp: Dim2WrapperComp
}



