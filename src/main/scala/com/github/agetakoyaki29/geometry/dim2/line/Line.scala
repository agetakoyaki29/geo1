package com.github.agetakoyaki29.geometry.dim2.line

import com.github.agetakoyaki29.geometry.dim2.figure.AABB
import com.github.agetakoyaki29.geometry.dim2.Point


case class Line(val sp: Point, val dir: Dir) {

  def isChomp: Boolean = dir match {
    case chomp: DirChomp => true
    case ex: Dir => false
  }

  def localize(pt: Point) = (pt-sp).asInstanceOf[Point]  // TODO macro
  def localize(line: Line) = line-sp

	//

  def +(pt: Point): Line = Line((sp+pt).asInstanceOf[Point], dir)
  def -(pt: Point): Line = Line((sp-pt).asInstanceOf[Point], dir)

  def *(d: Double): Line = Line((sp*d).asInstanceOf[Point], (dir*d).asInstanceOf[Dir])
  def /(d: Double): Line = Line((sp/d).asInstanceOf[Point], (dir/d).asInstanceOf[Dir])

  //

  def distance(pt: Point): Double = dir distance localize(pt)
  def distanceSqr(pt: Point): Double = dir distanceSqr localize(pt)

  def nearest(pt: Point): Point = dir nearest localize(pt)

  def isOn(pt: Point): Boolean = dir isOn localize(pt)
  def isOnWithDelta(pt: Point): Boolean = dir isOnWithDelta localize(pt)

  //

  def sameWithDelta(op: Line) = dir sameWithDelta localize(op)

  def aabb: AABB = dir.aabb + sp

  def isIntersect(op: Line): Boolean = dir isIntersect localize(op)

  def intersect(op: Line): Seq[Point] = dir intersect localize(op)

  //

  def inRegion1(pt: Point) = dir inRegion1 localize(pt)
  def inRegion2(pt: Point) = dir inRegion2 localize(pt)

  def normalize: Line = new Line(sp, dir.normalize)

  def normal: Line = new Line(sp, dir.normal)

  def isNormal(op: Line): Boolean = this.dir isNormal op.dir
  def isNormalWithDelta(op: Line): Boolean = this.dir isNormalWithDelta op.dir

  def isParallel(op: Line): Boolean = this.dir isParallel op.dir
  def isParallelWithDelta(op: Line): Boolean = this.dir isParallelWithDelta op.dir

  def angle: Double = dir.angle

  def angleTo(op: Line): Double = this.dir angleTo op.dir

  def cosTo(op: Line): Double = this.dir cosTo op.dir

  def sinTo(op: Line): Double = this.dir sinTo op.dir

}
