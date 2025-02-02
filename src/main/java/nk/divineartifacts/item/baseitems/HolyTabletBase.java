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

public class HolyTabletBase extends ItemBaseClass {
	public HolyTabletBase(Properties properties , String tooltip , Supplier<Boolean> enabled , GlintRenderTypes glintType) {
		super(properties , tooltip , enabled , glintType);
	}
	@Override

	public @NotNull Component getName(@NotNull ItemStack stack) {

		TextColor color = TextColor.fromRgb(0xEABE0F); //

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

	@javax.annotation.Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack , @javax.annotation.Nullable CompoundTag nbt) {

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
					return HolyTabletBase.this.getAttributesTooltip(replaceTooltips(tooltips));
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
		TextColor color = TextColor.fromRgb(0xEABE0F);
		TextColor color2 = TextColor.fromRgb(0xFFE9A5);
		TextColor loreColor = TextColor.fromRgb(0xFFF0C3);
		TextColor Value = TextColor.fromRgb(0xEEE17B);
		TextColor abiC = TextColor.fromRgb(0xFFD350);
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
		MutableComponent eldritch = Component.translatable("school." + DivineArtifacts.MODID + ".holy").withStyle(s -> s.withColor(color2));

		//================ Lore by Amadhe, Magician of the Wiggle

		MutableComponent lore = Component.translatable("lore." + DivineArtifacts.MODID + ".holy").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore2 = Component.translatable("lore2." + DivineArtifacts.MODID + ".holy").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore3 = Component.translatable("lore3." + DivineArtifacts.MODID + ".holy").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore4 = Component.translatable("lore4." + DivineArtifacts.MODID + ".holy").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore5 = Component.translatable("lore5." + DivineArtifacts.MODID + ".holy").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore6 = Component.translatable("lore6." + DivineArtifacts.MODID + ".holy").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore7 = Component.translatable("lore7." + DivineArtifacts.MODID + ".holy").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore8 = Component.translatable("lore8." + DivineArtifacts.MODID + ".holy").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore9 = Component.translatable("lore9." + DivineArtifacts.MODID + ".holy").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore10 = Component.translatable("lore10." + DivineArtifacts.MODID + ".holy").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore11 = Component.translatable("lore11." + DivineArtifacts.MODID + ".holy").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore12 = Component.translatable("lore12." + DivineArtifacts.MODID + ".holy").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore13 = Component.translatable("lore13." + DivineArtifacts.MODID + ".holy").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore14 = Component.translatable("lore14." + DivineArtifacts.MODID + ".holy").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore15 = Component.translatable("lore15." + DivineArtifacts.MODID + ".holy").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore16 = Component.translatable("lore16." + DivineArtifacts.MODID + ".holy").withStyle(s -> s.withColor(loreColor));
		MutableComponent lore17 = Component.translatable("lore17." + DivineArtifacts.MODID + ".holy").withStyle(s -> s.withColor(loreColor));
		tooltip.add(Component.literal(""));
		tooltip.add(slot.append(tablet));
		tooltip.add(Component.literal(""));
		tooltip.add(Component.literal(""));
		tooltip.add(school.append(eldritch));
		tooltip.add(Component.literal(""));
		if (!isShiftPressed) {

			tooltip.add(lore);
			tooltip.add(lore2);;
			tooltip.add(Component.literal(""));
			tooltip.add(press.append(shift.append(info)));

		}
		if (isShiftPressed) {
			tooltip.add(lore);
			tooltip.add(lore3);
			tooltip.add(Component.literal(""));
			tooltip.add(lore4);
			tooltip.add(lore5);
			tooltip.add(lore6);
			tooltip.add(Component.literal(""));
			tooltip.add(lore7);
			tooltip.add(lore8);
			tooltip.add(lore9);
			tooltip.add(lore10);
			tooltip.add(Component.literal(""));
			tooltip.add(lore11);
			tooltip.add(lore12);
			tooltip.add(lore13);
			tooltip.add(Component.literal(""));
			tooltip.add(lore14);
			tooltip.add(lore15);
			tooltip.add(lore16);
			tooltip.add(lore17);
		}

		tooltip.add(Component.literal(""));
		MutableComponent abi = Component.translatable("abi." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(color));
		MutableComponent abiName = Component.translatable("holy.5." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(abiC));
		MutableComponent abiDesc = Component.translatable("holy.6." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(Value));

		MutableComponent burnRange = Component.translatable("holy.7." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(Value));
		MutableComponent valBurnRange = Component.literal("" + getHlyTabSunFireRange()).withStyle(s -> s.withColor(loreColor));

		MutableComponent burnDuration = Component.translatable("holy.11." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(Value));
		MutableComponent valBurnDuration = Component.literal("" + getHlyTabFireDuration()).withStyle(s -> s.withColor(loreColor));

		MutableComponent brunCost = Component.translatable("holy.8." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(negValue));
		MutableComponent valBrunCost = Component.literal("" + getHlyTabSunFireCost()).withStyle(s -> s.withColor(negValue));

		MutableComponent nockRange = Component.translatable("holy.9." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(Value));
		MutableComponent valNockRange = Component.literal("" + getHlyTabSunKnockRange()).withStyle(s -> s.withColor(loreColor));

		MutableComponent chance = Component.translatable("holy.10." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(Value));
		MutableComponent valChance = Component.literal( + getHlyTabSunKnockChance()+"%").withStyle(s -> s.withColor(loreColor));

		MutableComponent knockCost = Component.translatable("holy.8." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(negValue));
		MutableComponent valKnockCost = Component.literal("" + getHlyTabSunKnockCost()).withStyle(s -> s.withColor(negValue));

		MutableComponent spacer = Component.literal(" | ").withStyle(s -> s.withColor(color));

		MutableComponent atr = Component.translatable("attr." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(color));
		if(toggleSunShieldAbilities()){
			tooltip.add(abi);
			tooltip.add(Component.literal(""));
			tooltip.add(abiName.append(abiDesc));
			tooltip.add(Component.literal(""));
			tooltip.add(burnRange.append(valBurnRange.append(spacer).append(burnDuration.append(valBurnDuration).append(spacer).append(brunCost).append(valBrunCost))));
			tooltip.add(Component.literal(""));
			tooltip.add(nockRange.append(valNockRange.append(spacer).append(chance.append(valChance).append(spacer).append(knockCost).append(valKnockCost))));
			tooltip.add(Component.literal(""));
		}
		tooltip.add(atr);
		tooltip.add(Component.literal(""));
		MutableComponent v1 = Component.literal("  + " + getHlyTabHollyMagicResist() + "% ").withStyle(s -> s.withColor(Value));
		MutableComponent v2 = Component.literal("  + " + getHlyTabHollyMagicSpellPower() + "% ").withStyle(s -> s.withColor(Value));
		MutableComponent v3 = Component.literal("  + " + getHlyTabManaRegen() + "% ").withStyle(s -> s.withColor(Value));
		MutableComponent v4 = Component.literal("  - " + getHlyTabEvocationMagicResist() + "% ").withStyle(s -> s.withColor(negValue));

		MutableComponent a1 = Component.translatable("holy.1." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(Value));
		MutableComponent a2 = Component.translatable("holy.2." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(Value));
		MutableComponent a3 = Component.translatable("holy.3." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(Value));
		MutableComponent a4 = Component.translatable("holy.4." + DivineArtifacts.MODID + ".tooltip").withStyle(s -> s.withColor(negValue));

		if (toggleHlyTabHollyMagicResist()) {
			tooltip.add(v1.append(a1));
			tooltip.add(Component.literal(""));
		}
		if (toggleHlyTabHollyMagicSpellPower()) {
			tooltip.add(v2.append(a2));
			tooltip.add(Component.literal(""));
		}
		if (toggleHlyTabManaRegen()) {
			tooltip.add(v3.append(a3));
			tooltip.add(Component.literal(""));
		}
		if (toggleHlyTabEvocationMagicResist()) {
			tooltip.add(v4.append(a4));
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