package com.magneticprism.immersiveatomics.core.registration;

import blusunrize.immersiveengineering.api.multiblocks.blocks.MultiblockRegistration;
import com.magneticprism.immersiveatomics.common.block.multiblock.logic.IAGasCentrifugeLogic;
import com.magneticprism.immersiveatomics.common.block.multiblock.logic.IANuclearReactorLogic;

public class IAMultiblockProvider
{
    //public static final MultiblockRegistration<IENBurnerLogic.State> BURNER = IENRegistrationHolder.registerMetalMultiblock("burner", new IENBurnerLogic(), () -> IENRegistrationHolder.getMBTemplate.apply("burner"));
    public static final MultiblockRegistration<IAGasCentrifugeLogic.State> GAS_CENTRIFUGE = IARegistrationHolder.registerMetalMultiblock("gas_centrifuge", new IAGasCentrifugeLogic(), () -> IARegistrationHolder.getMBTemplate.apply("gas_centrifuge"));
    public static final MultiblockRegistration<IANuclearReactorLogic.State> NUCLEAR_REACTOR = IARegistrationHolder.registerMetalMultiblock("nuclear_reactor", new IANuclearReactorLogic(), () -> IARegistrationHolder.getMBTemplate.apply("nuclear_reactor"));

    public static void forceClassLoad(){};
}
