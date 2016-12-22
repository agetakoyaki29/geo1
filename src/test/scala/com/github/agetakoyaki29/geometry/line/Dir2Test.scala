package com.github.agetakoyaki29.geometry.line

import org.junit.Test
import org.junit.Assert._
import com.github.agetakoyaki29.geometry.Delta


class Dir2Test {
  
  val dir1 = Dir2(3, 4)
  val dir2 = Dir2(7, -3)
  val pt1 = Point2(-5, 1)
  
  @Test def test1 = normalizedNormIs1(dir1)
  @Test def test2 = nearestIsOn(dir1, pt1)
  @Test def test3 = normalIsNormal(dir1)
  @Test def test4 = normalIsPlus90Degree(dir1)
  @Test def test5 = testSinCosAngle(dir1, dir2)

  def nearestIsOn(dir: Dir2, pt: Point2) = {
    val near = dir.nearest(pt)
    val t1 = dir.isOnWithDelta(near)
    val t2 = dir.isOn(near)
    assertTrue("dir nearest is on dir", t1)
    assertTrue("dir nearest is on dir(strict)", t2)
  }
  
  def normalizedNormIs1(dir: Dir2) = {
    val norm = dir.normalize.norm
    val t1 = Delta.eq(norm, 1)
    assertTrue("normalized norm is 1", t1)
//    assertEquals(1, norm, Delta.delta)
  }
  
  def normalIsNormal(dir: Dir2) = {
    val normal = dir.normal
    val t1 = dir.isNormal(normal)
    assertTrue("dir normal is normal by dir", t1)
  }
  
  def normalIsPlus90Degree(dir: Dir2) = {
    val normal = dir.normal
    val theta = dir.angleTo(normal)
    val zero = theta % (2*Math.PI) - (Math.PI/2)
    val t1 = Delta.eq0(zero)
    assertTrue("dir to normal is 90 degree", t1)
  }
  
  def testSinCosAngle(dir1: Dir2, dir2: Dir2) = {
    val deg = dir1 angleTo dir2
    fail
  }
  
}
