package com.jship.everythingisfluid;

import com.jship.everythingisfluid.fluid.HoneyFluid;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EverythingIsFluid implements ModInitializer {
        public static final String MOD_ID = "everything_is_fluid";

        public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

        public static final FlowingFluid HONEY;
        public static final FlowingFluid FLOWING_HONEY;
        public static final Block HONEY_SOURCE_BLOCK;
        public static final Item HONEY_BUCKET;
        public static final TagKey<Fluid> C_HONEY;

        static {
                HONEY = Registry.register(BuiltInRegistries.FLUID, new ResourceLocation(MOD_ID, "honey"),
                                new HoneyFluid.Source());
                FLOWING_HONEY = Registry.register(BuiltInRegistries.FLUID,
                                new ResourceLocation(MOD_ID, "flowing_honey"), new HoneyFluid.Flowing());
                HONEY_SOURCE_BLOCK = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MOD_ID, "honey"),
                                new LiquidBlock(HONEY, BlockBehaviour.Properties.of()
                                                .mapColor(MapColor.COLOR_YELLOW)
                                                .replaceable()
                                                .noCollission()
                                                .strength(100.0F)
                                                .pushReaction(PushReaction.DESTROY)
                                                .speedFactor(0.2F)
                                                .jumpFactor(0.3F)
                                                .noLootTable()
                                                .liquid()
                                                .sound(SoundType.HONEY_BLOCK)));
                HONEY_BUCKET = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, "honey_bucket"),
                                new BucketItem(HONEY, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
                C_HONEY = TagKey.create(Registries.FLUID, new ResourceLocation("c", "honey"));

        }

        @Override
        public void onInitialize() {
                ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(content -> {
                        content.addAfter(Items.LAVA_BUCKET, HONEY_BUCKET);
                });
        }
}
