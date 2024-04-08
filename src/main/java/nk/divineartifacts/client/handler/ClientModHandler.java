package nk.divineartifacts.client.handler;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nk.divineartifacts.DivineArtifacts;
import nk.divineartifacts.client.Keybindings;

@Mod.EventBusSubscriber(modid = DivineArtifacts.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
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
}
