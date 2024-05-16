package nk.divineartifacts.item.subItems;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import nk.divineartifacts.client.GlintRenderTypes;
import nk.divineartifacts.item.baseitems.EnderTabletBase;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;
import java.util.function.Supplier;

import static nk.divineartifacts.config.ServerConfig.*;

public class EnderTablet extends EnderTabletBase {
	private static final UUID ENDER_MAGIC_RISIST_UUID = UUID.fromString("f76d2810-0f03-11ef-a166-325096b39f47");
	private static final UUID ENDER_SPELL_POWER_UUID = UUID.fromString("f76d2a22-0f03-11ef-962a-325096b39f47");
	private static final UUID SPELL_RESIST_UUID = UUID.fromString("f76d2a90-0f03-11ef-8ff8-325096b39f47");
	public EnderTablet(
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

		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && TogEndTabEnderMagicResist.get() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.EVOCATION_MAGIC_RESIST.get() ,
					new AttributeModifier(ENDER_MAGIC_RISIST_UUID , "" , ValEndTabEnderMagicResist.get() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && TogEndTabEnderMagicSpellPower.get() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.ENDER_SPELL_POWER.get() ,
					new AttributeModifier(ENDER_SPELL_POWER_UUID , "" , ValEndTabEnderMagicSpellPower.get() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && TogHlyTabManaRegen.get() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.SPELL_RESIST.get()  ,
					new AttributeModifier(SPELL_RESIST_UUID , "" , ValEndTabSpellResist.get() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}

		return modifiers;
	}

	@Override
	public void onUnequippedCurio(String identifier , LivingEntity livingEntity) {

	}
}