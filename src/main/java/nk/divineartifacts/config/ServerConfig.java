package nk.divineartifacts.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class ServerConfig {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	private static final serverConfig SERVER_CONFIG = new serverConfig(BUILDER);
	public static final ForgeConfigSpec serverConfig = BUILDER.build();

	public static BooleanValue configDivineRing;
	public static BooleanValue configOrbOfMagic;


	public static BooleanValue Tattack;
	public static BooleanValue TArmor;
	public static BooleanValue TArmorToughness;
	public static BooleanValue TMAXHealth;
	public static BooleanValue TKnockbackResistance;
	public static BooleanValue TStepHeight;
	public static BooleanValue TBlockReach;
	public static BooleanValue TEntityReach;
	public static BooleanValue TMovementSpeed;
	public static BooleanValue TSwimSpeed;
	public static BooleanValue TFlyingSpeed;
	public static BooleanValue TLuck;
	public static BooleanValue TCritChance;
	public static BooleanValue TArmorPierce;
	public static BooleanValue TArmorShred;
	public static BooleanValue TArrowVelocity;
	public static BooleanValue TArrowDamage;
	public static BooleanValue TDrawSpeed;

	//================================================================
	public static IntValue MagnetRange;
	public static IntValue MaxHeart;
	public static IntValue RegenerateHealth;
	public static IntValue Luck;
	public static IntValue FireResistance;
	public static IntValue ATTACK_DAMAGE;
	public static IntValue ARMOR;
	public static IntValue ARMOR_TOUGHNESS;
	public static IntValue KNOCKBACK_RESISTANCE;
	public static IntValue STEP_HEIGHT;
	public static IntValue BLOCK_REACH;
	public static IntValue ENTITY_REACH;
	public static IntValue MOVEMENT_SPEED;
	public static IntValue SWIM_SPEED;
	public static IntValue FLYING_SPEED;
	public static IntValue LUCK;
	public static IntValue CRIT_CHANCE;
	public static IntValue ARMOR_PIERCE;
	public static IntValue ARMOR_SHRED;
	public static IntValue ARROW_VELOCITY;
	public static IntValue ARROW_DAMAGE;
	public static IntValue DRAW_SPEED;
	public static IntValue WATER_BREATHING;
	public static IntValue NIGHT_VISION;
	public static IntValue SATURATION;
	public static IntValue AoeDamage;
	public static IntValue ExtraDrops;


	//============================== Orb Of Magic

	public static BooleanValue TSPELL_RESIST;
	public static BooleanValue TSPELL_POWER;
	public static BooleanValue TMANA_REGEN;
	public static BooleanValue TMAX_MANA;
	public static BooleanValue TCOOLDOWN_REDUCTION;
	public static BooleanValue TCAST_TIME_REDUCTION;
	public static IntValue SPELL_RESIST;
	public static IntValue SPELL_POWER;
	public static IntValue MANA_REGEN;
	public static IntValue MAX_MANA;
	public static IntValue COOLDOWN_REDUCTION;
	public static IntValue CAST_TIME_REDUCTION;


	public static class serverConfig {
		public serverConfig(final ForgeConfigSpec.Builder builder) {
			builder.push("Divine Artifacts");
			builder.push("Enable/Disable Artifacts");
			configDivineRing = builder.define("Items.Ring Of Divinity" , true);
			configOrbOfMagic = builder.define("Items.Divine Orb" , true);
			builder.pop();

			//================================================================ Abilities

			MagnetRange = BUILDER.comment("Magnet Range").defineInRange("Ring Of Divinity.Abilities.Magnet Range" , 60 , 1 , 100);
			AoeDamage = BUILDER.comment("AOE Damage").defineInRange("Ring Of Divinity.Abilities.AOE Damage" , 30 , 1 , 100);
			ExtraDrops = BUILDER.comment("Extra Drops").defineInRange("Ring Of Divinity.Abilities.Drops Amounts" , 9 , 1 , 100);

			//================================================================ Potion Effects

			RegenerateHealth = BUILDER.comment("Regeneration Level").defineInRange("Ring Of Divinity.Potion Effects.Health Regeneration" , 10 , 1 , 255);
			Luck = BUILDER.comment("Luck Level").defineInRange("Ring Of Divinity.Potion Effects.Luck" , 10 , 1 , 255);
			FireResistance = BUILDER.comment("Fire Resistance").defineInRange("Ring Of Divinity.Potion Effects.Fire Resistance" , 10 , 1 , 255);
			WATER_BREATHING = BUILDER.comment("Water Breathing").defineInRange("Ring Of Divinity.Potion Effects.Water Breathing" , 1 , 1 , 255);
			NIGHT_VISION = BUILDER.comment("Night Vision").defineInRange("Ring Of Divinity.Potion Effects.Night Vision" , 1 , 1 , 255);
			SATURATION = BUILDER.comment("Saturation").defineInRange("Ring Of Divinity.Potion Effects.Saturation" , 10 , 1 , 255);

			//=================================================================== Attribute

			Tattack = BUILDER.comment("Enable Attack Damage Attribute").define("Ring Of Divinity.Attribute.Toggle.Attack Damage" , true);
			ATTACK_DAMAGE = BUILDER.comment("Attack Damage").defineInRange("Ring Of Divinity.Attribute.Value.Attack Damage" , 100 , 1 , 1000);

			TArmor = BUILDER.comment("Enable Armor Attribute").define("Ring Of Divinity.Attribute.Toggle.Armor" , true);
			ARMOR = BUILDER.comment("Armor").defineInRange("Ring Of Divinity.Attribute.Value.Armor" , 100 , 1 , 1000);

			TArmorToughness = BUILDER.comment("Enable Armor Toughness Attribute").define("Ring Of Divinity.Attribute.Toggle.Armor Toughness" , true);
			ARMOR_TOUGHNESS = BUILDER.comment("Armor Toughness").defineInRange("Ring Of Divinity.Attribute.Value.Armor Toughness" , 10 , 1 , 1000);

			TKnockbackResistance = BUILDER.comment("Enable Knockback Resistance Attribute").define("Ring Of Divinity.Attribute.Toggle.Knockback Resistance" , true);
			KNOCKBACK_RESISTANCE = BUILDER.comment("Knock back Resistance").defineInRange("Ring Of Divinity.Attribute.Value.Knock back Resistance %" , 100 , 10 , 100);

			TMAXHealth = BUILDER.comment("Enable Max Health Attribute").define("Ring Of Divinity.Attribute.Toggle.Max Health" , true);
			MaxHeart = BUILDER.comment("Max Heart").defineInRange("Ring Of Divinity.Attribute.Value.Max Health %" , 500 , 100 , 1000);

			TStepHeight = BUILDER.comment("Enable Step Height Attribute").define("Ring Of Divinity.Attribute.Toggle.Step Height" , true);
			STEP_HEIGHT = BUILDER.comment("Step Height").defineInRange("Ring Of Divinity.Attribute.Value.Step Height" , 1 , 1 , 4);

			TBlockReach = BUILDER.comment("Enable Block Reach Attribute").define("Ring Of Divinity.Attribute.Toggle.Block Reach" , true);
			BLOCK_REACH = BUILDER.comment("Block Reach").defineInRange("Ring Of Divinity.Attribute.Value.Block Reach" , 10 , 1 , 100);

			TEntityReach = BUILDER.comment("Enable Entity Reach Attribute").define("Ring Of Divinity.Attribute.Toggle.Entity Reach" , true);
			ENTITY_REACH = BUILDER.comment("Entity Reach").defineInRange("Ring Of Divinity.Attribute.Value.Entity Reach" , 10 , 1 , 100);

			TMovementSpeed = BUILDER.comment("Enable Movement Speed Attribute").define("Ring Of Divinity.Attribute.Toggle.Movement Speed" , true);
			MOVEMENT_SPEED = BUILDER.comment("Movement Speed").defineInRange("Ring Of Divinity.Attribute.Value.Movement Speed %" , 200 , 100 , 1000);

			TSwimSpeed = BUILDER.comment("Enable Swim Speed Attribute").define("Ring Of Divinity.Attribute.Toggle.Swim Speed" , true);
			SWIM_SPEED = BUILDER.comment("Swim Speed").defineInRange("Ring Of Divinity.Attribute.Value.Swim Speed %" , 400 , 100 , 1000);

			TFlyingSpeed = BUILDER.comment("Enable Flying Speed Attribute").define("Ring Of Divinity.Attribute.Toggle.Flying Speed" , true);
			FLYING_SPEED = BUILDER.comment("Flying Speed").defineInRange("Ring Of Divinity.Attribute.Value.Flying Speed %" , 100 , 100 , 1000);

			TLuck = BUILDER.comment("Enable Luck Attribute").define("Ring Of Divinity.Attribute.Toggle.Luck" , true);
			LUCK = BUILDER.comment("Luck").defineInRange("Ring Of Divinity.Attribute.Value.Luck" , 10 , 1 , 1000);

			TCritChance = BUILDER.comment("Enable Crit Chance Attribute").define("Ring Of Divinity.Attribute.Toggle.Crit Chance" , true);
			CRIT_CHANCE = BUILDER.comment("Crit Chance").defineInRange("Ring Of Divinity.Attribute.Value.Crit Chance %" , 100 , 100 , 1000);

			TArmorPierce = BUILDER.comment("Enable Armor Pierce Attribute").define("Ring Of Divinity.Attribute.Toggle.Armor Pierce" , true);
			ARMOR_PIERCE = BUILDER.comment("Armor Pierce").defineInRange("Ring Of Divinity.Attribute.Value.Armor Pierce %" , 100 , 100 , 1000);

			TArmorShred = BUILDER.comment("Enable Armor Shred Attribute").define("Ring Of Divinity.Attribute.Toggle.Armor Shred" , true);
			ARMOR_SHRED = BUILDER.comment("Armor Shred").defineInRange("Ring Of Divinity.Attribute.Value.Armor Shred %" , 100 , 100 , 1000);

			TArrowVelocity = BUILDER.comment("Enable Arrow Velocity Attribute").define("Ring Of Divinity.Attribute.Toggle.Arrow Velocity" , true);
			ARROW_VELOCITY = BUILDER.comment("Arrow Velocity").defineInRange("Ring Of Divinity.Attribute.Value.Arrow Velocity %" , 100 , 100 , 1000);

			TArrowDamage = BUILDER.comment("Enable Arrow Damage Attribute").define("Ring Of Divinity.Attribute.Toggle.Arrow Damage" , true);
			ARROW_DAMAGE = BUILDER.comment("Arrow Damage").defineInRange("Ring Of Divinity.Attribute.Value.Arrow Damage %" , 200 , 100 , 1000);

			TDrawSpeed = BUILDER.comment("Enable Draw Speed Attribute").define("Ring Of Divinity.Attribute.Toggle.Draw Speed" , true);
			DRAW_SPEED = BUILDER.comment("Draw Speed").defineInRange("Ring Of Divinity.Attribute.Value.Draw Speed %" , 200 , 100, 1000);

			//================================================ Orb of Magic

			TSPELL_RESIST = BUILDER.comment("Enable Spell Resist Attribute").define("Orb Of Magic.Attribute.Toggle.Spell Resist" , true);
			SPELL_RESIST = BUILDER.comment("Spell Resist").defineInRange("Orb Of Magic.Attribute.Value.Spell Resist %" , 200 , 100 , 1000);

			TSPELL_POWER = BUILDER.comment("Enable Spell Power Attribute").define("Orb Of Magic.Attribute.Toggle.Spell Power" , true);
			SPELL_POWER = BUILDER.comment("Spell Power").defineInRange("Orb Of Magic.Attribute.Value.Spell Power %" , 200 , 100 , 1000);

			TMANA_REGEN = BUILDER.comment("Enable Mana Regen Attribute").define("Orb Of Magic.Attribute.Toggle.Mana Regen" , true);
			MANA_REGEN = BUILDER.comment("Mana Regen").defineInRange("Orb Of Magic.Attribute.Value.Mana Regen %" , 200 , 100 , 1000);

			TMAX_MANA = BUILDER.comment("Enable Max Mana Attribute").define("Orb Of Magic.Attribute.Toggle.Max Mana" , true);
			MAX_MANA = BUILDER.comment("Max Mana").defineInRange("Orb Of Magic.Attribute.Value.Max Mana %" , 200 , 100 , 1000);

			TCOOLDOWN_REDUCTION = BUILDER.comment("Enable Cool-Down Reduction Attribute").define("Orb Of Magic.Attribute.Toggle.Cool-Down Reduction" , true);
			COOLDOWN_REDUCTION = BUILDER.comment("Cool-Down Reduction").defineInRange("Orb Of Magic.Attribute.Value.Cool-Down Reduction %" , 200 , 100 , 1000);

			TCAST_TIME_REDUCTION = BUILDER.comment("Enable Cast-Time Reduction Attribute").define("Orb Of Magic.Attribute.Toggle.Cast-Time Reduction" , true);
			CAST_TIME_REDUCTION = BUILDER.comment("Cast-Time Reduction").defineInRange("Orb Of Magic.Attribute.Value.Cast-Time Reduction %" , 200 , 100 , 1000);

		}
	}

}