package nk.divineartifacts.events;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nk.divineartifacts.DivineArtifacts;
import nk.divineartifacts.init.ModItems;
import nk.divineartifacts.utils.Utils;

import static nk.divineartifacts.client.handler.ToggleHelper.*;
import static nk.divineartifacts.utils.UtilsHelper.lastActionTimes;

@Mod.EventBusSubscriber(modid = DivineArtifacts.MODID)
public class DeadKingHeartEvent {
	@SubscribeEvent
	public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
		if (!toggleDeadKingHeart()) return;
		if (!TogRegeneration()) return;
		Player player = event.getEntity();
		if(player.isCreative() || player.isSpectator())return;
		ItemStack deadKingHeart = Utils.getFirstCurio(ModItems.DEAD_KING_HEART.get() , player);
		if (deadKingHeart != null) {
			player.removeAllEffects();
		}
	}

	@SubscribeEvent
	public void HealthRegen(LivingEvent.LivingTickEvent event) {
		if (!toggleDeadKingHeart()) return;
		if (!TogRegeneration()) return;
		if (!(event.getEntity() instanceof Player player)) return;
		if(player.isCreative() || player.isSpectator())return;
		long currentTime = System.currentTimeMillis();
		long lastActionTime = lastActionTimes.getOrDefault(player.getUUID() , 0L);
		float health = player.getHealth();
		float maxHealth = player.getMaxHealth();
		int Mana = (int) MagicData.getPlayerMagicData(player).getMana();
		ItemStack deadKingHeart = Utils.getFirstCurio(ModItems.DEAD_KING_HEART.get() , player);
		if (deadKingHeart != null && Mana >= getKingHeartManaCost() && health < maxHealth && currentTime - lastActionTime >= (getKingHeartTickDelay() * 50L)) {
			player.setHealth(health + getKingHeartHealAmount());
			MagicData.getPlayerMagicData(player).setMana(Mana - getKingHeartManaCost());
			lastActionTimes.put(player.getUUID() , currentTime);
		}
	}
}

