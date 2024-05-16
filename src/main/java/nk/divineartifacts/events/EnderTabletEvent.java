package nk.divineartifacts.events;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.entity.living.EnderManAngerEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nk.divineartifacts.init.ModItems;
import nk.divineartifacts.utils.Utils;

import static nk.divineartifacts.config.ServerConfig.TogEndTabEnderManNoAgro;
import static nk.divineartifacts.config.ServerConfig.TogEnderTablet;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EnderTabletEvent {
	@SubscribeEvent
	public static void docileEnderMan(LivingChangeTargetEvent event) {
		if (!TogEnderTablet.get()) return;
		if (!TogEndTabEnderManNoAgro.get()) return;
		if (event.getNewTarget() instanceof Player player) {
			if (player.isCreative() || player.isSpectator()) return;
			ItemStack enderTablet = Utils.getFirstCurio( ModItems.ENDER_TABLET.get() , player);
			if (enderTablet != null && event.getEntity() instanceof EnderMan) {
				event.setCanceled(true);
			}
		}
	}
	@SubscribeEvent
	public static void onSoundPlayed(PlaySoundEvent event) {
		Player player = Minecraft.getInstance().player;
		ItemStack enderTablet = Utils.getFirstCurio( ModItems.ENDER_TABLET.get() , player);
		if (enderTablet != null && event.getSound() != null && event.getSound().getSource().name().equals("entity.enderman.scream")) {
			event.setSound(null);
		}
	}
	@SubscribeEvent
	public static void enderMan(EnderManAngerEvent event) {
		if (!TogEnderTablet.get()) return;
		if (!TogEndTabEnderManNoAgro.get()) return;
		Player player = event.getPlayer();
		ItemStack enderTablet = Utils.getFirstCurio( ModItems.ENDER_TABLET.get() , player);
		if (enderTablet != null ){
			event.setCanceled(true);
		}
	}
}
