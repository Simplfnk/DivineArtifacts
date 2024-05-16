package nk.divineartifacts.item.old;

import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import nk.divineartifacts.DivineArtifacts;
import nk.divineartifacts.client.GlintRenderTypes;
import nk.divineartifacts.item.ItemBaseClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public class DivineCrystal extends ItemBaseClass {

	public DivineCrystal(Properties properties , String tooltip , Supplier<Boolean> enabled , GlintRenderTypes glintType) {
		super(properties.stacksTo(1),tooltip,enabled,glintType);
	}
	@Override
	public Multimap<Attribute, AttributeModifier> curioModifiers(SlotContext slotContext , UUID uuid , ItemStack stack) {
		return null;
	}
	@Override
	public void onUnequippedCurio(String identifier , LivingEntity livingEntity) {

	}
	@Override
	public ItemStack getStack() {
		return null;
	}
	@Override

	public @NotNull Component getName(@NotNull ItemStack stack) {
		TextColor color = TextColor.fromRgb(0x34E3F6); //

		return super.getName(stack).copy().withStyle(ChatFormatting.BOLD).withStyle(s -> s.withColor(color));
	}

	@Override
	public void appendHoverText(@NotNull ItemStack itemStack , @Nullable Level level , @NotNull List<Component> tooltip , @NotNull TooltipFlag tooltipFlag) {
		super.appendHoverText(itemStack , level , tooltip , tooltipFlag);
		if (this.tooltip == null) return;
		TextColor color = TextColor.fromRgb(0xE9971D);
		tooltip.add(Component.literal(Component.translatable(this.tooltip).getString()).withStyle(s -> s.withColor(color)));
		TextColor Aqua = TextColor.fromRgb(0x55FFFF);
		tooltip.add(Component.translatable("tooltip." + DivineArtifacts.MODID + ".crystal.lore").withStyle(s -> s.withColor(Aqua)));
		tooltip.add(Component.translatable("tooltip." + DivineArtifacts.MODID + ".crystal.lore2").withStyle(s -> s.withColor(Aqua)));
		tooltip.add(Component.literal(""));
		tooltip.add(Component.literal(""));
	}
	@Override
	public boolean isFoil(@NotNull ItemStack stack) {
		return true;
	}

	@Override
	public @NotNull Rarity getRarity(@NotNull ItemStack stack) {
		return Rarity.EPIC;
	}
}
