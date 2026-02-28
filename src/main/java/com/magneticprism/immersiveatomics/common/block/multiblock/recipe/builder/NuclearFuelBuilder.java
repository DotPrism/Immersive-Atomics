package com.magneticprism.immersiveatomics.common.block.multiblock.recipe.builder;

import blusunrize.immersiveengineering.api.crafting.builders.IEFinishedRecipe;
import com.magneticprism.immersiveatomics.common.block.multiblock.recipe.NuclearFuel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class NuclearFuelBuilder extends IEFinishedRecipe<NuclearFuelBuilder>
{
    private NuclearFuelBuilder()
    {
        super(NuclearFuel.SERIALIZER.get());
        this.maxResultCount=0;
    }

    public static NuclearFuelBuilder builder(ItemLike input) {
        return new NuclearFuelBuilder().addInput(new ItemLike[]{input});
    }

    public static NuclearFuelBuilder builder(ItemStack input) {
        return new NuclearFuelBuilder().addInput(new ItemStack[]{input});
    }

    public static NuclearFuelBuilder builder(TagKey<Item> input) {
        return new NuclearFuelBuilder().addInput(Ingredient.of(input));
    }
}
