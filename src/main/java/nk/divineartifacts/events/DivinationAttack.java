package nk.divineartifacts.events;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nk.divineartifacts.config.ServerConfig;
import nk.divineartifacts.init.ModItems;
import nk.divineartifacts.utils.Utils;

import static nk.divineartifacts.client.handler.ToggleHelper.toggleAoeDamage;
import static nk.divineartifacts.config.ServerConfig.AoeDamage;
import static nk.divineartifacts.config.ServerConfig.configDivineRing;
import static nk.divineartifacts.utils.UtilsHelper.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DivinationAttack {
	private static final String MARKER = "depleted.arrow";

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onPlayerAttack(LivingAttackEvent event) {
		if (!(toggleAoeDamage())) return;
		if(!(ServerConfig.configDivineRing.get())) return;
		Entity sourceEntity = event.getSource().getEntity();
		if (sourceEntity instanceof Player player && !(player.level().isClientSide)) {
			ItemStack ring = Utils.getFirstCurio(ModItems.DIVINE_RING.get() , player);
			Entity target = event.getEntity();
			DamageSource source = event.getSource();
			boolean isThrowable = source.is(DamageTypes.THROWN);
			if (ring != null) {
				if (!(isThrowable || target.equals(player))) {
					if (processingEntities.contains(target)) {
						return; // Skip processing if this entity is already being processed
					}
					try {
						processingEntities.add(target); // Mark this entity as being processed
						target.setSecondsOnFire(10);
						hitNearbyEntities(player , target , AoeDamage.get());
						addExplosionEffect(player , target);
						if (event.getSource().getDirectEntity() != player) {
							applyKnockBackFromSource(source , target);
						}
						else {
							applyKnockBack(player , target);
						}
					} finally {
						processingEntities.remove(target); // Ensure the entity is unmarked after processing
					}
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onProjImpact(ProjectileImpactEvent event) {
		if (!toggleAoeDamage()) return;
		if (!configDivineRing.get()) return;
		if (event.getProjectile().getOwner() instanceof Player player) {
			Projectile arrow = event.getProjectile();
			if (arrow.getTags().stream().anyMatch(tag -> tag.equals(MARKER))) return;
			if (player.level().isClientSide && arrow.level().isClientSide) return;
			ItemStack ring = Utils.getFirstCurio(ModItems.DIVINE_RING.get() , player);
			if (ring != null) {
				if (event.getRayTraceResult() instanceof BlockHitResult) {
					if (!(arrow instanceof ThrowableItemProjectile)) {
						addExplosionEffect(player , arrow);
						damageEntityNearArrow(player , arrow , AoeDamage.get());
						if (arrow instanceof AbstractArrow) {
							arrow.addTag(MARKER);
						}
					}
				}
			}
		}
	}

}