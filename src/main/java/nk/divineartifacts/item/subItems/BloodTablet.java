package nk.divineartifacts.item.subItems;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import nk.divineartifacts.client.GlintRenderTypes;
import nk.divineartifacts.item.baseitems.BloodTabletBase;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;
import java.util.function.Supplier;

import static nk.divineartifacts.client.handler.ToggleHelper.*;

public class BloodTablet extends BloodTabletBase {
	private static final UUID BLOOD_MAGIC_RESIS_UUID = UUID.fromString("59405198-0ee1-11ef-9422-325096b39f47");
	private static final UUID BLOOD_SPELL_UUID = UUID.fromString("594053b4-0ee1-11ef-8f73-325096b39f47");
	private static final UUID CAST_TIME_UUID = UUID.fromString("5940542c-0ee1-11ef-9360-325096b39f47");
	private static final UUID COOL_DAWON_UUID = UUID.fromString("5940547c-0ee1-11ef-90d8-325096b39f47");
	public BloodTablet(
			Properties properties , String tooltip , Supplier<Boolean> enabled , GlintRenderTypes glintType
	) {
		super(properties , tooltip , enabled , glintType);
	}
	public void tickCurio(String identifier , int index , LivingEntity livingEntity) {
		if (!this.isEnabled.get()) return;

	}
	@Override
	public Multimap<Attribute, AttributeModifier> curioModifiers(
			SlotContext slotContext , UUID uuid , ItemStack stack) {
		Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && toggleBldTabBloodMagicResist() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.BLOOD_MAGIC_RESIST.get() ,
					new AttributeModifier(BLOOD_MAGIC_RESIS_UUID , "" , getBldTabBloodMagicResist() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && toggleBldBloodSpellPower() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.BLOOD_SPELL_POWER.get() ,
					new AttributeModifier(BLOOD_SPELL_UUID , "" , getBldTabBloodSpellPower() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && toggleBldTabCastTime() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.CAST_TIME_REDUCTION.get() ,
					new AttributeModifier(CAST_TIME_UUID , "" , getBldTabCastTime() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && toggleBldTabCoolDown() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.COOLDOWN_REDUCTION.get() ,
					new AttributeModifier(COOL_DAWON_UUID , "" , -getBldTabCoolDown() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}

		return modifiers;
	}

	@Override
	public void onUnequippedCurio(String identifier , LivingEntity livingEntity) {

	}
}