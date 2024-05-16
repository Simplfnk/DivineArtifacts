package nk.divineartifacts.client.handler;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nk.divineartifacts.DivineArtifacts;
import nk.divineartifacts.client.Keybindings;
import nk.divineartifacts.client.hud.GaiaBlessingAbilities;
import nk.divineartifacts.client.hud.HolyTabletHud;
import nk.divineartifacts.client.hud.RingAbilitiesHud;
import nk.divineartifacts.client.hud.AttractorAbilities;

@Mod.EventBusSubscriber(modid = DivineArtifacts.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModHandler {
	@SubscribeEvent
	public static void registerKeys(RegisterKeyMappingsEvent event) {
		event.register(Keybindings.INSTANCE.magnetKey);
		event.register(Keybindings.INSTANCE.explodedKey);
		event.register(Keybindings.INSTANCE.shieldKey);
		event.register(Keybindings.INSTANCE.blockBreakKey);
		event.register(Keybindings.INSTANCE.extraDropsKey);
		event.register(Keybindings.INSTANCE.showRingState);
		event.register(Keybindings.INSTANCE.ToggleAllOn);
		event.register(Keybindings.INSTANCE.ToggleAllOff);
	}
	@SubscribeEvent
	public static void registerHUDGui(RegisterGuiOverlaysEvent event) {
		event.registerAboveAll(DivineArtifacts.MODID + "hud" , RingAbilitiesHud.RING_ABILITIES);
		event.registerAboveAll(DivineArtifacts.MODID + "attractor_hud" , AttractorAbilities.ATTRACTOR_ABILITIES);
		event.registerAboveAll(DivineArtifacts.MODID + "gaia_blessing_hud" , GaiaBlessingAbilities.GAIA_BLESSING);
		event.registerAboveAll(DivineArtifacts.MODID + "sun_shield_hud" , HolyTabletHud.SUN_SHIELD);

	}
}
