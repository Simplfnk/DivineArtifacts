package nk.divineartifacts.item.old;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import nk.divineartifacts.DivineArtifacts;
import nk.divineartifacts.client.GlintRenderTypes;
import nk.divineartifacts.client.Keybindings;
import nk.divineartifacts.item.ItemBaseClass;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;

import static nk.divineartifacts.client.handler.ClientForgeHandler.isShiftPressed;
import static nk.divineartifacts.client.handler.ToggleHelper.*;


public class DivineRingBase extends ItemBaseClass {

	public DivineRingBase(Properties properties , String tooltip , Supplier<Boolean> enabled , GlintRenderTypes glintType) {
		super(properties.stacksTo(1),tooltip,enabled,glintType);


	}

	@Override

	public @NotNull Component getName(@NotNull ItemStack stack) {

		TextColor color = TextColor.fromRgb(0xE98A1D); //

		return super.getName(stack).copy().withStyle(ChatFormatting.BOLD).withStyle(
				s -> s.withColor(color));
	}

	public Rarity getRarity(ItemStack stack) {

		return Rarity.EPIC;
	}

	public void tickCurio(String identifier , int index , LivingEntity livingEntity) {

	}
	public Multimap<Attribute, AttributeModifier> curioModifiers(
			SlotContext slotContext , UUID uuid , ItemStack stack) {

		return HashMultimap.create();
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack , Enchantment enchantment) {

		return false;
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack , ItemStack book) {

		return false;
	}

	@Override
	public boolean isEnchantable(@NotNull ItemStack stack) {

		return false;
	}

	public void onEquippedCurio(String identifier , LivingEntity livingEntity) {

	}

	public void onUnequippedCurio(String identifier , LivingEntity livingEntity) {

	}
	@Override
	public ItemStack getStack() {
		return null;
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack , @Nullable CompoundTag nbt) {

		return new ICapabilityProvider() {

			private final LazyOptional<ICurio> lazyCurio = LazyOptional.of(() -> new ICurio() {

				@Override
				public ItemStack getStack() {

					return stack;
				}

				@Override
				public void curioTick(SlotContext slotContext) {

					if (!slotContext.cosmetic()) tickCurio(slotContext.identifier() , slotContext.index() ,
							slotContext.entity()
					);
				}

				@NotNull
				@Override
				public SoundInfo getEquipSound(SlotContext slotContext) {

					return new SoundInfo(SoundEvents.ARMOR_EQUIP_GOLD , 1.0f , 1.0f);
				}

				@Override
				public void onEquip(SlotContext slotContext , ItemStack prevStack) {

					if (!slotContext.cosmetic()) onEquippedCurio(slotContext.identifier() , slotContext.entity());
				}

				@NotNull
				@Override
				public DropRule getDropRule(
						SlotContext slotContext , DamageSource source , int lootingLevel , boolean recentlyHit
				) {

					return DropRule.DEFAULT;
				}

				@Override
				public void onUnequip(SlotContext slotContext , ItemStack newStack) {

					if (!slotContext.cosmetic()) onUnequippedCurio(slotContext.identifier() , slotContext.entity());
				}

				@Override
				public boolean canEquipFromUse(SlotContext slotContext) {

					return true;
				}

				@Override
				public Multimap<Attribute, AttributeModifier> getAttributeModifiers(
						SlotContext slotContext , UUID uuid
				) {

					return curioModifiers(slotContext , uuid , stack);
				}

				@Override
				public List<Component> getSlotsTooltip(List<Component> tt) {

					List<Component> tooltips = ICurio.super.getAttributesTooltip(tt);
					return replaceTooltips(tooltips);
				}

				@Override
				public List<Component> getAttributesTooltip(List<Component> tt) {

					List<Component> tooltips = ICurio.super.getAttributesTooltip(tt);
					return DivineRingBase.this.getAttributesTooltip(replaceTooltips(tooltips));
				}

				@Override
				public boolean canEquip(SlotContext slotContext) {

					return !slotContext.cosmetic();
				}
			});

			@Override
			public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> capability , Direction side) {

				return CuriosCapability.ITEM.orEmpty(capability , lazyCurio);
			}
		};
	}
	public List<Component> getAttributesTooltip(List<Component> tt) {

		return tt;
	}

	@Override
	public boolean isFoil(@NotNull ItemStack stack) {

		return true;
	}

	@Override
	public void appendHoverText(@NotNull ItemStack stack , @Nullable Level worldIn , @NotNull List<Component> tooltip , @NotNull TooltipFlag flagIn) {
		super.appendHoverText(stack , worldIn , tooltip , flagIn);
		if (this.tooltip == null) return;
		//================================================================ Colors
		TextColor magnetColor = TextColor.fromRgb(0xFFE03F);
		TextColor attributes = TextColor.fromLegacyFormat(ChatFormatting.GOLD);
		TextColor color2 = TextColor.fromRgb(0xF9D511);
		TextColor color3 = TextColor.fromRgb(0xFF951B);
		TextColor clOn = TextColor.fromRgb(0x2EC910);
		TextColor clOff = TextColor.fromRgb(0xD21B1B);
		//================================================================ Components / Mutable Components:

		MutableComponent magnet = Component.translatable("tooltip." + DivineArtifacts.MODID + ".magnet")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));

		MutableComponent explode = Component.translatable("tooltip." + DivineArtifacts.MODID + ".explode")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));

		MutableComponent shield = Component.translatable("tooltip." + DivineArtifacts.MODID + ".shield")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));

		MutableComponent blockBreak = Component.translatable("tooltip." + DivineArtifacts.MODID + ".blockbreak")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));
		MutableComponent Drops = Component.translatable("tooltip." + DivineArtifacts.MODID + ".drops")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(magnetColor));

		MutableComponent on = Component.translatable("on." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOn));

		MutableComponent off = Component.translatable("off." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(s -> s.withColor(clOff));

		MutableComponent press = Component.translatable("press." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(s -> s.withColor(TextColor.fromRgb(0xD4D4D4)));
		MutableComponent pressMag = Component.translatable("pressmag." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(s -> s.withColor(TextColor.fromRgb(0xD4D4D4)));
		MutableComponent pressExp = Component.translatable("pressexp." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(s -> s.withColor(TextColor.fromRgb(0xD4D4D4)));
		MutableComponent pressShi = Component.translatable("pressshi." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(s -> s.withColor(TextColor.fromRgb(0xD4D4D4)));
		MutableComponent pressBlock = Component.translatable("pressblock." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(s -> s.withColor(TextColor.fromRgb(0xD4D4D4)));
		MutableComponent pressDrops = Component.translatable("pressdrops." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(s -> s.withColor(TextColor.fromRgb(0xD4D4D4)));

		MutableComponent info = Component.translatable("info." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(s -> s.withColor(TextColor.fromRgb(0xD4D4D4)));

		MutableComponent shift = Component.translatable("shift." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(s -> s.withColor(TextColor.fromRgb(0xFFE458)));

		MutableComponent toToggle = Component.translatable("to." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(s -> s.withColor(TextColor.fromRgb(0xD4D4D4)));

		MutableComponent magKey = Keybindings.INSTANCE.magnetKey.getKey().getDisplayName().plainCopy()
				.withStyle(s -> s.withColor(TextColor.fromRgb(0xFFE458)));

		MutableComponent expKey = Keybindings.INSTANCE.explodedKey.getKey().getDisplayName().plainCopy()
				.withStyle(s -> s.withColor(TextColor.fromRgb(0xFFE458)));
		MutableComponent breKey = Keybindings.INSTANCE.blockBreakKey.getKey().getDisplayName().plainCopy()
				.withStyle(s -> s.withColor(TextColor.fromRgb(0xFFE458)));

		MutableComponent dropsKey = Keybindings.INSTANCE.extraDropsKey.getKey().getDisplayName().plainCopy()
				.withStyle(s -> s.withColor(TextColor.fromRgb(0xFFE458)));

		MutableComponent shieldKey = Keybindings.INSTANCE.shieldKey.getKey().getDisplayName().plainCopy().withStyle(
				s -> s.withColor(TextColor.fromRgb(0xFFE458)));

		MutableComponent slot = Component.translatable("slot." + DivineArtifacts.MODID + ".tooltip").withStyle(
				s -> s.withColor(color3));

		MutableComponent ring = Component.translatable("ring." + DivineArtifacts.MODID + ".tooltip").withStyle(
				s -> s.withColor(color2));

		Component desc = Component.translatable("desc.div." + DivineArtifacts.MODID + ".tooltip").withStyle(
				ChatFormatting.BOLD).withStyle(s -> s.withColor(color3));

		Component lore = Component.translatable("lore.div." + DivineArtifacts.MODID + ".tooltip").withStyle(
				s -> s.withColor(color2));

		Component lore2 = Component.translatable("lore2.div." + DivineArtifacts.MODID + ".tooltip").withStyle(
				s -> s.withColor(color2));
		Component lore3 = Component.translatable("lore3.div." + DivineArtifacts.MODID + ".tooltip").withStyle(
				s -> s.withColor(color2));
		Component lore4 = Component.translatable("lore4.div." + DivineArtifacts.MODID + ".tooltip").withStyle(
				s -> s.withColor(color2));

		MutableComponent atr = Component.translatable("attr." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD).withStyle(s -> s.withColor(attributes));

		//================================================================ Slots
		tooltip.add(Component.literal(""));
		tooltip.add(slot.append(ring));

		//================================================================ description and lore

		tooltip.add(Component.literal(""));
		tooltip.add(desc);
		tooltip.add(Component.literal(""));
		tooltip.add(lore);
		tooltip.add(lore2);
		tooltip.add(lore3);
		tooltip.add(lore4);
		tooltip.add(Component.literal(""));
		//================================================================ advanced tooltips = Shift press tooltips

		if (!isShiftPressed) {
			tooltip.add(press.append(shift.append(info)));
			tooltip.add(Component.literal(""));
		}

		// toggle tooltips;
		//================================================================= magnet

		if (toggleMagnet()) {
			tooltip.add(magnet.append(on));
		}

		if (!toggleMagnet()) {
			tooltip.add(magnet.append(off));
		}

		if (isShiftPressed) {
			tooltip.add(pressMag.append(magKey.append(toToggle)));
		}

		tooltip.add(Component.literal(""));

		//================================================================ AIO Damage

		if (toggleAoeDamage()) {
			tooltip.add(explode.append(on));
		}

		if (!toggleAoeDamage()) {
			tooltip.add(explode.append(off));
		}

		if (isShiftPressed) {
			tooltip.add(pressExp.append(expKey.append(toToggle)));
		}

		tooltip.add(Component.literal(""));

		//================================================================ shield
		if (toggleShield()) {
			tooltip.add(shield.append(on));
		}

		if (!toggleShield()) {
			tooltip.add(shield.append(off));
		}

		if (isShiftPressed) {
			tooltip.add(pressShi.append(shieldKey.append(toToggle)));
		}

		tooltip.add(Component.literal(""));

		//================================================================ blockBreaking
		if (toggleBlockBreak()) {
			tooltip.add(blockBreak.append(on));
		}

		if (!toggleBlockBreak()) {
			tooltip.add(blockBreak.append(off));
		}

		if (isShiftPressed) {
			tooltip.add(pressBlock.append(breKey.append(toToggle)));
		}

		tooltip.add(Component.literal(""));
		//================================================================ Toggle Extra Loot

		if (toggleExtraDrops()) {
			tooltip.add(Drops.append(on));
		}

		if (!toggleExtraDrops()) {
			tooltip.add(Drops.append(off));
		}
		if (isShiftPressed) {
			tooltip.add(pressDrops.append(dropsKey.append(toToggle)));
		}

		tooltip.add(Component.literal(""));

		//=================================================================
		// Attributes tooltips;
		if (isShiftPressed) {
			tooltip.add(atr);
			tooltip.add(Component.literal("---------").withStyle(ChatFormatting.BOLD).withStyle(
					s -> s.withColor(color2)));
			String Green = "\u00A7a";
			TextColor Gold = TextColor.fromRgb(0xFFFF55);
			MutableComponent attack = Component.translatable("tooltip." + DivineArtifacts.MODID + ".attack").withStyle(s -> s.withColor(Gold));
			MutableComponent armor = Component.translatable("tooltip." + DivineArtifacts.MODID + ".armor").withStyle(s -> s.withColor(Gold));
			MutableComponent toughness = Component.translatable("tooltip." + DivineArtifacts.MODID + ".toughness").withStyle(s -> s.withColor(Gold));
			MutableComponent health = Component.translatable("tooltip." + DivineArtifacts.MODID + ".health").withStyle(s -> s.withColor(Gold));
			MutableComponent knockResist = Component.translatable("tooltip." + DivineArtifacts.MODID + ".knock.resist").withStyle(s -> s.withColor(Gold));
			MutableComponent step = Component.translatable("tooltip." + DivineArtifacts.MODID + ".step").withStyle(s -> s.withColor(Gold));
			MutableComponent blockReach = Component.translatable("tooltip." + DivineArtifacts.MODID + ".block.reach").withStyle(s -> s.withColor(Gold));
			MutableComponent entityReach = Component.translatable("tooltip." + DivineArtifacts.MODID + ".entity.reach").withStyle(s -> s.withColor(Gold));
			MutableComponent movementSpeed = Component.translatable("tooltip." + DivineArtifacts.MODID + ".movement.speed").withStyle(s -> s.withColor(Gold));
			MutableComponent swimSpeed = Component.translatable("tooltip." + DivineArtifacts.MODID + ".swim.speed").withStyle(s -> s.withColor(Gold));
			MutableComponent flyingSpeed = Component.translatable("tooltip." + DivineArtifacts.MODID + ".flying.speed").withStyle(s -> s.withColor(Gold));
			MutableComponent luck = Component.translatable("tooltip." + DivineArtifacts.MODID + ".luck").withStyle(s -> s.withColor(Gold));
			MutableComponent critChance = Component.translatable("tooltip." + DivineArtifacts.MODID + ".crit.chance").withStyle(s -> s.withColor(Gold));
			MutableComponent armorPiercing = Component.translatable("tooltip." + DivineArtifacts.MODID + ".armor.piercing").withStyle(s -> s.withColor(Gold));
			MutableComponent armorShredding = Component.translatable("tooltip." + DivineArtifacts.MODID + ".armor.shredding").withStyle(s -> s.withColor(Gold));
			MutableComponent arrowVelocity = Component.translatable("tooltip." + DivineArtifacts.MODID + ".arrow.velocity").withStyle(s -> s.withColor(Gold));
			MutableComponent arrowDamage = Component.translatable("tooltip." + DivineArtifacts.MODID + ".arrow.damage").withStyle(s -> s.withColor(Gold));
			MutableComponent drawSpeed = Component.translatable("tooltip." + DivineArtifacts.MODID + ".draw.speed").withStyle(s -> s.withColor(Gold));

			MutableComponent attackDamageValue = Component.literal(Green + "+" + getAttackDamage());
			MutableComponent armorValue = Component.literal(Green + "+" + getArmor());
			MutableComponent armorToughValue = Component.literal(Green + "+" + getArmorToughness());
			MutableComponent maxHeartValue = Component.literal(Green + "+" + getMaxHeart() + "%");
			MutableComponent knockValue = Component.literal(Green + "+" + getKnockbackResistance() + "%");
			MutableComponent stepHeightValue = Component.literal(Green + "+" + getStepHeight());
			MutableComponent blockReachValue = Component.literal(Green + "+" + getBlockReach());
			MutableComponent entityReachValue = Component.literal(Green + "+" + getEntityReach());
			MutableComponent moveSpeedValue = Component.literal(Green + "+" + getMovementSpeed() + "%");
			MutableComponent swimSpeedValue = Component.literal(Green + "+" + getSwimSpeed() + "%");
			MutableComponent flaySpeedValue = Component.literal(Green + "+" + getFlyingSpeed() + "%");
			MutableComponent luckValue = Component.literal(Green + "+" + getLuck());
			MutableComponent critChanceValue = Component.literal(Green + "+" + getCritChance() + "%");
			MutableComponent armorPiercingV = Component.literal(Green + "+" + getArmorPierce() + "%");
			MutableComponent armorShreddingV = Component.literal(Green + "+" + getArmorShred() + "%");
			MutableComponent arrowVelocityValue = Component.literal(Green + "+" + getArrowVelocity() + "%");
			MutableComponent arrowDamageValue = Component.literal(Green + "+" + getArrowDamage() + "%");
			MutableComponent drawSpeedValue = Component.literal(Green + "+" + getDrawSpeed() + "%");

			if (toggleTattack()) {
				tooltip.add(attack.append(attackDamageValue));
			}
			if (toggleTArmor()) {
				tooltip.add(armor.append(armorValue));
			}
			if (toggleTArmorToughness()) {
				tooltip.add(toughness.append(armorToughValue));
			}
			if (toggleTMAXHealth()) {
				tooltip.add(health.append(maxHeartValue));
			}
			if (toggleTKnockbackResistance()) {
				tooltip.add(knockResist.append(knockValue));
			}
			if (toggleTStepHeight()) {
				tooltip.add(step.append(stepHeightValue));
			}
			if (toggleTBlockReach()) {
				tooltip.add(blockReach.append(blockReachValue));
			}
			if (toggleTEntityReach()) {
				tooltip.add(entityReach.append(entityReachValue));
			}
			if (toggleTMovementSpeed()) {
				tooltip.add(movementSpeed.append(moveSpeedValue));
			}
			if (toggleTSwimSpeed()) {
				tooltip.add(swimSpeed.append(swimSpeedValue));
			}
			if (toggleTFlyingSpeed()) {
				tooltip.add(flyingSpeed.append(flaySpeedValue));
			}
			if (toggleTLuck()) {
				tooltip.add(luck.append(luckValue));
			}
			if (toggleTCritChance()) {
				tooltip.add(critChance.append(critChanceValue));
			}
			if (toggleTArmorPierce()) {
				tooltip.add(armorPiercing.append(armorPiercingV));
			}
			if (toggleTArmorShred()) {
				tooltip.add(armorShredding.append(armorShreddingV));
			}
			if (toggleTArrowVelocity()) {
				tooltip.add(arrowVelocity.append(arrowVelocityValue));
			}
			if (toggleTArrowDamage()) {
				tooltip.add(arrowDamage.append(arrowDamageValue));
			}
			if (toggleTDrawSpeed()) {
				tooltip.add(drawSpeed.append(drawSpeedValue));
			}

			tooltip.add(Component.literal(""));
		}
		//================================================================
	}

	private static List<Component> replaceTooltips(List<Component> tooltips) {

		tooltips.replaceAll(tooltip -> changeColors(tooltip , TextColor.fromLegacyFormat(ChatFormatting.BLUE) ,
				TextColor.fromLegacyFormat(ChatFormatting.YELLOW)
		));
		tooltips.replaceAll(tooltip -> changeColors(tooltip , TextColor.fromLegacyFormat(ChatFormatting.GOLD) ,
				TextColor.fromRgb(0xFF992E)
		));
		tooltips.clear();
		return tooltips;
	}

	private static Component changeColors(Component component , TextColor from , TextColor to) {

		MutableComponent mutable = component.copy();

		if (Objects.equals(mutable.getStyle().getColor() , from)) mutable.setStyle(mutable.getStyle().withColor(to));

		mutable.getSiblings().replaceAll(component1 -> changeColors(component1 , from , to));

		return mutable;
	}

}