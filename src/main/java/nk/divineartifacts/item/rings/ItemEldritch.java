package nk.divineartifacts.item.rings;

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
import nk.divineartifacts.item.EldritchTabletBase;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

import static nk.divineartifacts.config.ServerConfig.*;

public class ItemEldritch extends EldritchTabletBase {
	private static final UUID SPELL_RESIST_UUID = UUID.fromString("94e911da-0d8d-11ef-ac36-968596b39f47");
	private static final UUID ELDRITCH_SPEL_UUID = UUID.fromString("94e9148c-0d8d-11ef-9313-965896b39f47");
	private static final UUID MAX_MANA_UUID = UUID.fromString("94e9152c-0d8d-11ef-bbce-325096598f47");
	public ItemEldritch(
			Properties properties , String tooltip , boolean enabled , GlintRenderTypes glintType
	) {
		super(properties , tooltip , enabled , glintType);
	}
	public void tickCurio(String identifier , int index , LivingEntity livingEntity) {
		if (!this.isEnabled) return;
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

		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && isEnabled && TogElSpellPower.get()) {
			modifiers.put(AttributeRegistry.ELDRITCH_SPELL_POWER.get() ,
					new AttributeModifier(ELDRITCH_SPEL_UUID , "" , ElSpellPower.get() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && isEnabled && TogElSPellResist.get()) {
			modifiers.put(AttributeRegistry.SPELL_RESIST.get() ,
					new AttributeModifier(SPELL_RESIST_UUID , "" , ElSPellResist.get() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && isEnabled && TogElMaxMana.get()) {
			modifiers.put(AttributeRegistry.MAX_MANA.get() ,
					new AttributeModifier(MAX_MANA_UUID , "" , -0.33 ,
							AttributeModifier.Operation.MULTIPLY_BASE));
		}
		return modifiers;
	}

	@Override
	public void onUnequippedCurio(String identifier , LivingEntity livingEntity) {
		if (!isEnabled) {
		}

	}
}