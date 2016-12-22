package com.github.agetakoyaki29.geometry.dim2

import com.github.agetakoyaki29.geometry.Delta


object Dim2 {
  def apply(x: Double, y: Double): Dim2 = new SimpleD2(x, y)
  def apply(op: Dim2): Dim2 = new SimpleD2(op.x, op.y)
 
  val ZERO = this(0, 0)
  val E1 = this(1, 0)
  val E2 = this(0, 1)
}

trait Dim2 extends IndexedSeq[Double] {
  def x: Double
  def y: Double
  
  override def foreach[U](f: Double => U): Unit = {
    f(x)
    f(y)
  }
  
  override def apply(idx: Int): Double = idx match {
    case 0 => x
    case 1 => y
  }
  
  override def length: Int = 2

  // ----
  
  def isZero: Boolean = x==0 && y==0
  def isZeroWithDelta: Boolean = Delta.eq0(x) && Delta.eq0(y)
  
  def mapD2(f: Double => Double): Dim2 = Dim2(f(x), f(y))

  // -- std --

  override def toString = s"Dim2(${x}, ${y})"

  override def equals(op: Any) = op match {
    case dim2: Dim2 => x==dim2.x && y==dim2.y
    case _ => false
  }
  def equalsWithDelta(op: Any) = op match {
    case dim2: Dim2 => Delta.eq(x, dim2.x) && Delta.eq(y, dim2.y)
    case _ => false
  }
  
  override def hashCode = x.## * 31 + y.##  // TODO Provisional  
}


final class SimpleD2(override val x: Double, override val y: Double) extends Dim2

