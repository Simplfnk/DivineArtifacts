package nk.divineartifacts.client.handler;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nk.divineartifacts.DivineArtifacts;
import nk.divineartifacts.client.Keybindings;
import nk.divineartifacts.init.ModItems;
import nk.divineartifacts.utils.Utils;
import nk.divineartifacts.utils.UtilsHelper;

import static nk.divineartifacts.client.handler.ToggleHelper.*;
import static nk.divineartifacts.utils.UtilsHelper.playTogOffSound;
import static nk.divineartifacts.utils.UtilsHelper.playTogOnSound;

@Mod.EventBusSubscriber(modid = DivineArtifacts.MODID)

public class ClientForgeHandler {
	private static final TextColor magnetColor = TextColor.fromRgb(0xFFD21A);
	private static final TextColor clOn = TextColor.fromRgb(0x2EC910);
	private static final TextColor clOff = TextColor.fromRgb(0xD21B1B);
	private static final TextColor magnetColor2 = TextColor.fromRgb(0xC882A5);
	private static final TextColor clOn2 = TextColor.fromRgb(0xFFB4B4);
	private static final TextColor clOff2 = TextColor.fromRgb(0xD53F3F);
	private static final TextColor magnetColor3 = TextColor.fromRgb(0x16B96B);
	private static final TextColor clOn3 = TextColor.fromRgb(0x80FF9A);
	private static final TextColor clOff3 = TextColor.fromRgb(0xD53F3F);
	@SubscribeEvent
	public static void setToggleMagnet(TickEvent.ClientTickEvent event) {

		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		boolean ring = Utils.isItemEquipped(ModItems.DIVINE_RING.get() , player);
		boolean attractor = Utils.isItemEquipped(ModItems.GREAT_ATTRACTOR.get() , player);
		MutableComponent magnet = Component.translatable("tooltip." + DivineArtifacts.MODID + ".magnet")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));
		MutableComponent on = Component.translatable("on." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOn));
		MutableComponent off = Component.translatable("off." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOff));

		MutableComponent magnet2 = Component.translatable("tooltip." + DivineArtifacts.MODID + ".magnet.attractor")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor2));
		MutableComponent on2 = Component.translatable("on." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOn2));
		MutableComponent off2 = Component.translatable("off." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOff2));
		if (ring && Keybindings.INSTANCE.magnetKey.consumeClick() && player != null) {
			if (toggleMagnet()) {
				setToggleAttractorMagnet(false);
				player.displayClientMessage(magnet.append(off) , true);
				UtilsHelper.playTogOffSound(player);
			}
			else {
				setToggleAttractorMagnet(true);
				player.displayClientMessage(magnet.append(on) , true);
				playTogOnSound(player);
			}

		}
		if (attractor && Keybindings.INSTANCE.magnetKey.consumeClick() && player != null) {
			if (toggleMagnet()) {
				setToggleAttractorMagnet(false);
				player.displayClientMessage(magnet2.append(off2) , true);
				UtilsHelper.playTogOffSound(player);
			}
			else {
				setToggleAttractorMagnet(true);
				player.displayClientMessage(magnet2.append(on2) , true);
				playTogOnSound(player);
			}

		}
	}

	@SubscribeEvent
	public static void setToggleExplodeKey(TickEvent.ClientTickEvent event) {

		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		boolean ring = Utils.isItemEquipped(ModItems.DIVINE_RING.get() , player);
		boolean nature = Utils.isItemEquipped(ModItems.NATURE_TABLET.get() , player);
		MutableComponent aio = Component.translatable("tooltip." + DivineArtifacts.MODID + ".explode")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));
		MutableComponent on = Component.translatable("on." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOn));
		MutableComponent off = Component.translatable("off." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOff));

		MutableComponent gaia = Component.translatable("gaia.blessing." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor3));
		MutableComponent on2 = Component.translatable("on." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOn3));
		MutableComponent off2 = Component.translatable("off." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOff3));
		if (ring && Keybindings.INSTANCE.explodedKey.consumeClick() && player != null) {
			if (toggleAoeDamage()) {
				setToggleAoeDamage(false);
				player.displayClientMessage(aio.append(off) , true);
				playTogOffSound(player);
			}
			else {
				setToggleAoeDamage(true);
				player.displayClientMessage(aio.append(on) , true);
				playTogOnSound(player);
			}
		}
	}
	@SubscribeEvent
	public static void toggleGaiaBlessing(TickEvent.ClientTickEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		boolean nature = Utils.isItemEquipped(ModItems.NATURE_TABLET.get() , player);
		MutableComponent gaia = Component.translatable("gaia.blessing." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor3));
		MutableComponent on2 = Component.translatable("on." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOn3));
		MutableComponent off2 = Component.translatable("off." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOff3));
		if (nature && Keybindings.INSTANCE.explodedKey.consumeClick() && player != null) {
			if (togGaiaBlessing()) {
				setTogGaiaBlessing(false);
				player.displayClientMessage(gaia.append(off2) , true);
				playTogOffSound(player);
			}
			else {
				setTogGaiaBlessing(true);
				player.displayClientMessage(gaia.append(on2) , true);
				playTogOnSound(player);
			}

		}
	}

	@SubscribeEvent
	public static void setToggleShieldKey(TickEvent.ClientTickEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		boolean ring = Utils.isItemEquipped(ModItems.DIVINE_RING.get() , player);
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
				setToggleShield(false);
				playTogOffSound(player);
				player.displayClientMessage(shield.append(off) , true);
			}
			else {
				setToggleShield(true);
				playTogOnSound(player);
				player.displayClientMessage(shield.append(on) , true);
			}
		}
	}
	@SubscribeEvent
	public static void onToggleShield(TickEvent.ClientTickEvent event) {

		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		boolean tablet = Utils.isItemEquipped(ModItems.HOLY_TABLET.get() , player);
		TextColor title = TextColor.fromRgb(0xFFD700);
		TextColor con = TextColor.fromRgb(0xFDE7A4);
		TextColor cof = TextColor.fromRgb(0xFF6B6B);
		MutableComponent shield = Component.translatable("tooltip." + DivineArtifacts.MODID + ".sun.shield")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(title));
		MutableComponent on = Component.translatable("on." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(con));
		MutableComponent off = Component.translatable("off." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(cof));
		if (tablet && Keybindings.INSTANCE.shieldKey.consumeClick() && player != null) {
			if (TogSunShield()) {
				player.displayClientMessage(shield.append(off) , true);
				setTogSunShield(false);
				playTogOffSound(player);
			}
			else {
				setTogSunShield(true);
				playTogOnSound(player);
				player.displayClientMessage(shield.append(on) , true);
			}
		}
	}
	@SubscribeEvent
	public static void setToggleBlockBreakKey(TickEvent.ClientTickEvent event) {

		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		boolean ring = Utils.isItemEquipped(ModItems.DIVINE_RING.get() , player);
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
				setToggleBlockBreak(false);
				playTogOffSound(player);
			}
			else {
				setToggleBlockBreak(true);
				playTogOnSound(player);
				player.displayClientMessage(blockBreak.append(on) , true);
			}
		}
	}
	@SubscribeEvent
	public static void setToggleExtraLootKey(TickEvent.ClientTickEvent event) {

		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		boolean ring = Utils.isItemEquipped(ModItems.DIVINE_RING.get() , player);
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
				setToggleExtraDrops(false);
				playTogOffSound(player);
			}
			else {
				setToggleExtraDrops(true);
				playTogOnSound(player);
				player.displayClientMessage(extraDrops.append(on) , true);
			}
		}
	}

	@SubscribeEvent
	public static void toggleAllAbilitiesOn(TickEvent.ClientTickEvent event) {

		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		boolean ring = Utils.isItemEquipped(ModItems.DIVINE_RING.get() , player);
		if (ring && Keybindings.INSTANCE.ToggleAllOn.consumeClick() && player != null) {
			setToggleAttractorMagnet(true);
			setToggleAoeDamage(true);
			setToggleShield(true);
			setToggleBlockBreak(true);
			setToggleExtraDrops(true);
			playTogOnSound(player);
		}
	}
	@SubscribeEvent
	public static void toggleAllAbilitiesOff(TickEvent.ClientTickEvent event) {

		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		ItemStack stack = Utils.getFirstCurio(ModItems.DIVINE_RING.get() , player);
		boolean ring = Utils.isItemEquipped(ModItems.DIVINE_RING.get() , player);
		if (ring && Keybindings.INSTANCE.ToggleAllOff.consumeClick() && player != null) {
			setToggleAttractorMagnet(false);
			setToggleAoeDamage(false);
			setToggleShield(false);
			setToggleBlockBreak(false);
			setToggleExtraDrops(false);
			playTogOffSound(player);
		}
	}

	//	@SubscribeEvent
//	public static void addNbtOnKeyPressed(TickEvent.ClientTickEvent event) {
//		Minecraft minecraft = Minecraft.getInstance();
//		Player player = minecraft.player;
//		ItemStack stack = Utils.getFirstCurio(ModItems.ringDivine.get() , player);
//		if (stack != null && Keybindings.INSTANCE.addNBT.consumeClick() && player != null) {
//			if (stack.getItem() instanceof DivineRingBase subItems) {
//				PacketHandler.INSTANCE.sendToServer(new C2SToggle(subItems));
//			}
//		}
//	}
	public static boolean isShiftPressed;

	@SubscribeEvent
	public static void isShiftKeyPressed(TickEvent.ClientTickEvent event) {
		isShiftPressed = Screen.hasShiftDown();
	}

}
