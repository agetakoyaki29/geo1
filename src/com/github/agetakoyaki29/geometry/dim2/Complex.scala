package com.github.agetakoyaki29.geometry.dim2


object Complex {
  def apply(d: Double) = new Complex(Dim2(d, 0))
  def apply(x: Double, y: Double) = new Complex(Dim2(x, y))
  def apply(dim2: Dim2) = new Complex(dim2)

  val I = this(0, 1)
}

class Complex protected(override protected val wrapped: Dim2) extends Dim2Wrapper(wrapped) with Dim2 {
  override def mapD2(f: Double => Double) = Complex(super[Dim2].mapD2(f))

  def unary_+(): Complex = this
  def unary_-(): Complex = mapD2(-_)
  def unary_~(): Complex = Complex(x, -y) // conjugate
  
  def +(op: Dim2): Complex = Complex(x+op.x, y+op.y)
  def -(op: Dim2): Complex = Complex(x-op.x, y-op.y)
  
  def *(d: Double): Complex = Complex(x*d, y*d)
  def *(op: Dim2): Complex = Complex(x*op.x - y*op.y, x*op.y + y*op.x)
  
  def /(d: Double): Complex = Complex(x/d, y/d)
  def /(op: Dim2): Complex = this / Complex(op)
  def /(op: Complex): Complex = (this * ~op) / op.sqrNorm  // TODO instead of implisit
  
  def norm: Double = Math.sqrt(sqrNorm)
  def sqrNorm: Double = x*x + y*y
  
//  override def toString(): String = this.getClass.getSimpleName + s"(${x}, ${y}i)"
}
