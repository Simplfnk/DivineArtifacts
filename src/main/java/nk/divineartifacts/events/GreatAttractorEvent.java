package nk.divineartifacts.events;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nk.divineartifacts.DivineArtifacts;
import nk.divineartifacts.init.ModItems;
import nk.divineartifacts.utils.Utils;

import static nk.divineartifacts.client.handler.ToggleHelper.*;
import static nk.divineartifacts.utils.UtilsHelper.lastActionTimes;

@Mod.EventBusSubscriber(modid = DivineArtifacts.MODID)
public class GreatAttractorEvent {
	@SubscribeEvent
	public void GreatAttractorManaCost(LivingEvent.LivingTickEvent event) {
		if (!toggleGreatAttractor()) return;
		if (!toggleMagnet()) return;
		if (!(event.getEntity() instanceof Player player)) return;
		long currentTime = System.currentTimeMillis();
		long lastActionTime = lastActionTimes.getOrDefault(player.getUUID() , 0L);
		int Mana = (int) MagicData.getPlayerMagicData(player).getMana();
		int Cost = getGreatAttractorRange() / 20 * getGreatAttractorManaCost();
		int TickDelay = getGreatAttractorTickDelay() * 50;
		ItemStack greatAttractor = Utils.getFirstCurio(ModItems.GREAT_ATTRACTOR.get() , player);
		if (greatAttractor != null && Mana >= Cost && currentTime - lastActionTime >= TickDelay) {
			MagicData.getPlayerMagicData(player).setMana(Mana - Cost);
			lastActionTimes.put(player.getUUID() , currentTime);
		}
	}



}
