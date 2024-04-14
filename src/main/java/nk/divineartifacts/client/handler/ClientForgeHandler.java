package nk.divineartifacts.client.handler;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.player.Player;
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

@Mod.EventBusSubscriber(modid = DivineArtifacts.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)

public class ClientForgeHandler {
	private static final TextColor magnetColor = TextColor.fromRgb(0xFFD21A);
	private static final TextColor clOn = TextColor.fromRgb(0x2EC910);
	private static final TextColor clOff = TextColor.fromRgb(0xD21B1B);
	@SubscribeEvent
	public static void setToggleMagnet(TickEvent.ClientTickEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		boolean ring = Utils.isRingEquipped(ModItemGod.ringDivine.get() , player);
		MutableComponent magnet = Component.translatable("tooltip." + DivineArtifacts.MODID + ".magnet")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));
		MutableComponent on = Component.translatable("on." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOn));
		MutableComponent off = Component.translatable("off." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOff));
		if (ring && Keybindings.INSTANCE.magnetKey.consumeClick() && minecraft.player != null) {
			if (toggleMagnet()) {
				Config.toggleMagnet.set(false);
				Config.spec.save();
				minecraft.player.displayClientMessage(magnet.append(off) , true);
			}
			else {
				Config.toggleMagnet.set(true);
				Config.spec.save();
				minecraft.player.displayClientMessage(magnet.append(on) , true);
			}

		}
	}

	@SubscribeEvent
	public static void setToggleExplodeKey(TickEvent.ClientTickEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		boolean ring = Utils.isRingEquipped(ModItemGod.ringDivine.get() , player);
		MutableComponent aio = Component.translatable("tooltip." + DivineArtifacts.MODID + ".explode")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));
		MutableComponent on = Component.translatable("on." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOn));
		MutableComponent off = Component.translatable("off." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOff));
		if (ring && Keybindings.INSTANCE.explodedKey.consumeClick() && player != null) {
			if (toggleAioDamage()) {
				Config.toggleAioDamage.set(false);
				Config.spec.save();
				player.displayClientMessage(aio.append(off) , true);
			}
			else {
				Config.toggleAioDamage.set(true);
				Config.spec.save();
				player.displayClientMessage(aio.append(on) , true);
			}

		}
	}

	@SubscribeEvent
	public static void setToggleShieldKey(TickEvent.ClientTickEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		boolean ring = Utils.isRingEquipped(ModItemGod.ringDivine.get() , player);
		MutableComponent shield = Component.translatable("tooltip." + DivineArtifacts.MODID + ".shield")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));
		MutableComponent on = Component.translatable("on." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOn));
		MutableComponent off = Component.translatable("off." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOff));
		if (ring && Keybindings.INSTANCE.shieldKey.consumeClick() && minecraft.player != null) {
			if (toggleShield()) {
				minecraft.player.displayClientMessage(shield.append(off) , true);
				Config.toggleShield.set(false);
				Config.spec.save();
			}
			else {
				Config.toggleShield.set(true);
				Config.spec.save();
				minecraft.player.displayClientMessage(shield.append(on) , true);
			}
		}
	}
	@SubscribeEvent
	public static void setToggleBlockBreakKey(TickEvent.ClientTickEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		boolean ring = Utils.isRingEquipped(ModItemGod.ringDivine.get() , player);
		MutableComponent blockBreak = Component.translatable("tooltip." + DivineArtifacts.MODID + ".blockbreak")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));
		MutableComponent on = Component.translatable("on." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOn));
		MutableComponent off = Component.translatable("off." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOff));
		if (ring && Keybindings.INSTANCE.blockBreakKey.consumeClick() && minecraft.player != null) {
			if (toggleBlockBreak()) {
				minecraft.player.displayClientMessage(blockBreak.append(off) , true);
				Config.toggleBlockBreak.set(false);
				Config.spec.save();
			}
			else {
				Config.toggleBlockBreak.set(true);
				Config.spec.save();
				minecraft.player.displayClientMessage(blockBreak.append(on) , true);
			}
		}
	}
	@SubscribeEvent
	public static void setToggleExtraLootKey(TickEvent.ClientTickEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		boolean ring = Utils.isRingEquipped(ModItemGod.ringDivine.get() , player);
		MutableComponent extraDrops = Component.translatable("tooltip." + DivineArtifacts.MODID + ".drops")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));
		MutableComponent on = Component.translatable("on." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOn));
		MutableComponent off = Component.translatable("off." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOff));
		if (ring && Keybindings.INSTANCE.extraDropsKey.consumeClick() && minecraft.player != null) {
			if (toggleExtraDrops()) {
				minecraft.player.displayClientMessage(extraDrops.append(off) , true);
				Config.toggleExtraDrops.set(false);
				Config.spec.save();
			}
			else {
				Config.toggleExtraDrops.set(true);
				Config.spec.save();
				minecraft.player.displayClientMessage(extraDrops.append(on) , true);
			}
		}
	}

	@SubscribeEvent
	public static void toggleAllAbilitiesOn(TickEvent.ClientTickEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		boolean ring = Utils.isRingEquipped(ModItemGod.ringDivine.get() , player);
		if (ring && Keybindings.INSTANCE.ToggleAllOn.consumeClick() && player != null) {
			Config.toggleMagnet.set(true);
			Config.toggleAioDamage.set(true);
			Config.toggleShield.set(true);
			Config.toggleBlockBreak.set(true);
			Config.toggleExtraDrops.set(true);

			Config.spec.save();
		}
	}
	@SubscribeEvent
	public static void toggleAllAbilitiesOff(TickEvent.ClientTickEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		boolean ring = Utils.isRingEquipped(ModItemGod.ringDivine.get() , player);
		if (ring && Keybindings.INSTANCE.ToggleAllOff.consumeClick() && player != null) {

			Config.toggleMagnet.set(false);
			Config.toggleAioDamage.set(false);
			Config.toggleShield.set(false);
			Config.toggleBlockBreak.set(false);
			Config.toggleExtraDrops.set(false);

			Config.spec.save();
		}
	}
	@SubscribeEvent
	public static void showAbilitiesState(TickEvent.ClientTickEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		boolean ring = Utils.isRingEquipped(ModItemGod.ringDivine.get() , player);
		if (ring && Keybindings.INSTANCE.showRingState.consumeClick() && player != null) {
			if (toggleHudElements()){
				Config.toggleHudElements.set(false);
                Config.spec.save();
			}
			else {
                Config.toggleHudElements.set(true);
                Config.spec.save();
            }

		}
	}
	public static boolean isShiftPressed;

	@SubscribeEvent
	public static void isShiftKeyPressed(TickEvent.ClientTickEvent event) {
		isShiftPressed = Screen.hasShiftDown();
	}

}
