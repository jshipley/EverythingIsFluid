package com.jship.everythingisfluid.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

import org.jetbrains.annotations.Nullable;

public abstract class AbstractEIFluid extends FlowingFluid {
	protected SoundEvent ambientSound = SoundEvents.WATER_AMBIENT;
	protected SimpleParticleType fluidParticle = ParticleTypes.UNDERWATER;
	protected SimpleParticleType dripParticle = ParticleTypes.DRIPPING_WATER;
	
	@Override
	public void animateTick(Level level, BlockPos pos, FluidState state, RandomSource random) {
		if (!state.isSource() && !(Boolean)state.getValue(FALLING)) {
			if (random.nextInt(64) == 0) {
				level.playLocalSound(
					(double)pos.getX() + 0.5,
					(double)pos.getY() + 0.5,
					(double)pos.getZ() + 0.5,
					this.ambientSound,
					SoundSource.BLOCKS,
					random.nextFloat() * 0.25F + 0.75F,
					random.nextFloat() + 0.5F,
					false
				);
			}
		} else if (random.nextInt(10) == 0) {
			level.addParticle(
				fluidParticle,
				(double)pos.getX() + random.nextDouble(),
				(double)pos.getY() + random.nextDouble(),
				(double)pos.getZ() + random.nextDouble(),
				0.0,
				0.0,
				0.0
			);
		}
	}

	@Nullable
	@Override
	public ParticleOptions getDripParticle() {
		return dripParticle;
	}

	@Override
	protected boolean canConvertToSource(Level level) {
		return false;
	}

	@Override
	protected void beforeDestroyingBlock(LevelAccessor level, BlockPos pos, BlockState state) {
		BlockEntity blockEntity = state.hasBlockEntity() ? level.getBlockEntity(pos) : null;
		Block.dropResources(state, level, pos, blockEntity);
	}

	@Override
	public int getSlopeFindDistance(LevelReader level) {
		return 4;
	}

	@Override
	public int getDropOff(LevelReader level) {
		return 1;
	}

	@Override
	public int getTickDelay(LevelReader level) {
		return 5;
	}

	@Override
	public boolean canBeReplacedWith(FluidState state, BlockGetter level, BlockPos pos, Fluid fluid, Direction direction) {
		return false;
	}

	@Override
	protected float getExplosionResistance() {
		return 100.0F;
	}
}
