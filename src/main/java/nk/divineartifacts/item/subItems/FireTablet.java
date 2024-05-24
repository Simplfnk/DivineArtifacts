package nk.divineartifacts.item.subItems;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import nk.divineartifacts.client.GlintRenderTypes;
import nk.divineartifacts.item.baseitems.FireTabletBase;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;
import java.util.function.Supplier;

import static nk.divineartifacts.client.handler.ToggleHelper.*;

public class FireTablet extends FireTabletBase {
	private static final UUID FIRE_MAGIC_RESIST_UUID = UUID.fromString("541389d4-1467-11ef-8d29-325096b39f47");
	private static final UUID FIRE_SPELL_POWER_UUID = UUID.fromString("54138c86-1467-11ef-9c5c-325096b39f47");
	private static final UUID COOLDOWN_REDUCTION_UUID = UUID.fromString("54138d12-1467-11ef-a97d-325096b39f47");
	private static final UUID ICE_MAGIC_RESIST_UUID = UUID.fromString("54138d8a-1467-11ef-9461-325096b39f47");
	public FireTablet(
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

		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && toggleFirTabFireMagicResist() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.FIRE_MAGIC_RESIST.get() ,
					new AttributeModifier(FIRE_MAGIC_RESIST_UUID , "" , getFirTabFireMagicResist() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && toggleFirTabFireSpellPower() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.FIRE_SPELL_POWER.get() ,
					new AttributeModifier(FIRE_SPELL_POWER_UUID , "" , getFirTabFireSpellPower() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && toggleFirTabCoolDonReduction() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.COOLDOWN_REDUCTION.get() ,
					new AttributeModifier(COOLDOWN_REDUCTION_UUID , "" , getFirTabCoolDonReduction() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && toggleFirTabIceMagicResist() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.ICE_MAGIC_RESIST.get() ,
					new AttributeModifier(ICE_MAGIC_RESIST_UUID , "" , -getFirTabIceMagicResist() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}

		return modifiers;
	}

	@Override
	public void onUnequippedCurio(String identifier , LivingEntity livingEntity) {

	}
}