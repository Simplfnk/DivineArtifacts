package nk.divineartifacts.events;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PowderSnowBlock;
import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nk.divineartifacts.config.ServerConfig;
import nk.divineartifacts.init.ModItems;
import nk.divineartifacts.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static nk.divineartifacts.config.ServerConfig.configDivineRing;
import static nk.divineartifacts.utils.UtilsHelper.getAllCurioItems;

public class PlayerDivineHealth {

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
		if (!ServerConfig.configDivineRing.get()) return;
		Player player = event.getEntity();
		ItemStack ring = Utils.getFirstCurio(ModItems.DIVINE_RING.get() , player);
		if (ring != null) {
			player.removeAllEffects();
		}
	}

	@SubscribeEvent
	public void onPlayerUpdate(LivingEvent.LivingTickEvent event) {
		if (!configDivineRing.get()) return;
		if (!(event.getEntity() instanceof Player player)) return;
		if (player.isCreative() || player.isSpectator()) return;
		ItemStack ring = Utils.getFirstCurio(ModItems.DIVINE_RING.get() , player);
		if (ring != null) {
			int baseX = player.getBlockX();
			int baseY = player.getBlockY();
			int baseZ = player.getBlockZ();
			List<BlockPos> positions = new ArrayList<>();
			for (int x = baseX - 1; x <= baseX + 1; x++) {
				for (int y = baseY - 1; y <= baseY + 2; y++) { // Go up to y + 2 for the extra height
					for (int z = baseZ - 1; z <= baseZ + 1; z++) {
						positions.add(new BlockPos(x , y , z));
					}
				}
			}
			for (BlockPos bp : positions) {
				BlockState bs = player.level().getBlockState(bp);
				Block bl = bs.getBlock();
				if (bl instanceof WebBlock || bl instanceof PowderSnowBlock) {
					player.level().destroyBlock(bp , true);
				}
			}
			Iterable<ItemStack> itemStack = player.getAllSlots();
			Iterable<ItemStack> itemStack2 = player.getInventory().items;
			for (ItemStack stack : itemStack) {
				if (stack.getDamageValue() > 0) {
					stack.setDamageValue(0);
				}
			}
			for (ItemStack stack : itemStack2) {
				if (stack.getDamageValue() > 0) {
					stack.setDamageValue(0);
				}
			}
			List<ItemStack> itemStack3 = getAllCurioItems(player).stream().toList();
			for (ItemStack stack : itemStack3) {
				if (stack.getDamageValue() > 0) {
					stack.setDamageValue(0);
				}
			}
		}
	}
}



