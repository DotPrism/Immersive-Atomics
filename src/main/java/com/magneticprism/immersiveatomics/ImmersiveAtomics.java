package com.magneticprism.immersiveatomics;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ImmersiveAtomics.MODID)
public class ImmersiveAtomics
{
    public static final String MODID = "immersiveatomics";

    public ImmersiveAtomics(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus =  FMLJavaModLoadingContext.get().getModEventBus();
        //IALib.IEN_LOGGER.info("IEN Starting");
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetup);
        //ARecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);

        //IARegistrationHolder.addRegistersToEventBus(modEventBus);
        //IAContent.modContruction(modEventBus);
    }

    private void clientSetup(FMLClientSetupEvent event)
    {
        //IAClientRenderHandler.register();
        //IAClientRenderHandler.init(event);
        //IAContent.initializeManualEntries();
    }

    public void setup(final FMLCommonSetupEvent event)
    {

    }
}
