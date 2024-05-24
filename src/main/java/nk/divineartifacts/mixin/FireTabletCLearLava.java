package nk.divineartifacts.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.tags.FluidTags;
import nk.divineartifacts.init.ModItems;
import nk.divineartifacts.utils.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static nk.divineartifacts.client.handler.ToggleHelper.toggleFirTabLavaVision;
import static nk.divineartifacts.client.handler.ToggleHelper.toggleFireTablet;

@Mixin(ScreenEffectRenderer.class)
public class FireTabletCLearLava {
	@Inject(at = @At("HEAD"), method = "renderFire", cancellable = true)
	private static void clearLavaView(Minecraft minecraft , PoseStack poseStack , CallbackInfo ci) {
		LocalPlayer player = Minecraft.getInstance().player;
		boolean firTablet = Utils.isItemEquipped(ModItems.FIRE_TABLET.get() , player);
		boolean iceTablet = Utils.isItemEquipped(ModItems.ICE_TABLET.get() , player);
		if (!toggleFireTablet()) return;
		if (!toggleFirTabLavaVision()) return;

		if (player.isCreative())
			ci.cancel();
		if (player.isEyeInFluid(FluidTags.LAVA) && firTablet) {
			poseStack.translate(0 , -0.25 , 0);
		}
		if (player.isEyeInFluid(FluidTags.WATER) && iceTablet) {
			poseStack.translate(0 , -0.25 , 0);
		}
	}
}