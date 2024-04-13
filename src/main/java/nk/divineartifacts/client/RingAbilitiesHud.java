package nk.divineartifacts.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import nk.divineartifacts.DivineArtifacts;
import nk.divineartifacts.init.ModItemGod;
import nk.divineartifacts.utils.Utils;

import static nk.divineartifacts.client.handler.ToggleHelper.*;
public class RingAbilitiesHud {
	private static final ResourceLocation magOn = new ResourceLocation(DivineArtifacts.MODID ,
			"textures/gui/mag_on.png");
	private static final ResourceLocation magOff = new ResourceLocation(DivineArtifacts.MODID ,
			"textures/gui/mag_off.png");
	private static final ResourceLocation aioOn = new ResourceLocation(DivineArtifacts.MODID ,
			"textures/gui/aio_on.png");
	private static final ResourceLocation aioOff = new ResourceLocation(DivineArtifacts.MODID ,
			"textures/gui/aio_off.png");
	private static final ResourceLocation shieldOn = new ResourceLocation(DivineArtifacts.MODID ,
			"textures/gui/shield_on.png");
	private static final ResourceLocation shieldOff = new ResourceLocation(DivineArtifacts.MODID ,
			"textures/gui/shield_off.png");
	private static final ResourceLocation breakOn = new ResourceLocation(DivineArtifacts.MODID ,
			"textures/gui/break_on.png");
	private static final ResourceLocation breakOff = new ResourceLocation(DivineArtifacts.MODID ,
			"textures/gui/break_off.png");
	private static final ResourceLocation dropOn = new ResourceLocation(DivineArtifacts.MODID ,
			"textures/gui/drop_on.png");
	private static final ResourceLocation dropOff = new ResourceLocation(DivineArtifacts.MODID ,
			"textures/gui/drop_off.png");

	public static final IGuiOverlay RING_ABILITIES = ((gui , poseStack , partialTick , width , height) -> {
		int x = width / 2;
		int y = height;

		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F , 1.0F , 1.0F , 1.0F);
		RenderSystem.setShaderTexture(0 , magOn);
		RenderSystem.setShaderTexture(0 , magOff);
		RenderSystem.setShaderTexture(0 , aioOn);
		RenderSystem.setShaderTexture(0 , aioOff);
		RenderSystem.setShaderTexture(0 , shieldOn);
		RenderSystem.setShaderTexture(0 , shieldOff);
		RenderSystem.setShaderTexture(0 , breakOn);
		RenderSystem.setShaderTexture(0 , breakOff);
		RenderSystem.setShaderTexture(0 , dropOn);
		RenderSystem.setShaderTexture(0 , dropOff);
		Player player = Minecraft.getInstance().player;
		ItemStack ring = Utils.getFirstCurio(ModItemGod.ringDivine.get() , player);
		int xCord = 475;
		int yCord = 300;
		int size = 16;
		int spacing = 10;

		int yCord1 = yCord - spacing;
		int yCord2 = yCord1 - spacing;
		int yCord3 = yCord2 - spacing;
		int yCord4 = yCord3 - spacing;
		if (ring != null && toggleHudElements()) {
 
			if (toggleMagnet()) {
				poseStack.blit(magOn , x - xCord , y - yCord , 0 , 0 , size , size , size , size);
			}
			else {
				poseStack.blit(magOff , x - xCord , y - yCord , 0 , 0 , size , size , size , size);
			}
			if (toggleAioDamage()) {
				poseStack.blit(aioOn , x - xCord , y - yCord1 , 0 , 0 , size , size , size , size);
			}
			else {
				poseStack.blit(aioOff , x - xCord , y - yCord1 , 0 , 0 , size , size , size , size);
			}
			if (toggleShield()) {
                poseStack.blit(shieldOn , x - xCord , y - yCord2 , 0 , 0 , size , size , size , size);
            }
			else {
                poseStack.blit(shieldOff , x - xCord , y - yCord2 , 0 , 0 , size , size , size , size);
            }
			if (toggleBlockBreak()) {
                poseStack.blit(breakOn , x - xCord , y - yCord3 , 0 , 0 , size , size , size , size);
            }
			else {
                poseStack.blit(breakOff , x - xCord , y - yCord3 , 0 , 0 , size , size , size , size);
            }
			if (toggleExtraDrops()) {
                poseStack.blit(dropOn , x - xCord , y - yCord4 , 0 , 0 , size , size , size , size);
            }
			else {
                poseStack.blit(dropOff , x - xCord , y - yCord4 , 0 , 0 , size , size , size , size);
            }
		}

	});
}
