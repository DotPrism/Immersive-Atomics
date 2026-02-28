package com.magneticprism.immersiveatomics.common.block.multiblock.logic;

import blusunrize.immersiveengineering.api.multiblocks.blocks.component.IClientTickableComponent;
import blusunrize.immersiveengineering.api.multiblocks.blocks.component.IServerTickableComponent;
import blusunrize.immersiveengineering.api.multiblocks.blocks.env.IInitialMultiblockContext;
import blusunrize.immersiveengineering.api.multiblocks.blocks.env.IMultiblockContext;
import blusunrize.immersiveengineering.api.multiblocks.blocks.logic.IMultiblockLogic;
import blusunrize.immersiveengineering.api.multiblocks.blocks.logic.IMultiblockState;
import blusunrize.immersiveengineering.api.multiblocks.blocks.util.*;
        import blusunrize.immersiveengineering.api.utils.CapabilityReference;
import blusunrize.immersiveengineering.common.blocks.multiblocks.logic.interfaces.MBOverlayText;
import com.google.common.collect.ImmutableList;
import com.magneticprism.immersiveatomics.common.block.multiblock.shapes.FullblockShape;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class IANuclearReactorLogic implements IMultiblockLogic<IANuclearReactorLogic.State>, IServerTickableComponent<IANuclearReactorLogic.State>, IClientTickableComponent<IANuclearReactorLogic.State>, MBOverlayText<IANuclearReactorLogic.State>
{
    public static final BlockPos MASTER_OFFSET = new BlockPos(0,0,0);
    public static final int INPUT_SLOT = 0;
    public static final int NUM_SLOTS = 1;

    @Override
    public void tickClient(IMultiblockContext<State> ctx)
    {

    }

    @Override
    public void tickServer(IMultiblockContext<State> ctx) {
        final State state = ctx.getState();
        boolean active = state.active;

        state.active = active;
        ctx.markMasterDirty();
        ctx.requestMasterBESync();
    }

    @Override
    public State createInitialState(IInitialMultiblockContext<State> context)
    {
        return new IANuclearReactorLogic.State(context);
    }

    @Override
    public Function<BlockPos, VoxelShape> shapeGetter(ShapeType shapeType) {
        return FullblockShape.GETTER;
    }

    @Override
    public <T>
    LazyOptional<T> getCapability(IMultiblockContext<State> ctx, CapabilityPosition position, Capability<T> cap)
    {
        //if (cap== ForgeCapabilities.ITEM_HANDLER)
            //return ctx.getState().invCap.cast(ctx);

        return LazyOptional.empty();
    }

    @Nullable
    @Override
    public List<Component> getOverlayText(State state, Player player, boolean b) {
        return List.of();
    }

//    @Nullable
//    @Override
//    public List<Component> getOverlayText(State state, Player player, boolean b)
//    {
//        ItemStack stack = state.inventory.getStackInSlot(0);
//        return List.of(Component.literal("hasFuel: "  + state.hasFuel), stack.getDisplayName(),
//                Component.literal("Count: "+stack.getCount()), Component.literal("Burn Time: "+state.burnTime),
//                Component.literal("Output: "+state.output+ " RF"));
//    }

    //@Override
    //public void dropExtraItems(State state, Consumer<ItemStack> drop)
    //{
    //    MBInventoryUtils.dropItems(state.inventory, drop);
    //}

    public static class State implements IMultiblockState
    {
        private boolean active = false;
        private boolean hasFuel = false;
        private int burnTime = 0;
        //private final SlotwiseItemHandler inventory;
        //private final StoredCapability<IItemHandler> invCap;

        public State(IInitialMultiblockContext<State> ctx)
        {
            final Supplier<@Nullable Level> levelGetter = ctx.levelSupplier();
            //this.inventory = new SlotwiseItemHandler(List.of(
            //        SlotwiseItemHandler.IOConstraint.input(i -> BurnerFuel.getRecipeFor(levelGetter.get(), i)!=null)
            //),
            //        ctx.getMarkDirtyRunnable()
            //);
            //this.invCap = new StoredCapability<>(this.inventory);
        }

        @Override
        public void writeSaveNBT(CompoundTag nbt)
        {
            nbt.putBoolean("active", active);
            nbt.putBoolean("fuel", hasFuel);
            nbt.putInt("burnTime", burnTime);
            //nbt.put("inventory", inventory.serializeNBT());
        }

        @Override
        public void readSaveNBT(CompoundTag nbt)
        {
            active = nbt.getBoolean("active");
            hasFuel = nbt.getBoolean("fuel");
            burnTime = nbt.getInt("burnTime");
            //inventory.deserializeNBT(nbt.getCompound("inventory"));
        }

        @Override
        public void writeSyncNBT(CompoundTag nbt)
        {
            writeSaveNBT(nbt);
            nbt.putBoolean("active", active);
        }

        @Override
        public void readSyncNBT(CompoundTag nbt)
        {
            readSaveNBT(nbt);
            final boolean oldActive = active;
            active = nbt.getBoolean("active");
            if(active&&!oldActive)
            {
                //animation_fanFadeIn = 80;
            }
            else if(!active&&oldActive)
            {
                //animation_fanFadeOut = 80;
            }
        }

        public boolean isActive()
        {
            return active;
        }

        //public SlotwiseItemHandler getInventory()
        //{
        //    return inventory;
        //}
    }
}

