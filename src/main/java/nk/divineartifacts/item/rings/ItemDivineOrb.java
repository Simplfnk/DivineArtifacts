package nk.divineartifacts.item.rings;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import nk.divineartifacts.client.GlintRenderTypes;
import nk.divineartifacts.item.DivineOrbBase;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.UUID;

import static nk.divineartifacts.config.Config.*;
public class ItemDivineOrb extends DivineOrbBase {

	private static final UUID SPELL_RESIST_UUID = UUID.fromString("b29c34f3-1450-48ff-ab28-639647e11861");
	private static final UUID SPELL_POWER_UUID = UUID.fromString("b29c34f3-1450-48ff-ab28-639647e11871");
	private static final UUID MANA_REGEN_UUID = UUID.fromString("b29c34f3-1450-42ff-ab28-639647e11871");
	private final UUID MAX_MANA_UUID = UUID.fromString("14378aa6-035b-4794-9137-da589a6dfe05");
	private static final UUID COOLDOWN_REDUCTION_UUID = UUID.fromString("320d847e-eecd-402f-b6cf-d339d2fa97af");
	private static final UUID CAST_TIME_REDUCTION_UUID = UUID.fromString("14378aa6-035b-4794-9137-da589a6dfe06");

	public ItemDivineOrb(
			Properties properties , String tooltip , boolean enabled , GlintRenderTypes glintType
	) {
		super(properties , tooltip , enabled , glintType);
	}

	@Override
	public Multimap< Attribute, AttributeModifier > curioModifiers(ItemStack stack , String identifier) {
		Multimap< Attribute, AttributeModifier > modifiers = HashMultimap.create();

		if ( CuriosApi.getItemStackSlots(stack).containsKey(identifier) && TSPELL_RESIST.get() && configDivineArtifacts.get() || isEnabled ) {
			modifiers.put(AttributeRegistry.SPELL_RESIST.get() ,
			              new AttributeModifier(SPELL_RESIST_UUID , "Spell Resistance" , SPELL_RESIST.get() ,
			                                    AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if ( CuriosApi.getItemStackSlots(stack).containsKey(identifier) && TSPELL_POWER.get() && configDivineArtifacts.get() || isEnabled ) {
			modifiers.put(AttributeRegistry.SPELL_POWER.get() ,
			              new AttributeModifier(SPELL_POWER_UUID , "Spell Power" , SPELL_POWER.get() ,
			                                    AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if ( CuriosApi.getItemStackSlots(stack).containsKey(identifier) && TMANA_REGEN.get() && configDivineArtifacts.get() || isEnabled ) {
			modifiers.put(AttributeRegistry.MANA_REGEN.get() ,
			              new AttributeModifier(MANA_REGEN_UUID , "Mana Regen" , MANA_REGEN.get() ,
			                                    AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if ( CuriosApi.getItemStackSlots(stack).containsKey(identifier) && TMAX_MANA.get() && configDivineArtifacts.get() || isEnabled ) {
			modifiers.put(AttributeRegistry.MAX_MANA.get() ,
			              new AttributeModifier(MAX_MANA_UUID , "Max Mana" , MAX_MANA.get() ,
			                                    AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if ( CuriosApi.getItemStackSlots(stack).containsKey(identifier)&& TCOOLDOWN_REDUCTION.get() && configDivineArtifacts.get() || isEnabled ) {
			modifiers.put(AttributeRegistry.COOLDOWN_REDUCTION.get() ,
			              new AttributeModifier(COOLDOWN_REDUCTION_UUID , "Cooldown Reduction" , COOLDOWN_REDUCTION.get() ,
			                                    AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if ( CuriosApi.getItemStackSlots(stack).containsKey(identifier) && TCAST_TIME_REDUCTION.get() && configDivineArtifacts.get() || isEnabled ) {
			modifiers.put(AttributeRegistry.CAST_TIME_REDUCTION.get() ,
			              new AttributeModifier(CAST_TIME_REDUCTION_UUID , "Cast Time Reduction" , CAST_TIME_REDUCTION.get() ,
			                                    AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		return modifiers;
	}

	@Override
	public void onUnequippedCurio(String identifier , LivingEntity livingEntity) {
		if ( ! isEnabled ) {
		}

	}
}

