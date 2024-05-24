package nk.divineartifacts.item.subItems;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import nk.divineartifacts.client.GlintRenderTypes;
import nk.divineartifacts.item.baseitems.NatureTabletBase;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

import static nk.divineartifacts.client.handler.ToggleHelper.*;

public class NatureTablet extends NatureTabletBase {

	private static final UUID NATURE_MAGIC_RESIS_UUID = UUID.fromString("59405198-0ee1-11ef-9422-325096b39f47");
	private static final UUID NATURE_SPELL_UUID = UUID.fromString("594053b4-0ee1-11ef-8f73-325096b39f47");
	private static final UUID CAST_TIME_UUID = UUID.fromString("5940542c-0ee1-11ef-9360-325096b39f47");
	private static final UUID FIRE_MAGIC_RESIST_UUID = UUID.fromString("5940547c-0ee1-11ef-90d8-325096b39f47");
	private int delay = getNatureGrowthTickDelay();
	public NatureTablet(Properties properties , String tooltip , Supplier<Boolean> enabled , GlintRenderTypes glintType) {
		super(properties , tooltip , enabled , glintType);
	}

	@Override
	public void tickCurio(String identifier , int index , LivingEntity livingEntity) {
		if (!isEnabled.get()) return;
		if (!(livingEntity instanceof Player player)) return;
		int mana = (int) MagicData.getPlayerMagicData(player).getMana();

		if (player.hasEffect(MobEffects.POISON) && toggleNaturePoisonImmunity()) {
			player.removeEffect(MobEffects.POISON);
			MagicData.getPlayerMagicData(player).setMana(mana - getNaturePoisonImmunityCost());

		}

		if (toggleNaturePlantGrowth() && togGaiaBlessing()) {
			if (delay <= 0) {
				delay = getNatureGrowthTickDelay();

				BlockPos entityPos = new BlockPos(livingEntity.getBlockX() , livingEntity.getBlockY() , livingEntity.getBlockZ());
				int range = getNatureGrowthRange();
				int limit = 0;

				List<BlockPos> blocks = new ArrayList<>();

				for (BlockPos pos : BlockPos.betweenClosed(entityPos.getX() - range , entityPos.getY() - range , entityPos.getZ() - range , entityPos.getX() + range , entityPos.getY() + range , entityPos.getZ() + range)) {
					Block block = livingEntity.level().getBlockState(pos).getBlock();
					if (block instanceof CropBlock || block instanceof StemBlock || block instanceof KelpBlock || block instanceof SeagrassBlock || block instanceof CactusBlock || block instanceof SaplingBlock) {
						blocks.add(new BlockPos(pos));
					}
				}

				if (blocks.size() >= 1) {
					Random random = new Random();

					while (blocks.size() >= 1 && limit < 3 && mana >= ((blocks.size() * getNatureGrowthCost()))) {
						BlockPos pos = blocks.remove(random.nextInt(blocks.size()));
						BlockState state = livingEntity.level().getBlockState(pos);

						if (BoneMealItem.applyBonemeal(new ItemStack(Items.BONE_MEAL) , livingEntity.level() , pos , (Player) livingEntity)) {
							if (state != livingEntity.level().getBlockState(pos)) {
								limit++;
								livingEntity.level().levelEvent(2005 , pos , 0);
								MagicData.getPlayerMagicData(player).setMana(mana - (blocks.size() * getNatureGrowthCost()));
							}

						}
					}
				}
			}
			else {
				delay--;
			}
		}
	}
	@Override
	public Multimap<Attribute, AttributeModifier> curioModifiers(SlotContext slotContext , UUID uuid , ItemStack stack) {
		Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && toggleNatureMagicResist() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.NATURE_MAGIC_RESIST.get() , new AttributeModifier(NATURE_MAGIC_RESIS_UUID , "" , getNatureMagicResist() / 100.0 , AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && toggleNatureSpellPower() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.NATURE_SPELL_POWER.get() , new AttributeModifier(NATURE_SPELL_UUID , "" , getNatureSpellPower() / 100.0 , AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		if (CuriosApi.getItemStackSlots(stack , slotContext.entity()).containsKey(slotContext.identifier()) && toggleNatureFireResist() && this.isEnabled.get()) {
			modifiers.put(AttributeRegistry.FIRE_MAGIC_RESIST.get() , new AttributeModifier(FIRE_MAGIC_RESIST_UUID , "" , -getNatureFireResist() / 100.0 , AttributeModifier.Operation.MULTIPLY_TOTAL));
		}

		return modifiers;
	}

	@Override
	public void onUnequippedCurio(String identifier , LivingEntity livingEntity) {

	}
}