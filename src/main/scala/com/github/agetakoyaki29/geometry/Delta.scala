package com.github.agetakoyaki29.geometry


object Delta {
  val delta: Double = Double.MinPositiveValue * 100
  
  def deltaMin(ds: Double*): Double = ds.map(Math.abs(_)).min * Math.pow(0.1, 15) // 16 ha dame

  def eq0(d: Double): Boolean = Math.abs(d) < delta    // TODO delete
  private def eq0(d: Double, delta: Double): Boolean = Math.abs(d) < delta
  def eq(d1: Double, d2: Double): Boolean = eq0(d1-d2, deltaMin(d1, d2))
  
  def gt0(d: Double, delta: Double): Boolean = d > -delta
  def ge0(d: Double, delta: Double): Boolean = d >= -delta
  def gt(d1: Double, d2: Double): Boolean = gt0(d1-d2, deltaMin(d1, d2))
  def ge(d1: Double, d2: Double): Boolean = ge0(d1-d2, deltaMin(d1, d2))
  
  def lt0(d: Double, delta: Double): Boolean = d < -delta
  def le0(d: Double, delta: Double): Boolean = d <= -delta
  def lt(d1: Double, d2: Double): Boolean = lt0(d1-d2, deltaMin(d1, d2))
  def le(d1: Double, d2: Double): Boolean = le0(d1-d2, deltaMin(d1, d2))
}