package com.magneticprism.immersiveatomics.common.block.multiblock;

import blusunrize.immersiveengineering.api.multiblocks.ClientMultiblocks;
import com.magneticprism.immersiveatomics.common.block.multiblock.helper.IAClientMultiblockProperties;
import com.magneticprism.immersiveatomics.core.lib.IALib;
import com.magneticprism.immersiveatomics.core.registration.IAMultiblockProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public class NuclearReactor extends IATemplateMultiblock {
    public static final NuclearReactor INSTANCE = new NuclearReactor();

    public NuclearReactor() {
        super(new ResourceLocation(IALib.MODID, "multiblocks/nuclear_reactor"),
                new BlockPos(0,0,0), new BlockPos(1,1,1), new BlockPos(13,13,13),
                IAMultiblockProvider.NUCLEAR_REACTOR);
    }


    @Override public void disassemble(Level world, BlockPos origin, boolean mirrored, Direction clickDirectionAtCreation) {
        super.disassemble(world, origin, mirrored, clickDirectionAtCreation);
    }

    @Override public float getManualScale() {
        return 16f;
    }

    @Override
    public void initializeClient(Consumer<ClientMultiblocks.MultiblockManualData> consumer) {
        consumer.accept(new IAClientMultiblockProperties(this, 0,0,0));
    }

    @Override public boolean canBeMirrored() {
        return false;
    }
}