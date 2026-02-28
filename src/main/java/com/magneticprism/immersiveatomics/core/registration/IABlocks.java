package com.magneticprism.immersiveatomics.core.registration;

import mctmods.immersivetechnology.common.blocks.helper.ITStairsBlock;
import mctmods.immersivetechnology.common.blocks.helper.ITWallBlock;
import mctmods.immersivetechnology.core.lib.ITLib;
import mctmods.immersivetechnology.core.registration.ITBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class IABlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ITLib.MODID);
    public static final Map<ResourceLocation, ITBlocks.BlockEntry<? extends SlabBlock>> TO_SLAB = new HashMap<>();
    public static final Map<ResourceLocation, ITBlocks.BlockEntry<? extends ITStairsBlock>> TO_STAIRS = new HashMap<>();
    public static final Map<ResourceLocation, ITBlocks.BlockEntry<? extends ITWallBlock>> TO_WALL = new HashMap<>();

    private static final HashMap<String, RegistryObject<? extends Block>> BLOCK_REGISTRY_MAP = new HashMap<>();
    public static Function<String, Block> getBlock = (key) -> BLOCK_REGISTRY_MAP.get(key).get();

    private static final Supplier<BlockBehaviour.Properties> DEFAULT_METAL_PROPERTIES = () -> BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).strength(3.0F, 15.0F).requiresCorrectToolForDrops();
    private static final Supplier<BlockBehaviour.Properties> METAL_PROPERTIES_NO_OVERLAY = () -> DEFAULT_METAL_PROPERTIES.get().isViewBlocking((state, blockReader, pos) -> false);
    public static final Supplier<BlockBehaviour.Properties> METAL_PROPERTIES_NO_OCCLUSION = () -> METAL_PROPERTIES_NO_OVERLAY.get().noOcclusion().forceSolidOn();
}
