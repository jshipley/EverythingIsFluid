package com.jship.everythingisfluid.fluid;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

import com.jship.everythingisfluid.EverythingIsFluid;

public abstract class HoneyFluid extends AbstractEIFluid {

	public HoneyFluid() {
		this.ambientSound = SoundEvents.BEEHIVE_DRIP;
		this.fluidParticle = ParticleTypes.FALLING_HONEY;
		this.dripParticle = ParticleTypes.DRIPPING_HONEY;

	}
	@Override
	public Fluid getFlowing() {
		return EverythingIsFluid.FLOWING_HONEY;
	}

	@Override
	public Fluid getSource() {
		return EverythingIsFluid.HONEY;
	}

	@Override
	public Item getBucket() {
		return EverythingIsFluid.HONEY_BUCKET;
	}

	@Override
	public int getSlopeFindDistance(LevelReader level) {
		return 2;
	}

	@Override
	public BlockState createLegacyBlock(FluidState state) {
		return EverythingIsFluid.HONEY_SOURCE_BLOCK.defaultBlockState().setValue(LiquidBlock.LEVEL, Integer.valueOf(getLegacyLevel(state)));
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isSame(Fluid fluid) {
		return fluid.is(EverythingIsFluid.C_HONEY);
	}

	@Override
	public int getDropOff(LevelReader level) {
		return 2;
	}

	@Override
	public int getTickDelay(LevelReader level) {
		return 60;
	}

	@Override
	public int getSpreadDelay(Level level, BlockPos pos, FluidState currentState, FluidState newState) {
		int i = this.getTickDelay(level);
		if (!currentState.isEmpty()
			&& !newState.isEmpty()
			&& !(Boolean)currentState.getValue(FALLING)
			&& !(Boolean)newState.getValue(FALLING)
			&& newState.getHeight(level, pos) > currentState.getHeight(level, pos)
			&& level.getRandom().nextInt(4) != 0) {
			i *= 4;
		}

		return i;
	}

	@Override
	public Optional<SoundEvent> getPickupSound() {
		return Optional.of(SoundEvents.BEEHIVE_ENTER);
	}

	public static class Flowing extends HoneyFluid {
		@Override
		protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
			super.createFluidStateDefinition(builder);
			builder.add(LEVEL);
		}

		@Override
		public int getAmount(FluidState state) {
			return (Integer)state.getValue(LEVEL);
		}

		@Override
		public boolean isSource(FluidState state) {
			return false;
		}
	}

	public static class Source extends HoneyFluid {
		@Override
		public int getAmount(FluidState state) {
			return 8;
		}

		@Override
		public boolean isSource(FluidState state) {
			return true;
		}
	}
}
