package com.github.agetakoyaki29.geometry.dim2.circle

import com.github.agetakoyaki29.geometry.dim2.Point


class Circle(val sp: Point, val range: Range) {

  def localize(pt: Point) = (pt-sp).asInstanceOf[Point]  // TODO macro

}
