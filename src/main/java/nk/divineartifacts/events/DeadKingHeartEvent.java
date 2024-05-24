package nk.divineartifacts.events;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nk.divineartifacts.init.ModItems;
import nk.divineartifacts.utils.Utils;

import static nk.divineartifacts.config.ServerConfig.*;
import static nk.divineartifacts.utils.UtilsHelper.lastActionTimes;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DeadKingHeartEvent {
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
		if (!TogDeadKingHeart.get()) return;
		if (!TogRegeneration.get()) return;
		Player player = event.getEntity();
		if(player.isCreative() || player.isSpectator())return;
		ItemStack deadKingHeart = Utils.getFirstCurio(ModItems.DEAD_KING_HEART.get() , player);
		if (deadKingHeart != null) {
			player.removeAllEffects();
		}
	}
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void HealthRegen(LivingEvent.LivingTickEvent event) {
		if (!TogDeadKingHeart.get()) return;
		if (!TogRegeneration.get()) return;
		if (!(event.getEntity() instanceof Player player)) return;
		if(player.isCreative() || player.isSpectator())return;
		long currentTime = System.currentTimeMillis();
		long lastActionTime = lastActionTimes.getOrDefault(player.getUUID() , 0L);
		float health = player.getHealth();
		float maxHealth = player.getMaxHealth();
		int Mana = (int) MagicData.getPlayerMagicData(player).getMana();
		ItemStack deadKingHeart = Utils.getFirstCurio(ModItems.DEAD_KING_HEART.get() , player);
		if (deadKingHeart != null && Mana >= ValKingHeartManaCost.get() && health < maxHealth && currentTime - lastActionTime >= (ValKingHeartTickDelay.get() * 50)) {
			player.setHealth(health + ValKingHeartHealAmount.get());
			MagicData.getPlayerMagicData(player).setMana(Mana - ValKingHeartManaCost.get());
			lastActionTimes.put(player.getUUID() , currentTime);
		}
	}
}

