package nk.divineartifacts.events;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nk.divineartifacts.init.ModItems;
import nk.divineartifacts.utils.Utils;

import static nk.divineartifacts.item.subItems.HeartOfKnowledge.HEART_OF_KNOWLEDGE_MAX_MANA_UUID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HeartOfKnowledgeEvent {
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void ExpChangEvent(PlayerXpEvent.LevelChange event) {
		Player player = event.getEntity();
		if(player.isCreative() || player.isSpectator())return;
		int xpLevel = player.experienceLevel;
		ItemStack heartOfKnowledge = Utils.getFirstCurio(ModItems.HEART_OF_KNOWLEDGE.get() , player);
		if (heartOfKnowledge != null) {
			player.getAttribute(AttributeRegistry.MAX_MANA.get()).removeModifier(HEART_OF_KNOWLEDGE_MAX_MANA_UUID);
			player.getAttribute(AttributeRegistry.MAX_MANA.get()).addPermanentModifier(new AttributeModifier(HEART_OF_KNOWLEDGE_MAX_MANA_UUID,"",xpLevel/100.0, AttributeModifier.Operation.MULTIPLY_TOTAL ));

		}
	}

}

