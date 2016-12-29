package com.github.agetakoyaki29.geometry.circle2

import com.github.agetakoyaki29.geometry.dim2.line.Point


class Circle2(val sp: Point, val range: Range2) {

  def localize(pt: Point) = (pt-sp).asInstanceOf[Point]  // TODO macro

}
