package com.magneticprism.immersiveatomics.core.registration;

import com.magneticprism.immersiveatomics.common.item.IAMBFormationItem;
import com.magneticprism.immersiveatomics.common.item.helper.IABaseItem;
import com.magneticprism.immersiveatomics.core.lib.IALib;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class IAItems {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, IALib.MODID);

    private static final HashMap<String, RegistryObject<? extends Item>> ITEM_REGISTRY_MAP = new HashMap<>();
    public static Function<String, Item> getItem = (key) -> ITEM_REGISTRY_MAP.get(key).get();

    public static HashMap<String, RegistryObject<? extends Item>> getItemRegistryMap() { return ITEM_REGISTRY_MAP; }

    public static final ItemRegObject<IAMBFormationItem> IA_FORMATION_TOOL = register("ia_formation_tool", IAMBFormationItem::new);

    public static void initItems() { }

    public static List<Item> getITItems() { return REGISTER.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList()); }

    public static void init(IEventBus event) {
        initItems();
        REGISTER.register(event);
        ITEM_REGISTRY_MAP.put("ia_formation_tool", IA_FORMATION_TOOL.regObject);
    }

    private static <T> Consumer<T> nothing() { return $ -> { }; }

    private static ItemRegObject<IABaseItem> simpleWithStackSize(String name, int maxSize) { return simple(name, p -> p.stacksTo(maxSize), i -> { }); }

    private static ItemRegObject<IABaseItem> simple(String name) { return simple(name, $ -> { }, $ -> { }); }

    private static ItemRegObject<IABaseItem> simple(String name, Consumer<Item.Properties> makeProps, Consumer<IABaseItem> processItem) { return register(name, () -> Util.make(new IABaseItem(Util.make(new Item.Properties(), makeProps)), processItem)); }

    private static <T extends Item> ItemRegObject<T> simple(Supplier<T> make) { return register("ia_formation_tool", make); }

    static <T extends Item> ItemRegObject<T> register(String name, Supplier<? extends T> make) { return new ItemRegObject<>(REGISTER.register(name, make)); }

    private static <T extends Item> ItemRegObject<T> of(T existing) { return new ItemRegObject<>(RegistryObject.create(BuiltInRegistries.ITEM.getKey(existing), ForgeRegistries.ITEMS)); }

    public record ItemRegObject<T extends Item>(RegistryObject<T> regObject) implements Supplier<T>, ItemLike {
        @Override
        @Nonnull
        public T get() { return regObject.get(); }

        @Nonnull
        @Override
        public Item asItem() { return regObject.get(); }
        public ResourceLocation getId() { return regObject.getId(); }
    }
}
