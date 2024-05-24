package nk.divineartifacts.mixin;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.level.material.FogType;
import nk.divineartifacts.init.ModItems;
import nk.divineartifacts.utils.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static nk.divineartifacts.config.ServerConfig.TogFirTabLavaVision;
import static nk.divineartifacts.config.ServerConfig.TogFireTablet;

@Mixin(FogRenderer.class)
public class FogEditMixin {

	@Redirect(method = "setupFog", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;getFluidInCamera()Lnet/minecraft/world/level/material/FogType;", ordinal = 0))
	private static net.minecraft.world.level.material.FogType lessFogRendering(Camera instance) {
		LocalPlayer player = Minecraft.getInstance().player;
		boolean iceTablet = Utils.isItemEquipped(ModItems.ICE_TABLET.get() , player);
		if (!TogFireTablet.get()) return null;
		if (!TogFirTabLavaVision.get()) return null;
		if (iceTablet && player.isInPowderSnow) {
			return FogType.NONE;
		}
		return instance.getFluidInCamera();
	}
}


