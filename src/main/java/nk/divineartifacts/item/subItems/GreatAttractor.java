package nk.divineartifacts.item.subItems;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import nk.divineartifacts.client.GlintRenderTypes;
import nk.divineartifacts.item.baseitems.GreatAttractorBase;

import java.util.List;
import java.util.function.Supplier;

import static nk.divineartifacts.client.handler.ToggleHelper.toggleMagnet;
import static nk.divineartifacts.config.ServerConfig.ValGreatAttractorRang;

public class GreatAttractor extends GreatAttractorBase {
	private final int RANGE = ValGreatAttractorRang.get();
	public GreatAttractor(
			Properties properties , String tooltip , Supplier<Boolean> enabled , GlintRenderTypes glintType
	) {
		super(properties , tooltip , enabled , glintType);
	}
	public void tickCurio(String identifier , int index , LivingEntity livingEntity) {
		if (!this.isEnabled.get()) return;

		if (livingEntity instanceof Player && this.isEnabled.get() && !livingEntity.level().isClientSide && toggleMagnet()) {
			BlockPos pos = new BlockPos(livingEntity.getBlockX() , livingEntity.getBlockY() , livingEntity.getBlockZ());
			List<ItemEntity> entities = livingEntity.level().getEntitiesOfClass(ItemEntity.class , new AABB(pos.getX() + RANGE , pos.getY() + RANGE , pos.getZ() + RANGE , pos.getX() - RANGE , pos.getY() - RANGE , pos.getZ() - RANGE));
			for (ItemEntity item : entities) {
				if (item.isAlive() && !item.hasPickUpDelay()) {
					item.playerTouch((Player) livingEntity);
				}
			}
		}
		if (livingEntity instanceof Player && this.isEnabled.get() && !livingEntity.level().isClientSide && toggleMagnet()) {

			BlockPos pos = new BlockPos(livingEntity.getBlockX() , livingEntity.getBlockY() , livingEntity.getBlockZ());
			List<ExperienceOrb> entities = livingEntity.level().getEntitiesOfClass(ExperienceOrb.class , new AABB(pos.getX() + RANGE , pos.getY() + RANGE , pos.getZ() + RANGE , pos.getX() - RANGE , pos.getY() - RANGE , pos.getZ() - RANGE));
			for (ExperienceOrb orb : entities) {
				if (orb.isAlive()) {
					orb.playerTouch((Player) livingEntity);
				}
			}
		}

	}
}