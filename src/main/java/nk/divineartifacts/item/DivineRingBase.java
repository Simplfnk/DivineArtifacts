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

		return super.getName(stack).copy().withStyle(ChatFormatting.BOLD).withStyle(
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

		if (toggleAioDamage()) {
			tooltip.add(explode.append(on));
		}

		if (!toggleAioDamage()) {
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
			MutableComponent flyingSpeed = Component.translatable("tooltip." + DivineArtifacts.MODID + ".flying.speed" ).withStyle(s -> s.withColor(Gold));
			MutableComponent luck = Component.translatable("tooltip." + DivineArtifacts.MODID + ".luck").withStyle(s -> s.withColor(Gold));
			MutableComponent critChance = Component.translatable("tooltip." + DivineArtifacts.MODID + ".crit.chance").withStyle(s -> s.withColor(Gold));
			MutableComponent armorPiercing = Component.translatable("tooltip." + DivineArtifacts.MODID + ".armor.piercing").withStyle(s -> s.withColor(Gold));
			MutableComponent armorShredding = Component.translatable("tooltip." + DivineArtifacts.MODID + ".armor.shredding").withStyle(s -> s.withColor(Gold));
			MutableComponent arrowVelocity = Component.translatable("tooltip." + DivineArtifacts.MODID + ".arrow.velocity").withStyle(s -> s.withColor(Gold));
			MutableComponent arrowDamage = Component.translatable("tooltip." + DivineArtifacts.MODID + ".arrow.damage").withStyle(s -> s.withColor(Gold));
			MutableComponent drawSpeed = Component.translatable("tooltip." + DivineArtifacts.MODID + ".draw.speed").withStyle(s -> s.withColor(Gold));
			MutableComponent int100 = Component.literal(Green + "+100");
			MutableComponent int10 = Component.literal(Green + "+10");
			MutableComponent int100p = Component.literal(Green + "+100%");
			MutableComponent int200p = Component.literal(Green + "+200%");
			MutableComponent int400p = Component.literal(Green + "+400%");
			MutableComponent int500p = Component.literal(Green + "+500%");
			MutableComponent int1 = Component.literal(Green + "+1");


			tooltip.add(attack.append(int100));
			tooltip.add(armor.append(int100));
			tooltip.add(toughness.append(int10));
			tooltip.add(health.append(int500p));
			tooltip.add(knockResist.append(int10));
			tooltip.add(step.append(int1));
			tooltip.add(blockReach.append(int10));
			tooltip.add(entityReach.append(int10));
			tooltip.add(flyingSpeed.append(int100p));
			tooltip.add(movementSpeed.append(int200p));
			tooltip.add(swimSpeed.append(int400p));
			tooltip.add(luck.append(int10));
			tooltip.add(critChance.append(int100p));
			tooltip.add(armorPiercing.append(int100p));
			tooltip.add(armorShredding.append(int100p));
			tooltip.add(arrowVelocity.append(int100p));
			tooltip.add(arrowDamage.append(int200p));
			tooltip.add(drawSpeed.append(int200p));

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