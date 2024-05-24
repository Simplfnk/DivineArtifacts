package nk.divineartifacts.item.subItems;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import nk.divineartifacts.client.GlintRenderTypes;
import nk.divineartifacts.item.baseitems.EldritchTabletBase;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;
import java.util.function.Supplier;

import static nk.divineartifacts.client.handler.ToggleHelper.*;

public class EldritchTablet extends EldritchTabletBase {
	private static final UUID SPELL_RESIST_UUID = UUID.fromString("94e911da-0d8d-11ef-ac36-968596b39f47");
	private static final UUID ELDRITCH_SPEL_UUID = UUID.fromString("94e9148c-0d8d-11ef-9313-965896b39f47");
	private static final UUID MAX_MANA_UUID = UUID.fromString("94e9152c-0d8d-11ef-bbce-325096598f47");
	private static final UUID MANA_REGEN_UUID = UUID.fromString("6b7c4242-0e12-11ef-bbee-325096b39f47");
	public EldritchTablet(
			Properties properties , String tooltip , Supplier<Boolean> enabled , GlintRenderTypes glintType
	) {
		super(properties , tooltip , enabled , glintType);
	}
	public void tickCurio(String identifier , int index , LivingEntity livingEntity) {
		if (!this.isEnabled.get()) return;
	}
	private double getAttValue(Attribute attribute) {
		Player player = Minecraft.getInstance().player;
		if (player == null) return 0;
		AttributeInstance arr = player.getAttribute(attribute);
		if (arr == null) return 0;
		return arr.getValue();
	}
	;
	@Override
	public Multimap<Attribute, AttributeModifier> curioModifiers(
			SlotContext slotContext , UUID uuid , ItemStack stack) {
		Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && this.isEnabled.get() && toggleElSpellPower()) {
			modifiers.put(AttributeRegistry.ELDRITCH_SPELL_POWER.get() ,
					new AttributeModifier(ELDRITCH_SPEL_UUID , "" , getElSpellPower() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && this.isEnabled.get() && toggleElSpellResist()) {
			modifiers.put(AttributeRegistry.SPELL_RESIST.get() ,
					new AttributeModifier(SPELL_RESIST_UUID , "" , getElSpellResist() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && this.isEnabled.get() && toggleElMaxMana()) {
			modifiers.put(AttributeRegistry.MAX_MANA.get() ,
					new AttributeModifier(MAX_MANA_UUID , "" , -getElMaxMana() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		return modifiers;
	}

	@Override
	public void onUnequippedCurio(String identifier , LivingEntity livingEntity) {
		if (!isEnabled.get()) {
		}

	}
}