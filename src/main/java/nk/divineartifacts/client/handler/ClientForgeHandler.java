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
import nk.divineartifacts.config.ToggleAbilities;
import nk.divineartifacts.init.ModItemGod;
import nk.divineartifacts.utils.Utils;

import static nk.divineartifacts.client.handler.ToggleHelper.*;
import static nk.divineartifacts.events.DivineHelper.playTogOffSound;
import static nk.divineartifacts.events.DivineHelper.playTogOnSound;

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
		if (ring && Keybindings.INSTANCE.magnetKey.consumeClick() && player != null) {
			if (toggleMagnet()) {
				ToggleAbilities.toggleMagnet.set(false);
				ToggleAbilities.ClientSpec.save();
				player.displayClientMessage(magnet.append(off) , true);
				playTogOffSound(player);
			}
			else {
				ToggleAbilities.toggleMagnet.set(true);
				ToggleAbilities.ClientSpec.save();
				player.displayClientMessage(magnet.append(on) , true);
				playTogOnSound(player);
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
			if (toggleAoeDamage()) {
				ToggleAbilities.toggleAoeDamage.set(false);
				ToggleAbilities.ClientSpec.save();
				player.displayClientMessage(aio.append(off) , true);
				playTogOffSound(player);
			}
			else {
				ToggleAbilities.toggleAoeDamage.set(true);
				ToggleAbilities.ClientSpec.save();
				player.displayClientMessage(aio.append(on) , true);
				playTogOnSound(player);
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
		if (ring && Keybindings.INSTANCE.shieldKey.consumeClick() && player != null) {
			if (toggleShield()) {
				player.displayClientMessage(shield.append(off) , true);
				ToggleAbilities.toggleShield.set(false);
				playTogOffSound(player);
				ToggleAbilities.ClientSpec.save();
			}
			else {
				ToggleAbilities.toggleShield.set(true);
				ToggleAbilities.ClientSpec.save();
				playTogOnSound(player);
				player.displayClientMessage(shield.append(on) , true);
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
		if (ring && Keybindings.INSTANCE.blockBreakKey.consumeClick() && player != null) {
			if (toggleBlockBreak()) {
				player.displayClientMessage(blockBreak.append(off) , true);
				ToggleAbilities.toggleBlockBreak.set(false);
				ToggleAbilities.ClientSpec.save();
				playTogOffSound(player);
			}
			else {
				ToggleAbilities.toggleBlockBreak.set(true);
				ToggleAbilities.ClientSpec.save();
				playTogOnSound(player);
				player.displayClientMessage(blockBreak.append(on) , true);
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
		if (ring && Keybindings.INSTANCE.extraDropsKey.consumeClick() && player != null) {
			if (toggleExtraDrops()) {
				player.displayClientMessage(extraDrops.append(off) , true);
				ToggleAbilities.toggleExtraDrops.set(false);
				playTogOffSound(player);
				ToggleAbilities.ClientSpec.save();
			}
			else {
				ToggleAbilities.toggleExtraDrops.set(true);
				ToggleAbilities.ClientSpec.save();
				playTogOnSound(player);
				player.displayClientMessage(extraDrops.append(on) , true);
			}
		}
	}

	@SubscribeEvent
	public static void toggleAllAbilitiesOn(TickEvent.ClientTickEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		boolean ring = Utils.isRingEquipped(ModItemGod.ringDivine.get() , player);
		if (ring && Keybindings.INSTANCE.ToggleAllOn.consumeClick() && player != null) {
			ToggleAbilities.toggleMagnet.set(true);
			ToggleAbilities.toggleAoeDamage.set(true);
			ToggleAbilities.toggleShield.set(true);
			ToggleAbilities.toggleBlockBreak.set(true);
			ToggleAbilities.toggleExtraDrops.set(true);
			playTogOnSound(player);
			ToggleAbilities.ClientSpec.save();
		}
	}
	@SubscribeEvent
	public static void toggleAllAbilitiesOff(TickEvent.ClientTickEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		ItemStack stack = Utils.getFirstCurio(ModItemGod.ringDivine.get() , player);
		boolean ring = Utils.isRingEquipped(ModItemGod.ringDivine.get() , player);
		if (ring && Keybindings.INSTANCE.ToggleAllOff.consumeClick() && player != null) {
			ToggleAbilities.toggleMagnet.set(false);
			ToggleAbilities.toggleAoeDamage.set(false);
			ToggleAbilities.toggleShield.set(false);
			ToggleAbilities.toggleBlockBreak.set(false);
			ToggleAbilities.toggleExtraDrops.set(false);
			playTogOffSound(player);
			ToggleAbilities.ClientSpec.save();
		}
	}
	@SubscribeEvent
	public static void showAbilitiesState(TickEvent.ClientTickEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		boolean ring = Utils.isRingEquipped(ModItemGod.ringDivine.get() , player);
		if (ring && Keybindings.INSTANCE.showRingState.consumeClick() && player != null) {
			if (toggleHudElements()) {
				ToggleAbilities.toggleHudElements.set(false);
				ToggleAbilities.ClientSpec.save();
				playTogOffSound(player);
			}
			else {
				ToggleAbilities.toggleHudElements.set(true);
				playTogOnSound(player);
				ToggleAbilities.ClientSpec.save();
			}

		}
	}
//	@SubscribeEvent
//	public static void addNbtOnKeyPressed(TickEvent.ClientTickEvent event) {
//		Minecraft minecraft = Minecraft.getInstance();
//		Player player = minecraft.player;
//		ItemStack stack = Utils.getFirstCurio(ModItemGod.ringDivine.get() , player);
//		if (stack != null && Keybindings.INSTANCE.addNBT.consumeClick() && player != null) {
//			if (stack.getItem() instanceof DivineRingBase rings) {
//				PacketHandler.INSTANCE.sendToServer(new C2SToggle(rings));
//			}
//		}
//	}
	public static boolean isShiftPressed;

	@SubscribeEvent
	public static void isShiftKeyPressed(TickEvent.ClientTickEvent event) {
		isShiftPressed = Screen.hasShiftDown();
	}

}
