package nk.divineartifacts.init;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nk.divineartifacts.DivineArtifacts;
import nk.divineartifacts.client.GlintRenderTypes;
import nk.divineartifacts.config.ServerConfig;
import nk.divineartifacts.item.DiveGoldRing;
import nk.divineartifacts.item.DivineCrystal;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ItemInit {

	public static List< Item > item = new ArrayList<>();

	private static final DeferredRegister< Item > items = DeferredRegister.create(ForgeRegistries.ITEMS , DivineArtifacts.MODID);

	private static RegistryObject< Item > register(String name , Supplier< Item > supplier) {
		return items.register(name , supplier);
	}

	public static final RegistryObject< Item > divineCrystal = register(
			"div_crystal" , () -> new DivineCrystal(
					new Item.Properties() ,
					"" ,
					ServerConfig.configDivineRing.get() ,
					GlintRenderTypes.PURPLE)
	);
	public static final RegistryObject< Item > diveGold = register(
			"div_gold_ring" , () -> new DiveGoldRing(
					new Item.Properties() ,
					"" ,
					ServerConfig.configDivineRing.get() ,
					GlintRenderTypes.ORANGE)
	);

	public static void init(IEventBus bus) {
		items.register(bus);
	}

}
