package com.magneticprism.immersiveatomics.common.data;

import com.magneticprism.immersiveatomics.core.lib.IALib;
import com.magneticprism.immersiveatomics.common.data.generators.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

@Mod.EventBusSubscriber(modid = IALib.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class IADataProvider {
    public static Logger log = LogManager.getLogger(IALib.MODID + "/DataGenerator");

    @SubscribeEvent
    public static void generate(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        PackOutput out = generator.getPackOutput();
        final var lookup = event.getLookupProvider();

        log.info("-===== Starting Data Generation for Immersive Atomics =====-");

        if(event.includeServer()){
            IABlockStateProvider blockStateProvider = new IABlockStateProvider(generator, helper);
            generator.addProvider(true, blockStateProvider);
            generator.addProvider(true, new IAItemModelProvider(generator, helper));
            generator.addProvider(true, new IAComplexItemModelProvider(out, helper));
            //BlockTagsProvider blockTags = new IABlockTags(out, lookup, helper);
            //generator.addProvider(true, blockTags);
            //generator.addProvider(true, new IAFluidTags(out, lookup, helper));
            //generator.addProvider(true, new IAItemTags(out, lookup, blockTags.contentsGetter(), helper));
            generator.addProvider(true, new IADynamicModelProvider(blockStateProvider, out, helper));
            generator.addProvider(true, new IARecipes(out));
            //generator.addProvider(true, new LootTableProvider(out, Collections.emptySet(), List.of(new LootTableProvider.SubProviderEntry(IABlockLootProvider::new, LootContextParamSets.BLOCK))));
        }
    }

}
