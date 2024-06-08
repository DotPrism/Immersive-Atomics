package com.magneticprism.immersiveatomics.common;

import blusunrize.immersiveengineering.common.IEContent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class IACreativeTab extends CreativeTabs
{

    public IACreativeTab(String label) 
    {
        super(label);
    }

    @Override
    public ItemStack getTabIconItem() 
    {
        return new ItemStack(IEContent.blockMetalDecoration0, 1, 3);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public String getTranslatedTabLabel()
    {
        return "itemGroup.immersiveatomics";
    }
}
