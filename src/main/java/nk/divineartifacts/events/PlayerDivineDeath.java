package nk.divineartifacts.events;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nk.divineartifacts.DivineArtifacts;
import nk.divineartifacts.init.ModItems;
import nk.divineartifacts.utils.Utils;

import static nk.divineartifacts.client.handler.ToggleHelper.toggleDivineRing;

@Mod.EventBusSubscriber(modid = DivineArtifacts.MODID)
public class PlayerDivineDeath {
	@SubscribeEvent
	public void onLivingDeath( LivingDeathEvent event ) {
		if ( ! toggleDivineRing() ) return;
		if ( ! ( event.getEntity( ) instanceof ServerPlayer player ) ) return;
		if ( player.isCreative( ) || player.isSpectator( ) ) return;
		ItemStack ring = Utils.getFirstCurio(ModItems.DIVINE_RING.get( ) , player );
		if ( ring != null ) {
			event.setCanceled( true );
			player.awardStat( Stats.ITEM_USED.get( Items.TOTEM_OF_UNDYING ) );
			CriteriaTriggers.USED_TOTEM.trigger( player , ring );
			player.setHealth( 20F );
			player.removeAllEffects( );
			player.addEffect( new MobEffectInstance( MobEffects.HEAL , 20 , 255 ) );
			player.level( ).broadcastEntityEvent( player , ( byte ) 35 );
			if ( player.level( ).isClientSide ) Minecraft.getInstance( ).gameRenderer.displayItemActivation( ring );
		}
	}
	
}
