package nk.divineartifacts.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import nk.divineartifacts.init.ModItems;
import nk.divineartifacts.utils.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static nk.divineartifacts.config.ServerConfig.TogFirTabLavaVision;
import static nk.divineartifacts.config.ServerConfig.TogFireTablet;

@Mixin(LivingEntity.class)
public class canFreezeMixin {

	@Redirect(method = "canFreeze()Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isSpectator()Z", ordinal = 0))
	private boolean lessFogRendering(LivingEntity instance) {
		Player player = (Player) instance;
		boolean iceTablet = Utils.isItemEquipped(ModItems.ICE_TABLET.get() , player);
		if (!TogFireTablet.get()) return false;
		if (!TogFirTabLavaVision.get()) return false;
		if (iceTablet) {
			return true;
		}
		return instance.isSpectator();
	}
}


