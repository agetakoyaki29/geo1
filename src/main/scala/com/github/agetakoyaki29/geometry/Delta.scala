package com.github.agetakoyaki29.geometry


object Delta {
  val delta: Double = Double.MinPositiveValue * 100
  
  def deltaMin(ds: Double*): Double = ds.map(Math.abs(_)).min * Math.pow(0.1, 15) // 16 ha dame

  def eq0(d: Double): Boolean = Math.abs(d) < delta    // TODO delete
  def eq0(d: Double, delta: Double): Boolean = Math.abs(d) < delta
  def eq(d1: Double, d2: Double): Boolean = eq0(d1-d2, deltaMin(d1, d2))
}