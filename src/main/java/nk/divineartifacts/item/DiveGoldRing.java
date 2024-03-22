package nk.divineartifacts.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import nk.divineartifacts.client.GlintRenderTypes;
import nk.divineartifacts.init.ItemInit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DiveGoldRing extends Item {
	protected String tooltip;
	public final boolean isEnabled;
	public final GlintRenderTypes glintType;

	public DiveGoldRing(Properties properties , String tooltip , boolean enabled , GlintRenderTypes glintType) {
		super(properties.stacksTo(1));
		this.tooltip = tooltip;
		this.isEnabled = enabled;
		this.glintType = glintType;
		ItemInit.item.add(this);
	}
	@Override

	public @NotNull Component getName(@NotNull ItemStack stack) {
		TextColor color = TextColor.fromRgb(0xFFDB21); //

		return super.getName(stack).copy().withStyle(ChatFormatting.BOLD, ChatFormatting.UNDERLINE ).withStyle(s -> s.withColor(color));
	}

	@Override
	public void appendHoverText(@NotNull ItemStack itemStack , @Nullable Level level , @NotNull List< Component > tooltip , TooltipFlag tooltipFlag) {
		super.appendHoverText(itemStack , level , tooltip , tooltipFlag);
		if ( this.tooltip == null ) return;
		TextColor color = TextColor.fromRgb(0xE9971D);
		tooltip.add(Component.literal(Component.translatable(this.tooltip).getString()).withStyle(s -> s.withColor(color)));
		tooltip.add(Component.literal("this dense ring used to craft"));
		tooltip.add(Component.literal("the ultimate Divine Ring"));
	}

	@Override
	public @NotNull Rarity getRarity(@NotNull ItemStack stack) {
		return Rarity.EPIC;
	}
}

