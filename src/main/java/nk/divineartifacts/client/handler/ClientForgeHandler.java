package nk.divineartifacts.client.handler;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nk.divineartifacts.DivineArtifacts;
import nk.divineartifacts.client.Keybindings;
import nk.divineartifacts.init.ModItemGod;
import nk.divineartifacts.utils.Utils;

@Mod.EventBusSubscriber( modid = DivineArtifacts.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT )
public class ClientForgeHandler {
	public static boolean toggleMagnet;
	public static boolean toggleExplode;
	public static boolean toggleShield;

	@SubscribeEvent
	public static void setToggleMagnet(TickEvent.ClientTickEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		ItemStack ring = Utils.getFirstCurio(ModItemGod.ringDivine.get() , minecraft.player);
		if ( ring != null && Keybindings.INSTANCE.magnetKey.consumeClick() && minecraft.player != null ) {
			// TODO: Send a packet to the server.
			TextColor magnetColor = TextColor.fromRgb(0xFFD21A);
			TextColor clOn = TextColor.fromRgb(0x2EC910);
			TextColor clOff = TextColor.fromRgb(0xD21B1B);
			MutableComponent magnet = Component.literal(ChatFormatting.BOLD + "Magnet:").withStyle(s -> s.withColor(magnetColor));
			MutableComponent on = Component.literal(ChatFormatting.BOLD + " On").withStyle(s -> s.withColor(clOn));
			MutableComponent off = Component.literal(ChatFormatting.BOLD + " Off").withStyle(s -> s.withColor(clOff));
			if ( toggleMagnet ) {
				toggleMagnet = false;
				minecraft.player.displayClientMessage(magnet.append(off) , true);
			}
			else {
				toggleMagnet = true;
				minecraft.player.displayClientMessage(magnet.append(on) , true);
			}
		}
	}

	@SubscribeEvent
	public static void setToggleExplodeKey(TickEvent.ClientTickEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		ItemStack ring = Utils.getFirstCurio(ModItemGod.ringDivine.get() , minecraft.player);
		if ( ring != null && Keybindings.INSTANCE.explodedKey.consumeClick() && minecraft.player != null ) {
			// TODO: Send a packet to the server.
			TextColor magnetColor = TextColor.fromRgb(0xFFD21A);
			TextColor clOn = TextColor.fromRgb(0x2EC910);
			TextColor clOff = TextColor.fromRgb(0xD21B1B);
			MutableComponent magnet = Component.literal(ChatFormatting.BOLD + "Chain Attack:").withStyle(s -> s.withColor(magnetColor));
			MutableComponent on = Component.literal(ChatFormatting.BOLD + " On").withStyle(s -> s.withColor(clOn));
			MutableComponent off = Component.literal(ChatFormatting.BOLD + " Off").withStyle(s -> s.withColor(clOff));
			if ( toggleExplode ) {
				toggleExplode = false;
				minecraft.player.displayClientMessage(magnet.append(off) , true);
			}
			else {
				toggleExplode = true;
				minecraft.player.displayClientMessage(magnet.append(on) , true);
			}
		}
	}
	@SubscribeEvent
	public static void setToggleShieldKey(TickEvent.ClientTickEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		ItemStack ring = Utils.getFirstCurio(ModItemGod.ringDivine.get() , minecraft.player);
		if ( ring != null && Keybindings.INSTANCE.shieldKey.consumeClick() && minecraft.player != null ) {
			// TODO: Send a packet to the server.
			TextColor magnetColor = TextColor.fromRgb(0xFFD21A);
			TextColor clOn = TextColor.fromRgb(0x2EC910);
			TextColor clOff = TextColor.fromRgb(0xD21B1B);
			MutableComponent magnet = Component.literal(ChatFormatting.BOLD + "Shield:").withStyle(s -> s.withColor(magnetColor));
			MutableComponent on = Component.literal(ChatFormatting.BOLD + " On").withStyle(s -> s.withColor(clOn));
			MutableComponent off = Component.literal(ChatFormatting.BOLD + " Off").withStyle(s -> s.withColor(clOff));
			if ( toggleShield ) {
				toggleShield = false;
				minecraft.player.displayClientMessage(magnet.append(off) , true);
			}
			else {
				toggleShield = true;
				minecraft.player.displayClientMessage(magnet.append(on) , true);
			}
		}
	}

	public static boolean isShiftPressed;

	@SubscribeEvent
	public static void isShiftKeyPressed(TickEvent.ClientTickEvent event) {
		isShiftPressed = Screen.hasShiftDown();
	}
}
