package com.github.agetakoyaki29.geometry.circle2

import com.github.agetakoyaki29.geometry.line.Point2


class Circle2(val sp: Point2, val range: Range2) {

  def localize(pt: Point2) = (pt-sp).asInstanceOf[Point2]  // TODO macro

}
