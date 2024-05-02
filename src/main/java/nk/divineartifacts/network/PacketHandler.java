package nk.divineartifacts.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import nk.divineartifacts.DivineArtifacts;

public class PacketHandler {
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(DivineArtifacts.MODID , "main") ,
			() -> PROTOCOL_VERSION ,
			PROTOCOL_VERSION::equals ,
			PROTOCOL_VERSION::equals);

	public static void register() {
		int id = 0;
		INSTANCE.registerMessage(
				id++ ,
				S2CPacketData.class ,
				S2CPacketData::encode ,
				S2CPacketData::decode ,
				S2CPacketData::handle
		);
//		INSTANCE.registerMessage(
//				id++ ,
//				C2SToggle.class ,
//				C2SToggle::encode ,
//				C2SToggle::new ,
//				C2SToggle::apply
//		);
	}
}
