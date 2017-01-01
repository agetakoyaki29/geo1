package com.github.agetakoyaki29.geometry.dim2


object Transition extends Dim2Factory[Transition] {
  def apply(d: Double) = new Transition(Dim2(d, d))
  def apply(x: Double, y: Double) = new Transition(Dim2(x, y))
  def apply(dim2: Dim2) = new Transition(dim2)
}

class Transition protected(override protected val wrapped: Dim2) extends Point(wrapped) {

  override def factory: Dim2Factory[_ <: Transition] = Transition
  
  def local(pt: Point): Point = (pt - this).asInstanceOf[Point]
  def local[T](has: HasTransition[T]): HasTransition[T] = has.updatedTransition((has.transition - this).asInstanceOf[Transition])
  
  def global(pt: Point): Point = (pt + this).asInstanceOf[Point]
//  def global(has: HasTransition): Point = (pt + this).asInstanceOf[Point]
  
  def loc[A]: PartialFunction[A, A] = {
//    case pt: Point => local(pt)
    case any => any
  }
  
  def fff[A, R](f: A => R) = { any: A => any match {
    case pt: Point => local(pt).asInstanceOf[A]
    case has: HasTransition[_] => local(has).asInstanceOf[A]
    case any => any
  }} andThen f
  andThen {
//    case pt: Point => global(pt)
//    case has: HasTransition => global(has)
    case any => any 
  }
  
}


trait HasTransition[T] {
  def updatedTransition(transition: Transition): HasTransition[T]
  def transition: Transition
}

class HasTest(override val transition: Transition) extends HasTransition[HasTest] {
  def updatedTransition(op: Transition) = new HasTest(op)
}
