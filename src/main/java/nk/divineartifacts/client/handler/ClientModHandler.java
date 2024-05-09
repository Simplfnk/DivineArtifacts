package nk.divineartifacts.client.handler;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nk.divineartifacts.DivineArtifacts;
import nk.divineartifacts.client.Keybindings;
import nk.divineartifacts.client.RingAbilitiesHud;

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

	}
}
