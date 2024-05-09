package nk.divineartifacts.init;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nk.divineartifacts.DivineArtifacts;
import nk.divineartifacts.client.GlintRenderTypes;
import nk.divineartifacts.config.ServerConfig;
import nk.divineartifacts.item.DivineOrbBase;
import nk.divineartifacts.item.rings.ItemDivineOrb;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
public class ModItemOrb {
    public static List<DivineOrbBase> GodOrb = new ArrayList<>();

    private static final DeferredRegister<Item> register = DeferredRegister.create(ForgeRegistries.ITEMS, DivineArtifacts.MODID);
    public static RegistryObject<DivineOrbBase> divineOrb = register("orb_divine",() -> new ItemDivineOrb(
            new Item.Properties(),
            "",
            ServerConfig.configOrbOfMagic.get(),
            GlintRenderTypes.PURPLE));

    public static void init(IEventBus bus) {
        register.register(bus);
    }
    private static RegistryObject<DivineOrbBase> register(String name, Supplier<DivineOrbBase> supplier) {
        return register.register(name, supplier);
    }

}
