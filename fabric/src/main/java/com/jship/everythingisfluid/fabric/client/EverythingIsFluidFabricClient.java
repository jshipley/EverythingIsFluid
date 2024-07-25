package com.jship.everythingisfluid.fabric.client;

import com.jship.everythingisfluid.EverythingIsFluid;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.MinecartRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class EverythingIsFluidFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        FluidRenderHandlerRegistry.INSTANCE.register(EverythingIsFluid.HONEY, EverythingIsFluid.FLOWING_HONEY,
                new SimpleFluidRenderHandler(
                        new ResourceLocation(EverythingIsFluid.MOD_ID, "block/honey_still"),
                        new ResourceLocation(EverythingIsFluid.MOD_ID, "block/honey_flow"),
                        SimpleFluidRenderHandler.WATER_OVERLAY,
                        0xCCFED167));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(),
                EverythingIsFluid.HONEY, EverythingIsFluid.FLOWING_HONEY);
    }
}
