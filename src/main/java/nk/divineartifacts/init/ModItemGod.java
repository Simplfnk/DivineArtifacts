package nk.divineartifacts.init;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nk.divineartifacts.DivineArtifacts;
import nk.divineartifacts.client.GlintRenderTypes;
import nk.divineartifacts.item.DivineRingBase;
import nk.divineartifacts.item.rings.ItemRingGod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static nk.divineartifacts.config.ServerConfig.*;

public class ModItemGod {
	public static List<DivineRingBase> GodRing = new ArrayList<>();

	private static final DeferredRegister<Item> register = DeferredRegister.create(ForgeRegistries.ITEMS , DivineArtifacts.MODID);

	public static RegistryObject<DivineRingBase> ringDivine = register("ring_divine" , () -> new ItemRingGod(
			new Item.Properties() ,
			"" ,
			configDivineRing.get() ,
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

	));

	public static void init(IEventBus bus) {
		register.register(bus);
	}
	private static RegistryObject<DivineRingBase> register(String name , Supplier<DivineRingBase> supplier) {
		return register.register(name , supplier);
	}

}
