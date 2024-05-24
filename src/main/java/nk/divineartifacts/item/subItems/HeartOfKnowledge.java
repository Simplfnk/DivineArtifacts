package nk.divineartifacts.item.subItems;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import nk.divineartifacts.client.GlintRenderTypes;
import nk.divineartifacts.item.baseitems.HeartOfKnowledgeBase;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;
import java.util.function.Supplier;

public class HeartOfKnowledge extends HeartOfKnowledgeBase {
	public static final UUID HEART_OF_KNOWLEDGE_MAX_MANA_UUID = UUID.fromString("5a11bdf6-1151-11ef-9078-325096b39f47");
	public HeartOfKnowledge(
			Properties properties , String tooltip , Supplier<Boolean> enabled , GlintRenderTypes glintType
	) {
		super(properties , tooltip , enabled , glintType);
	}

	@Override
	public Multimap<Attribute, AttributeModifier> curioModifiers(SlotContext slotContext , UUID uuid , ItemStack stack) {
		Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

		Player player = (Player) slotContext.entity();
		if (player == null) return modifiers;
		if (player.isCreative() || player.isSpectator()) return modifiers;
		int xpLevel = player.experienceLevel;

		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.MAX_MANA.get() ,
					new AttributeModifier(HEART_OF_KNOWLEDGE_MAX_MANA_UUID , "" , xpLevel / 100.0 ,
							AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		return modifiers;
	}
	@Override
	public void onUnequippedCurio(String identifier , LivingEntity livingEntity) {
		if (!this.isEnabled.get()) return;

	}
}