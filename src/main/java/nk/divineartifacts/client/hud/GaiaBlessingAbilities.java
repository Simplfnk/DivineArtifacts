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

import static nk.divineartifacts.client.handler.ToggleHelper.togGaiaBlessing;
import static nk.divineartifacts.client.handler.ToggleHelper.toggleHudElements;

public class GaiaBlessingAbilities {
	private static final ResourceLocation growthOn = new ResourceLocation(DivineArtifacts.MODID ,
			"textures/gui/growth_on.png");
	private static final ResourceLocation growthOff = new ResourceLocation(DivineArtifacts.MODID ,
			"textures/gui/growth_off.png");

	public static final IGuiOverlay GAIA_BLESSING = ((gui , poseStack , partialTick , width , height) -> {

		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F , 1.0F , 1.0F , 1.0F);
		RenderSystem.setShaderTexture(0 , growthOn);
		RenderSystem.setShaderTexture(0 , growthOff);
		Player player = Minecraft.getInstance().player;
		boolean natureTablet = Utils.isItemEquipped(ModItems.NATURE_TABLET.get() , player);
		int size = 16;
		int xCord = 4;
		int yCord = height / 2 - size * 4;
		int spacing = 2 + size;

		int yCord1 = yCord + spacing;
		int yCord2 = yCord1 + spacing;
		int yCord3 = yCord2 + spacing;
		int yCord4 = yCord3 + spacing;
		int yCord5 = yCord4 + spacing;
		int yCord6 = yCord5 + spacing;


		if (natureTablet && toggleHudElements()) {

			if (togGaiaBlessing()) {
				poseStack.blit(growthOn , xCord , yCord1 , 0 , 0 , size , size , size , size);
			}
			else {
				poseStack.blit(growthOff , xCord , yCord1 , 0 , 0 , size , size , size , size);
			}
		}

	});
}
