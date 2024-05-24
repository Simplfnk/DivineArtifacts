package nk.divineartifacts.item.baseitems;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
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
import nk.divineartifacts.init.SoundRegistry;
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

public class EldritchTabletBase extends ItemBaseClass {

	public EldritchTabletBase(Properties properties , String tooltip , Supplier<Boolean> enabled , GlintRenderTypes glintType) {

		super(properties  , tooltip , enabled , glintType);

	}

	@Override

	public @NotNull Component getName(@NotNull ItemStack stack) {

		TextColor color = TextColor.fromRgb(0x028546); //

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

					return new SoundInfo(SoundRegistry.TABLET.get() , 1.0f , 1.0f);
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
					return EldritchTabletBase.this.getAttributesTooltip(replaceTooltips(tooltips));
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
		//================================================================ Colors
		TextColor color = TextColor.fromRgb(0x16B96B);
		TextColor color2 = TextColor.fromRgb(0x7ACEA5);
		TextColor loreColor = TextColor.fromRgb(0x59B28F);
		TextColor Value = TextColor.fromRgb(0x81E3B3);
		TextColor negValue = TextColor.fromRgb(0xD53F3F);

		MutableComponent press = Component.translatable("press." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(s -> s.withColor(TextColor.fromRgb(0xD4D4D4)));
		MutableComponent info = Component.translatable("info." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(s -> s.withColor(TextColor.fromRgb(0xD4D4D4)));

		MutableComponent shift = Component.translatable("shift." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(s -> s.withColor(color));

		MutableComponent slot = Component.translatable("slot." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(color));
		MutableComponent tablet = Component.translatable("tablet." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(color2));
		MutableComponent school = Component.translatable("school." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(color));
		MutableComponent eldritch = Component.translatable("school." + DivineArtifacts.MODID + ".eldritch").withStyle(s -> s.withColor(color2));

		//================ Lore by Amadhe, Magician of the Wiggle

		MutableComponent lore = Component.translatable("lore." + DivineArtifacts.MODID + ".eldritch").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore2 = Component.translatable("lore2." + DivineArtifacts.MODID + ".eldritch").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore3 = Component.translatable("lore3." + DivineArtifacts.MODID + ".eldritch").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore4 = Component.translatable("lore4." + DivineArtifacts.MODID + ".eldritch").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore5 = Component.translatable("lore5." + DivineArtifacts.MODID + ".eldritch").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore6 = Component.translatable("lore6." + DivineArtifacts.MODID + ".eldritch").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore7 = Component.translatable("lore7." + DivineArtifacts.MODID + ".eldritch").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore8 = Component.translatable("lore8." + DivineArtifacts.MODID + ".eldritch").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore9 = Component.translatable("lore9." + DivineArtifacts.MODID + ".eldritch").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore10 = Component.translatable("lore10." + DivineArtifacts.MODID + ".eldritch").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore11 = Component.translatable("lore11." + DivineArtifacts.MODID + ".eldritch").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore12 = Component.translatable("lore12." + DivineArtifacts.MODID + ".eldritch").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore13 = Component.translatable("lore13." + DivineArtifacts.MODID + ".eldritch").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore14 = Component.translatable("lore14." + DivineArtifacts.MODID + ".eldritch").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore15 = Component.translatable("lore15." + DivineArtifacts.MODID + ".eldritch").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore16 = Component.translatable("lore16." + DivineArtifacts.MODID + ".eldritch").withStyle(s -> s.withColor(loreColor));
		tooltip.add(Component.literal(""));
		tooltip.add(slot.append(tablet));
		tooltip.add(Component.literal(""));
		tooltip.add(Component.literal(""));
		tooltip.add(school.append(eldritch));
		tooltip.add(Component.literal(""));
		if (!isShiftPressed) {

			tooltip.add(lore);
			tooltip.add(lore2);
			tooltip.add(lore3);
			tooltip.add(Component.literal(""));
			tooltip.add(press.append(shift.append(info)));

		}
		if (isShiftPressed) {
			tooltip.add(lore);
			tooltip.add(lore2);
			tooltip.add(lore3);
			tooltip.add(Component.literal(""));
			tooltip.add(lore4);
			tooltip.add(lore5);
			tooltip.add(lore6);
			tooltip.add(lore7);
			tooltip.add(lore8);
			tooltip.add(lore9);
			tooltip.add(lore10);
			tooltip.add(lore11);
			tooltip.add(Component.literal(""));
			tooltip.add(lore12);
			tooltip.add(lore13);
			tooltip.add(lore14);
			tooltip.add(lore15);
			tooltip.add(lore16);
		}

		tooltip.add(Component.literal(""));
		MutableComponent atr = Component.translatable("attr." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(color));
		tooltip.add(atr);
		tooltip.add(Component.literal(""));

		MutableComponent v1 = Component.literal("  + " + getElSpellPower() + "% ").withStyle(s -> s.withColor(Value));
		MutableComponent v2 = Component.literal("  + " + getElSpellResist() + "% ").withStyle(s -> s.withColor(Value));
		MutableComponent v3 = Component.literal("  - " + getElMaxMana() + "% ").withStyle(s -> s.withColor(negValue));

		MutableComponent a1 = Component.translatable("eldritch.1." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(Value));
		MutableComponent a2 = Component.translatable("eldritch.2." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(Value));
		MutableComponent a3 = Component.translatable("eldritch.3." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(negValue));

		if (toggleElSpellPower()) {
			tooltip.add(v1.append(a1));
			tooltip.add(Component.literal(""));
		}
		if (toggleElSpellResist()) {
			tooltip.add(v2.append(a2));
			tooltip.add(Component.literal(""));
		}
		if (toggleElMaxMana()) {
			tooltip.add(v3.append(a3));
			tooltip.add(Component.literal(""));
		}

		tooltip.add(Component.literal(""));

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
