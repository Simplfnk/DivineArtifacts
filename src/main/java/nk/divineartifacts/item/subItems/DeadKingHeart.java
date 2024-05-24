package nk.divineartifacts.item.subItems;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import nk.divineartifacts.client.GlintRenderTypes;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;
import java.util.function.Supplier;

import static nk.divineartifacts.client.handler.ToggleHelper.*;

public class DeadKingHeart extends nk.divineartifacts.item.baseitems.DeadKingHeart {
	private static final UUID MAX_HEALTH_UUID = UUID.fromString("51283180-0e3f-11ef-81f8-325096b39f47");
	private static final UUID BLOOD_SPELL_UUID = UUID.fromString("51283338-0e3f-11ef-8f38-325096b39f47");
	private static final UUID HOLY_SPELL_POWER_UUID = UUID.fromString("5128350e-0e3f-11ef-a928-325096b39f47");
	private static final UUID HOLY_MAGIC_RESIST_UUID = UUID.fromString("5128354a-0e3f-11ef-9271-325096b39f47");

	public DeadKingHeart(
			Properties properties  , String tooltip , Supplier<Boolean> enabled , GlintRenderTypes glintType
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

		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && toggleMaxHearts() && this.isEnabled.get()) {
			modifiers.put(Attributes.MAX_HEALTH ,
					new AttributeModifier(MAX_HEALTH_UUID , "" , getKingHeartMaxHearts() * 2 ,
							AttributeModifier.Operation.ADDITION));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && toggleBloodSpell() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.BLOOD_SPELL_POWER.get() ,
					new AttributeModifier(BLOOD_SPELL_UUID , "" , getKingHeartBloodSpell() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && toggleHolySpells() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.HOLY_SPELL_POWER.get() ,
					new AttributeModifier(HOLY_SPELL_POWER_UUID , "" , -getKingHeartHolySpells() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && toggleHolyMagic() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.HOLY_MAGIC_RESIST.get() ,
					new AttributeModifier(HOLY_MAGIC_RESIST_UUID , "" , -getKingHeartHolyMagic() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}

		return modifiers;
	}

	@Override
	public void onUnequippedCurio(String identifier , LivingEntity livingEntity) {
		if(!this.isEnabled.get()) return;
		if (livingEntity.getHealth() > livingEntity.getMaxHealth())
			livingEntity.setHealth(livingEntity.getMaxHealth());
	}
}