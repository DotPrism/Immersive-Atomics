package com.magneticprism.immersiveatomics;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import com.magneticprism.immersiveatomics.common.CommonProxy;
import com.magneticprism.immersiveatomics.common.sound.IASounds;
import com.magneticprism.immersiveatomics.common.util.IALogger;

@Mod(modid = ImmersiveAtomics.MODID, version = ImmersiveAtomics.VERSION,
		//xaxaxa, trick! yuo can't steal mod if mod is steal-proof
		certificateFingerprint = "770570c49a2652e64a9b29b9b9d9919ca68b7065",
		dependencies = "required-after:forge@[14.23.5.2820,)"+
				";required-after:immersiveengineering@[0.12,)"+
				";after:immersiveengineering@[0.12,)"+
				";after:immersiveposts@[0.2,)"+
				";before:buildcraftlib")
public class ImmersiveAtomics
{
	public static final String MODID = "immersiveatomics";
	public static final String VERSION = "@VERSION@";

	@SidedProxy(clientSide = "com.magneticprism.immersiveatomics.client.ClientProxy", serverSide = "com.magneticprism.immersiveatomics.common.CommonProxy")
	public static CommonProxy proxy;

	@Instance(MODID)
	public static ImmersiveAtomics INSTANCE;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		IALogger.logger = event.getModLog();
		proxy.preInit();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		//TODO: 03.08.2023 GUI handling
//		NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, proxy);

		proxy.init();
		IASounds.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit();
	}

	@Mod.EventHandler
	public void loadComplete(FMLLoadCompleteEvent event)
	{
		//TODO: 03.08.2023 compat module loading
//		IEAddonCompatModule.doModulesLoadComplete();
	}
}
