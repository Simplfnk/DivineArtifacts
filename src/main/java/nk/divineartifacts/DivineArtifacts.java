package nk.divineartifacts;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import nk.divineartifacts.client.handler.ClientForgeHandler;
import nk.divineartifacts.config.ServerConfig;
import nk.divineartifacts.config.ToggleAbilities;
import nk.divineartifacts.events.*;
import nk.divineartifacts.init.ModItems;
import nk.divineartifacts.init.SoundRegistry;
import nk.divineartifacts.item.ItemBaseClass;
import nk.divineartifacts.loot.ArtifactsLootModifiers;
import nk.divineartifacts.network.PacketHandler;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DivineArtifacts.MODID)
public class DivineArtifacts {
	public static final String MODID = "divineartifacts";

	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB_REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB , MODID);
	public static final RegistryObject<CreativeModeTab> CREATIVE_TAB = CREATIVE_MODE_TAB_REGISTRY
			.register("creative_tab" , () -> CreativeModeTab.builder()
					.title(Component.translatable("itemGroup" + DivineArtifacts.MODID))
					.icon(() -> new ItemStack(ModItems.DIVINE_RING.get()))
					.displayItems((params , output) -> {
						for (Item entOne : ModItems.AllITEMS) {
							if (entOne instanceof ItemBaseClass i && i.isEnabled.get()) {
								output.accept(entOne);
							}
						}
					})
					.build());

	public DivineArtifacts() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		SoundRegistry.register(modEventBus);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT , ToggleAbilities.ClientSpec , String.format("%s-client.toml" , DivineArtifacts.MODID));
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON , ServerConfig.serverConfig , String.format("%s-server.toml" , DivineArtifacts.MODID));
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
		ModItems.init(FMLJavaModLoadingContext.get().getModEventBus());
		ArtifactsLootModifiers.REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
		CREATIVE_MODE_TAB_REGISTRY.register(FMLJavaModLoadingContext.get().getModEventBus());
		MinecraftForge.EVENT_BUS.register(new PlayerDivineDeath());
		MinecraftForge.EVENT_BUS.register(new PlayerDivineHealth());
		MinecraftForge.EVENT_BUS.register(new PlayerDivineUno());
		MinecraftForge.EVENT_BUS.register(new DivinePotionRemover());
		MinecraftForge.EVENT_BUS.register(new DivineLuck());
		MinecraftForge.EVENT_BUS.register(new DivinationAttack());
		MinecraftForge.EVENT_BUS.register(new ClientForgeHandler());
		MinecraftForge.EVENT_BUS.register(new EnderTabletEvent());
		MinecraftForge.EVENT_BUS.register(new DeadKingHeartEvent());
		MinecraftForge.EVENT_BUS.register(new GreatAttractorEvent());
		MinecraftForge.EVENT_BUS.register(new HeartOfKnowledgeEvent());
		MinecraftForge.EVENT_BUS.register(this);

	}

	private void doClientStuff(final FMLClientSetupEvent event) {
	}

	public void commonSetup(final FMLCommonSetupEvent event) {
		PacketHandler.register();
	}

}
