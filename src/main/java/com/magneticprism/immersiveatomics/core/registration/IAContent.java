package com.magneticprism.immersiveatomics.core.registration;

import blusunrize.immersiveengineering.api.ManualHelper;
import blusunrize.lib.manual.ManualEntry;
import blusunrize.lib.manual.ManualInstance;
import blusunrize.lib.manual.Tree;
import com.magneticprism.immersiveatomics.common.tag.IATags;
import com.magneticprism.immersiveatomics.core.lib.IALib;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.ParallelDispatchEvent;

public class IAContent
{
    public static void modContruction(IEventBus event)
    {
        IALib.IA_LOGGER.info("Registering Multiblocks to Immersive Engineering");
        IAMultiblockProvider.forceClassLoad();
        IARegistrationHolder.initialize();
        IATags.initialize();
        IARecipeTypes.init();
        IABlockEntities.BLOCK_ENTITIES.register(event);
        IABlocks.BLOCKS.register(event);
    }

    public static void initializeManualEntries()
    {
        ManualInstance instance = ManualHelper.getManual();
        Tree.InnerNode<ResourceLocation, ManualEntry> parent_category = instance.getRoot().getOrCreateSubnode(new ResourceLocation(IALib.MODID, "main"), 99);

        ManualEntry.ManualEntryBuilder builder = new ManualEntry.ManualEntryBuilder(ManualHelper.getManual());
        builder.readFromFile(new ResourceLocation(IALib.MODID, "intro"));
        instance.addEntry(parent_category, builder.create());

        Tree.InnerNode<ResourceLocation, ManualEntry> multiblock_category = parent_category.getOrCreateSubnode(new ResourceLocation(IALib.MODID, "ia_multiblocks"), 0);
        multiblockEntry(instance, multiblock_category, "gas_centrifuge");
    }

    private static void multiblockEntry(ManualInstance instance, Tree.InnerNode<ResourceLocation, ManualEntry> category, String id)
    {
        ManualEntry.ManualEntryBuilder multiblock = new ManualEntry.ManualEntryBuilder(ManualHelper.getManual());
        multiblock.readFromFile(new ResourceLocation(IALib.MODID, id));
        instance.addEntry(category, multiblock.create());
    }

    public static void initialize(IEventBus event)
    {

    }
}
