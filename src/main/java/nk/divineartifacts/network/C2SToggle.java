package nk.divineartifacts.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.Item;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;
import nk.divineartifacts.item.old.DivineRingBase;

import java.util.function.Supplier;

import static nk.divineartifacts.utils.DivineHelper.toggleMagnetNBT;

public class C2SToggle {
	private final DivineRingBase item;
	public C2SToggle(FriendlyByteBuf buffer) {
		Item item = ForgeRegistries.ITEMS.getValue(buffer.readResourceLocation());
		if (!(item instanceof DivineRingBase divineRingBase)) {
			throw new IllegalStateException();
		}
		this.item = divineRingBase;
	}
	public C2SToggle(DivineRingBase item) {
		this.item = item;
	}
	void encode(FriendlyByteBuf buffer) {
		buffer.writeResourceLocation(ForgeRegistries.ITEMS.getKey(item));

	}
	void apply(Supplier<NetworkEvent.Context> context) {
		ServerPlayer player = context.get().getSender();
		if (player != null) {
			context.get().enqueueWork(() -> toggleMagnetNBT(player));
			player.level().playSound(null , player.getX() , player.getY() , player.getZ() , SoundEvents.EXPERIENCE_ORB_PICKUP , SoundSource.PLAYERS , 1.0F , 1.0F);
		}
	}
}
