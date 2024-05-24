package nk.divineartifacts.events;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PowderSnowBlock;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nk.divineartifacts.client.Keybindings;
import nk.divineartifacts.config.ToggleAbilities;
import nk.divineartifacts.init.ModItems;
import nk.divineartifacts.utils.Utils;
import org.joml.Math;

import java.util.UUID;

import static nk.divineartifacts.client.handler.ToggleHelper.toggleHudElements;
import static nk.divineartifacts.config.ServerConfig.*;
import static nk.divineartifacts.utils.DivineHelper.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class IceTabletEvent {
//	@SubscribeEvent()
//	public void fireDamage(LivingAttackEvent event) {
//		if (!TogIceTablet.get()) return;
//		if (!TogIceTabFreezingImmunity.get()) return;
//		if (event.getEntity() instanceof Player player) {
//			if (player.isCreative() || player.isSpectator()) return;
//			boolean iceTablet = Utils.isItemEquipped(ModItems.ICE_TABLET.get() , player);
//			DamageSource damageSource = event.getSource();
//			boolean iceDamage = ICE_DAMAGE_TYPE.stream().anyMatch(damageSource::is);
//			if (iceTablet && iceDamage) {
//				player.setTicksFrozen(0);
//				event.setCanceled(true);
//			}
//		}
//	}
	@SubscribeEvent()
	public void onEffect(TickEvent.PlayerTickEvent event) {
		if (!TogIceTablet.get()) return;
		if (!TogIceTabWaterVision.get()) return;
		Player player = event.player;
		if (player.isCreative() || player.isSpectator()) return;
		boolean iceTablet = Utils.isItemEquipped(ModItems.ICE_TABLET.get() , player);
		if (iceTablet && player.isEyeInFluidType(ForgeMod.WATER_TYPE.get())) {
			player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION , 50 , 1 , false , false));
			player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING , 50 , 1 , false , false));
		}

	}

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (!TogIceTablet.get()) return;
		if (event.phase == TickEvent.Phase.END) {
			Player player = event.player;
			if (player.isCreative() || player.isSpectator()) return;
			boolean iceTablet = Utils.isItemEquipped(ModItems.ICE_TABLET.get() , player);
			ResourceKey<Biome> biomes = player.level().getBiome(player.getOnPos()).unwrapKey().orElse(null);
			boolean isColdBiomes = Biomes_To_Check.contains(biomes);
			Block block = player.level().getBlockState(player.getOnPos()).getBlock();
			if (iceTablet && TogIceTabIceSpeed.get() && isColdBiomes || player.isInPowderSnow || block instanceof PowderSnowBlock) {
				player.setNoGravity(true);
				player.setSpeed(0.1f);
				Minecraft minecraft = Minecraft.getInstance();
				boolean moveW = minecraft.options.keyUp.isDown();
				boolean moveS = minecraft.options.keyDown.isDown();
				boolean moveA = minecraft.options.keyLeft.isDown();
				boolean moveD = minecraft.options.keyRight.isDown();
				boolean moveUp = minecraft.options.keyJump.isDown();
				boolean moveDown = minecraft.options.keyShift.isDown();
				if (moveW && !moveS) {
					Vec3 lookVec = player.getLookAngle().normalize().scale(0.1);
					Vec3 speed = player.getDeltaMovement();
					Vec3 motion = new Vec3(lookVec.x * 5 , speed.y , lookVec.z * 5);
					player.setDeltaMovement(motion);
				}
				if (moveS && !moveW) {
					Vec3 lookVec = player.getLookAngle().normalize().scale(0.1);
					Vec3 speed = player.getDeltaMovement();
					Vec3 motion = new Vec3(-lookVec.x * 5 , speed.y , -lookVec.z * 5);
					player.setDeltaMovement(motion);
				}
				if (moveUp && isNoBlockAbove(player)) {

					player.setPos(player.getX() , player.getY() + 0.15 , player.getZ());

				}
				if (moveDown && isNoBlockBelow(player) && !(player.onGround())) {

					player.setPos(player.getX() , player.getY() - 0.15 , player.getZ());

				}
				if (moveA) {
					movePlayerLeft(player);

				}
				if (moveD) {
					movePlayerRight(player);

				}
			}
			else {
				player.setNoGravity(false);
			}
		}
	}
	public static void movePlayerLeft(Player player) {
		float yaw = player.getYRot();
		float leftYaw = yaw - 90.0F;
		double radians = Math.toRadians(leftYaw);
		double moveDistance = 0.15;
		double newX = player.getX() + (-Math.sin((float) radians) * moveDistance);
		double newZ = player.getZ() + (Math.cos((float) radians) * moveDistance);
		player.setPos(newX , player.getY() , newZ);
	}
	public static void movePlayerRight(Player player) {
		float yaw = player.getYRot();
		float rightYaw = yaw + 90.0F;
		double radians = Math.toRadians(rightYaw);
		double moveDistance = 0.15;
		double newX = player.getX() + (-Math.sin((float) radians) * moveDistance);
		double newZ = player.getZ() + (Math.cos((float) radians) * moveDistance);
		player.setPos(newX , player.getY() , newZ);
	}
	public static boolean isNoBlockAbove(Player player) {
		int X = player.getBlockX();
		int Y = player.getBlockY() + 2;
		int Z = player.getBlockZ();
		Level world = player.level();
		BlockPos posAbove = new BlockPos(X , Y , Z);
		return world.getBlockState(posAbove).getBlock() == Blocks.WATER || world.getBlockState(posAbove).getBlock() == Blocks.AIR;
	}
	public static boolean isNoBlockBelow(Player player) {
		int X = player.getBlockX();
		int Y = player.getBlockY();
		int Z = player.getBlockZ();
		Level world = player.level();
		BlockPos posAbove = new BlockPos(X , Y - 1 , Z);
		return world.getBlockState(posAbove).getBlock() == Blocks.WATER || world.getBlockState(posAbove).getBlock() == Blocks.AIR;
	}
	@SubscribeEvent(priority = EventPriority.LOW)
	public static void lavaMiningSpeedOnGround(PlayerEvent.BreakSpeed event) {
		if (!TogIceTablet.get()) return;
		if (!TogIceTabIceMiningSpeed.get()) return;
		if (event.isCanceled()) return;
		Player player = event.getEntity();
		if (player.isCreative() || player.isSpectator()) return;
		boolean iceTablet = Utils.isItemEquipped(ModItems.ICE_TABLET.get() , player);
		if (iceTablet && player.isInWater() && player.onGround()) {
			event.setNewSpeed(Math.max(event.getNewSpeed() , event.getOriginalSpeed() * 5.0F));
		}
	}

	@SubscribeEvent()
	public static void lavaMiningSpeed(PlayerEvent.BreakSpeed event) {
		if (!TogIceTablet.get()) return;
		if (!TogIceTabIceMiningSpeed.get()) return;
		if (event.isCanceled()) return;
		Player player = event.getEntity();
		if (player.isCreative() || player.isSpectator()) return;
		boolean iceTablet = Utils.isItemEquipped(ModItems.ICE_TABLET.get() , player);
		if (iceTablet && player.isInWater() && !(player.onGround())) {
			event.setNewSpeed(Math.max(event.getNewSpeed() , event.getOriginalSpeed() * 20.0F));
		}
	}
	private static final UUID ICE_SPELL_POWER_UUID = UUID.fromString("ed79f0de-15c2-11ef-94b3-325096b39f47");
	@SubscribeEvent
	public static void lavaPower(TickEvent.PlayerTickEvent event) {
		if (!TogIceTablet.get()) return;
		Player player = event.player;
		ResourceKey<Biome> biomes = player.level().getBiome(player.getOnPos()).unwrapKey().orElse(null);
		boolean isColdBiomes = Biomes_To_Check.contains(biomes);
		if (player.isCreative() || player.isSpectator()) return;
		boolean IceTablet = Utils.isItemEquipped(ModItems.ICE_TABLET.get() , player);
		if (IceTablet && isColdBiomes && TogIceTabExtraIceSpellPower.get()) {
			player.getAttribute(AttributeRegistry.ICE_SPELL_POWER.get()).removeModifier(ICE_SPELL_POWER_UUID);
			player.getAttribute(AttributeRegistry.ICE_SPELL_POWER.get()).addPermanentModifier(new AttributeModifier(ICE_SPELL_POWER_UUID , "" , valIceTabExtraIceSpellPower.get() / 100.0 , AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		else {

			player.getAttribute(AttributeRegistry.ICE_SPELL_POWER.get()).removeModifier(ICE_SPELL_POWER_UUID);

		}

	}
	@SubscribeEvent
	public static void showAbilitiesState(TickEvent.ClientTickEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		boolean ring = Utils.isItemEquipped(ModItems.DIVINE_RING.get() , player);
		if (ring && Keybindings.INSTANCE.magnetKey.consumeClick() && player != null) {
			if (toggleHudElements()) {
				ToggleAbilities.toggleHudElements.set(false);
				ToggleAbilities.ClientSpec.save();
				playTogOffSound(player);
			}
			else {
				ToggleAbilities.toggleHudElements.set(true);
				playTogOnSound(player);
				ToggleAbilities.ClientSpec.save();
			}

		}

	}
}


