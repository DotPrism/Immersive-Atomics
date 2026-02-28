package com.magneticprism.immersiveatomics.core.lib;

import com.mojang.logging.LogUtils;
import mctmods.immersivetechnology.core.lib.ITLib;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

public class IALib
{
    public static final String MODID = "immersiveatomics";
    public static final String VERSION = "1.0.0";

    public static final Logger IA_LOGGER = LogUtils.getLogger();

    public static ResourceLocation makeTextureLocation(String name) { return rl("textures/gui/" + name + ".png"); }

    public static ResourceLocation rl(String name) { return new ResourceLocation(ITLib.MODID, name); }

    public static Logger getNewLogger()
    {
        return  LogUtils.getLogger();
    }
}
