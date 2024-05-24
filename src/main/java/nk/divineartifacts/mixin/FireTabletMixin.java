package nk.divineartifacts.mixin;

import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import nk.divineartifacts.init.ModItems;
import nk.divineartifacts.utils.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static nk.divineartifacts.config.ServerConfig.TogFirTabLavaVision;
import static nk.divineartifacts.config.ServerConfig.TogFireTablet;

@Mixin(FogRenderer.class)
public class FireTabletMixin {
	@Redirect(
			method = "setupFog(Lnet/minecraft/client/Camera;Lnet/minecraft/client/renderer/FogRenderer$FogMode;FZF)V", at = @At(
			value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;isSpectator()Z", ordinal = 0
	)
	)
	private static boolean lessFogRendering(Entity entity) {

		Player player = (Player) entity;
		boolean firTablet = Utils.isItemEquipped(ModItems.FIRE_TABLET.get() , player);

		if (player.isCreative() || firTablet && TogFirTabLavaVision.get() && TogFireTablet.get()) {
			return true;
		}

		return entity.isSpectator();
	}

}