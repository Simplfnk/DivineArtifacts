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
import static nk.divineartifacts.client.handler.ToggleHelper.toggleMagnet;

public class AttractorAbilities {
	private static final ResourceLocation magOn = new ResourceLocation(DivineArtifacts.MODID ,
			"textures/gui/attr_on.png");
	private static final ResourceLocation magOff = new ResourceLocation(DivineArtifacts.MODID ,
			"textures/gui/attr_off.png");


	public static final IGuiOverlay ATTRACTOR_ABILITIES = ((gui , poseStack , partialTick , width , height) -> {

		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F , 1.0F , 1.0F , 1.0F);
		RenderSystem.setShaderTexture(0 , magOn);
		RenderSystem.setShaderTexture(0 , magOff);
		Player player = Minecraft.getInstance().player;
		boolean attractor = Utils.isRingEquipped(ModItems.GREAT_ATTRACTOR.get() , player);
		int size = 16;
		int xCord = 4;
		int yCord = height / 2 - size * 4;
		int spacing = 2 + size;

		int yCord1 = yCord + spacing;
		int yCord2 = yCord1 + spacing;
		int yCord3 = yCord2 + spacing;
		int yCord4 = yCord3 + spacing;
		if (attractor && toggleHudElements()) {

			if (toggleMagnet()) {
				poseStack.blit(magOn , xCord , yCord , 0 , 0 , size , size , size , size);
			}
			else {
				poseStack.blit(magOff , xCord , yCord , 0 , 0 , size , size , size , size);
			}
		}

	});
}
