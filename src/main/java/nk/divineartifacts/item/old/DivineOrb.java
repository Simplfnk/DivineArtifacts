package nk.divineartifacts.item.old;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import nk.divineartifacts.client.GlintRenderTypes;
import nk.divineartifacts.config.ServerConfig;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;
import java.util.function.Supplier;

import static nk.divineartifacts.config.ServerConfig.*;

public class DivineOrb extends DivineOrbBase {

	private static final UUID SPELL_RESIST_UUID = UUID.fromString("b29c34f3-1450-48ff-ab28-639647e11861");
	private static final UUID SPELL_POWER_UUID = UUID.fromString("b29c34f3-1450-48ff-ab28-639647e11871");
	private static final UUID MANA_REGEN_UUID = UUID.fromString("b29c34f3-1450-42ff-ab28-639647e11871");
	private static final UUID MAX_MANA_UUID = UUID.fromString("14378aa6-035b-4794-9137-da589a6dfe05");
	private static final UUID COOLDOWN_REDUCTION_UUID = UUID.fromString("320d847e-eecd-402f-b6cf-d339d2fa97af");
	private static final UUID CAST_TIME_REDUCTION_UUID = UUID.fromString("14378aa6-035b-4794-9137-da589a6dfe06");

	public DivineOrb(
			Properties properties , String tooltip , Supplier<Boolean> enabled , GlintRenderTypes glintType
	) {
		super(properties , tooltip , enabled , glintType);
	}

	@Override
	public Multimap<Attribute, AttributeModifier> curioModifiers (
			SlotContext slotContext , UUID uuid , ItemStack stack) {
		Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && ServerConfig.TSPELL_RESIST.get() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.SPELL_RESIST.get() ,
					new AttributeModifier(SPELL_RESIST_UUID , "Spell Resistance" , (double) SPELL_RESIST.get() / 100 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && TSPELL_POWER.get() && configOrbOfMagic.get() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.SPELL_POWER.get() ,
					new AttributeModifier(SPELL_POWER_UUID , "Spell Power" , (double) SPELL_POWER.get() / 100 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && TMANA_REGEN.get() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.MANA_REGEN.get() ,
					new AttributeModifier(MANA_REGEN_UUID , "Mana Regen" , (double) MANA_REGEN.get() / 100 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && TMAX_MANA.get() && configOrbOfMagic.get() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.MAX_MANA.get() ,
					new AttributeModifier(MAX_MANA_UUID , "Max Mana" , (double) MAX_MANA.get() / 100 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && TCOOLDOWN_REDUCTION.get() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.COOLDOWN_REDUCTION.get() ,
					new AttributeModifier(COOLDOWN_REDUCTION_UUID , "Cooldown Reduction" , (double) COOLDOWN_REDUCTION.get() / 100 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && TCAST_TIME_REDUCTION.get() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.CAST_TIME_REDUCTION.get() ,
					new AttributeModifier(CAST_TIME_REDUCTION_UUID , "Cast Time Reduction" , (double) CAST_TIME_REDUCTION.get() / 100 ,
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

