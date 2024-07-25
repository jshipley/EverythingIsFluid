package com.jship.everythingisfluid.fabric;

import com.jship.everythingisfluid.EverythingIsFluid;
import com.jship.everythingisfluid.fluid.HoneyFluid;
import com.jship.everythingisfluid.fluidblock.HoneyFluidBlock;

import io.github.tropheusj.milk.Milk;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

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
                                new HoneyFluidBlock(EverythingIsFluid.HONEY, BlockBehaviour.Properties
                                                .copy(Blocks.WATER)
                                                .mapColor(MapColor.COLOR_YELLOW)
                                                .speedFactor(0.2F)
                                                .jumpFactor(0.3F)
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
