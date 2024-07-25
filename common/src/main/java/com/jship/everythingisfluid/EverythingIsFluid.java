package com.jship.everythingisfluid;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EverythingIsFluid {
        public static final String MOD_ID = "everything_is_fluid";

        public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

        public static FlowingFluid HONEY;
        public static FlowingFluid FLOWING_HONEY;
        public static Block HONEY_SOURCE_BLOCK;
        public static Item HONEY_BUCKET;
        public static TagKey<Fluid> C_HONEY;
}
