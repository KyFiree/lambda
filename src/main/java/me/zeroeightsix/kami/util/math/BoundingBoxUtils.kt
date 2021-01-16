package me.zeroeightsix.kami.util.math

import me.zeroeightsix.kami.util.math.VectorUtils.plus
import me.zeroeightsix.kami.util.math.VectorUtils.times
import me.zeroeightsix.kami.util.math.VectorUtils.toVec3d
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.Vec3d

val AxisAlignedBB.xLength get() = maxX - minX

val AxisAlignedBB.yLength get() = maxY - minY

val AxisAlignedBB.zLength get() = maxY - minY

val AxisAlignedBB.lengths get() = Vec3d(xLength, yLength, zLength)

fun AxisAlignedBB.corners(scale: Double): Array<Vec3d> {
    val growSizes = lengths * (scale - 1.0)
    return grow(growSizes.x, growSizes.y, growSizes.z).corners()
}

fun AxisAlignedBB.corners() = arrayOf(
    Vec3d(minX, minY, minZ),
    Vec3d(minX, minY, maxZ),
    Vec3d(minX, maxY, minZ),
    Vec3d(minX, maxY, maxZ),
    Vec3d(maxX, minY, minZ),
    Vec3d(maxX, minY, maxZ),
    Vec3d(maxX, maxY, minZ),
    Vec3d(maxX, maxY, maxZ),
)

fun AxisAlignedBB.side(side: EnumFacing, scale: Double = 0.5): Vec3d {
    val lengths = lengths
    val sideDirectionVec = side.directionVec.toVec3d()
    return lengths * sideDirectionVec * scale + center
}

fun AxisAlignedBB.faceCorners(facing: EnumFacing, scale: Double) : Array<Vec3d> {

    val selectedCorners = mutableListOf<Vec3d>()
    when (facing) {
        EnumFacing.UP -> {
            selectedCorners.add(Vec3d(minX + scale * xLength, maxY - 0.01, minZ + scale * zLength))
            selectedCorners.add(Vec3d(maxX - scale * xLength, maxY - 0.01, minZ + scale * zLength))
            selectedCorners.add(Vec3d(minX + scale * xLength, maxY - 0.01, maxZ - scale * zLength))
            selectedCorners.add(Vec3d(maxX - scale * xLength, maxY - 0.01, maxZ - scale * zLength))
        }
        EnumFacing.DOWN -> {
            selectedCorners.add(Vec3d(minX + scale * xLength, minY + 0.01, minZ + scale * zLength))
            selectedCorners.add(Vec3d(maxX - scale * xLength, minY + 0.01, minZ + scale * zLength))
            selectedCorners.add(Vec3d(minX + scale * xLength, minY + 0.01, maxZ - scale * zLength))
            selectedCorners.add(Vec3d(maxX - scale * xLength, minY + 0.01, maxZ - scale * zLength))
        }
        EnumFacing.NORTH -> {
            selectedCorners.add(Vec3d(minX + scale * xLength, minY + scale * yLength, minZ + 0.01))
            selectedCorners.add(Vec3d(maxX - scale * xLength, minY + scale * yLength, minZ + 0.01))
            selectedCorners.add(Vec3d(minX + scale * xLength, maxY - scale * yLength, minZ + 0.01))
            selectedCorners.add(Vec3d(maxX - scale * xLength, maxY - scale * yLength, minZ + 0.01))
        }
        EnumFacing.EAST -> {
            selectedCorners.add(Vec3d(maxX - 0.01, minY + scale * yLength, minZ + scale * zLength))
            selectedCorners.add(Vec3d(maxX - 0.01, minY + scale * yLength, minZ + scale * zLength))
            selectedCorners.add(Vec3d(maxX - 0.01, maxY - scale * yLength, maxZ - scale * zLength))
            selectedCorners.add(Vec3d(maxX - 0.01, maxY - scale * yLength, maxZ - scale * zLength))
        }
        EnumFacing.SOUTH -> {
            selectedCorners.add(Vec3d(minX + scale * xLength, minY + scale * yLength, maxZ - 0.01))
            selectedCorners.add(Vec3d(maxX - scale * xLength, minY + scale * yLength, maxZ - 0.01))
            selectedCorners.add(Vec3d(minX + scale * xLength, maxY - scale * yLength, maxZ - 0.01))
            selectedCorners.add(Vec3d(maxX - scale * xLength, maxY - scale * yLength, maxZ - 0.01))
        }
        EnumFacing.WEST -> {
            selectedCorners.add(Vec3d(minX + 0.01, minY + scale * yLength, minZ + scale * zLength))
            selectedCorners.add(Vec3d(minX + 0.01, minY + scale * yLength, minZ + scale * zLength))
            selectedCorners.add(Vec3d(minX + 0.01, maxY - scale * yLength, maxZ - scale * zLength))
            selectedCorners.add(Vec3d(minX + 0.01, maxY - scale * yLength, maxZ - scale * zLength))
        }
    }
    return selectedCorners.toTypedArray()
}