package com.magneticprism.immersiveatomics.common.data.generators;

import blusunrize.immersiveengineering.api.IETags;
import blusunrize.immersiveengineering.common.register.IEItems;
import com.igteam.immersivegeology.common.item.IGGenericItem;
import com.magneticprism.immersiveatomics.common.block.multiblock.recipe.builder.NuclearFuelBuilder;
import com.magneticprism.immersiveatomics.core.lib.*;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class IARecipes extends RecipeProvider
{
    public IARecipes(PackOutput pOutput)
    {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer)
    {
        multiblockRecipes(consumer);
        itemRecipes(consumer);
    }

    private void itemRecipes(Consumer<FinishedRecipe> consumer)
    {

    }


    private void multiblockRecipes(Consumer<FinishedRecipe> consumer)
    {
        IALib.IA_LOGGER.info("Starting Multiblock Recipe Registration");
        //NuclearFuelBuilder.builder().setTime(800).setEnergy(512).build(consumer, ResourceUtils.ia("nuclear/uranium_pellet"));
    }
}
