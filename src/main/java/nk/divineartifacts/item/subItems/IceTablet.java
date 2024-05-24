package nk.divineartifacts.item.subItems;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import nk.divineartifacts.client.GlintRenderTypes;
import nk.divineartifacts.item.baseitems.IceTabletBase;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;
import java.util.function.Supplier;

import static nk.divineartifacts.config.ServerConfig.*;

public class IceTablet extends IceTabletBase {
	private static final UUID ICE_MAGIC_RESIST_UUID = UUID.fromString("273935e2-15b3-11ef-999f-325096b39f47");
	private static final UUID ICE_SPELL_POWER_UUID = UUID.fromString("2739389e-15b3-11ef-9d7a-325096b39f47");
	private static final UUID MAX_MANA_UUID = UUID.fromString("27393934-15b3-11ef-9a33-325096b39f47");
	private static final UUID NATURE_MAGIC_RESIST_UUID = UUID.fromString("273939ac-15b3-11ef-b9f6-325096b39f47");
	public IceTablet(
			Properties properties  , String tooltip , Supplier<Boolean> enabled , GlintRenderTypes glintType
	) {
		super(properties.stacksTo(1),tooltip,enabled,glintType);
	}
	public void tickCurio(String identifier , int index , LivingEntity livingEntity) {
		if (!this.isEnabled.get()) return;

	}
	@Override
	public Multimap<Attribute, AttributeModifier> curioModifiers(
			SlotContext slotContext , UUID uuid , ItemStack stack) {
		Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && TogIceTabICE_MAGIC_RESIST.get() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.ICE_MAGIC_RESIST.get() ,
					new AttributeModifier(ICE_MAGIC_RESIST_UUID , "" , valIceTabICE_MAGIC_RESIST.get() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && TogIceTabICE_SPELL_POWER.get() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.ICE_SPELL_POWER.get() ,
					new AttributeModifier(ICE_SPELL_POWER_UUID , "" , valIceTabICE_SPELL_POWER.get() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && TogIceTabMAX_MANA.get() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.MAX_MANA.get()  ,
					new AttributeModifier(MAX_MANA_UUID , "" , valIceTabMAX_MANA.get()  ,
							AttributeModifier.Operation.ADDITION));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && TogIceTabNATURE_MAGIC_RESIST.get() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.NATURE_MAGIC_RESIST.get() ,
					new AttributeModifier(NATURE_MAGIC_RESIST_UUID , "" , -valIceTabNATURE_MAGIC_RESIST.get() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}

		return modifiers;
	}

	@Override
	public void onUnequippedCurio(String identifier , LivingEntity livingEntity) {

	}
}