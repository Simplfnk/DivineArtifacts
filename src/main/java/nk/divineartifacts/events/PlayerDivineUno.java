package nk.divineartifacts.events;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nk.divineartifacts.config.ServerConfig;
import nk.divineartifacts.init.ModItemGod;
import nk.divineartifacts.utils.Utils;

import static nk.divineartifacts.client.handler.ToggleHelper.toggleShield;
import static nk.divineartifacts.events.DivineHelper.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerDivineUno {
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onLivingAttack(LivingAttackEvent event) {
		if (!ServerConfig.configDivineRing.get()) return;
		if (!toggleShield()) return;
		if (event.getEntity() instanceof Player player) {
			if (player.isCreative() || player.isSpectator()) return;
			ItemStack ring = Utils.getFirstCurio(ModItemGod.ringDivine.get() , player);
			DamageSource damageSource = event.getSource();
			String entName1 = event.getSource().getEntity() != null ? event.getSource().getEntity().getDisplayName().getString() : "";
			String entName2 = event.getSource().getDirectEntity() != null ? event.getSource().getDirectEntity().getDisplayName().getString() : "";
			boolean notPlayer = event.getSource().getEntity() instanceof Player || event.getSource().getDirectEntity() instanceof Player;
			boolean LiveEntity = event.getSource().getEntity() instanceof LivingEntity || event.getSource().getDirectEntity() instanceof LivingEntity;
			boolean NameTrue1 = Names.stream().anyMatch(name -> name.equalsIgnoreCase(entName1));
			boolean NameTrue2 = Names.stream().anyMatch(name -> name.equalsIgnoreCase(entName2));
			boolean isFireDamage = ELEMENT_DAMAGE.stream().anyMatch(damageSource::is);
			if (ring != null) {
				if (isFireDamage) {
					player.clearFire();
					event.setCanceled(true);
				}
				if (LiveEntity && !(notPlayer) && !(NameTrue1 || NameTrue2)) {
					Entity attacker = event.getSource().getEntity();
					playImpactSound(player);
					PunishAttackerWithMobEffects(attacker);
					applyKnockBack(player , attacker);
					event.setCanceled(true);
				}
				if (NameTrue1) {
					Entity attacker = event.getSource().getEntity();
					playImpactSound(player);
					applyKnockBack(player , attacker);
					attacker.kill();
					event.setCanceled(true);
				}
				if (NameTrue2) {
					Entity sources = event.getSource().getDirectEntity();
					playImpactSound(player);
					applyKnockBack(player , sources);
					sources.kill();
					event.setCanceled(true);
				}
				if (event.getSource().getDirectEntity() instanceof Arrow arrow) {
					arrow.kill();
				}
				else if (!(notPlayer)) {
					event.setCanceled(true);
				}
			}
		}
	}
}

