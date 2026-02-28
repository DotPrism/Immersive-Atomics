package com.magneticprism.immersiveatomics;

import com.magneticprism.immersiveatomics.client.IAClientRenderHandler;
import com.magneticprism.immersiveatomics.common.network.IAPacketHandler;
import com.magneticprism.immersiveatomics.core.lib.IALib;
import com.magneticprism.immersiveatomics.core.proxy.ClientProxy;
import com.magneticprism.immersiveatomics.core.proxy.CommonProxy;
import com.magneticprism.immersiveatomics.core.registration.IAContent;
import com.magneticprism.immersiveatomics.core.registration.IARegistrationHolder;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import static mctmods.immersivetechnology.common.fluids.ITFluid.BUCKET_DISPENSE_BEHAVIOR;
import static mctmods.immersivetechnology.core.lib.ITLib.MODID;

@Mod(IALib.MODID)
public class ImmersiveAtomics
{
    public static CommonProxy proxy = Util.make(() -> {
        if (FMLLoader.getDist().isClient()) return new ClientProxy();
        return new CommonProxy();
    });

    public ImmersiveAtomics()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IALib.IA_LOGGER.info("IT Starting");
        modEventBus.addListener(this::commonSetup);
        //IARecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);

        IALib.IA_LOGGER.info("Adding ITRegistrationHolder Registries");
        IARegistrationHolder.addRegistersToEventBus(modEventBus);

        IALib.IA_LOGGER.info("Starting Proxy Mod Construction");
        CommonProxy.modConstruction(modEventBus);

        IALib.IA_LOGGER.info("Initialzing Packet Handler");
        IAPacketHandler.initialize();

        IALib.IA_LOGGER.info("Initialzing Mixins and adding Mixin Configuration");
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.immersiveatomics.json");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        IALib.IA_LOGGER.info("HELLO FROM COMMON SETUP");

        //for (ITFluids.FluidEntry entry : ITFluids.ALL_ENTRIES)
            //DispenserBlock.registerBehavior(entry.getBucket(), BUCKET_DISPENSE_BEHAVIOR);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        IALib.IA_LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            IALib.IA_LOGGER.info("HELLO FROM CLIENT SETUP");
            IALib.IA_LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

            IAClientRenderHandler.register();
            IAClientRenderHandler.init(event);
            IAContent.initializeManualEntries();
            //IAContent.registerContainersAndScreens();
        }
    }
}
