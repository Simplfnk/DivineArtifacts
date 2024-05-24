package nk.divineartifacts.events;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nk.divineartifacts.init.ModItems;
import nk.divineartifacts.init.SoundRegistry;
import nk.divineartifacts.utils.Utils;

import java.util.List;
import java.util.Random;

import static nk.divineartifacts.config.ServerConfig.*;
import static nk.divineartifacts.config.ToggleAbilities.TogSunShield;
import static nk.divineartifacts.utils.DivineHelper.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)

public class HolyTabletEvent {
	private static int ticksElapsed = 0;
	private static boolean soundPlaying = false;
	@SubscribeEvent
	public void onPlayerJoaaa(TickEvent.ClientTickEvent event) {
		if (!TogHolyTablet.get()) return;
		if (!TogSunShield.get()) return;
		if (soundPlaying) {
			ticksElapsed++;
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onPlayerJoin(TickEvent.PlayerTickEvent event) {
		if (!TogHolyTablet.get()) return;
		if (!TogSunShield.get()) return;
		if (!(event.player instanceof ServerPlayer player)) return;
		if (player.level().isClientSide) return;
		float mana = MagicData.getPlayerMagicData(player).getMana();
		Random random = new Random();
		int chance = random.nextInt(99) + 1;
		int NockBackChance = ValHlyTabSunKnockChance.get();
		int fireRange = ValHlyTabSunFireRange.get();
		int KnockBackRange = ValHlyTabSunKnockRange.get();
		int FireManaCost = ValHlyTabSunFireCost.get();
		int KnockBackManaCost = ValHlyTabSunKnockCost.get();
		int FireDuration = ValHlyTabFireDuration.get();
		long currentTime = System.currentTimeMillis();
		long lastActionTime = lastActionTimes.getOrDefault(player.getUUID() , 0L);

		ItemStack holyTablet = Utils.getFirstCurio(ModItems.HOLY_TABLET.get() , player);
		if (holyTablet == null) return;
		List<Entity> nearbyEntities = player.level().getEntities(null , player.getBoundingBox().inflate(fireRange));
		for (Entity entity : nearbyEntities) {
			if (mana >= 5 && entity instanceof LivingEntity mobs &&
					(mobs instanceof Zombie || mobs instanceof Skeleton || mobs instanceof Phantom)) {
				double bBox = mobs.getBoundingBox().getYsize();
				double bBoxCenter = bBox / 2;
				if (processingEntities.contains(entity)) {
					return;
				}
				try {
					processingEntities.add(mobs);
					if (!mobs.isOnFire() && !soundPlaying && mobs.getTags().stream().noneMatch(tag -> tag.equals("Marked"))) {
						mobs.addTag("Marked");
						player.level().playSound(null , mobs.getX() , mobs.getY() , mobs.getZ() ,
								SoundRegistry.HOLY_START.get() , SoundSource.PLAYERS , 0.5F , 0.1F);
						soundPlaying = true;
						ticksElapsed = 0;
					}

					if (!mobs.isOnFire() && mana >= FireManaCost && ticksElapsed >= 80 && soundPlaying) {
						player.serverLevel().sendParticles(player , ParticleTypes.FLASH , false , mobs.getX() , mobs.getY() + bBoxCenter , mobs.getZ() , 2 , 0 , 0 , 0 , 0);
						player.serverLevel().sendParticles(player , ParticleTypes.SMALL_FLAME , false , mobs.getX() , mobs.getY() + bBoxCenter , mobs.getZ() , 3 , 1D , 1D , 1 , (0.8D + (player.level().random.nextFloat() - player.level().random.nextFloat()) * 0.1F) * 0.2F);
						HolyTabApplyKnockBack(player , mobs);
						mobs.hurt(player.damageSources().playerAttack(player) , 1);
						mobs.setSecondsOnFire(FireDuration);
						MagicData.getPlayerMagicData(player).setMana(mana - FireManaCost);
						mobs.addTag("Burned");
						mobs.addEffect(new MobEffectInstance(MobEffects.GLOWING , FireDuration * 20 , 1 , false , false));
						soundPlaying = false;

					}
					if (mobs.isOnFire() && mobs.getTags().stream().anyMatch(tag -> tag.equals("Marked"))) {
						mobs.removeTag("Marked");
					}
					if (!mobs.isOnFire() && mobs.getTags().stream().anyMatch(tag -> tag.equals("Marked")) && mobs.getTags().stream().noneMatch(tag -> tag.equals("Burned"))) {
						mobs.removeTag("Marked");
					}
					if (!mobs.isOnFire() && mobs.getTags().stream().anyMatch(tag -> tag.equals("Marked")) && mobs.getTags().stream().anyMatch(tag -> tag.equals("Burned"))) {
						mobs.removeTag("Marked");
					}
					if (currentTime - lastActionTime >= 1000) {
						if (mobs.isOnFire() && mobs.distanceTo(player) <= KnockBackRange && chance <= NockBackChance && mana >= KnockBackManaCost) {
							HolyTabApplyKnockBack(player , mobs);
							mobs.hurt(player.damageSources().playerAttack(player) , 1);
							MagicData.getPlayerMagicData(player).setMana(mana - KnockBackManaCost);
							lastActionTimes.put(player.getUUID() , currentTime);
						}
					}
				} finally {
					processingEntities.remove(mobs);
				}
			}
		}

	}
}

