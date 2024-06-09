package com.magneticprism.immersiveatomics.common;

import java.util.ArrayList;

import com.magneticprism.immersiveatomics.ImmersiveAtomics;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author DotPrism
 * @since 06.06.2024
 */
public class IAContent
{
    public static ArrayList<Block> registeredIABlocks = new ArrayList<Block>();

    public static ArrayList<Item> registeredIAItems = new ArrayList<Item>();

    public static void preInit()
    {

    }

    public static void init()
    {
        
    }

    public static void registerTile(Class<? extends TileEntity> tile)
	{
		String s = tile.getSimpleName();
		s = s.substring(s.indexOf("TileEntity") + "TileEntity".length());
		GameRegistry.registerTileEntity(tile, ImmersiveAtomics.MODID + ":" + s);
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
		for(Block block:registeredIABlocks)
        {
			event.getRegistry().register(block);
		}
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		for (Item item : registeredIAItems)
		{
			event.getRegistry().register(item);
		}
	}

	private static ResourceLocation createRegistryName(String unlocalized)
	{
		unlocalized = unlocalized.substring(unlocalized.indexOf("immersive"));
		unlocalized = unlocalized.replaceFirst("\\.", ":");
		return new ResourceLocation(unlocalized);
	}
}
