package com.jship.everythingisfluid.fabric;

import com.jship.everythingisfluid.EverythingIsFluid;
import com.jship.everythingisfluid.fluid.HoneyFluid;

import io.github.tropheusj.milk.Milk;

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

public class EverythingIsFluidFabric implements ModInitializer {
        static {
                EverythingIsFluid.HONEY = Registry.register(BuiltInRegistries.FLUID,
                                new ResourceLocation(EverythingIsFluid.MOD_ID, "honey"),
                                new HoneyFluid.Source());
                EverythingIsFluid.FLOWING_HONEY = Registry.register(BuiltInRegistries.FLUID,
                                new ResourceLocation(EverythingIsFluid.MOD_ID, "flowing_honey"),
                                new HoneyFluid.Flowing());
                EverythingIsFluid.HONEY_SOURCE_BLOCK = Registry.register(BuiltInRegistries.BLOCK,
                                new ResourceLocation(EverythingIsFluid.MOD_ID, "honey"),
                                new LiquidBlock(EverythingIsFluid.HONEY, BlockBehaviour.Properties.of()
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
                EverythingIsFluid.HONEY_BUCKET = Registry.register(BuiltInRegistries.ITEM,
                                new ResourceLocation(EverythingIsFluid.MOD_ID, "honey_bucket"),
                                new BucketItem(EverythingIsFluid.HONEY,
                                                new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
                EverythingIsFluid.C_HONEY = TagKey.create(Registries.FLUID, new ResourceLocation("c", "honey"));

        }

        public EverythingIsFluidFabric() {
                Milk.enableMilkFluid();
                Milk.enableMilkPlacing();
                Milk.finiteMilkFluid();
                Milk.enableCauldron();
                Milk.enableAllMilkBottles();
        }

        @Override
        public void onInitialize() {
                ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(content -> {
                        content.addAfter(Items.LAVA_BUCKET, EverythingIsFluid.HONEY_BUCKET);
                });
        }
}
