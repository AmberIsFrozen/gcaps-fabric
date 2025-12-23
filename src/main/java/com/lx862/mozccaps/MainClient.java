package com.lx862.mozccaps;

import com.lx862.mozccaps.render.CapArmorRenderer;
import com.lx862.mozccaps.network.Networking;
import com.lx862.mozccaps.render.HudOverlayRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import org.lwjgl.glfw.GLFW;

public class MainClient implements ClientModInitializer {
	private static final AtamaInput atamaInput = new AtamaInput();
	private static final KeyMapping.Category keybindCategory = KeyMapping.Category.register(Main.id("default"));
	public static final KeyMapping toggleInputKey = KeyBindingHelper.registerKeyBinding(new KeyMapping("key.mozc_caps.toggle_input", GLFW.GLFW_KEY_Y, keybindCategory));

	@Override
	public void onInitializeClient() {
		ArmorRenderer.register(new CapArmorRenderer(false), Main.CAPS);
		ArmorRenderer.register(new CapArmorRenderer(true), Main.CAPS_STRAPPED);

		HudElementRegistry.addLast(Main.id("typing_hud"), new HudOverlayRenderer());
		ClientTickEvents.START_CLIENT_TICK.register(this::handleInput);
		Networking.registerClient();

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(content -> {
			content.accept(Main.CAPS);
			content.accept(Main.CAPS_STRAPPED);
		});
	}

	private void handleInput(Minecraft minecraft) {
		if(minecraft.player == null || !capEquipped(minecraft.player)) return;

		while(toggleInputKey.consumeClick()) {
			atamaInput.toggleInput();
		}

		if(atamaInput.inputEnabled()) {
			// LMB
			while(minecraft.options.keyAttack.consumeClick()) {
				atamaInput.input(minecraft.player.getYHeadRot());
				minecraft.player.swing(minecraft.player.getUsedItemHand());
				Networking.sendKeyPressedClient(minecraft.player);
			}

			// MMB
			while(minecraft.options.keyPickItem.consumeClick()) {
				atamaInput.cycleLayout();
			}

			// RMB
			while(minecraft.options.keyUse.consumeClick()) {
				atamaInput.sendMessage(minecraft);
			}
		}
	}

	public static boolean capEquipped(Player playerEntity) {
		return capEquipped(playerEntity,false) || capEquipped(playerEntity, true);
	}

	public static boolean capEquipped(Player playerEntity, boolean chinStrapped) {
		Item helmetItem = playerEntity.getItemBySlot(EquipmentSlot.HEAD).getItem();
		return chinStrapped ? helmetItem == Main.CAPS_STRAPPED : helmetItem == Main.CAPS;
	}

	public static AtamaInput getAtamaInput() {
		return atamaInput;
	}
}