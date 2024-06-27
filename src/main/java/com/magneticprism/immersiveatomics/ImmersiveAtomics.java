package com.magneticprism.immersiveatomics;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import com.magneticprism.immersiveatomics.common.CommonProxy;
import com.magneticprism.immersiveatomics.common.IAContent;
import com.magneticprism.immersiveatomics.common.IACreativeTab;
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

	public static final CreativeTabs iaTab = new IACreativeTab("Immersive Atomics");

	@SidedProxy(clientSide = "com.magneticprism.immersiveatomics.client.ClientProxy", serverSide = "com.magneticprism.immersiveatomics.common.CommonProxy")
	public static CommonProxy proxy;

	@Instance(MODID)
	public static ImmersiveAtomics INSTANCE;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		IALogger.logger = event.getModLog();
		IAContent.preInit();
		proxy.preInit();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		//TODO: 03.08.2023 GUI handling
//		NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, proxy);

		IAContent.init();
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
		//IEAddonCompatModule.doModulesLoadComplete();
	}

	public static <T extends IForgeRegistryEntry<?>> T register(T object, String name)
	{
		return registerByFullName(object, MODID+":"+name);
	}
	public static <T extends IForgeRegistryEntry<?>> T registerByFullName(T object, String name)
	{
		object.setRegistryName(new ResourceLocation(name));
		return GameRegistry.register(object);
	}
	public static Block registerBlockByFullName(Block block, ItemBlock itemBlock, String name)
	{
		block = registerByFullName(block, name);
		registerByFullName(itemBlock, name);
		return block;
	}
	public static Block registerBlockByFullName(Block block, Class<? extends ItemBlock> itemBlock, String name)
	{
		try{
			return registerBlockByFullName(block, itemBlock.getConstructor(Block.class).newInstance(block), name);
		}catch(Exception e){e.printStackTrace();}
		return null;
	}
	public static Block registerBlock(Block block, Class<? extends ItemBlock> itemBlock, String name)
	{
		try{
			return registerBlockByFullName(block, itemBlock.getConstructor(Block.class).newInstance(block), MODID+":"+name);
		}catch(Exception e){e.printStackTrace();}
		return null;
	}
}
