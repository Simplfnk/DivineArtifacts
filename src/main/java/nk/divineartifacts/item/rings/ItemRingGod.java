package nk.divineartifacts.item.rings;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import dev.shadowsoffire.attributeslib.api.ALObjects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.ForgeMod;
import nk.divineartifacts.client.GlintRenderTypes;
import nk.divineartifacts.item.DivineRingBase;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static nk.divineartifacts.client.handler.ToggleHelper.toggleMagnet;
import static nk.divineartifacts.config.Config.*;

public class ItemRingGod extends DivineRingBase {

	private static final UUID HEALTH_UUID = UUID.fromString("b29c34f3-1450-48ff-ab28-639647e11861");
	private static final UUID ARMOR_UUID = UUID.fromString("b29c34f3-1450-48ff-ab28-639647e11871");
	private static final UUID ARMOR_TOUGHNESS_UUID = UUID.fromString("b29c34f3-1450-42ff-ab28-639647e11871");
	private final UUID STEP_HEIGHT_UUID = UUID.fromString("14378aa6-035b-4794-9137-da589a6dfe05");
	private static final UUID KNOBACK_RESISTANCE_UUID = UUID.fromString("320d847e-eecd-402f-b6cf-d339d2fa97af");
	private static final UUID BLOCK_REACH_UUID = UUID.fromString("14378aa6-035b-4794-9137-da589a6dfe06");
	private static final UUID ENTITY_REACH_UUID = UUID.fromString("14378aa6-035b-4794-9137-da589a6dfe08");
	private static final UUID SWIM_SPEED_UUID = UUID.fromString("14378aa6-035b-4794-9137-da589a6dfe09");
	private static final UUID FLYING_SPEED_UUID = UUID.fromString("14378aa6-035b-4795-9137-da589a6df10");
	private static final UUID ATTACK_DAMAGE_UUID = UUID.fromString("14378aa6-035b-4600-9137-da589a6dfe09");
	private static final UUID MOVEMENT_SPEED_UUID = UUID.fromString("14378aa6-035b-4601-9137-da589a6dfe09");
	private static final UUID LUCK_UUID = UUID.fromString("14378aa6-035b-4602-9137-da589a6dfe09");
	private static final UUID CRIT_CHANCE_UUID = UUID.fromString("14378aa6-035b-4603-9137-da589a6dfe19");
	private static final UUID ARMOR_PIERCE_UUID = UUID.fromString("14378aa6-045b-4624-9137-da589a6dfe29");
	private static final UUID ARMOR_SHRED_UUID = UUID.fromString("14378aa6-055b-4635-9137-da589a6dfe39");
	private static final UUID ARROW_VELOCITY_UUID = UUID.fromString("14378aa6-075b-4657-9137-da589a6dfe59");
	private static final UUID ARROW_DAMAGE_UUID = UUID.fromString("14378aa9-089b-5698-5486-da637a6dfe59");
	private static final UUID DRAW_SPEED_UUID = UUID.fromString("14378aa9-089b-5698-5486-da637a6dfe59");
	private static final int RANGE = MagnetRange.get();
	private final MobEffect effect1;
	private final MobEffect effect2;
	private final MobEffect effect3;
	private final MobEffect effect4;
	private final MobEffect effect5;
	private final MobEffect effect6;

	private final int amplifier1;
	private final int amplifier2;
	private final int amplifier3;
	private final int amplifier4;
	private final int amplifier5;
	private final int amplifier6;

	public ItemRingGod(
			Properties properties , String tooltip ,
			boolean enabled ,

			MobEffect effect1 ,
			MobEffect effect2 ,
			MobEffect effect3 ,
			MobEffect effect4 ,
			MobEffect effect5 ,
			MobEffect effect6 ,

			int amplifier1 ,
			int amplifier2 ,
			int amplifier3 ,
			int amplifier4 ,
			int amplifier5 ,
			int amplifier6 ,

			GlintRenderTypes glintType
	) {

		super(properties , tooltip , enabled , glintType);

		this.effect1 = effect1;
		this.effect2 = effect2;
		this.effect3 = effect3;
		this.effect4 = effect4;
		this.effect5 = effect5;
		this.effect6 = effect6;

		this.amplifier1 = amplifier1;
		this.amplifier2 = amplifier2;
		this.amplifier3 = amplifier3;
		this.amplifier4 = amplifier4;
		this.amplifier5 = amplifier5;
		this.amplifier6 = amplifier6;

	}

	public void onEquippedCurio(String identifier , LivingEntity livingEntity) {
		if (!isEnabled) return;
		if (livingEntity instanceof Player player && configDivineArtifacts.get()) {
			player.getAbilities().mayfly = true;
			player.getAbilities().flying = true;
			player.onUpdateAbilities();
		}
	}

	public void tickCurio(String identifier , int index , LivingEntity livingEntity) {
		if (!this.isEnabled) return;
		if (livingEntity instanceof Player player && configDivineArtifacts.get()) {
			player.getAbilities().setFlyingSpeed(0.08F);
		}
		if (livingEntity instanceof Player player && configDivineArtifacts.get()) {

			if (!player.getAbilities().mayfly) {

				player.getAbilities().mayfly = true;
				player.onUpdateAbilities();
			}
		}
		if (livingEntity instanceof Player player && !configDivineArtifacts.get()) {

			if (player.getAbilities().mayfly) {

				player.getAbilities().mayfly = false;
				player.onUpdateAbilities();
			}
		}
		if (livingEntity instanceof Player player && !configDivineArtifacts.get() && !player.isCreative()) {
			player.getAbilities().mayfly = false;
			player.getAbilities().flying = false;
			player.onUpdateAbilities();
		}

		List<MobEffect> effectsToRemove = new ArrayList<>();

		// First, identify all harmful effects without modifying the original collection
		livingEntity.getActiveEffects().forEach(effectInstance -> {
			if (effectInstance.getEffect().getCategory() == MobEffectCategory.HARMFUL && configDivineArtifacts.get()) {
				effectsToRemove.add(effectInstance.getEffect());
			}
		});
		// Now, remove the identified harmful effects from the livingEntity
		effectsToRemove.forEach(livingEntity::removeEffect);

		if (livingEntity instanceof Player && configDivineArtifacts.get() && !livingEntity.level().isClientSide && !livingEntity.isCrouching() && toggleMagnet()) {
			BlockPos pos = new BlockPos(livingEntity.getBlockX() , livingEntity.getBlockY() , livingEntity.getBlockZ());
			List<ItemEntity> entities = livingEntity.level().getEntitiesOfClass(ItemEntity.class , new AABB(pos.getX() + RANGE , pos.getY() + RANGE , pos.getZ() + RANGE , pos.getX() - RANGE , pos.getY() - RANGE , pos.getZ() - RANGE));
			for (ItemEntity item : entities) {
				if (item.isAlive() && !item.hasPickUpDelay()) {
					item.playerTouch((Player) livingEntity);
				}
			}
		}
		if (livingEntity instanceof Player && configDivineArtifacts.get() && !livingEntity.level().isClientSide && !livingEntity.isCrouching()) {

			BlockPos pos = new BlockPos(livingEntity.getBlockX() , livingEntity.getBlockY() , livingEntity.getBlockZ());
			List<ExperienceOrb> entities = livingEntity.level().getEntitiesOfClass(ExperienceOrb.class , new AABB(pos.getX() + RANGE , pos.getY() + RANGE , pos.getZ() + RANGE , pos.getX() - RANGE , pos.getY() - RANGE , pos.getZ() - RANGE));
			for (ExperienceOrb orb : entities) {
				if (orb.isAlive()) {
					orb.playerTouch((Player) livingEntity);
				}
			}
		}

		if (livingEntity instanceof Player player && configDivineArtifacts.get()) {
			int arrowCount = player.getArrowCount();
			if (arrowCount > 0) {
				player.setArrowCount(0);
			}
		}
		if (livingEntity instanceof Player player && configDivineArtifacts.get()) {
			int ticksFrozen = player.getTicksFrozen();
			if (ticksFrozen > 0) {
				player.setTicksFrozen(0);
			}
		}
		if (!livingEntity.hasEffect(effect1) && configDivineArtifacts.get()) {
			MobEffectInstance effectInstance = new MobEffectInstance(effect1 , Integer.MAX_VALUE , amplifier1 , false , false);
			livingEntity.addEffect(effectInstance);
		}
		if (!livingEntity.hasEffect(effect2) && configDivineArtifacts.get()) {
			MobEffectInstance effectInstance = new MobEffectInstance(effect2 , Integer.MAX_VALUE , amplifier2 , false , false);
			livingEntity.addEffect(effectInstance);
		}
		if (!livingEntity.hasEffect(effect3) && configDivineArtifacts.get()) {
			MobEffectInstance effectInstance = new MobEffectInstance(effect3 , Integer.MAX_VALUE , amplifier3 , false , false);
			livingEntity.addEffect(effectInstance);
		}
		if (!livingEntity.hasEffect(effect4) && configDivineArtifacts.get()) {
			MobEffectInstance effectInstance = new MobEffectInstance(effect4 , Integer.MAX_VALUE , amplifier4 , false , false);
			livingEntity.addEffect(effectInstance);
		}
		if (!livingEntity.hasEffect(effect5) && configDivineArtifacts.get()) {
			MobEffectInstance effectInstance = new MobEffectInstance(effect5 , Integer.MAX_VALUE , amplifier5 , false , false);
			livingEntity.addEffect(effectInstance);
		}
		if (!livingEntity.hasEffect(effect6) && configDivineArtifacts.get()) {
			MobEffectInstance effectInstance = new MobEffectInstance(effect6 , Integer.MAX_VALUE , amplifier6 , false , false);
			livingEntity.addEffect(effectInstance);
		}
	}

	@Override
	public Multimap<Attribute, AttributeModifier> curioModifiers(ItemStack stack , String identifier) {
		Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();
		if (CuriosApi.getItemStackSlots(stack).containsKey(identifier) && Tattack.get() && configDivineArtifacts.get() && isEnabled) {
			modifiers.put(Attributes.ATTACK_DAMAGE ,
					new AttributeModifier(ATTACK_DAMAGE_UUID , "" , ATTACK_DAMAGE.get() ,
							AttributeModifier.Operation.ADDITION));
		}
		if (CuriosApi.getItemStackSlots(stack).containsKey(identifier) && TArmor.get() && configDivineArtifacts.get() && isEnabled) {
			modifiers.put(Attributes.ARMOR ,
					new AttributeModifier(ARMOR_UUID , "" , ARMOR.get() ,
							AttributeModifier.Operation.ADDITION));
		}
		if (CuriosApi.getItemStackSlots(stack).containsKey(identifier) && TArmorToughness.get() && configDivineArtifacts.get() && isEnabled) {
			modifiers.put(Attributes.ARMOR_TOUGHNESS ,
					new AttributeModifier(ARMOR_TOUGHNESS_UUID , "" , ARMOR_TOUGHNESS.get() ,
							AttributeModifier.Operation.ADDITION));
		}
		if (CuriosApi.getItemStackSlots(stack).containsKey(identifier) && TMAXHealth.get() && configDivineArtifacts.get() && isEnabled) {
			modifiers.put(Attributes.MAX_HEALTH ,
					new AttributeModifier(HEALTH_UUID , "" , 20 * MaxHeart.get() ,
							AttributeModifier.Operation.ADDITION));
		}
		if (CuriosApi.getItemStackSlots(stack).containsKey(identifier) && TKnockbackResistance.get() && configDivineArtifacts.get() && isEnabled) {
			modifiers.put(Attributes.KNOCKBACK_RESISTANCE ,
					new AttributeModifier(KNOBACK_RESISTANCE_UUID , "" , KNOCKBACK_RESISTANCE.get() ,
							AttributeModifier.Operation.ADDITION));
		}
		if (CuriosApi.getItemStackSlots(stack).containsKey(identifier) && TStepHeight.get() && configDivineArtifacts.get() && isEnabled) {
			modifiers.put(ForgeMod.STEP_HEIGHT_ADDITION.get() ,
					new AttributeModifier(STEP_HEIGHT_UUID , "" , (float) STEP_HEIGHT.get() ,
							AttributeModifier.Operation.ADDITION));
		}
		if (CuriosApi.getItemStackSlots(stack).containsKey(identifier) && TBlockReach.get() && configDivineArtifacts.get() && isEnabled) {
			modifiers.put(ForgeMod.BLOCK_REACH.get() ,
					new AttributeModifier(BLOCK_REACH_UUID , "" , (float) BLOCK_REACH.get() ,
							AttributeModifier.Operation.ADDITION));
		}
		if (CuriosApi.getItemStackSlots(stack).containsKey(identifier) && TEntityReach.get() && configDivineArtifacts.get() && isEnabled) {
			modifiers.put(ForgeMod.ENTITY_REACH.get() ,
					new AttributeModifier(ENTITY_REACH_UUID , "" , (float) ENTITY_REACH.get() ,
							AttributeModifier.Operation.ADDITION));
		}
		if (CuriosApi.getItemStackSlots(stack).containsKey(identifier) && TMovementSpeed.get() && configDivineArtifacts.get() && isEnabled) {
			modifiers.put(Attributes.MOVEMENT_SPEED ,
					new AttributeModifier(MOVEMENT_SPEED_UUID , "" , (float) MOVEMENT_SPEED.get() ,
							AttributeModifier.Operation.MULTIPLY_BASE));
		}
		if (CuriosApi.getItemStackSlots(stack).containsKey(identifier) && TDrawSpeed.get() && configDivineArtifacts.get() && isEnabled) {
			modifiers.put(ForgeMod.SWIM_SPEED.get() ,
					new AttributeModifier(SWIM_SPEED_UUID , "" , SWIM_SPEED.get() ,
							AttributeModifier.Operation.ADDITION));
		}
		if (CuriosApi.getItemStackSlots(stack).containsKey(identifier)&& TFlyingSpeed.get() && configDivineArtifacts.get() && isEnabled) {
			modifiers.put(Attributes.FLYING_SPEED ,
					new AttributeModifier(FLYING_SPEED_UUID , "" , FLYING_SPEED.get() ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack).containsKey(identifier)&& TLuck.get() && configDivineArtifacts.get() && isEnabled) {
			modifiers.put(Attributes.LUCK ,
					new AttributeModifier(LUCK_UUID , "" , LUCK.get() ,
							AttributeModifier.Operation.ADDITION));
		}
		if (CuriosApi.getItemStackSlots(stack).containsKey(identifier)&& TCritChance.get() && configDivineArtifacts.get() && isEnabled) {
			modifiers.put(ALObjects.Attributes.CRIT_CHANCE.get() ,
					new AttributeModifier(CRIT_CHANCE_UUID , "" , CRIT_CHANCE.get() ,
							AttributeModifier.Operation.ADDITION));
		}
		if (CuriosApi.getItemStackSlots(stack).containsKey(identifier)&& TArmorPierce.get() && configDivineArtifacts.get() && isEnabled) {
			modifiers.put(ALObjects.Attributes.ARMOR_PIERCE.get() ,
					new AttributeModifier(ARMOR_PIERCE_UUID , "" , ARMOR_PIERCE.get() ,
							AttributeModifier.Operation.ADDITION));
		}
		if (CuriosApi.getItemStackSlots(stack).containsKey(identifier)&& TArmorShred.get() && configDivineArtifacts.get() && isEnabled) {
			modifiers.put(ALObjects.Attributes.ARMOR_SHRED.get() ,
					new AttributeModifier(ARMOR_SHRED_UUID , "" , ARMOR_SHRED.get() ,
							AttributeModifier.Operation.ADDITION));
		}
		if (CuriosApi.getItemStackSlots(stack).containsKey(identifier)&& TArrowVelocity.get() && configDivineArtifacts.get() && isEnabled) {
			modifiers.put(ALObjects.Attributes.ARROW_VELOCITY.get() ,
					new AttributeModifier(ARROW_VELOCITY_UUID , "" , (float) ARROW_VELOCITY.get() ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack).containsKey(identifier)&& TArrowDamage.get() && configDivineArtifacts.get() && isEnabled) {
			modifiers.put(ALObjects.Attributes.ARROW_DAMAGE.get() ,
					new AttributeModifier(ARROW_DAMAGE_UUID , "" , (float) ARROW_DAMAGE.get() ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack).containsKey(identifier) && TDrawSpeed.get() && configDivineArtifacts.get() && isEnabled) {
			modifiers.put(ALObjects.Attributes.DRAW_SPEED.get() ,
					new AttributeModifier(DRAW_SPEED_UUID , "" , (float) DRAW_SPEED.get() ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		return modifiers;
	}

	@Override
	public void onUnequippedCurio(String identifier , LivingEntity livingEntity) {
		if (!isEnabled) return;
		livingEntity.removeEffect(effect1);
		livingEntity.removeEffect(effect2);
		livingEntity.removeEffect(effect3);
		livingEntity.removeEffect(effect4);
		livingEntity.removeEffect(effect5);
		livingEntity.removeEffect(effect6);
		if (livingEntity.getHealth() > livingEntity.getMaxHealth())
			livingEntity.setHealth(livingEntity.getMaxHealth());
		if (livingEntity instanceof Player player && !player.isCreative()) {
			player.getAbilities().mayfly = false;
			player.getAbilities().flying = false;
			player.onUpdateAbilities();
		}
	}
}