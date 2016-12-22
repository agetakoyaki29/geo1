package com.github.agetakoyaki29.geometry.dim2


object Complex extends Dim2Factory[Complex] {
  def apply(d: Double) = new Complex(Dim2(d, 0))
  def apply(x: Double, y: Double) = new Complex(Dim2(x, y))
  def apply(dim2: Dim2) = new Complex(dim2)

  val I = this(0, 1)
}

class Complex protected(override protected val wrapped: Dim2) extends Dim2Wrapper(wrapped) {

  override def factory: Dim2Factory[_ <: Complex] = Complex
  
  def unary_+(): Complex = factory(this)
  def unary_-(): Complex = mapD2(-_).asInstanceOf[Complex]
  def unary_~(): Complex = factory(x, -y) // conjugate
  
  def +(op: Complex): Complex = factory(x+op.x, y+op.y)
  def -(op: Complex): Complex = factory(x-op.x, y-op.y)
  
  def *(d: Double): Complex = factory(x*d, y*d)
  def *(op: Complex): Complex = factory(x*op.x - y*op.y, x*op.y + y*op.x)
  
  def /(d: Double): Complex = factory(x/d, y/d)
  def /(op: Complex): Complex = (this * ~op) / op.sqrNorm
  
  def norm: Double = Math.sqrt(sqrNorm)
  def sqrNorm: Double = x*x + y*y
  
//  override def toString(): String = this.getClass.getSimpleName + s"(${x}, ${y}i)"
}
