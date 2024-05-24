package nk.divineartifacts.utils;

import com.google.common.base.Predicate;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import nk.divineartifacts.config.ServerConfig;
import nk.divineartifacts.init.ModItems;
import nk.divineartifacts.init.SoundRegistry;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.ISlotType;

import java.util.*;

import static nk.divineartifacts.client.handler.ToggleHelper.toggleAoeDamage;
import static nk.divineartifacts.config.ServerConfig.configDivineRing;

public class UtilsHelper {
	public static final HashMap<UUID, Long> lastActionTimes = new HashMap<>();
	public static final Set<Entity> processingEntities = Collections.newSetFromMap(new HashMap<>());
	public static final List<String> Names = new ArrayList<>();
	public static final List<ResourceKey<DamageType>> ELEMENT_DAMAGE = new ArrayList<>();
	public static final List<ResourceKey<DamageType>> FIRE_DAMAGE_TYPE = new ArrayList<>();
	public static final List<ResourceKey<DamageType>> ICE_DAMAGE_TYPE = new ArrayList<>();
	public static final Set<ResourceKey<Biome>> Biomes_To_Check = new HashSet<>();
	public static final Set<TagKey<Item>> curioTags = Set.of(ItemTags.create(new ResourceLocation("artifacts" , "artifacts")));
	public static final List<String> banItemList = new ArrayList<>();
	static {
		Biomes_To_Check.add(Biomes.DEEP_FROZEN_OCEAN);
		Biomes_To_Check.add(Biomes.FROZEN_OCEAN);
		Biomes_To_Check.add(Biomes.FROZEN_RIVER);
		Biomes_To_Check.add(Biomes.FROZEN_PEAKS);
		Biomes_To_Check.add(Biomes.SNOWY_TAIGA);
		Biomes_To_Check.add(Biomes.SNOWY_BEACH);
		Biomes_To_Check.add(Biomes.SNOWY_PLAINS);
		Biomes_To_Check.add(Biomes.SNOWY_SLOPES);
		Biomes_To_Check.add(Biomes.JAGGED_PEAKS);
		Biomes_To_Check.add(Biomes.ICE_SPIKES);
	}

	static {
		ELEMENT_DAMAGE.add(DamageTypes.IN_FIRE);
		ELEMENT_DAMAGE.add(DamageTypes.ON_FIRE);
		ELEMENT_DAMAGE.add(DamageTypes.LAVA);
		ELEMENT_DAMAGE.add(DamageTypes.HOT_FLOOR);
		ELEMENT_DAMAGE.add(DamageTypes.CACTUS);
		ELEMENT_DAMAGE.add(DamageTypes.EXPLOSION);
	}
	static {
		FIRE_DAMAGE_TYPE.add(DamageTypes.IN_FIRE);
		FIRE_DAMAGE_TYPE.add(DamageTypes.ON_FIRE);
		FIRE_DAMAGE_TYPE.add(DamageTypes.LAVA);
		FIRE_DAMAGE_TYPE.add(DamageTypes.HOT_FLOOR);
	}
	static {
		ICE_DAMAGE_TYPE.add(DamageTypes.FREEZE);
	}

	static {
		Names.add("summoned skeleton");
		Names.add("summoned vex");
		Names.add("summoned zombie");
		Names.add("summoned polar bear");
		Names.add("vex");
		Names.add("Silverfish");
		Names.add("Endermite");
		Names.add("Zombie");
		Names.add("wither skeleton");
		Names.add("Skeleton");
		Names.add("Spider");
		Names.add("Cave Spider");
		Names.add("Wither Skeleton");
		Names.add("Blaze");
		Names.add("Zombified Piglin");
		Names.add("Zombified Piglin");
		Names.add("Piglin");
		Names.add("Phantom");
		Names.add("Magma Cube");
		Names.add("Drowned");
		Names.add("Ghast");
		Names.add("Guardian");
		Names.add("Hoglin");
		Names.add("Husk");
		Names.add("Zombie Villager");
		Names.add("Zoglin");
		Names.add("Vindicator");
		Names.add("Stray");
		Names.add("Shulker");
		Names.add("Pufferfish");
		Names.add("Pillager");
		Names.add("Piglin Brute");
		Names.add("Slime");
	}

	static {
		banItemList.add("irons_spellbooks:tarnished_helmet");
		banItemList.add("irons_spellbooks:scroll");
		banItemList.add("irons_spellbooks:evoker_spell_book");
		banItemList.add("irons_spellbooks:blood_staff");
	}

	public static boolean isHandEmptyOrNotTool(ItemStack handItem) {
		List<Class<? extends Item>> classList = Arrays.asList(SwordItem.class , PickaxeItem.class , ShovelItem.class , AxeItem.class , HoeItem.class , FishingRodItem.class);

		return handItem.isEmpty() || classList.stream().noneMatch(toolClass -> toolClass.isInstance(handItem.getItem()));
	}

	public static List<ItemStack> getAllCurioItems(Player player) {
		List<ItemStack> items = new ArrayList<>();
		// Get the Curios inventory for the player
		CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
			// Iterate over all the Curio slots
			handler.getCurios().forEach((identifier , curioStacks) -> {
				for (int i = 0; i < curioStacks.getSlots(); i++) {
					ItemStack stack = curioStacks.getStacks().getStackInSlot(i);
					if (!stack.isEmpty()) {
						// Add the non-empty item stack to the list
						items.add(stack);
					}
				}
			});
		});
		return items;
	}

	public static Predicate<Entity> entityCleverPredicate(Entity player , Entity target) {
		return e -> {
			if (e instanceof AbstractVillager && !(target instanceof AbstractVillager)) return false;
			if (e instanceof Animal && !(target instanceof Animal)) return false;
			if (e instanceof Enemy && !(target instanceof Enemy)) return false;
			return e != player && e instanceof LivingEntity le && le.isAlive() && !le.isAlliedTo(player) || e instanceof EndCrystal;
		};
	}

	public static Predicate<Entity> entityPredicate(Entity player , Entity target) {
		return e -> {
			if (e instanceof AbstractVillager && !(target instanceof AbstractVillager)) return false;
			return e != player && e instanceof LivingEntity le && le.isAlive() && !le.isAlliedTo(player) || e instanceof EndCrystal;
		};
	}

	public static void applyKnockBack(Entity player , Entity target) {
		if (target instanceof LivingEntity entity) {
			int power = 2;
			float distToPlayer = entity.distanceTo(player);
			Vec3 vec = new Vec3(entity.getX() - player.getX() , entity.getY() - player.getY() , entity.getZ() - player.getZ());
			if (!(entity instanceof EnderDragon || entity instanceof WitherBoss)) {
				entity.push(vec.x * 2 / distToPlayer * power , vec.y * 2 / distToPlayer * power , vec.z * 2 / distToPlayer * power);
			}
		}
	}

	public static void applyKnockBackFromSource(DamageSource source , Entity target) {
		if (target instanceof LivingEntity entity) {
			if (source.getSourcePosition() == null) return;
			Vec3 vecSource = source.getSourcePosition();
			Vec3 vec = new Vec3(entity.getX() - vecSource.x , entity.getY() - vecSource.y , entity.getZ() - vecSource.z);
			if (!(entity instanceof EnderDragon || entity instanceof WitherBoss)) {
				entity.push(vec.x * 2 , vec.y * 2 , vec.z * 2);
			}
		}
	}

	public static void hitNearbyEntities(Player player , Entity target , float damage) {
		if (!(toggleAoeDamage())) return;
		if (!(ServerConfig.configDivineRing.get())) return;
		if (!(target instanceof LivingEntity LEntity)) return;
		if (player.level().isClientSide || LEntity.level().isClientSide) return;
		List<Entity> nearbyEntities = LEntity.level().getEntities(LEntity , new AABB(LEntity.blockPosition()).inflate(6) , entityCleverPredicate(player , LEntity));
		for (Entity entity : nearbyEntities) {
			if (entity instanceof LivingEntity mobs && mobs.isAlive()) {
				if (processingEntities.contains(entity)) {
					return;
				}
				try {
					processingEntities.add(mobs);
					mobs.setSecondsOnFire(10);// Ensure entities are not processed multiple times
					applyKnockBack(target , mobs);
					addSmallExplosionEffect(player , mobs);
					mobs.hurt(player.damageSources().playerAttack(player) , damage);
				} finally {
					processingEntities.remove(mobs); // Ensure the entity is unmarked after processing
				}
			}
			else if (entity instanceof EndCrystal crystal) {
				if (processingEntities.contains(crystal)) {
					return;
				}
				try {
					processingEntities.add(crystal);
					addSmallExplosionEffect(player , crystal);
					crystal.hurt(player.damageSources().playerAttack(player) , damage);
					processingEntities.remove(crystal);
				} finally {
					processingEntities.remove(crystal); // Ensure the entity is unmarked after processing
				}
			}
		}
	}

	public static void damageEntityNearArrow(Entity player , Projectile arrow , int damage) {
		if (!(toggleAoeDamage())) return;
		if (!(configDivineRing.get())) return;
		List<Entity> nearbyEntities = player.level().getEntities((Entity) null , arrow.getBoundingBox().inflate(6D) , entityPredicate(player , arrow));
		for (Entity entity : nearbyEntities) {
			if (entity instanceof LivingEntity mobs && mobs.isAlive()) {
				if (processingEntities.contains(entity)) {
					return;
				}
				try {
					processingEntities.add(mobs);
					mobs.setSecondsOnFire(10);// Ensure entities are not processed multiple times
					applyKnockBack(arrow , mobs);
					addSmallExplosionEffect(player , mobs);
					mobs.hurt(player.damageSources().playerAttack((Player) player) , damage);

				} finally {
					processingEntities.remove(mobs); // Ensure the entity is unmarked after processing
				}
			}
			else if (entity instanceof EndCrystal crystal) {
				if (processingEntities.contains(crystal)) {
					return;
				}
				try {
					processingEntities.add(crystal);
					addSmallExplosionEffect(player , crystal);
					crystal.hurt(player.damageSources().playerAttack((Player) player) , damage);

				} finally {
					processingEntities.remove(crystal); // Ensure the entity is unmarked after processing
				}
			}
		}
	}

	public static void PunishAttackerWithMobEffects(Entity attacker) {
		((LivingEntity) attacker).addEffect((new MobEffectInstance(MobEffects.BLINDNESS , 200 , 2 , false , false)));
		((LivingEntity) attacker).addEffect((new MobEffectInstance(MobEffects.WITHER , 200 , 2 , false , false)));
		((LivingEntity) attacker).addEffect((new MobEffectInstance(MobEffects.CONFUSION , 200 , 2 , false , false)));
		((LivingEntity) attacker).addEffect((new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN , 200 , 2 , false , false)));
		((LivingEntity) attacker).addEffect((new MobEffectInstance(MobEffects.GLOWING , 200 , 2 , false , false)));
		((LivingEntity) attacker).addEffect((new MobEffectInstance(MobEffects.DARKNESS , 200 , 2 , false , false)));
		((LivingEntity) attacker).addEffect((new MobEffectInstance(MobEffects.POISON , 200 , 2 , false , false)));
		((LivingEntity) attacker).addEffect((new MobEffectInstance(MobEffects.WEAKNESS , 200 , 2 , false , false)));
		((LivingEntity) attacker).addEffect((new MobEffectInstance(MobEffects.HUNGER , 200 , 2 , false , false)));
		((LivingEntity) attacker).setHealth((((LivingEntity) attacker).getHealth() / 1.5f));
	}

	public static void addExplosionEffect(Entity player , Entity target) {
		if (!(toggleAoeDamage())) return;
		if (!(configDivineRing.get())) return;
		if (player instanceof ServerPlayer serverPlayer) {
			double bBox = target.getBoundingBox().getYsize();
			double bBoxCenter = bBox / 2;
			if (player.distanceTo(target) < 30) {
				serverPlayer.serverLevel().playSound(null , target.getX() , target.getY() , target.getZ() , SoundEvents.DRAGON_FIREBALL_EXPLODE , SoundSource.BLOCKS , 2.0F , (1.0F + (player.level().random.nextFloat() - player.level().random.nextFloat()) * 0.2F) * 0.6F);
			}
			else {
				double dirX = target.getX() - player.getX();
				double dirY = target.getY() - player.getY();
				double dirZ = target.getZ() - player.getZ();
				double length = Math.sqrt(dirX * dirX + dirY * dirY + dirZ * dirZ);
				dirX /= length;
				dirY /= length;
				dirZ /= length;
				double soundX = player.getX() + dirX * 30;
				double soundY = player.getY() + dirY * 30;
				double soundZ = player.getZ() + dirZ * 30;
				serverPlayer.serverLevel().playSound(null , soundX , soundY , soundZ , SoundEvents.DRAGON_FIREBALL_EXPLODE , SoundSource.BLOCKS , 2F , (1.0F + (player.level().random.nextFloat() - player.level().random.nextFloat()) * 0.2F) * 0.6F);
			}
			serverPlayer.serverLevel().sendParticles(serverPlayer , ParticleTypes.LARGE_SMOKE , true , target.getX() , target.getY() + bBoxCenter , target.getZ() , 3 , 1D , 1D , 1 , (0.8D + (player.level().random.nextFloat() - player.level().random.nextFloat()) * 0.1F) * 0.2F);
			serverPlayer.serverLevel().sendParticles(serverPlayer , ParticleTypes.ASH , true , target.getX() , target.getY() + bBoxCenter , target.getZ() , 4 , 1D , 1D , 1D , (1D + (player.level().random.nextFloat() - player.level().random.nextFloat()) * 0.1F) * 0.2F);
			serverPlayer.serverLevel().sendParticles(serverPlayer , ParticleTypes.EXPLOSION , true , target.getX() , target.getY() + bBoxCenter , target.getZ() , 2 , 0.2D , 0.2D , 0.2D , 0);
			serverPlayer.serverLevel().sendParticles(serverPlayer , ParticleTypes.FLASH , true , target.getX() , target.getY() + bBoxCenter , target.getZ() , 2 , 0 , 0 , 0 , 0);
		}
	}

	public static void addSmallExplosionEffect(Entity player , Entity target) {
		if (!(toggleAoeDamage())) return;
		if (!(configDivineRing.get())) return;
		if (player instanceof ServerPlayer serverPlayer) {
			double bBox = target.getBoundingBox().getYsize();
			double bBoxCenter = bBox / 2;
			serverPlayer.serverLevel().sendParticles(serverPlayer , ParticleTypes.LARGE_SMOKE , true , target.getX() , target.getY() + bBoxCenter , target.getZ() , 2 , 1D , 1D , 1 , (0.8D + (player.level().random.nextFloat() - player.level().random.nextFloat()) * 0.1F) * 0.2F);
			serverPlayer.serverLevel().sendParticles(serverPlayer , ParticleTypes.FLAME , true , target.getX() , target.getY() + bBoxCenter , target.getZ() , 4 , 1D , 1D , 1D , (1D + (player.level().random.nextFloat() - player.level().random.nextFloat()) * 0.1F) * 0.2F);
			serverPlayer.serverLevel().sendParticles(serverPlayer , ParticleTypes.SMALL_FLAME , true , target.getX() , target.getY() + bBoxCenter , target.getZ() , 6 , 0.2D , 0.2D , 0.2D , 0);
		}
	}

	public static boolean matchedItemId(ItemStack item , String inGameItemId) {
		// Get the item's ResourceLocation ID
		ResourceLocation id = ForgeRegistries.ITEMS.getKey(item.getItem());
		// Check if the ID matches inGameItemId
		return id != null && id.equals(new ResourceLocation(inGameItemId));
	}

	public static boolean isArtifacts(ItemStack item) {
		return curioTags.stream().anyMatch(item::is);
	}

	public static boolean IsCurioItem(ItemStack item , Player player) {
		// Retrieve the slot types available to the player
		Map<String, ISlotType> playerSlots = CuriosApi.getPlayerSlots(player);

		// Retrieve the slot types that the item can go into
		Map<String, ISlotType> itemSlots = CuriosApi.getItemStackSlots(item , player);

		// Check if there's any overlap in the slot types (keys) between the player and the item
		return itemSlots.keySet().stream().anyMatch(playerSlots::containsKey);
	}

	public static void upGradeGem(ItemStack item) {
		CompoundTag gemTags = item.getTag();
		assert gemTags != null;
		String gemName = gemTags.getString("gem");
		CompoundTag gemRarity = new CompoundTag();
		gemTags.remove("gem");
		gemTags.remove("affix_data");
		gemTags.putString("gem" , gemName);
		gemRarity.putString("rarity" , "apotheosis:ancient");
		gemTags.put("affix_data" , gemRarity);
	}

	public static boolean banItems(ItemStack itemStack) {
		for (String bannedItemId : banItemList) {
			if (matchedItemId(itemStack , bannedItemId)) {
				return true; // The itemStack matches one of the banned item IDs
			}
		}
		return false; // The itemStack does not match any banned item IDs
	}
	public static void playTogOnSound(Player player) {
		player.level().playSound(player , player.getX() , player.getY() , player.getZ() , SoundRegistry.TOG_ON.get() , SoundSource.PLAYERS , 1.0F , 1.0F);
	}
	public static void playTogOffSound(Player player) {
		player.level().playSound(player , player.getX() , player.getY() , player.getZ() , SoundRegistry.TOG_OFF.get() , SoundSource.PLAYERS , 1.0F , 1.0F);
	}
	public static void playImpactSound(Player player) {
		player.level().playSound(null , player.getX() , player.getY() , player.getZ() , SoundRegistry.FORCE_SH.get() , SoundSource.PLAYERS , 0.5F , 0.2f);
	}
	public static void toggleMagnetNBT(ServerPlayer player) {
		ItemStack ring = Utils.getFirstCurio(ModItems.DIVINE_RING.get() , player);
		if (ring != null) {
			activeMag(ring , !isMagActive(ring));
		}
	}

	public static boolean isMagActive(ItemStack stack) {
		return !stack.hasTag() || !stack.getOrCreateTag().contains("isActivated") || stack.getOrCreateTag().getBoolean("isActivated");
	}

	public static void activeMag(ItemStack stack , boolean active) {
		stack.getOrCreateTag().putBoolean("isActivated" , active);
	}
	public static void HolyTabApplyKnockBack(Entity player , Entity target) {
		if (target instanceof LivingEntity entity) {
			float distToPlayer = entity.distanceTo(player);
			Vec3 vec = new Vec3(entity.getX() - player.getX() , entity.getY() - player.getY() , entity.getZ() - player.getZ());
			entity.push(vec.x * 2 / distToPlayer , vec.y * 2 / distToPlayer , vec.z * 2 / distToPlayer);
		}
	}

}
