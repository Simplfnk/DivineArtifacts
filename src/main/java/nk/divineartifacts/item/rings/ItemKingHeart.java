package nk.divineartifacts.item.rings;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import nk.divineartifacts.client.GlintRenderTypes;
import nk.divineartifacts.item.DeadKingHeart;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

import static nk.divineartifacts.config.ServerConfig.*;

public class ItemKingHeart extends DeadKingHeart {
	private static final UUID SPELL_RESIST_UUID = UUID.fromString("94e915a4-0d8d-11ef-abd6-325096b39f47");
	private static final UUID ELDRITCH_SPEL_UUID = UUID.fromString("94e91694-0d8d-11ef-9c9f-325096b39f47");
	private static final UUID MAX_MANA_UUID = UUID.fromString("94e916f8-0d8d-11ef-8e0f-325096b39f47");
	public ItemKingHeart(
			Properties properties , String tooltip , boolean enabled , GlintRenderTypes glintType
	) {
		super(properties , tooltip , enabled , glintType);
	}

	@Override
	public Multimap<Attribute, AttributeModifier> curioModifiers(
			SlotContext slotContext , UUID uuid , ItemStack stack) {
		Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && configOrbOfMagic.get()) {
			modifiers.put(AttributeRegistry.ELDRITCH_SPELL_POWER.get() ,
					new AttributeModifier(SPELL_RESIST_UUID , "ELDRITCH Spell Power" , 0.33f ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) &&  configOrbOfMagic.get()) {
			modifiers.put(AttributeRegistry.SPELL_RESIST.get() ,
					new AttributeModifier(ELDRITCH_SPEL_UUID, "Spell Power" , 0.15f ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) &&  configOrbOfMagic.get()) {
			modifiers.put(AttributeRegistry.MAX_MANA.get() ,
					new AttributeModifier(MAX_MANA_UUID , "Max Mana" , -0.33f ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		return modifiers;
	}

	@Override
	public void onUnequippedCurio(String identifier , LivingEntity livingEntity) {
		if (!isEnabled) {
		}

	}
}