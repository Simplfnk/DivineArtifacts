package nk.divineartifacts.events;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nk.divineartifacts.init.ModItems;
import nk.divineartifacts.utils.Utils;
import org.joml.Math;

import java.util.UUID;

import static nk.divineartifacts.config.ServerConfig.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FireTabletEvent {
//	@SubscribeEvent()
//	public void fireDamage(LivingAttackEvent event) {
//		if (!TogFireTablet.get()) return;
//		if (!TogFirTabFireImmunity.get()) return;
//		if (event.getEntity() instanceof Player player) {
//			if (player.isCreative() || player.isSpectator()) return;
//			ItemStack fireTablet = Utils.getFirstCurio(ModItems.FIRE_TABLET.get() , player);
//			DamageSource damageSource = event.getSource();
//			boolean isFireDamage = FIRE_DAMAGE_TYPE.stream().anyMatch(damageSource::is);
//			if (fireTablet != null && isFireDamage) {
//				player.clearFire();
//				player.setRemainingFireTicks(0);
//				event.setCanceled(true);
//			}
//		}
//	}

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (!TogFireTablet.get()) return;
		if (event.phase == TickEvent.Phase.END) {
			Player player = event.player;
			if (player.isCreative() || player.isSpectator()) return;
			boolean fireTablet = Utils.isItemEquipped(ModItems.FIRE_TABLET.get() , player);
			if (fireTablet && TogFirTabLavaSpeed.get() && player.isInLava()) {
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
					Vec3 motion = new Vec3(lookVec.x * 3 , speed.y , lookVec.z * 3);
					player.setDeltaMovement(motion);
				}
				if (moveS && !moveW) {
					Vec3 lookVec = player.getLookAngle().normalize().scale(0.1);
					Vec3 speed = player.getDeltaMovement();
					Vec3 motion = new Vec3(-lookVec.x * 3 , speed.y , -lookVec.z * 3);
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
		return world.getBlockState(posAbove).getBlock() == Blocks.LAVA || world.getBlockState(posAbove).getBlock() == Blocks.AIR;
	}
	public static boolean isNoBlockBelow(Player player) {
		int X = player.getBlockX();
		int Y = player.getBlockY();
		int Z = player.getBlockZ();
		Level world = player.level();
		BlockPos posAbove = new BlockPos(X , Y - 1 , Z);
		return world.getBlockState(posAbove).getBlock() == Blocks.LAVA;
	}
	@SubscribeEvent
	public static void onPlayerSpeed(TickEvent.PlayerTickEvent event) {
		if (!TogFireTablet.get()) return;
		if (event.phase == TickEvent.Phase.END) {
			Player player = event.player;
			if (player.isCreative() || player.isSpectator()) return;
			boolean fireTablet = Utils.isItemEquipped(ModItems.FIRE_TABLET.get() , player);
			if (fireTablet && TogFirTabLavaSpeed.get() && player.isEyeInFluidType(ForgeMod.LAVA_TYPE.get())) {
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
					Vec3 motion = new Vec3(lookVec.x * 7 , speed.y , lookVec.z * 7);
					player.setDeltaMovement(motion);
				}
				if (moveS && !moveW) {
					Vec3 lookVec = player.getLookAngle().normalize().scale(0.1);
					Vec3 speed = player.getDeltaMovement();
					Vec3 motion = new Vec3(-lookVec.x * 7 , speed.y , -lookVec.z * 7);
					player.setDeltaMovement(motion);
				}
				if (moveUp && isNoBlockAbove(player)) {

					player.setPos(player.getX() , player.getY() + 0.2 , player.getZ());

				}
				if (moveDown && isNoBlockBelow(player) && !(player.onGround())) {

					player.setPos(player.getX() , player.getY() - 0.2 , player.getZ());

				}
				if (moveA) {
					movePlayerLeft2(player);

				}
				if (moveD) {
					movePlayerRight2(player);

				}
			}
			else {
				player.setNoGravity(false);
			}
		}
	}
	public static void movePlayerLeft2(Player player) {
		float yaw = player.getYRot();
		float leftYaw = yaw - 90.0F;
		double radians = Math.toRadians(leftYaw);
		double moveDistance = 0.4;
		double newX = player.getX() + (-Math.sin((float) radians) * moveDistance);
		double newZ = player.getZ() + (Math.cos((float) radians) * moveDistance);
		player.setPos(newX , player.getY() , newZ);
	}
	public static void movePlayerRight2(Player player) {
		float yaw = player.getYRot();
		float rightYaw = yaw + 90.0F;
		double radians = Math.toRadians(rightYaw);
		double moveDistance = 0.4;
		double newX = player.getX() + (-Math.sin((float) radians) * moveDistance);
		double newZ = player.getZ() + (Math.cos((float) radians) * moveDistance);
		player.setPos(newX , player.getY() , newZ);
	}
	@SubscribeEvent(priority = EventPriority.LOW)
	public static void lavaMiningSpeedOnGround(PlayerEvent.BreakSpeed event) {
		if (!TogFireTablet.get()) return;
		if (!TogFirTabMiningSpeed.get()) return;
		if (event.isCanceled()) return;
		Player player = event.getEntity();
		if (player.isCreative() || player.isSpectator()) return;
		boolean fireTablet = Utils.isItemEquipped(ModItems.FIRE_TABLET.get() , player);
		if (fireTablet && player.isInLava() && player.onGround()) {
			event.setNewSpeed(Math.max(event.getNewSpeed() , event.getOriginalSpeed() * 5.0F));
		}
	}

	@SubscribeEvent(priority = EventPriority.LOW)
	public static void lavaMiningSpeed(PlayerEvent.BreakSpeed event) {
		if (!TogFireTablet.get()) return;
		if (!TogFirTabMiningSpeed.get()) return;
		if (event.isCanceled()) return;
		Player player = event.getEntity();
		if (player.isCreative() || player.isSpectator()) return;
		boolean fireTablet = Utils.isItemEquipped(ModItems.FIRE_TABLET.get() , player);
		if (fireTablet && player.isInLava() && !(player.onGround())) {
			event.setNewSpeed(Math.max(event.getNewSpeed() , event.getOriginalSpeed() * 20.0F));
		}
	}
	private static final UUID FIRE_SPELL_POWER_UUID = UUID.fromString("39f986bc-1538-11ef-a186-325096b39f47");
	@SubscribeEvent
	public static void lavaPower(TickEvent.PlayerTickEvent event) {
		if (!TogFireTablet.get()) return;
		Player player = event.player;
		if (player.isCreative() || player.isSpectator()) return;
		boolean fireTablet = Utils.isItemEquipped(ModItems.FIRE_TABLET.get() , player);
		if (fireTablet && player.isInLava() && TogFirTabExtraFirePowerSpell.get()) {
			player.getAttribute(AttributeRegistry.FIRE_SPELL_POWER.get()).removeModifier(FIRE_SPELL_POWER_UUID);
			player.getAttribute(AttributeRegistry.FIRE_SPELL_POWER.get()).addPermanentModifier(new AttributeModifier(FIRE_SPELL_POWER_UUID , "" , valFirTabExtraFirePowerSpell.get() / 100.0 , AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		else {

			player.getAttribute(AttributeRegistry.FIRE_SPELL_POWER.get()).removeModifier(FIRE_SPELL_POWER_UUID);

		}

	}
}


