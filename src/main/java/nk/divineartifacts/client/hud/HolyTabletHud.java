package nk.divineartifacts.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import nk.divineartifacts.DivineArtifacts;
import nk.divineartifacts.init.ModItems;
import nk.divineartifacts.utils.Utils;

import static nk.divineartifacts.client.handler.ToggleHelper.toggleHudElements;
import static nk.divineartifacts.config.ToggleAbilities.TogSunShield;

public class HolyTabletHud {
	private static final ResourceLocation sunOn = new ResourceLocation(DivineArtifacts.MODID ,
			"textures/gui/sun_on.png");
	private static final ResourceLocation sunOff = new ResourceLocation(DivineArtifacts.MODID ,
			"textures/gui/sun_off.png");


	public static final IGuiOverlay SUN_SHIELD = ((gui , poseStack , partialTick , width , height) -> {

		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F , 1.0F , 1.0F , 1.0F);
		RenderSystem.setShaderTexture(0 , sunOn);
		RenderSystem.setShaderTexture(0 , sunOff);
		Player player = Minecraft.getInstance().player;
		boolean item = Utils.isRingEquipped(ModItems.HOLY_TABLET.get() , player);
		int size = 16;
		int xCord = 4;
		int yCord = height / 2 - size * 4;
		int spacing = 2 + size;

		int yCord1 = yCord + spacing;
		int yCord2 = yCord1 + spacing;
		int yCord3 = yCord2 + spacing;
		int yCord4 = yCord3 + spacing;
		if (item && toggleHudElements()) {

			if (TogSunShield.get()) {
				poseStack.blit(sunOn , xCord , yCord2 , 0 , 0 , size , size , size , size);
			}
			else {
				poseStack.blit(sunOff , xCord , yCord2 , 0 , 0 , size , size , size , size);
			}
		}

	});
}
