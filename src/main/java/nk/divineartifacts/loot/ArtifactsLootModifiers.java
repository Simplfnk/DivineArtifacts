package nk.divineartifacts.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nk.divineartifacts.DivineArtifacts;
import nk.divineartifacts.item.DiveGoldRing;
import nk.divineartifacts.item.DivineCrystal;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ArtifactsLootModifiers  extends LootModifier {

	public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> REGISTER = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS , DivineArtifacts.MODID);
	public static final RegistryObject<Codec< ArtifactsLootModifiers >> CODEC = REGISTER.register("add_item", () ->
			RecordCodecBuilder.create(
					inst -> LootModifier.codecStart(inst).and(
							inst.group(
									Codec.list(Codec.STRING).fieldOf("loot_tables").forGetter(m -> m.lootTables),
									ForgeRegistries.ITEMS.getCodec().fieldOf("ring").forGetter(m -> m.item)
							)
					).apply(inst, ArtifactsLootModifiers::new)
			)
	);

	public final List<String> lootTables;
	public final Item item;

	public ArtifactsLootModifiers(LootItemCondition[] conditionsIn, List<String> lootTables, Item item) {
		super(conditionsIn);
		if(!(item instanceof DiveGoldRing || item instanceof DivineCrystal)) {
			lootTables.clear();
			throw new IllegalArgumentException("Provided item is not a item.");
		}

		this.lootTables = lootTables;
		this.item =  item;
	}

	@Override
	protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		if( lootTables.contains(context.getQueriedLootTableId().toString())) {
			generatedLoot.add(new ItemStack(item));
		}

		return generatedLoot;
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return CODEC.get();
	}
}
