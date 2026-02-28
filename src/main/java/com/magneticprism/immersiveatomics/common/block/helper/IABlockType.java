package com.magneticprism.immersiveatomics.common.block.helper;

import net.minecraft.world.level.block.Block;

public interface IABlockType {
    Block getBlock();

    int getColor(int index);
}
