package nk.divineartifacts.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nk.divineartifacts.DivineArtifacts;

public class SoundRegistry {
    private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DivineArtifacts.MODID);
    public static void register(IEventBus eventBus) {SOUND_EVENTS.register(eventBus);
    }
    public static RegistryObject<SoundEvent> FORCE_SH = registerSoundEvent("force_sh");
    public static RegistryObject<SoundEvent> TOG_ON = registerSoundEvent("tog_on");
    public static RegistryObject<SoundEvent> TOG_OFF = registerSoundEvent("tog_off");
    public static RegistryObject<SoundEvent> TABLET = registerSoundEvent("tablet");
    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DivineArtifacts.MODID, name)));

    }

}

