package nk.divineartifacts.events;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nk.divineartifacts.DivineArtifacts;
import nk.divineartifacts.client.handler.ToggleHelper;
import nk.divineartifacts.init.ModItems;
import nk.divineartifacts.init.SoundRegistry;
import nk.divineartifacts.utils.Utils;
import org.joml.Math;

import static nk.divineartifacts.client.handler.ToggleHelper.toggleDivineRing;
import static nk.divineartifacts.utils.UtilsHelper.applyKnockBack;

@Mod.EventBusSubscriber(modid = DivineArtifacts.MODID)
public class DivinePotionRemover {
	@SubscribeEvent
	public static void onPotionAttack(MobEffectEvent.Added event) {
		if (!ToggleHelper.toggleShield()) return;
		if (event.getEntity() instanceof Player player) {
			if (player.isCreative() || player.isSpectator()) return;
			ItemStack ring = Utils.getFirstCurio(ModItems.DIVINE_RING.get() , player);
			Entity attacker = event.getEffectSource();
			MobEffectCategory effect = event.getEffectInstance().getEffect().getCategory();
			if (ring != null && attacker instanceof LivingEntity && effect == MobEffectCategory.HARMFUL && !(attacker instanceof Player || attacker instanceof Warden || attacker instanceof ElderGuardian)) {
				attacker.level().playSound(null , attacker.getX() , attacker.getY() , attacker.getZ() , SoundRegistry.FORCE_SH.get() , SoundSource.PLAYERS , 1.0F , 1.0F);
				applyKnockBack(player , attacker);
				attacker.kill();
			}
		}
	}

	@SubscribeEvent
	public static void CreeperAttack(LivingChangeTargetEvent event) {
		if (!toggleDivineRing()) return;
		if (event.getNewTarget() instanceof Player player) {
			if (player.isCreative() || player.isSpectator()) return;
			String vx1 = EntityType.getKey(event.getEntity().getType()).toString();
			if (Utils.isItemEquipped(ModItems.DIVINE_RING.get() , player) && event.getEntity() instanceof Creeper || vx1.equals("alexsmobs:crimson_mosquito") || event.getEntity() instanceof Piglin) {
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public static void phantomSpawn(LivingChangeTargetEvent event) {
		if (!toggleDivineRing()) return;
		if (event.getNewTarget() instanceof Player player) {
			if (player.isCreative() || player.isSpectator()) return;
			boolean ring = Utils.isItemEquipped(ModItems.DIVINE_RING.get() , player);
			if (ring && event.getEntity() instanceof Phantom phantom) {
				phantom.kill();
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public static void handleBreakSpeedEvent(PlayerEvent.BreakSpeed event) {
		if (!toggleDivineRing()) return;
		if (event.isCanceled()) return;
		Player player = event.getEntity();
		if (player.isCreative() || player.isSpectator()) return;
		ItemStack ring = Utils.getFirstCurio(ModItems.DIVINE_RING.get() , player);
		if (ring != null && !player.onGround()) {
			event.setNewSpeed(Math.max(event.getNewSpeed() , event.getOriginalSpeed() * 5.0F));
		}
	}

	@SubscribeEvent
	public static void handleBreakSpeed(PlayerEvent.BreakSpeed event) {
		if (!toggleDivineRing()) return;
		if (event.isCanceled()) return;
		Player player = event.getEntity();
		if (player.isCreative() || player.isSpectator()) return;
		ItemStack ring = Utils.getFirstCurio(ModItems.DIVINE_RING.get() , player);
		if (ring != null && player.isInFluidType() && !(player.onGround())) {
			event.setNewSpeed(Math.max(event.getNewSpeed() , event.getOriginalSpeed() * 20.0F));
		}
	}

}

