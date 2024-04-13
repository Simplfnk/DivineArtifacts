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
				Config.toggleAioDamage.save();
				Config.spec.save();
				player.displayClientMessage(aio.append(off) , true);
			}
			else {
				Config.toggleAioDamage.set(true);
				Config.toggleAioDamage.save();
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
				Config.toggleBlockBreak.save();
				Config.spec.save();
			}
			else {
				Config.toggleBlockBreak.set(true);
				Config.toggleBlockBreak.save();
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
				Config.toggleExtraDrops.save();
				Config.spec.save();
			}
			else {
				Config.toggleExtraDrops.set(true);
				Config.toggleExtraDrops.save();
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
		MutableComponent on = Component.translatable("on." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOn));
		MutableComponent magnet = Component.translatable("tooltip." + DivineArtifacts.MODID + ".magnet")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));
		MutableComponent aio = Component.translatable("tooltip." + DivineArtifacts.MODID + ".explode")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));
		MutableComponent shield = Component.translatable("tooltip." + DivineArtifacts.MODID + ".shield")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));
		MutableComponent blockBreak = Component.translatable("tooltip." + DivineArtifacts.MODID + ".blockbreak")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));
		MutableComponent extraDrops = Component.translatable("tooltip." + DivineArtifacts.MODID + ".drops")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));
		if (ring && Keybindings.INSTANCE.ToggleAllOn.consumeClick() && player != null) {
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(magnet.append(on));

			player.sendSystemMessage(aio.append(on));

			player.sendSystemMessage(shield.append(on));

			player.sendSystemMessage(blockBreak.append(on));

			player.sendSystemMessage(extraDrops.append(on));

			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			Config.toggleMagnet.set(true);
			Config.toggleAioDamage.set(true);
			Config.toggleShield.set(true);
			Config.toggleBlockBreak.set(true);
			Config.toggleExtraDrops.set(true);

			Config.toggleMagnet.save();
			Config.toggleAioDamage.save();
			Config.toggleShield.save();
			Config.toggleBlockBreak.save();
			Config.toggleExtraDrops.save();
			Config.spec.save();
		}
	}
	@SubscribeEvent
	public static void toggleAllAbilitiesOff(TickEvent.ClientTickEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		boolean ring = Utils.isRingEquipped(ModItemGod.ringDivine.get() , player);
		MutableComponent off = Component.translatable("off." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOff));
		MutableComponent magnet = Component.translatable("tooltip." + DivineArtifacts.MODID + ".magnet")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));
		MutableComponent aio = Component.translatable("tooltip." + DivineArtifacts.MODID + ".explode")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));
		MutableComponent shield = Component.translatable("tooltip." + DivineArtifacts.MODID + ".shield")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));
		MutableComponent blockBreak = Component.translatable("tooltip." + DivineArtifacts.MODID + ".blockbreak")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));
		MutableComponent extraDrops = Component.translatable("tooltip." + DivineArtifacts.MODID + ".drops")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));
		if (ring && Keybindings.INSTANCE.ToggleAllOff.consumeClick() && player != null) {
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(magnet.append(off));

			player.sendSystemMessage(aio.append(off));

			player.sendSystemMessage(shield.append(off));

			player.sendSystemMessage(blockBreak.append(off));

			player.sendSystemMessage(extraDrops.append(off));
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			Config.toggleMagnet.set(false);
			Config.toggleAioDamage.set(false);
			Config.toggleShield.set(false);
			Config.toggleBlockBreak.set(false);
			Config.toggleExtraDrops.set(false);

			Config.toggleMagnet.save();
			Config.toggleAioDamage.save();
			Config.toggleShield.save();
			Config.toggleBlockBreak.save();
			Config.toggleExtraDrops.save();

			Config.spec.save();
		}
	}
	@SubscribeEvent
	public static void showAbilitiesState(TickEvent.ClientTickEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		boolean ring = Utils.isRingEquipped(ModItemGod.ringDivine.get() , player);
		MutableComponent on = Component.translatable("on." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOn));
		MutableComponent off = Component.translatable("off." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOff));
		MutableComponent magnet = Component.translatable("tooltip." + DivineArtifacts.MODID + ".magnet")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));
		MutableComponent aio = Component.translatable("tooltip." + DivineArtifacts.MODID + ".explode")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));
		MutableComponent shield = Component.translatable("tooltip." + DivineArtifacts.MODID + ".shield")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));
		MutableComponent blockBreak = Component.translatable("tooltip." + DivineArtifacts.MODID + ".blockbreak")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));
		MutableComponent extraDrops = Component.translatable("tooltip." + DivineArtifacts.MODID + ".drops")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));
		if (ring && Keybindings.INSTANCE.showRingState.consumeClick() && player != null) {
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			if (toggleMagnet()){
				player.sendSystemMessage(magnet.append(on));
			}
			else {
                player.sendSystemMessage(magnet.append(off));
            }
			if (toggleAioDamage()){
                player.sendSystemMessage(aio.append(on));
            }
			else {
                player.sendSystemMessage(aio.append(off));
            }
			if (toggleShield()){
                player.sendSystemMessage(shield.append(on));
            }
			else {
                player.sendSystemMessage(shield.append(off));
            }
			if (toggleBlockBreak()){
                player.sendSystemMessage(blockBreak.append(on));
            }
			else {
                player.sendSystemMessage(blockBreak.append(off));
            }
			if (toggleExtraDrops()){
                player.sendSystemMessage(extraDrops.append(on));
            }
			else {
                player.sendSystemMessage(extraDrops.append(off));
            }
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));
			player.sendSystemMessage(Component.literal(""));

		}
	}
	public static boolean isShiftPressed;

	@SubscribeEvent
	public static void isShiftKeyPressed(TickEvent.ClientTickEvent event) {
		isShiftPressed = Screen.hasShiftDown();
	}

}
