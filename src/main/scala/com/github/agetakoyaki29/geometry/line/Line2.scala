package com.github.agetakoyaki29.geometry.line


case class Line2(val sp: Point2, val dir: Dir2) {

  def isChomp: Boolean = dir match {
    case chomp: Dir2Chomp => true
    case ex: Dir2 => false
  }

  def localize(pt: Point2) = (pt-sp).asInstanceOf[Point2]  // TODO macro

	//

  def +(pt: Point2): Line2 = Line2((sp+pt).asInstanceOf[Point2], dir)
  def -(pt: Point2): Line2 = Line2((sp-pt).asInstanceOf[Point2], dir)

  def *(d: Double): Line2 = Line2((sp*d).asInstanceOf[Point2], (dir*d).asInstanceOf[Dir2])
  def /(d: Double): Line2 = Line2((sp/d).asInstanceOf[Point2], (dir/d).asInstanceOf[Dir2])

  //

  def distance(pt: Point2): Double = dir distance localize(pt)
  def distanceSqr(pt: Point2): Double = dir distanceSqr localize(pt)

  def nearest(pt: Point2): Point2 = dir nearest localize(pt)

  def isOn(pt: Point2): Boolean = dir isOn localize(pt)
  def isOnWithDelta(pt: Point2): Boolean = dir isOnWithDelta localize(pt)

  //

  def same(op: Line2) = ???

  def aabb: AABB2 = dir.aabb + sp

  def isIntersect(op: Line2): Boolean = ???

  def intersect(op: Line2): Seq[Point2] = ???

  //

  def inRegion1(pt: Point2) = dir inRegion1 localize(pt)
  def inRegion2(pt: Point2) = dir inRegion2 localize(pt)

  def normalize: Line2 = new Line2(sp, dir.normalize)

  def normal: Line2 = new Line2(sp, dir.normal)

  def isNormal(op: Line2): Boolean = this.dir isNormal op.dir
  def isNormalWithDelta(op: Line2): Boolean = this.dir isNormalWithDelta op.dir

  def isParallel(op: Line2): Boolean = this.dir isParallel op.dir
  def isParallelWithDelta(op: Line2): Boolean = this.dir isParallelWithDelta op.dir

  def angle: Double = dir.angle

  def angleTo(op: Line2): Double = this.dir angleTo op.dir

  def cosTo(op: Line2): Double = this.dir cosTo op.dir

  def sinTo(op: Line2): Double = this.dir sinTo op.dir

}
