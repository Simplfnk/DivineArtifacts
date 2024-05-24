package nk.divineartifacts.item.subItems;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import nk.divineartifacts.client.GlintRenderTypes;
import nk.divineartifacts.item.baseitems.HolyTabletBase;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;
import java.util.function.Supplier;

import static nk.divineartifacts.config.ServerConfig.*;

public class HolyTablet extends HolyTabletBase {
	private static final UUID HOLY_MAGIC_RISIST_UUID = UUID.fromString("553fbe54-0ef4-11ef-9b1c-325096b39f47");
	private static final UUID HOLLY_SPELL_POWER_UUID = UUID.fromString("553fc0f2-0ef4-11ef-a2e0-325096b39f47");
	private static final UUID MANA_REGEN_UUID = UUID.fromString("553fc19c-0ef4-11ef-bdd6-325096b39f47");
	private static final UUID EVOCATION_MAGIC_RESIST_UUID = UUID.fromString("553fc228-0ef4-11ef-9e8c-325096b39f47");
	public HolyTablet(
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

		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && TogHlyTabHollyMagicResist.get() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.HOLY_MAGIC_RESIST.get() ,
					new AttributeModifier(HOLY_MAGIC_RISIST_UUID , "" , ValHlyTabHollyMagicResist.get() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && TogHlyTabHollyMagicSpellPower.get() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.HOLY_SPELL_POWER.get() ,
					new AttributeModifier(HOLLY_SPELL_POWER_UUID , "" , ValHlyTabHollyMagicSpellPower.get() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && TogHlyTabManaRegen.get() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.MANA_REGEN.get()  ,
					new AttributeModifier(MANA_REGEN_UUID , "" , ValHlyTabManaRegen.get() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && TogHlyTabEvocationMagicResist.get() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.EVOCATION_MAGIC_RESIST.get() ,
					new AttributeModifier(EVOCATION_MAGIC_RESIST_UUID , "" , -ValHlyTabEvocationMagicResist.get() / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}

		return modifiers;
	}

	@Override
	public void onUnequippedCurio(String identifier , LivingEntity livingEntity) {

	}
}