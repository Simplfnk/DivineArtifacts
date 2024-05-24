package nk.divineartifacts.mixin;

import net.minecraft.world.entity.LivingEntity;
import nk.divineartifacts.init.ModItems;
import nk.divineartifacts.utils.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public class PlayerEntityMixin {

	@Redirect(method = "isPushable()Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isSpectator()Z", ordinal = 0))
	public boolean isAffectedByFluids(LivingEntity instance) {
		boolean isIceOrFire = Utils.isItemEquipped(ModItems.FIRE_TABLET.get() , instance) || Utils.isItemEquipped(ModItems.ICE_TABLET.get() , instance);
		if (isIceOrFire && instance.isInWater() || instance.isInLava()) {
			return false;
		}
		return instance.isAlive() && !instance.isSpectator() && !instance.onClimbable();
	}
}
