package nk.divineartifacts.item.subItems;

import com.google.common.collect.Multimap;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import nk.divineartifacts.client.GlintRenderTypes;
import nk.divineartifacts.item.ItemBaseClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public class LoreMaster extends ItemBaseClass {

	public LoreMaster(Properties properties , String tooltip , Supplier<Boolean> enabled , GlintRenderTypes glintType) {
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
		TextColor color = TextColor.fromRgb(0x4FDDB7); //

		return super.getName(stack).copy().withStyle(s -> s.withColor(color));
	}

	@Override
	public void appendHoverText(@NotNull ItemStack itemStack , @Nullable Level level , @NotNull List<Component> tooltip , @NotNull TooltipFlag tooltipFlag) {
		super.appendHoverText(itemStack , level , tooltip , tooltipFlag);
		if (this.tooltip == null) return;
		TextColor color = TextColor.fromRgb(0x89F2D6);
		TextColor color2 = TextColor.fromRgb(0x45B899);
		tooltip.add(Component.literal(""));

		MutableComponent a = Component.literal(" The ").withStyle(s -> s.withColor(color));
		MutableComponent b = Component.literal(  "Lore ").withStyle(s -> s.withColor(color2));
		MutableComponent c = Component.literal("Master And").withStyle(s -> s.withColor(color));

		MutableComponent f = Component.literal(" The Magician of the Wiggle").withStyle(s -> s.withColor(color));
		tooltip.add(a.append(b.append(c)));
		tooltip.add(Component.literal(""));
		tooltip.add(f);
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
