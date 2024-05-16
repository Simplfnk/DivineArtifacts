package nk.divineartifacts.item;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import nk.divineartifacts.client.GlintRenderTypes;
import nk.divineartifacts.init.ModItems;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.UUID;
import java.util.function.Supplier;

public abstract class ItemBaseClass extends Item implements ICurioItem {
	public final String tooltip;
	public final GlintRenderTypes glintType;
	public final Supplier<Boolean> isEnabled;
	public ItemBaseClass(Properties properties , String tooltip , Supplier<Boolean> enabled , GlintRenderTypes glintType) {
		super(properties.stacksTo(1));
		this.tooltip = tooltip;
		this.isEnabled = enabled;
		this.glintType = glintType;
		ModItems.AllITEMS.add(this);
	}
	public abstract Multimap<Attribute, AttributeModifier> curioModifiers(
			SlotContext slotContext , UUID uuid , ItemStack stack);
	public abstract void onUnequippedCurio(String identifier , LivingEntity livingEntity);
	public abstract ItemStack getStack();
}
