package nk.divineartifacts.item;

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
import net.minecraft.world.item.Item;
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
import nk.divineartifacts.init.ModItemOrb;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static nk.divineartifacts.client.handler.ClientForgeHandler.isShiftPressed;
import static nk.divineartifacts.config.Config.*;

public class DivineOrbBase extends Item implements ICurio {

	protected String tooltip;
	public final boolean isEnabled;
	public final GlintRenderTypes glintType;

	public DivineOrbBase(Properties properties , String tooltip , boolean enabled , GlintRenderTypes glintType) {
		super(properties.stacksTo(1));
		this.tooltip = tooltip;
		this.isEnabled = enabled;
		this.glintType = glintType;
		ModItemOrb.GodOrb.add(this);
	}

	@Override

	public @NotNull Component getName(@NotNull ItemStack stack) {
		TextColor color = TextColor.fromRgb(0xCC1AD8); //

		return super.getName(stack).copy().withStyle(ChatFormatting.BOLD).withStyle(s -> s.withColor(color));
	}

	public Rarity getRarity(ItemStack stack) {
		return Rarity.EPIC;
	}

	public void tickCurio(String identifier , int index , LivingEntity livingEntity) {
	}

	public Multimap<Attribute, AttributeModifier> curioModifiers(ItemStack stack , String identifier) {
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
					if (!slotContext.cosmetic())
						tickCurio(slotContext.identifier() , slotContext.index() , slotContext.entity());
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
				public DropRule getDropRule(SlotContext slotContext , DamageSource source , int lootingLevel , boolean recentlyHit) {
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
				public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext , UUID uuid) {
					return curioModifiers(stack , slotContext.identifier());
				}

				@Override
				public List<Component> getSlotsTooltip(List<Component> tt) {
					List<Component> tooltips = ICurio.super.getAttributesTooltip(tt);
					return replaceTooltips(tooltips);
				}

				@Override
				public List<Component> getAttributesTooltip(List<Component> tt) {
					List<Component> tooltips = ICurio.super.getAttributesTooltip(tt);
					return DivineOrbBase.this.getAttributesTooltip(replaceTooltips(tooltips));
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

	@Override
	public ItemStack getStack() {
		return null;
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
		TextColor color = TextColor.fromRgb(0xD00CD0);
		TextColor color2 = TextColor.fromLegacyFormat(ChatFormatting.LIGHT_PURPLE);
		MutableComponent slot = Component.translatable("slot." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(color));
		MutableComponent ring = Component.translatable("charm." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(color2));
		tooltip.add(slot.append(ring));

		//================================================================ description and lore
		tooltip.add(Component.literal(""));

		tooltip.add(Component.translatable("desc.magic." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD)
				.withStyle(style -> style.withColor(color)));
		tooltip.add(Component.literal(""));

		tooltip.add(Component.translatable("lore.magic." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(color2)));
		tooltip.add(Component.translatable("lore2.magic." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(color2)));
		tooltip.add(Component.literal(""));

		//================================================================ advanced tooltips = Shift press tooltips
		if (!isShiftPressed) {
			tooltip.add(Component.literal("\u00A77" + "Press" + "\u00A7d" + " Shift" + "\u00A77" + " for more information"));
			tooltip.add(Component.literal(""));
		}
		if (isShiftPressed) {
			TextColor attributes = TextColor.fromRgb(0xD535D5);
			MutableComponent atr = Component.literal(ChatFormatting.BOLD + "Attributes:").withStyle(s -> s.withColor(attributes));
			tooltip.add(atr);
			tooltip.add(Component.literal("---------").withStyle(ChatFormatting.BOLD).withStyle(s -> s.withColor(color2)));
			String Green = "\u00A7a";

			TextColor Purple = TextColor.fromLegacyFormat(ChatFormatting.LIGHT_PURPLE);
			MutableComponent spellResist = Component.translatable("tooltip." + DivineArtifacts.MODID + ".spell.resist").withStyle(s -> s.withColor(Purple));
			MutableComponent spellPower = Component.translatable("tooltip." + DivineArtifacts.MODID + ".spell.power").withStyle(s -> s.withColor(Purple));
			MutableComponent manaRegen = Component.translatable("tooltip." + DivineArtifacts.MODID + ".mana.regen").withStyle(s -> s.withColor(Purple));
			MutableComponent maxMana = Component.translatable("tooltip." + DivineArtifacts.MODID + ".max.mana").withStyle(s -> s.withColor(Purple));
			MutableComponent cooldownReduction = Component.translatable("tooltip." + DivineArtifacts.MODID + ".cooldown.reduction").withStyle(s -> s.withColor(Purple));
			MutableComponent castTimeReduction = Component.translatable("tooltip." + DivineArtifacts.MODID + ".cast.reduction").withStyle(s -> s.withColor(Purple));

			MutableComponent spellResistValue = Component.literal(Green + "+" + SPELL_RESIST.get() + "%");
			MutableComponent spellPowerValue = Component.literal(Green + "+" + SPELL_POWER.get() + "%");
			MutableComponent manaRegenValue = Component.literal(Green + "+" + MANA_REGEN.get() + "%");
			MutableComponent maxManaValue = Component.literal(Green + "+" + MAX_MANA.get() + "%");
			MutableComponent CoolDawnValue = Component.literal(Green + "+" + COOLDOWN_REDUCTION.get() + "%");
			MutableComponent castTimeValue = Component.literal(Green + "+" + CAST_TIME_REDUCTION.get() + "%");

			if (TSPELL_RESIST.get()) {
				tooltip.add(spellResist.append(spellResistValue));
			}
			if (TSPELL_POWER.get()) {
				tooltip.add(spellPower.append(spellPowerValue));
			}
			if (TMANA_REGEN.get()) {
				tooltip.add(manaRegen.append(manaRegenValue));
			}
			if (TMAX_MANA.get()) {
				tooltip.add(maxMana.append(maxManaValue));
			}
			if (TCOOLDOWN_REDUCTION.get()) {
				tooltip.add(cooldownReduction.append(CoolDawnValue));
			}
			if (TCAST_TIME_REDUCTION.get()) {
				tooltip.add(castTimeReduction.append(castTimeValue));
			}

			tooltip.add(Component.literal(""));
		}

	}

	private static List<Component> replaceTooltips(List<Component> tooltips) {
		tooltips.replaceAll(tooltip ->

				changeColors(tooltip , TextColor.fromLegacyFormat(ChatFormatting.BLUE) , TextColor.fromRgb(0xFB4CFF)));
		tooltips.replaceAll(tooltip ->

				changeColors(tooltip , TextColor.fromLegacyFormat(ChatFormatting.GOLD) , TextColor.fromRgb(0xC726DB)));
		tooltips.replaceAll(tooltip ->

				changeColors(tooltip , TextColor.fromLegacyFormat(ChatFormatting.YELLOW) , TextColor.fromRgb(0xFB4CFF)));
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