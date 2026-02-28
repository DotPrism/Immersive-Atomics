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

public class GasCentrifuge extends IATemplateMultiblock {
    public static final GasCentrifuge INSTANCE = new GasCentrifuge();

    public GasCentrifuge() {
        super(new ResourceLocation(IALib.MODID, "multiblocks/gas_centrifuge"),
                new BlockPos(0,0,0), new BlockPos(6,1,6), new BlockPos(7,7,7),
                IAMultiblockProvider.GAS_CENTRIFUGE);
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
