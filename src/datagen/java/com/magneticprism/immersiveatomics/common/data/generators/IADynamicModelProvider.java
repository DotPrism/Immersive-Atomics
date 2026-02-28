package com.magneticprism.immersiveatomics.common.data.generators;

import com.magneticprism.immersiveatomics.core.lib.IALib;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import java.util.Map;

public class IADynamicModelProvider extends ModelProvider<IADynamicModelProvider.SimpleModelBuilder>
{
    private final IABlockStateProvider multiblocks;

    public IADynamicModelProvider(IABlockStateProvider multiblocks, PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, IALib.MODID, "dynamic", rl -> new SimpleModelBuilder(rl, existingFileHelper), existingFileHelper);
        this.multiblocks = multiblocks;
    }

    @Override
    protected void registerModels()
    {
        for(Map.Entry<Block, ModelFile> multiblock : multiblocks.unsplitModels.entrySet())
            withExistingParent(BuiltInRegistries.BLOCK.getKey(multiblock.getKey()).getPath(), multiblock.getValue().getLocation());
    }

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(IALib.MODID, path);
    }

    @Nonnull
    @Override
    public String getName()
    {
        return "IA Dynamic models";
    }

    public static class SimpleModelBuilder extends ModelBuilder<SimpleModelBuilder>
    {

        public SimpleModelBuilder(ResourceLocation outputLocation, ExistingFileHelper existingFileHelper)
        {
            super(outputLocation, existingFileHelper);
        }
    }
}
