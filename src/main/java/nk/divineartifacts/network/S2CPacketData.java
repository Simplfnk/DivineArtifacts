package nk.divineartifacts.network;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record S2CPacketData( BlockPos pos , BlockState state ) {
	public static S2CPacketData decode( FriendlyByteBuf buffer ) {
		return new S2CPacketData( buffer.readBlockPos( ) , Block.stateById( buffer.readVarInt( ) ) );
	}

	public static void encode( S2CPacketData msg , FriendlyByteBuf buffer ) {
		buffer.writeBlockPos( msg.pos );
		buffer.writeVarInt( Block.getId( msg.state ) );
	}

	public void handle( Supplier< NetworkEvent.Context > contextSupplier ) {
		NetworkEvent.Context context = contextSupplier.get( );
		context.enqueueWork( ( ) -> {
			// This will be executed on the client side. Perform your client-side operation here, like adding the destroy block effect.
			assert Minecraft.getInstance( ).level != null;
			Minecraft.getInstance( ).level.addDestroyBlockEffect( pos , state( ) );
		} );
		context.setPacketHandled( true );
	}
}
