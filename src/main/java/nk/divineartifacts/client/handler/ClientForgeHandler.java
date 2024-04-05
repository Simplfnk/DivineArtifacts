package nk.divineartifacts.client.handler;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nk.divineartifacts.DivineArtifacts;
import nk.divineartifacts.client.Keybindings;
import nk.divineartifacts.config.Config;
import nk.divineartifacts.init.ModItemGod;
import nk.divineartifacts.utils.Utils;

import static nk.divineartifacts.client.handler.ToggleHelper.*;


@Mod.EventBusSubscriber( modid = DivineArtifacts.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT )

public class ClientForgeHandler {

	@SubscribeEvent
	public static void setToggleMagnet(TickEvent.ClientTickEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		ItemStack ring = Utils.getFirstCurio(ModItemGod.ringDivine.get() , minecraft.player);
		if (ring != null && Keybindings.INSTANCE.magnetKey.consumeClick() && minecraft.player != null ) {
			TextColor magnetColor = TextColor.fromRgb(0xFFD21A);
			TextColor clOn = TextColor.fromRgb(0x2EC910);
			TextColor clOff = TextColor.fromRgb(0xD21B1B);
			MutableComponent magnet = Component.literal(ChatFormatting.BOLD + "Magnet:").withStyle(s -> s.withColor(magnetColor));
			MutableComponent on = Component.literal(ChatFormatting.BOLD + " On").withStyle(s -> s.withColor(clOn));
			MutableComponent off = Component.literal(ChatFormatting.BOLD + " Off").withStyle(s -> s.withColor(clOff));
			if (toggleMagnet()) {
				Config.toggleMagnet.set(false);
				Config.toggleMagnet.save();
				Config.spec.save();
				minecraft.player.displayClientMessage(magnet.append(off) , true);
			}
			else {
				Config.toggleMagnet.set(true);
				Config.toggleMagnet.save();
				Config.spec.save();
				minecraft.player.displayClientMessage(magnet.append(on) , true);
			}

		}
	}

	@SubscribeEvent
	public static void setToggleExplodeKey(TickEvent.ClientTickEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		ItemStack ring = Utils.getFirstCurio(ModItemGod.ringDivine.get() , minecraft.player);
		if (ring != null &&  Keybindings.INSTANCE.explodedKey.consumeClick() && player != null ) {
			TextColor magnetColor = TextColor.fromRgb(0xFFD21A);
			TextColor clOn = TextColor.fromRgb(0x2EC910);
			TextColor clOff = TextColor.fromRgb(0xD21B1B);
			MutableComponent explode = Component.literal(ChatFormatting.BOLD + "Chain Attack:").withStyle(s -> s.withColor(magnetColor));
			MutableComponent on = Component.literal(ChatFormatting.BOLD + " On").withStyle(s -> s.withColor(clOn));
			MutableComponent off = Component.literal(ChatFormatting.BOLD + " Off").withStyle(s -> s.withColor(clOff));
			if (toggleExplode()) {
				Config.toggleExplode.set(false);
				Config.toggleExplode.save();
				Config.spec.save();
				player.displayClientMessage(explode.append(off) , true);
			}
			else {
				Config.toggleExplode.set(true);
				Config.toggleExplode.save();
				Config.spec.save();
				player.displayClientMessage(explode.append(on) , true);
			}

		}
	}

	@SubscribeEvent
	public static void setToggleShieldKey(TickEvent.ClientTickEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		ItemStack ring = Utils.getFirstCurio(ModItemGod.ringDivine.get() , minecraft.player);
		if (ModItemGod.ringDivine.get().isEnabled && Keybindings.INSTANCE.shieldKey.consumeClick() && minecraft.player != null ) {
			TextColor magnetColor = TextColor.fromRgb(0xFFD21A);
			TextColor clOn = TextColor.fromRgb(0x2EC910);
			TextColor clOff = TextColor.fromRgb(0xD21B1B);
			MutableComponent shield = Component.literal(ChatFormatting.BOLD + "Shield:").withStyle(s -> s.withColor(magnetColor));
			MutableComponent on = Component.literal(ChatFormatting.BOLD + " On").withStyle(s -> s.withColor(clOn));
			MutableComponent off = Component.literal(ChatFormatting.BOLD + " Off").withStyle(s -> s.withColor(clOff));
			if (toggleShield()) {
				minecraft.player.displayClientMessage(shield.append(off) , true);
				Config.toggleShield.set(false);
				Config.toggleShield.save();
				Config.spec.save();
			}
			else {
				Config.toggleShield.set(true);
				Config.toggleShield.save();
				Config.spec.save();
				minecraft.player.displayClientMessage(shield.append(on) , true);
			}
		}
	}
	public static boolean isShiftPressed;

	@SubscribeEvent
	public static void isShiftKeyPressed(TickEvent.ClientTickEvent event) {
		isShiftPressed = Screen.hasShiftDown();
	}
}
