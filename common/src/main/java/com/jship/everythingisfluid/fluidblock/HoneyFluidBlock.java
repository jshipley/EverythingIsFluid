package com.jship.everythingisfluid.fluidblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.phys.Vec3;

public class HoneyFluidBlock extends LiquidBlock {

    public HoneyFluidBlock(FlowingFluid flowingFluid, Properties properties) {
        super(flowingFluid, properties);   
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        entity.makeStuckInBlock(state, new Vec3(0.6, 0.6, 0.6));
    }
}
