package com.lambda.client.buildtools.pathfinding.strategies

import baritone.api.pathing.goals.GoalBlock
import baritone.api.process.PathingCommand
import baritone.api.process.PathingCommandType
import com.lambda.client.buildtools.pathfinding.MovementStrategy
import com.lambda.client.event.SafeClientEvent
import net.minecraft.util.math.BlockPos

object PickupStrategy : MovementStrategy {
    override fun SafeClientEvent.generatePathingCommand(blockPos: BlockPos): PathingCommand {
        return PathingCommand(GoalBlock(blockPos), PathingCommandType.SET_GOAL_AND_PATH)
    }

    override fun onLostControl() {
        /* ignore */
    }
}