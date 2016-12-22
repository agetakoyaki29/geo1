package com.github.agetakoyaki29.geometry


object Delta {
  val delta: Double = Double.MinPositiveValue * 100

  def eq0(d: Double): Boolean = Math.abs(d) < delta
  def eq(d1: Double, d2: Double): Boolean = eq0( d1-d2 )
}