package com.github.agetakoyaki29.geometry.line

import org.junit.Test
import org.junit.Assert._
import com.github.agetakoyaki29.geometry.Delta


class Dir2Test {
  
  val dir1 = Dir2(3, 4)
  val dir2 = Dir2(7, -3)
  val pt1 = Point2(-5, 1)
  
  @Test def test1 = nearestIsOn(dir1, pt1)
  @Test def test2 = normalizedNormIs1(dir1)
  @Test def test3 = normalIsNormal(dir1)
  @Test def test4 = {normalIsPlus90Degree(dir1); normalIsPlus90Degree(Dir2(pt1))}
  @Test def test5 = testCosAngle(dir1, dir2)
  @Test def test6 = testSinAngle(dir1, dir2)

  def nearestIsOn(dir: Dir2, pt: Point2) = {
    val near = dir.nearest(pt)
    val t1 = dir.isOnWithDelta(near)
    val t2 = dir.isOn(near)
    assertTrue("dir nearest is on dir", t1)
    assertTrue("dir nearest is on dir(strict)", t2)
  }
  
  def normalizedNormIs1(dir: Dir2) = {
    val norm = dir.normalize.norm
    assertEquals(1, norm, Delta.delta)
  }
  
  def normalIsNormal(dir: Dir2) = {
    val normal = dir.normal
    val t1 = dir.isNormal(normal)
    assertTrue("dir normal is normal by dir", t1)
  }
  
  def normalIsPlus90Degree(dir: Dir2) = {
    val normal = dir.normal
    val angle = dir.angleTo(normal)
    val normalized = mod(angle, 2*Math.PI)
    assertEquals(Math.PI/2, normalized, Delta.delta)
  }
  
  def mod(d1: Double, d2: Double) = (d1 % d2 + d2) % d2
  
  def testCosAngle(dir1: Dir2, dir2: Dir2) = {
    val deg = dir1 angleTo dir2
    val cos = dir1 cosTo dir2
    assertEquals(cos, Math.cos(deg), Delta.delta)
  }
  
  def testSinAngle(dir1: Dir2, dir2: Dir2) = {
    val deg = dir1 angleTo dir2
    val sin = dir1 sinTo dir2
    assertEquals(sin, Math.sin(deg), Delta.delta)
  }
  
}
