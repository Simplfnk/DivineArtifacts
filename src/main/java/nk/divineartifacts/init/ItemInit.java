package nk.divineartifacts.init;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nk.divineartifacts.DivineArtifacts;
import nk.divineartifacts.client.GlintRenderTypes;
import nk.divineartifacts.config.ServerConfig;
import nk.divineartifacts.item.*;
import nk.divineartifacts.item.rings.ItemEldritch;
import nk.divineartifacts.item.rings.ItemKingHeart;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static nk.divineartifacts.config.ServerConfig.TogEldritchTablet;

public class ItemInit {

	public static List<Item> item = new ArrayList<>();

	private static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS , DivineArtifacts.MODID);

	private static RegistryObject<Item> register(String name , Supplier<Item> supplier) {
		return items.register(name , supplier);
	}

	public static final RegistryObject<Item> divineCrystal = register(
			"div_crystal" , () -> new DivineCrystal(
					new Item.Properties() ,
					"" ,
					ServerConfig.configDivineRing.get() ,
					GlintRenderTypes.PURPLE)
	);
	public static final RegistryObject<Item> diveGold = register(
			"div_gold_ring" , () -> new DiveGoldRing(
					new Item.Properties() ,
					"" ,
					ServerConfig.configDivineRing.get() ,
					GlintRenderTypes.ORANGE)
	);
	public static final RegistryObject<Item> HolyTablet = register(
			"holy_tablet" , () -> new HolyTabletBase(
					new Item.Properties() ,
					"" ,
					true ,
					GlintRenderTypes.GOLD)
	);
	public static final RegistryObject<Item> EnderTablet = register(
			"ender_tablet" , () -> new ItemEldritch(
					new Item.Properties() ,
					"" ,
					true ,
					GlintRenderTypes.PURPLE)
	);
	public static final RegistryObject<Item> EldritchTablet = register(
			"eldritch_tablet" , () -> new ItemEldritch(
					new Item.Properties() ,
					"" ,
					TogEldritchTablet.get() ,
					GlintRenderTypes.DARK)
	);
	public static final RegistryObject<Item> BloodTablet = register(
			"blood_tablet" , () -> new BloodTabletBase(
					new Item.Properties() ,
					"" ,
					true ,
					GlintRenderTypes.RED)
	);
	public static final RegistryObject<Item> EvocationTablet = register(
			"evocation_tablet" , () -> new EvocationTabletBase(
					new Item.Properties() ,
					"" ,
					true ,
					GlintRenderTypes.WHITE)
	);
	public static final RegistryObject<Item> FireTablet = register(
			"fire_tablet" , () -> new FireTabletBase(
					new Item.Properties() ,
					"" ,
					true ,
					GlintRenderTypes.ORANGE)
	);
	public static final RegistryObject<Item> IceTablet = register(
			"ice_tablet" , () -> new IceTabletBase(
					new Item.Properties() ,
					"" ,
					true ,
					GlintRenderTypes.AQUA)
	);
	public static final RegistryObject<Item> LightningTablet = register(
			"lightning_tablet" , () -> new LightningTabletBase(
					new Item.Properties() ,
					"" ,
					true ,
					GlintRenderTypes.BLUE));
	public static final RegistryObject<Item> NatureTablet = register(
			"nature_tablet" , () -> new NatureTabletBase(
					new Item.Properties() ,
					"" ,
					true ,
					GlintRenderTypes.GREEN));
	public static final RegistryObject<Item> DeadKingHeart = register(
			"dead_king_heart" , () -> new ItemKingHeart(
					new Item.Properties() ,
					"" ,
					true ,
					GlintRenderTypes.RED));

	public static void init(IEventBus bus) {
		items.register(bus);
	}

}
