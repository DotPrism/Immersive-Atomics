package com.magneticprism.immersiveatomics.core.lib;

import blusunrize.immersiveengineering.api.Lib;
import net.minecraft.resources.ResourceLocation;

public class ResourceUtils
{
    public static ResourceLocation ia(String path)
    {
        return new ResourceLocation(IALib.MODID, path);
    }

    public static ResourceLocation ie(String path){
        return new ResourceLocation(Lib.MODID, path);
    }
}
