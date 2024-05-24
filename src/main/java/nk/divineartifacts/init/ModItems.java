package nk.divineartifacts.init;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nk.divineartifacts.DivineArtifacts;
import nk.divineartifacts.client.GlintRenderTypes;
import nk.divineartifacts.item.ItemBaseClass;
import nk.divineartifacts.item.baseitems.*;
import nk.divineartifacts.item.old.DiveGoldRing;
import nk.divineartifacts.item.old.DivineCrystal;
import nk.divineartifacts.item.old.DivineOrb;
import nk.divineartifacts.item.old.RingGod;
import nk.divineartifacts.item.subItems.DeadKingHeart;
import nk.divineartifacts.item.subItems.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static nk.divineartifacts.config.ServerConfig.*;

public class ModItems {
	public static List<ItemBaseClass> AllITEMS = new ArrayList<>();

	private static final DeferredRegister<Item> register = DeferredRegister.create(ForgeRegistries.ITEMS , DivineArtifacts.MODID);

	public static RegistryObject<ItemBaseClass> DIVINE_RING = register(
			"ring_divine" , () -> new RingGod(
					new Item.Properties() ,
					"" ,
					() -> configDivineRing.get() ,
					MobEffects.REGENERATION ,
					MobEffects.LUCK ,
					MobEffects.FIRE_RESISTANCE ,
					MobEffects.WATER_BREATHING ,
					MobEffects.NIGHT_VISION ,
					MobEffects.SATURATION ,
					RegenerateHealth.get() ,
					Luck.get() ,
					FireResistance.get() ,
					WATER_BREATHING.get() ,
					NIGHT_VISION.get() ,
					SATURATION.get() ,
					GlintRenderTypes.GOLD

			)
	);
	public static RegistryObject<ItemBaseClass> DIVINE_ORB = register(
			"orb_divine" , () -> new DivineOrb(
					new Item.Properties() ,
					"" ,
					() -> configOrbOfMagic.get() ,
					GlintRenderTypes.PURPLE
			)
	);
	public static final RegistryObject<ItemBaseClass> DIV_CRYSTAL = register(
			"div_crystal" , () -> new DivineCrystal(
					new Item.Properties() ,
					"" ,
					() -> togCrystal.get() ,
					GlintRenderTypes.PURPLE
			)
	);
	public static final RegistryObject<ItemBaseClass> DIV_GOLD_RING = register(
			"div_gold_ring" , () -> new DiveGoldRing(
					new Item.Properties() ,
					"" ,
					() -> configDivineRing.get() ,
					GlintRenderTypes.ORANGE
			)
	);
	public static final RegistryObject<ItemBaseClass> HOLY_TABLET = register(
			"holy_tablet" , () -> new HolyTablet(
					new Item.Properties() ,
					"" ,
					() -> TogHolyTablet.get() ,
					GlintRenderTypes.GOLD
			)
	);
	public static final RegistryObject<ItemBaseClass> ENDER_TABLET = register(
			"ender_tablet" , () -> new EnderTablet(
					new Item.Properties() ,
					"" ,
					() -> TogEnderTablet.get() ,
					GlintRenderTypes.PURPLE
			)
	);
	public static final RegistryObject<ItemBaseClass> ELDRITCH_TABLET = register(
			"eldritch_tablet" , () -> new EldritchTablet(
					new Item.Properties() ,
					"" ,
					() -> TogEldritchTablet.get() ,
					GlintRenderTypes.DARK
			)
	);
	public static final RegistryObject<ItemBaseClass> BLOOD_TABLET = register(
			"blood_tablet" , () -> new BloodTablet(
					new Item.Properties() ,
					"" ,
					() -> TogBloodTablet.get() ,
					GlintRenderTypes.RED
			)
	);
	public static final RegistryObject<ItemBaseClass> EVOCATION_TABLET = register(
			"evocation_tablet" , () -> new EvocationTabletBase(
					new Item.Properties() ,
					"" ,
					() -> true ,
					GlintRenderTypes.WHITE
			)
	);
	public static final RegistryObject<ItemBaseClass> FIRE_TABLET = register(
			"fire_tablet" , () -> new FireTablet(
					new Item.Properties() ,
					"" ,
					() -> TogFireTablet.get() ,
					GlintRenderTypes.ORANGE
			)
	);
	public static final RegistryObject<ItemBaseClass> ICE_TABLET = register(
			"ice_tablet" , () -> new IceTablet(
					new Item.Properties() ,
					"" ,
					() -> TogIceTablet.get() ,
					GlintRenderTypes.AQUA
			)
	);
	public static final RegistryObject<ItemBaseClass> LIGHTNING_TABLET = register(
			"lightning_tablet" , () -> new LightningTabletBase(
					new Item.Properties() ,
					"" ,
					() -> true ,
					GlintRenderTypes.BLUE
			)
	);
	public static final RegistryObject<ItemBaseClass> NATURE_TABLET = register(
			"nature_tablet" , () -> new NatureTablet(
					new Item.Properties() ,
					"" ,
					() -> TogNatureTablet.get() ,
					GlintRenderTypes.GREEN
			)
	);
	public static final RegistryObject<ItemBaseClass> DEAD_KING_HEART = register(
			"dead_king_heart" , () -> new DeadKingHeart(
					new Item.Properties() ,
					"" ,
					() -> TogDeadKingHeart.get() ,
					GlintRenderTypes.RED
			)
	);
	public static final RegistryObject<ItemBaseClass> LORE_MASTER = register(
			"lore_master" , () -> new LoreMaster(
					new Item.Properties() ,
					"" ,
					() -> true ,
					GlintRenderTypes.LORE
			)
	);
	public static final RegistryObject<ItemBaseClass> GREAT_ATTRACTOR = register(
			"great_attractor" , () -> new GreatAttractor(
					new Item.Properties() ,
					"" ,
					() -> TogGreatAttractor.get() ,
					GlintRenderTypes.ATTRACTOR
			)
	);
	public static final RegistryObject<ItemBaseClass> HEART_OF_KNOWLEDGE = register(
			"heart_of_knowledge" , () -> new HeartOfKnowledge(
					new Item.Properties() ,
					"" ,
					() -> true ,
					GlintRenderTypes.LORE
			)
	);


	public static void init(IEventBus bus) {
		register.register(bus);
	}
	private static RegistryObject<ItemBaseClass> register(String name , Supplier<ItemBaseClass> supplier) {
		return register.register(name , supplier);
	}
}
