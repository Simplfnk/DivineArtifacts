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
import nk.divineartifacts.client.Keybindings;
import nk.divineartifacts.init.ModItemGod;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static nk.divineartifacts.client.handler.ClientForgeHandler.isShiftPressed;
import static nk.divineartifacts.client.handler.ToggleHelper.*;

public class DivineRingBase extends Item implements ICurioItem {

	protected String tooltip;

	public final boolean isEnabled;

	public final GlintRenderTypes glintType;

	public DivineRingBase(Properties properties , String tooltip , boolean enabled , GlintRenderTypes glintType) {

		super(properties.stacksTo(1));
		this.tooltip = tooltip;
		this.isEnabled = enabled;
		this.glintType = glintType;
		ModItemGod.GodRing.add(this);
	}

	@Override

	public @NotNull Component getName(@NotNull ItemStack stack) {

		TextColor color = TextColor.fromRgb(0xE98A1D); //

		return super.getName(stack).copy().withStyle(ChatFormatting.BOLD , ChatFormatting.UNDERLINE).withStyle(
				s -> s.withColor(color));
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
		TextColor color = TextColor.fromRgb(0xE9971D);
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
		MutableComponent pressShi = Component.translatable("pressshi" + DivineArtifacts.MODID + ".tooltip")
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

		MutableComponent atr = Component.translatable("attr." + DivineArtifacts.MODID + ".tooltip")
				.withStyle(ChatFormatting.BOLD).withStyle(s -> s.withColor(attributes));

		//================================================================ Slots

		tooltip.add(slot.append(ring));

		//================================================================ description and lore

		tooltip.add(Component.literal(""));
		tooltip.add(desc);
		tooltip.add(Component.literal(""));
		tooltip.add(lore);
		tooltip.add(lore2);
		tooltip.add(Component.literal(""));
		tooltip.add(Component.literal(""));

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

		//================================================================ Chain Attack

		if (toggleExplode()) {
			tooltip.add(explode.append(on));
		}

		if (!toggleExplode()) {
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

		//================================================================ advanced tooltips = Shift press tooltips

		if (!isShiftPressed) {
			tooltip.add(press.append(shift.append(info)));
			tooltip.add(Component.literal(""));
		}

		//=================================================================
		// Attributes tooltips;
		if (isShiftPressed) {
			tooltip.add(atr);
			tooltip.add(Component.literal("--------------").withStyle(
					s -> s.withColor(color2)));
			String Green = "\u00A7a";
			String Gold = "\u00A7e";
			tooltip.add(Component.literal(Gold + "Attack Damage  " + Green + "+100"));
			tooltip.add(Component.literal(Gold + "Armor  " + Green + "+100"));
			tooltip.add(Component.literal(Gold + "Armor Toughness  " + Green + "+10"));
			tooltip.add(Component.literal(Gold + "Max Health  " + Green + "+500%"));
			tooltip.add(Component.literal(Gold + "Knock Resist  " + Green + "+10"));
			tooltip.add(Component.literal(Gold + "Step Height  " + Green + "+1"));
			tooltip.add(Component.literal(Gold + "Block Reach  " + Green + "+10"));
			tooltip.add(Component.literal(Gold + "Entity Reach  " + Green + "+10"));
			tooltip.add(Component.literal(Gold + "Movement Speed  " + Green + "+200%"));
			tooltip.add(Component.literal(Gold + "Swim Speed  " + Green + "+400%"));
			tooltip.add(Component.literal(Gold + "Flying Speed  " + Green + "+100%"));
			tooltip.add(Component.literal(Gold + "Luck  " + Green + "+10"));
			tooltip.add(Component.literal(Gold + "Crit Chance  " + Green + "+100%"));
			tooltip.add(Component.literal(Gold + "Armor Piercing  " + Green + "+100%"));
			tooltip.add(Component.literal(Gold + "Armor Shredding  " + Green + "+100%"));
			tooltip.add(Component.literal(Gold + "Arrow Velocity  " + Green + "+100%"));
			tooltip.add(Component.literal(Gold + "Arrow Damage  " + Green + "+200%"));
			tooltip.add(Component.literal(Gold + "Draw Speed  " + Green + "+200%"));
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