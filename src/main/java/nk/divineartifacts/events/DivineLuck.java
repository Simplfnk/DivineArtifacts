package nk.divineartifacts.events;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import nk.divineartifacts.config.ServerConfig;
import nk.divineartifacts.init.ModItemGod;
import nk.divineartifacts.network.PacketHandler;
import nk.divineartifacts.network.S2CPacketData;
import nk.divineartifacts.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static nk.divineartifacts.client.handler.ToggleHelper.toggleBlockBreak;
import static nk.divineartifacts.client.handler.ToggleHelper.toggleExtraDrops;
import static nk.divineartifacts.config.ServerConfig.ExtraDrops;
import static nk.divineartifacts.config.ServerConfig.configDivineRing;
import static nk.divineartifacts.events.DivineHelper.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DivineLuck {
	@SubscribeEvent(priority = EventPriority.LOW)
	public static void handleExp(LivingExperienceDropEvent event) {
		if (!ServerConfig.configDivineRing.get()) return;
		if (!toggleExtraDrops()) return;
		if (event.getAttackingPlayer() instanceof ServerPlayer player) {
			if (!player.level().isClientSide) {
				ItemStack ring = Utils.getFirstCurio(ModItemGod.ringDivine.get() , player);
				if (ring != null) {
					int x = event.getDroppedExperience();
					event.setDroppedExperience(x * 20);
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.LOW)
	public static void onLivingDrop(LivingDropsEvent event) {
		if (!toggleExtraDrops()) return;
		if (!configDivineRing.get()) return;
		if (!(event.getSource().getEntity() instanceof ServerPlayer player) || player.level().isClientSide()) return;
		ItemStack ring = Utils.getFirstCurio(ModItemGod.ringDivine.get() , player);
		if (ring != null) {
			LivingEntity target = event.getEntity();
			double bBox = target.getBoundingBox().getYsize();
			double bBoxCenter = bBox / 2;
			List<ItemEntity> drops = new ArrayList<>(event.getDrops());
			for (ItemEntity item : drops) {
				assert item.getItem().getTag() != null;
				boolean isGem = matchedItemId(item.getItem() , "apotheosis:gem");
				boolean isSpawnEgg = item.getItem().getItem() instanceof SpawnEggItem;
				boolean isToolOrArmor = item.getItem().is(Tags.Items.TOOLS) || item.getItem().is(Tags.Items.ARMORS);
				if (!(IsCurioItem(item.getItem() , player) || banItems(item.getItem()) || isToolOrArmor || isGem || isSpawnEgg || isArtifacts(item.getItem()))) {
					for (int i = 0; i < ExtraDrops.get(); i++) {
						event.getDrops().add(new ItemEntity(player.level() , item.getX() , item.getY() , item.getZ() , item.getItem().copy()));
					}
				}
				if (isGem) {
					upGradeGem(item.getItem());
				}
				if (item.getItem().getDamageValue() > 0) {
					item.getItem().setDamageValue(0); // Repair items outside the condition to avoid redundancy.
				}
			}
			for (ItemEntity item : event.getDrops()) {
				if (!item.getItem().getItem().canBeDepleted()) {
					item.setPos(target.getX() , target.getY() , target.getZ());
					item.setDeltaMovement(-0.3 + target.level().random.nextDouble() * 0.6 , 0.3 + target.level().random.nextDouble() * 0.3 , -0.3 + target.level().random.nextDouble() * 0.6);
				}
			}
			player.serverLevel().sendParticles(player , ParticleTypes.HAPPY_VILLAGER , true , target.getX() , target.getY() + bBoxCenter , target.getZ() , 10 , 1D , 1D , 1 , (0.8D + (player.level().random.nextFloat() - player.level().random.nextFloat()) * 0.1F) * 0.2F);
		}
	}

	@SubscribeEvent
	public static void DivBlockBreakEmptyHand(BlockEvent.BreakEvent event) {
		if (!toggleExtraDrops()) return;
		if (!configDivineRing.get()) return;
		if (!event.getLevel().isClientSide()) {
			if (!(event.getPlayer() instanceof ServerPlayer player)) return;
			if (player.isCreative() || player.isSpectator()) return;
			ItemStack ring = Utils.getFirstCurio(ModItemGod.ringDivine.get() , player);
			boolean handEmpty = player.getMainHandItem().isEmpty();
			boolean container = event.getLevel().getBlockState(event.getPos()) instanceof Container;
			BlockPos pos = event.getPos();
			ServerLevel serverLevel = (ServerLevel) event.getLevel();
			BlockState state = event.getState();
			int fortuneLvl = player.getMainHandItem().getEnchantmentLevel(Enchantments.BLOCK_FORTUNE);
			int exp = event.getExpToDrop();
			ItemStack handItem = player.getMainHandItem();
			if (ring != null && !handEmpty) {
				if (!container && !isHandEmptyOrNotTool(handItem)) {
					List<ItemStack> drops = Block.getDrops(state , serverLevel , pos , null , player , handItem);
					drops.removeIf(drop -> drop.getItem() instanceof BlockItem && !(drop.getItem() == Items.REDSTONE || drop.getItem() == Items.STRING || drop.is(Tags.Items.CROPS)));
					for (ItemStack drop : drops) {
						ItemStack stack = drop.copy();
						if (!(stack.getItem() instanceof BookItem)) {
							stack.setCount(drop.getCount() + fortuneLvl);
							ItemStack stack2 = stack.copy();
							stack2.setCount(stack.getCount() * (ExtraDrops.get() / 2));
							Block.popResource(player.level() , event.getPos() , stack2);
						}
						if (exp > 0) {
							event.setExpToDrop(exp * 20);
						}
					}
				}
				else {
					if (exp > 0) {
						event.setExpToDrop(exp * 20);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void DivBlockBreak(BlockEvent.BreakEvent event) {
		if (!toggleExtraDrops()) return;
		if (!configDivineRing.get()) return;
		if (!(event.getLevel().isClientSide())) {
			if (!(event.getPlayer() instanceof ServerPlayer player)) return;
			if (player.isCreative() || player.isSpectator()) return;
			ItemStack ring = Utils.getFirstCurio(ModItemGod.ringDivine.get() , player);
			ItemStack handItem = player.getMainHandItem();
			boolean container = event.getLevel().getBlockState(event.getPos()) instanceof Container;
			boolean blockEntity = event.getLevel().getBlockState(event.getPos()).hasBlockEntity();
			BlockPos pos = event.getPos();
			ServerLevel serverLevel = (ServerLevel) event.getLevel();
			BlockState state = event.getState();
			Block block = state.getBlock();
			int exp = block.getExpDrop(state , serverLevel , serverLevel.random , pos , 10 , 0);
			if (ring != null && isHandEmptyOrNotTool(handItem)) {
				player.level().addDestroyBlockEffect(pos , state);
				if (!container && !blockEntity) {
					List<ItemStack> drops = Block.getDrops(state , serverLevel , pos , null);
					drops.removeIf(drop -> drop.getItem() instanceof BlockItem && !(drop.getItem() == Items.REDSTONE || drop.getItem() == Items.STRING || drop.is(Tags.Items.CROPS)));
					for (ItemStack drop : drops) {
						ItemStack stack = drop.copy();
						if (!(stack.getItem() instanceof BookItem)) {
							stack.setCount(drop.getCount() * ExtraDrops.get());
							Block.popResource(player.level() , event.getPos() , stack);
						}
						if (exp > 0) {
							block.popExperience(serverLevel , pos , exp * 10);
						}
					}
				}
				else {
					if (exp > 0) {
						block.popExperience(serverLevel , pos , exp * 10);
					}
				}
			}
		}
	}

	public static float divBreakSpeed;

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void divLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
		if (!toggleBlockBreak()) return;
		if (!configDivineRing.get()) return;
		if (!(event.getLevel().isClientSide)) {
			if (!(event.getEntity() instanceof ServerPlayer player)) return;
			if (player.isCreative() || player.isSpectator()) return;
			long currentTime = System.currentTimeMillis();
			long lastActionTime = lastActionTimes.getOrDefault(player.getUUID() , 0L);
			ItemStack ring = Utils.getFirstCurio(ModItemGod.ringDivine.get() , player);
			ItemStack handItem = player.getMainHandItem();
			BlockPos pos = event.getPos();
			ServerLevel serverLevel = (ServerLevel) event.getLevel();
			BlockState state = serverLevel.getBlockState(pos);
			SoundEvent sound = state.getSoundType().getBreakSound();
			SoundEvent sound2 = state.getSoundType().getHitSound();
			S2CPacketData packet = new S2CPacketData(pos , state);
			if (ring != null && isHandEmptyOrNotTool(handItem)) {
				if (!state.isAir() && !state.isAir() && state.getDestroySpeed(serverLevel , pos) >= 0) {
					if (currentTime - lastActionTime >= 100) {
						PacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player) , packet);
						serverLevel.playSound(null , pos , sound , SoundSource.BLOCKS);
						divBreakSpeed = Float.MAX_VALUE;
						lastActionTimes.put(player.getUUID() , currentTime);
					}
					else {
						event.setCanceled(true);
					}
				}
				else {
					event.setCanceled(true);
					if (currentTime - lastActionTime >= 100) {
						serverLevel.playSound(null , pos , sound2 , SoundSource.BLOCKS);
						lastActionTimes.put(player.getUUID() , currentTime);
					}
				}
			}
		}
		if (event.getLevel().isClientSide) {
			if (!(event.getEntity() instanceof Player)) return;
			Player player = event.getEntity();
			if (player.isCreative() || player.isSpectator()) return;
			ItemStack ring = Utils.getFirstCurio(ModItemGod.ringDivine.get() , player);
			ItemStack handItem = player.getMainHandItem();
			if (ring != null && isHandEmptyOrNotTool(handItem)) {
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void divBreakTire(PlayerEvent.HarvestCheck event) {
		if (!configDivineRing.get()) return;
		if (!toggleBlockBreak()) return;
		if (!event.getEntity().level().isClientSide) {
			Player player = event.getEntity();
			if (player.isCreative() || player.isSpectator()) return;
			ItemStack handItem = player.getMainHandItem();
			ItemStack ring = Utils.getFirstCurio(ModItemGod.ringDivine.get() , player);
			if (ring != null && isHandEmptyOrNotTool(handItem)) {
				event.setCanHarvest(true);
			}
		}
	}
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void divHandleBreakSpeed(PlayerEvent.BreakSpeed event) {
		if (!configDivineRing.get()) return;
		if (!toggleBlockBreak()) return;
		Player player = event.getEntity();
		if (player.isCreative() || player.isSpectator()) return;
		ItemStack handItem = player.getMainHandItem();
		ItemStack ring = Utils.getFirstCurio(ModItemGod.ringDivine.get() , player);
		if (ring != null && isHandEmptyOrNotTool(handItem)) {
			event.setNewSpeed( divBreakSpeed );
		}
	}
}




