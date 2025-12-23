package com.lx862.mozccaps;

import com.lx862.mozccaps.network.Networking;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.ArmorType;

public class Main implements ModInitializer {
	public static final String MOD_ID = "mozc_caps";
	public static final ResourceKey<Item> CAPS_KEY = ResourceKey.create(Registries.ITEM, id("caps"));
	public static final ResourceKey<Item> CAPS_STRAPPED_KEY = ResourceKey.create(Registries.ITEM, id("caps_strapped"));
	public static final Item CAPS = Registry.register(BuiltInRegistries.ITEM, CAPS_KEY, new Item(new Item.Properties().humanoidArmor(ArmorMaterials.LEATHER, ArmorType.HELMET).setId(CAPS_KEY).useItemDescriptionPrefix()));
	public static final Item CAPS_STRAPPED = Registry.register(BuiltInRegistries.ITEM, CAPS_STRAPPED_KEY, new Item(new Item.Properties().humanoidArmor(ArmorMaterials.LEATHER, ArmorType.HELMET).setId(CAPS_STRAPPED_KEY).useItemDescriptionPrefix()));

	@Override
	public void onInitialize() {
		Networking.registerServer();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}