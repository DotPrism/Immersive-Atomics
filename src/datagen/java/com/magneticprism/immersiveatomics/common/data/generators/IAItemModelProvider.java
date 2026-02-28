package com.magneticprism.immersiveatomics.common.data.generators;

import com.magneticprism.immersiveatomics.core.lib.IALib;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.slf4j.Logger;

public class IAItemModelProvider extends ItemModelProvider
{
    private final Logger logger = IALib.getNewLogger();
    public IAItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), IALib.MODID, existingFileHelper);
    }

    private void generateBlockItem(String item_name, String parent_loc)
    {
        String itemLocation = new ResourceLocation(IALib.MODID, "item/"+ item_name).getPath();
        ResourceLocation parentLocation = new ResourceLocation(IALib.MODID, "block/"+parent_loc);

        withExistingParent(itemLocation, parentLocation);
    }

    @Override
    protected void registerModels() {

    }
}
