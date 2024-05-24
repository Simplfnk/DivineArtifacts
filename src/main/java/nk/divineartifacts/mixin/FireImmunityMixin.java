package nk.divineartifacts.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import nk.divineartifacts.init.ModItems;
import nk.divineartifacts.utils.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static nk.divineartifacts.config.ServerConfig.TogFirTabLavaVision;
import static nk.divineartifacts.config.ServerConfig.TogFireTablet;

@Mixin(Entity.class)
public class FireImmunityMixin {

	@Redirect(method = "fireImmune", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/EntityType;fireImmune()Z", ordinal = 0))
	private boolean lessFogRendering(EntityType instance) {

		Player player = Minecraft.getInstance().player;
		boolean fireTablet = Utils.isItemEquipped(ModItems.FIRE_TABLET.get() , player);
		if (fireTablet && TogFirTabLavaVision.get() && TogFireTablet.get()) {
			return true;
		}
		return instance.fireImmune();
	}
}


