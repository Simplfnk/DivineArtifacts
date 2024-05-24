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

	//================================== Eldritch Tablet

	public static BooleanValue TogEldritchTablet;
	public static BooleanValue TogElSpellPower;
	public static IntValue ElSpellPower;
	public static BooleanValue TogElSPellResist;
	public static IntValue ElSPellResist;
	public static BooleanValue TogElMaxMana;
	public static IntValue ElMaxMana;

	//================================== Blood Tablet

	public static BooleanValue TogBloodTablet;
	public static BooleanValue TogBldTabBloodMagicResist;
	public static IntValue ValBldTabBloodMagicResist;
	public static BooleanValue TogBldBloodSpellPower;
	public static IntValue ValBldTabBloodSpellPower;
	public static BooleanValue TogBldTabCastTime;
	public static IntValue ValBldTabCastTime;
	public static BooleanValue TogBldTabCoolDown;
	public static IntValue ValBldTabCoolDown;

	//================================== Dead King's Heart

	public static BooleanValue TogDeadKingHeart;
	public static BooleanValue TogMaxHearts;
	public static BooleanValue TogRegeneration;
	public static BooleanValue TogBloodSpell;
	public static BooleanValue TogHolySpells;
	public static BooleanValue TogHolyMagic;
	public static IntValue ValKingHeartMaxHearts;
	public static IntValue ValKingHeartBloodSpell;
	public static IntValue ValKingHeartHolySpells;
	public static IntValue ValKingHeartHolyMagic;
	public static IntValue ValKingHeartHealAmount;
	public static IntValue ValKingHeartManaCost;
	public static IntValue ValKingHeartTickDelay;

	//================================== Holy Tablet

	public static BooleanValue TogHolyTablet;
	public static BooleanValue TogHlyTabHollyMagicResist;
	public static IntValue ValHlyTabHollyMagicResist;
	public static BooleanValue TogHlyTabHollyMagicSpellPower;
	public static BooleanValue TogSunShieldAbilities;
	public static IntValue ValHlyTabHollyMagicSpellPower;
	public static BooleanValue TogHlyTabManaRegen;
	public static IntValue ValHlyTabManaRegen;
	public static BooleanValue TogHlyTabEvocationMagicResist;
	public static IntValue ValHlyTabEvocationMagicResist;
	public static IntValue ValHlyTabSunFireRange;
	public static IntValue ValHlyTabSunKnockRange;
	public static IntValue ValHlyTabSunKnockChance;
	public static IntValue ValHlyTabSunKnockCost;
	public static IntValue ValHlyTabSunFireCost;
	public static IntValue ValHlyTabFireDuration;

	
	
	//================================== Ender Tablet

	public static BooleanValue TogEnderTablet;
	public static BooleanValue TogEndTabEnderManNoAgro;
	public static BooleanValue TogEndTabEnderMagicResist;
	public static BooleanValue TogEndTabEnderMagicSpellPower;
	public static BooleanValue TogEndTabSpellResist;
	public static IntValue ValEndTabEnderMagicResist;
	public static IntValue ValEndTabEnderMagicSpellPower;
	public static IntValue ValEndTabSpellResist;
	public static BooleanValue togCrystal;

	//================================== Great Attractor

	public static BooleanValue TogGreatAttractor;
	public static IntValue ValGreatAttractorRang;
	public static IntValue ValGreatAttractorManaCost;
	public static IntValue ValGreatAttractorTickDelay;

	//================================== Nature Tablet
	public static BooleanValue TogNatureTablet;
	public static BooleanValue TogNatureMagicResist;
	public static BooleanValue TogNatureSpellPower;
	public static BooleanValue TogNatureFireResist;
	public static BooleanValue TogNaturePoisonImmunity;
	public static BooleanValue TogNaturePlantGrowth;
	public static IntValue ValNatureMagicResist;
	public static IntValue ValNatureSpellPower;
	public static IntValue ValNatureFireResist;
	public static IntValue ValNatureGrowthCost;
	public static IntValue ValNatureGrowthTickDelay;
	public static IntValue ValNatureGrowthRang;
	public static IntValue TogNaturePoisonImmunityCost;

	//================================== Fire Tablet

	public static BooleanValue TogFireTablet;
	public static BooleanValue TogFirTabFireMagicResist;
	public static BooleanValue TogFirTabFireSpellPower;
	public static BooleanValue TogFirTabCoolDonReduction;
	public static BooleanValue TogFirTabIceMagicResist;
	public static BooleanValue TogFirTabFireImmunity;
	public static BooleanValue TogFirTabLavaSpeed;
	public static BooleanValue TogFirTabMiningSpeed;
	public static BooleanValue TogFirTabLavaVision;
	public static BooleanValue TogFirTabExtraFirePowerSpell;


	public static IntValue ValFirTabFireMagicResist;
	public static IntValue ValFirTabFireSpellPower;
	public static IntValue ValFirTabCoolDonReduction;
	public static IntValue valFirTabIceMagicResist;
	public static IntValue valFirTabExtraFirePowerSpell;

	//================================== Ice Tablet
	public static BooleanValue TogIceTablet;
	public static BooleanValue TogIceTabICE_MAGIC_RESIST;
	public static BooleanValue TogIceTabICE_SPELL_POWER;
	public static BooleanValue TogIceTabMAX_MANA;
	public static BooleanValue TogIceTabNATURE_MAGIC_RESIST;
	public static BooleanValue TogIceTabExtraIceSpellPower;
	public static BooleanValue TogIceTabFreezingImmunity;
	public static BooleanValue TogIceTabWaterVision;
	public static BooleanValue TogIceTabIceSpeed;
	public static BooleanValue TogIceTabIceMiningSpeed;

	public static IntValue valIceTabICE_MAGIC_RESIST;
	public static IntValue valIceTabICE_SPELL_POWER;
	public static IntValue valIceTabMAX_MANA;
	public static IntValue valIceTabNATURE_MAGIC_RESIST;
	public static IntValue valIceTabExtraIceSpellPower;

	public static class serverConfig {
		public serverConfig(final ForgeConfigSpec.Builder builder) {
			builder.push("Divine Artifacts");
			configDivineRing = builder.worldRestart().define("Miscellaneous.Ring Of Divinity.Ring Of Divinity" , true);
			configOrbOfMagic = builder.worldRestart().define("Miscellaneous.Divine Orb.Divine Orb" , true);
			togCrystal = BUILDER.comment("Disable/Enable crystal").define("Miscellaneous.Crystal.Crystal " , true);

			//================================== Nature Tablet
			TogIceTablet = BUILDER.worldRestart().comment("Disable/Enable Ice Tablet").define("Tablets.Ice Tablet.Nature Tablet " , true);
			TogIceTabICE_MAGIC_RESIST = BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Ice Tablet.Toggle Attribute.\u00A7a Ice Magic Resistance" , true);
			TogIceTabICE_SPELL_POWER = BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Ice Tablet.Toggle Attribute.\u00A7a Ice Spell Power" , true);
			TogIceTabMAX_MANA= BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Ice Tablet.Toggle Attribute.\u00A7a Max Mana" , true);
			TogIceTabNATURE_MAGIC_RESIST= BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Ice Tablet.Toggle Attribute.\u00A7c Nature Magic Resistance" , true);
			TogIceTabExtraIceSpellPower= BUILDER.comment("Toggle On/Off Abilities").define("Tablets.Ice Tablet.Toggle Abilities.\u00A7a Extra Ice Spell Power" , true);
			TogIceTabFreezingImmunity= BUILDER.comment("Toggle On/Off Abilities").define("Tablets.Ice Tablet.Toggle Abilities.\u00A7a Freezing Immunity" , true);
			TogIceTabWaterVision= BUILDER.comment("Toggle On/Off Abilities").define("Tablets.Ice Tablet.Toggle Abilities.\u00A7a Water Vision" , true);
			TogIceTabIceSpeed= BUILDER.comment("Toggle On/Off Abilities").define("Tablets.Ice Tablet.Toggle Abilities.\u00A7a SPeed Move Movement in water" , true);
			TogIceTabIceMiningSpeed= BUILDER.comment("Toggle On/Off Abilities").define("Tablets.Ice Tablet.Toggle Abilities.\u00A7a Mining Speed In Water and Cold Biomes" , true);

			valIceTabICE_MAGIC_RESIST= BUILDER.comment("Attribute Value").defineInRange("Tablets.Ice Tablet.Value.\u00A7a Ice Magic Resistance %" , 15 , 1 , 1000);
			valIceTabICE_SPELL_POWER = BUILDER.comment("Attribute Value").defineInRange("Tablets.Ice Tablet.Value.\u00A7a Ice Spell Power %" , 30 , 1 , 1000);
			valIceTabMAX_MANA= BUILDER.comment("Attribute Value").defineInRange("Tablets.Ice Tablet.Value.\u00A7a Max Mana Points" , 8 , 1 , 1000);
			valIceTabNATURE_MAGIC_RESIST= BUILDER.comment("Attribute Value").defineInRange("Tablets.Ice Tablet.Value.\u00A7c Nature Magic Resistance %" , 70 , 1 , 1000);
			valIceTabExtraIceSpellPower= BUILDER.comment("Attribute Value").defineInRange("Tablets.Ice Tablet.Value.\u00A7a Extra Ice Spell Power In Cold Biomes %" , 20 , 1 , 1000);

			//================================== Nature Tablet
			TogFireTablet = BUILDER.worldRestart().comment("Disable/Enable Nature Tablet").define("Tablets.Fire Tablet.Nature Tablet " , true);
			TogFirTabFireMagicResist = BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Fire Tablet.Toggle Attribute.\u00A7a Fire Magic Resistance" , true);
			TogFirTabFireSpellPower = BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Fire Tablet.Toggle Attribute.\u00A7a Fire Spell Power" , true);
			TogFirTabCoolDonReduction = BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Fire Tablet.Toggle Attribute.\u00A7a CoolDown Reduction" , true);
			TogFirTabIceMagicResist = BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Fire Tablet.Toggle Attribute.\u00A7c Ice Magic Resistance" , true);
			TogFirTabFireImmunity = BUILDER.comment("Toggle On/Off Abilities").define("Tablets.Fire Tablet.Toggle Abilities.\u00A7a Fire immunity" , true);
			TogFirTabLavaSpeed = BUILDER.comment("Toggle On/Off Abilities").define("Tablets.Fire Tablet.Toggle Abilities.\u00A7a Fast Movement In Lava" , true);
			TogFirTabMiningSpeed = BUILDER.comment("Toggle On/Off Abilities").define("Tablets.Fire Tablet.Toggle Abilities.\u00A7a Fast Mining In Lava" , true);
			TogFirTabLavaVision = BUILDER.comment("Toggle On/Off Abilities").define("Tablets.Fire Tablet.Toggle Abilities.\u00A7a Lava Vision" , true);
			TogFirTabExtraFirePowerSpell = BUILDER.comment("Toggle On/Off Abilities").define("Tablets.Fire Tablet.Toggle Abilities.\u00A7a Extra Fire Spell Power In Lava" , true);

			ValFirTabFireMagicResist = BUILDER.comment("Attribute Value").defineInRange("Tablets.Fire Tablet.Value.\u00A7a Fire Magic Resistance %" , 15 , 1 , 1000);
			ValFirTabFireSpellPower = BUILDER.comment("Attribute Value").defineInRange("Tablets.Fire Tablet.Value.\u00A7a Fire Spell Power %" , 30 , 1 , 1000);
			ValFirTabCoolDonReduction = BUILDER.comment("Attribute Value").defineInRange("Tablets.Fire Tablet.Value.\u00A7a CoolDown Reduction %" , 8 , 1 , 1000);
			valFirTabIceMagicResist = BUILDER.comment("Attribute Value").defineInRange("Tablets.Fire Tablet.Value.\u00A7c Ice Magic Resistance %" , 70 , 1 , 1000);
			valFirTabExtraFirePowerSpell = BUILDER.comment("Attribute Value").defineInRange("Tablets.Fire Tablet.Value.\u00A7a Extra Fire Spell Power In Lava %" , 20 , 1 , 1000);

			//================================== Nature Tablet
			TogNatureTablet = BUILDER.worldRestart().comment("Disable/Enable Nature Tablet").define("Tablets.Nature Tablet.Nature Tablet " , true);
			TogNatureMagicResist = BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Nature Tablet.Toggle Attribute.\u00A7a Nature Magic Resistance" , true);
			TogNatureSpellPower = BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Nature Tablet.Toggle Attribute.\u00A7a Nature Spell Power" , true);
			TogNatureFireResist = BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Nature Tablet.Toggle Attribute.\u00A7c Fire Magic Resistance" , true);
			TogNaturePoisonImmunity = BUILDER.comment("Toggle On/Off Abilities").define("Tablets.Nature Tablet.Toggle Abilities.\u00A7a Poison Immunity" , true);
			TogNaturePlantGrowth = BUILDER.comment("Toggle On/Off Abilities").define("Tablets.Nature Tablet.Toggle Abilities.\u00A7a Gaia Blessing" , true);

			ValNatureMagicResist = BUILDER.comment("Attribute Value").defineInRange("Tablets.Nature Tablet.Value.\u00A7a Nature Magic Resistance %" , 15 , 1 , 1000);
			ValNatureSpellPower = BUILDER.comment("Attribute Value").defineInRange("Tablets.Nature Tablet.Value.\u00A7a Nature Spell Power %" , 30 , 1 , 1000);
			ValNatureFireResist = BUILDER.comment("Attribute Value").defineInRange("Tablets.Nature Tablet.Value.\u00A7c Fire Magic Resist Reduction %" , 60 , 1 , 1000);
			ValNatureGrowthCost = BUILDER.comment("Abilities Value").defineInRange("Tablets.Nature Tablet.Value.\u00A7a Gaia Blessing Mana Cost" , 1 , 1 , 100);
			ValNatureGrowthTickDelay = BUILDER.comment("Abilities Value").defineInRange("Tablets.Nature Tablet.Value.\u00A7a Gaia Blessing Delay" , 6 , 1 , 1000);
			ValNatureGrowthRang = BUILDER.comment("Abilities Value").defineInRange("Tablets.Nature Tablet.Value.\u00A7a Gaia Blessing Range" , 6 , 1 , 100);
			TogNaturePoisonImmunityCost = BUILDER.comment("Abilities Value").defineInRange("Tablets.Nature Tablet.Value.\u00A7c Poison Immunity Cost" , 5 , 1 , 1000);
			//================================== Ender Tablet

			TogEnderTablet = BUILDER.worldRestart().comment("Disable/Enable Ender Tablet").define("Tablets.Ender Tablet.Ender Tablet " , true);
			TogEndTabEnderManNoAgro = BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Ender Tablet.Toggle Abilities.\u00A7a Docile EnderMan" , true);

			TogEndTabEnderMagicResist = BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Ender Tablet.Toggle Attribute.\u00A7aEnder Magic Resistance" , true);
			TogEndTabEnderMagicSpellPower = BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Ender Tablet.Toggle Attribute.\u00A7aEnder Spell Power " , true);
			TogEndTabSpellResist = BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Ender Tablet.Toggle Attribute.\u00A7cSpells Resistance Reduction" , true);

			ValEndTabEnderMagicResist = BUILDER.comment("Attribute Value").defineInRange("Tablets.Ender Tablet.Value.\u00A7aEnder Magic Resistance %" , 15 , 1 , 1000);
			ValEndTabEnderMagicSpellPower = BUILDER.comment("Attribute Value").defineInRange("Tablets.Ender Tablet.Value.\u00A7aEnder Spell Power %" , 35 , 1 , 1000);
			ValEndTabSpellResist = BUILDER.comment("Attribute Value").defineInRange("Tablets.Ender Tablet.Value.\u00A7cSpell Resistance Reduction %" , 20 , 1 , 1000);

			//================================== Great Attractor

			TogGreatAttractor = BUILDER.worldRestart().comment("Disable/Enable Great Attractor").define("Miscellaneous.Great Attractor.Great Attractor" , true);
			ValGreatAttractorRang = BUILDER.comment("Regeneration").defineInRange("Miscellaneous.Great Attractor.Value.\u00A7a Attractor Rang " , 60 , 1 , 100);
			ValGreatAttractorManaCost = BUILDER.comment("Regeneration").defineInRange("Miscellaneous.Great Attractor.Value.\u00A7c Attractor Mana Cost " , 1 , 1 , 20);
			ValGreatAttractorTickDelay = BUILDER.comment("The Speed of Mana Consumption").defineInRange("Miscellaneous.Great Attractor.Value.\u00A7a Attractor Tick Delay " , 6 , 1 , 20);

			//================================== Holy Tablet

			TogHolyTablet = BUILDER.worldRestart().comment("Disable/Enable Holy Tablet").define("Tablets.Holy Tablet.Holy Tablet " , true);
			TogHlyTabHollyMagicResist = BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Holy Tablet.Toggle Attribute.\u00A7aHoly Magic Resistance" , true);
			TogHlyTabHollyMagicSpellPower = BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Holy Tablet.Toggle Attribute.\u00A7aHoly Spell Power " , true);
			TogHlyTabManaRegen = BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Holy Tablet.Toggle Attribute.\u00A7aMana Regeneration" , true);
			TogHlyTabEvocationMagicResist = BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Holy Tablet.Toggle Attribute.\u00A7cEvocation Magic Resist Reduction" , true);
			TogSunShieldAbilities = BUILDER.comment("Toggle On/Off Sun Shield Ability ").define("Tablets.Holy Tablet.Toggle Abilities.\u00A7c Sun Shield" , true);

			ValHlyTabHollyMagicResist = BUILDER.comment("Attribute Value").defineInRange("Tablets.Holy Tablet.Value.\u00A7aHoly Magic Resistance %" , 15 , 1 , 1000);
			ValHlyTabHollyMagicSpellPower = BUILDER.comment("Attribute Value").defineInRange("Tablets.Holy Tablet.Value.\u00A7aHoly Spell Power %" , 30 , 1 , 1000);
			ValHlyTabManaRegen = BUILDER.comment("Attribute Value").defineInRange("Tablets.Holy Tablet.Value.\u00A7aMana Regeneration %" , 25 , 1 , 1000);
			ValHlyTabEvocationMagicResist = BUILDER.comment("Attribute Value").defineInRange("Tablets.Holy Tablet.Value.\u00A7cEvocation Magic Resist Reduction %" , 80 , 1 , 1000);

			ValHlyTabFireDuration = BUILDER.comment("Duration of the mob being on fire in second").defineInRange("Tablets.Holy Tablet.Value.\u00A7a The Fire duration" , 10 , 1 , 1000);
			ValHlyTabSunFireRange = BUILDER.comment("Range in which the mob will be set on fire").defineInRange("Tablets.Holy Tablet.Value.\u00A7a The range of setting mobs on fire %" , 10 , 1 , 1000);
			ValHlyTabSunKnockRange = BUILDER.comment("Range in which the mob will be knocked back").defineInRange("Tablets.Holy Tablet.Value.\u00A7a The range of Knocking back mobs %" , 10 , 1 , 1000);
			ValHlyTabSunKnockChance = BUILDER.comment("Chance of the knock-back effect").defineInRange("Tablets.Holy Tablet.Value.\u00A7a Chance of the knock-back %" , 5 , 1 , 1000);
			ValHlyTabSunKnockCost = BUILDER.comment("Cost of the knock-back for every mob in Mana").defineInRange("Tablets.Holy Tablet.Value.\u00A7c Cost of the knock-back %" , 5 , 1 , 1000);
			ValHlyTabSunFireCost = BUILDER.comment("Cost of the fire for every mob in Mana\"").defineInRange("Tablets.Holy Tablet.Value.\u00A7c Cost of setting mobs on fire %" , 5 , 1 , 1000);

			//================================== Blood Tablet

			TogBloodTablet = BUILDER.worldRestart().comment("Disable/Enable Blood Tablet").define("Tablets.Blood Tablet.Blood Tablet " , true);
			TogBldTabBloodMagicResist = BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Blood Tablet.Toggle Attribute.\u00A7aBlood Magic Resistance" , true);
			TogBldBloodSpellPower = BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Blood Tablet.Toggle Attribute.\u00A7aBlood Spell Power " , true);
			TogBldTabCastTime = BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Blood Tablet.Toggle Attribute.\u00A7aCast-Time Reduction" , true);
			TogBldTabCoolDown = BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Blood Tablet.Toggle Attribute.\u00A7cCool-Down Increase" , true);

			ValBldTabBloodMagicResist = BUILDER.comment("Attribute Value").defineInRange("Tablets.Blood Tablet.Value.\u00A7aBlood Magic Resistance %" , 15 , 1 , 1000);
			ValBldTabBloodSpellPower = BUILDER.comment("Attribute Value").defineInRange("Tablets.Blood Tablet.Value.\u00A7aBlood Spell Power %" , 30 , 1 , 1000);
			ValBldTabCastTime = BUILDER.comment("Attribute Value").defineInRange("Tablets.Blood Tablet.Value.\u00A7aCast-Time Reduction %" , 10 , 1 , 1000);
			ValBldTabCoolDown = BUILDER.comment("Attribute Value").defineInRange("Tablets.Blood Tablet.Value.\u00A7cCool-Down Increase %" , 15 , 1 , 1000);

			//================================== Dead King's Hart

			TogDeadKingHeart = BUILDER.worldRestart().comment("Disable/Enable Dead King's Heart").define("Miscellaneous.Dead King's Heart.Dead King's Heart " , true);
			TogMaxHearts = BUILDER.comment("Toggle On/Off Attribute").define("Miscellaneous.Dead King's Heart.Toggle Attribute.\u00A7aExtra Hearts" , true);
			TogRegeneration = BUILDER.comment("Toggle On/Off Ability").define("Miscellaneous.Dead King's Heart.Toggle Ability.\u00A7aRegeneration" , true);
			TogBloodSpell = BUILDER.comment("Toggle On/Off Attribute").define("Miscellaneous.Dead King's Heart.Toggle Attribute.\u00A7aBlood Spell Power" , true);
			TogHolySpells = BUILDER.comment("Toggle On/Off Attribute").define("Miscellaneous.Dead King's Heart.Toggle Attribute.\u00A7cHoly Spell Power Reduction" , true);
			TogHolyMagic = BUILDER.comment("Toggle On/Off Attribute").define("Miscellaneous.Dead King's Heart.Toggle Attribute.\u00A7cHoly Magic Resist Reduction" , true);

			ValKingHeartTickDelay = BUILDER.comment("Regeneration").defineInRange("Miscellaneous.Dead King's Heart.Value.\u00A7a Regeneration Speed In Ticks " , 6 , 1 , 20);
			ValKingHeartManaCost = BUILDER.comment("Regeneration").defineInRange("Miscellaneous.Dead King's Heart.Value.\u00A7a Regeneration Cost In Mana " , 5 , 1 , 100);
			ValKingHeartHealAmount = BUILDER.comment("Regeneration").defineInRange("Miscellaneous.Dead King's Heart.Value.\u00A7a Heal Amount 1 = half heart " , 1 , 1 , 100);
			ValKingHeartMaxHearts = BUILDER.comment("Attribute Value").defineInRange("Miscellaneous.Dead King's Heart.Value.\u00A7aExtra Hearts " , 10 , 1 , 1000);
			ValKingHeartBloodSpell = BUILDER.comment("Attribute Value").defineInRange("Miscellaneous.Dead King's Heart.Value.\u00A7aBlood Spell Power %" , 8 , 1 , 1000);
			ValKingHeartHolySpells = BUILDER.comment("Attribute Value").defineInRange("Miscellaneous.Dead King's Heart.Value.\u00A7cHoly Spell Power Reduction %" , 20 , 1 , 1000);
			ValKingHeartHolyMagic = BUILDER.comment("Attribute Value").defineInRange("Miscellaneous.Dead King's Heart.Value.\u00A7cHoly Magic Resist Reduction %" , 20 , 1 , 1000);

			//================================== Eldritch Tablet

			TogEldritchTablet = BUILDER.worldRestart().comment("Disable/Enable Eldritch Tablet").define("Tablets.Eldritch Tablet.Eldritch Tablet  " , true);
			TogElSpellPower = BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Eldritch Tablet.Toggle Attribute.\u00A7aEldritch Spells Power" , true);
			TogElSPellResist = BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Eldritch Tablet.Toggle Attribute.\u00A7aSpells Resistance" , true);
			TogElMaxMana = BUILDER.comment("Toggle On/Off Attribute").define("Tablets.Eldritch Tablet.Toggle Attribute.\u00A7cMax-Mana Reduction " , true);

			ElSpellPower = BUILDER.comment("Attribute Value").defineInRange("Tablets.Eldritch Tablet.Value.\u00A7aEldritch Spells Power %" , 30 , 1 , 100);
			ElSPellResist = BUILDER.comment("Attribute Value").defineInRange("Tablets.Eldritch Tablet.Value.\u00A7aSpells Resistance %" , 15 , 1 , 100);
			ElMaxMana = BUILDER.comment("Attribute Value").defineInRange("Tablets.Eldritch Tablet.Value.\u00A7cMax-Mana Reduction %" , 15 , 1 , 100);

			//================================================================ Abilities

			MagnetRange = BUILDER.comment("Magnet Range").defineInRange("Miscellaneous.Ring Of Divinity.Abilities.Magnet Range" , 60 , 1 , 100);
			AoeDamage = BUILDER.comment("AOE Damage").defineInRange("Miscellaneous.Ring Of Divinity.Abilities.AOE Damage" , 30 , 1 , 100);
			ExtraDrops = BUILDER.comment("Extra Drops").defineInRange("Miscellaneous.Ring Of Divinity.Abilities.Drops Amounts" , 9 , 1 , 100);

			//================================================================ Potion Effects

			RegenerateHealth = BUILDER.comment("Regeneration Level").defineInRange("Miscellaneous.Ring Of Divinity.Potion Effects.Health Regeneration" , 10 , 1 , 255);
			Luck = BUILDER.comment("Luck Level").defineInRange("Miscellaneous.Ring Of Divinity.Potion Effects.Luck" , 10 , 1 , 255);
			FireResistance = BUILDER.comment("Fire Resistance").defineInRange("Miscellaneous.Ring Of Divinity.Potion Effects.Fire Resistance" , 10 , 1 , 255);
			WATER_BREATHING = BUILDER.comment("Water Breathing").defineInRange("Miscellaneous.Ring Of Divinity.Potion Effects.Water Breathing" , 1 , 1 , 255);
			NIGHT_VISION = BUILDER.comment("Night Vision").defineInRange("Miscellaneous.Ring Of Divinity.Potion Effects.Night Vision" , 1 , 1 , 255);
			SATURATION = BUILDER.comment("Saturation").defineInRange("Miscellaneous.Ring Of Divinity.Potion Effects.Saturation" , 10 , 1 , 255);

			//=================================================================== Attribute

			Tattack = BUILDER.comment("Enable Attack Damage Attribute").define("Miscellaneous.Ring Of Divinity.Attribute.Toggle.Attack Damage" , true);
			ATTACK_DAMAGE = BUILDER.comment("Attack Damage").defineInRange("Miscellaneous.Ring Of Divinity.Attribute.Value.Attack Damage" , 100 , 1 , 1000);

			TArmor = BUILDER.comment("Enable Armor Attribute").define("Miscellaneous.Ring Of Divinity.Attribute.Toggle.Armor" , true);
			ARMOR = BUILDER.comment("Armor").defineInRange("Miscellaneous.Ring Of Divinity.Attribute.Value.Armor" , 100 , 1 , 1000);

			TArmorToughness = BUILDER.comment("Enable Armor Toughness Attribute").define("Miscellaneous.Ring Of Divinity.Attribute.Toggle.Armor Toughness" , true);
			ARMOR_TOUGHNESS = BUILDER.comment("Armor Toughness").defineInRange("Miscellaneous.Ring Of Divinity.Attribute.Value.Armor Toughness" , 10 , 1 , 1000);

			TKnockbackResistance = BUILDER.comment("Enable Knockback Resistance Attribute").define("Miscellaneous.Ring Of Divinity.Attribute.Toggle.Knockback Resistance" , true);
			KNOCKBACK_RESISTANCE = BUILDER.comment("Knock back Resistance").defineInRange("Miscellaneous.Ring Of Divinity.Attribute.Value.Knock back Resistance %" , 100 , 10 , 100);

			TMAXHealth = BUILDER.comment("Enable Max Health Attribute").define("Miscellaneous.Ring Of Divinity.Attribute.Toggle.Max Health" , true);
			MaxHeart = BUILDER.comment("Max Heart").defineInRange("Miscellaneous.Ring Of Divinity.Attribute.Value.Max Health %" , 500 , 100 , 1000);

			TStepHeight = BUILDER.comment("Enable Step Height Attribute").define("Miscellaneous.Ring Of Divinity.Attribute.Toggle.Step Height" , true);
			STEP_HEIGHT = BUILDER.comment("Step Height").defineInRange("Miscellaneous.Ring Of Divinity.Attribute.Value.Step Height" , 1 , 1 , 4);

			TBlockReach = BUILDER.comment("Enable Block Reach Attribute").define("Miscellaneous.Ring Of Divinity.Attribute.Toggle.Block Reach" , true);
			BLOCK_REACH = BUILDER.comment("Block Reach").defineInRange("Miscellaneous.Ring Of Divinity.Attribute.Value.Block Reach" , 10 , 1 , 100);

			TEntityReach = BUILDER.comment("Enable Entity Reach Attribute").define("Miscellaneous.Ring Of Divinity.Attribute.Toggle.Entity Reach" , true);
			ENTITY_REACH = BUILDER.comment("Entity Reach").defineInRange("Miscellaneous.Ring Of Divinity.Attribute.Value.Entity Reach" , 10 , 1 , 100);

			TMovementSpeed = BUILDER.comment("Enable Movement Speed Attribute").define("Miscellaneous.Ring Of Divinity.Attribute.Toggle.Movement Speed" , true);
			MOVEMENT_SPEED = BUILDER.comment("Movement Speed").defineInRange("Miscellaneous.Ring Of Divinity.Attribute.Value.Movement Speed %" , 200 , 100 , 1000);

			TSwimSpeed = BUILDER.comment("Enable Swim Speed Attribute").define("Miscellaneous.Ring Of Divinity.Attribute.Toggle.Swim Speed" , true);
			SWIM_SPEED = BUILDER.comment("Swim Speed").defineInRange("Miscellaneous.Ring Of Divinity.Attribute.Value.Swim Speed %" , 400 , 100 , 1000);

			TFlyingSpeed = BUILDER.comment("Enable Flying Speed Attribute").define("Miscellaneous.Ring Of Divinity.Attribute.Toggle.Flying Speed" , true);
			FLYING_SPEED = BUILDER.comment("Flying Speed").defineInRange("Miscellaneous.Ring Of Divinity.Attribute.Value.Flying Speed %" , 100 , 100 , 1000);

			TLuck = BUILDER.comment("Enable Luck Attribute").define("Miscellaneous.Ring Of Divinity.Attribute.Toggle.Luck" , true);
			LUCK = BUILDER.comment("Luck").defineInRange("Miscellaneous.Ring Of Divinity.Attribute.Value.Luck" , 10 , 1 , 1000);

			TCritChance = BUILDER.comment("Enable Crit Chance Attribute").define("Miscellaneous.Ring Of Divinity.Attribute.Toggle.Crit Chance" , true);
			CRIT_CHANCE = BUILDER.comment("Crit Chance").defineInRange("Miscellaneous.Ring Of Divinity.Attribute.Value.Crit Chance %" , 100 , 100 , 1000);

			TArmorPierce = BUILDER.comment("Enable Armor Pierce Attribute").define("Miscellaneous.Ring Of Divinity.Attribute.Toggle.Armor Pierce" , true);
			ARMOR_PIERCE = BUILDER.comment("Armor Pierce").defineInRange("Miscellaneous.Ring Of Divinity.Attribute.Value.Armor Pierce %" , 100 , 100 , 1000);

			TArmorShred = BUILDER.comment("Enable Armor Shred Attribute").define("Miscellaneous.Ring Of Divinity.Attribute.Toggle.Armor Shred" , true);
			ARMOR_SHRED = BUILDER.comment("Armor Shred").defineInRange("Miscellaneous.Ring Of Divinity.Attribute.Value.Armor Shred %" , 100 , 100 , 1000);

			TArrowVelocity = BUILDER.comment("Enable Arrow Velocity Attribute").define("Miscellaneous.Ring Of Divinity.Attribute.Toggle.Arrow Velocity" , true);
			ARROW_VELOCITY = BUILDER.comment("Arrow Velocity").defineInRange("Miscellaneous.Ring Of Divinity.Attribute.Value.Arrow Velocity %" , 100 , 100 , 1000);

			TArrowDamage = BUILDER.comment("Enable Arrow Damage Attribute").define("Miscellaneous.Ring Of Divinity.Attribute.Toggle.Arrow Damage" , true);
			ARROW_DAMAGE = BUILDER.comment("Arrow Damage").defineInRange("Miscellaneous.Ring Of Divinity.Attribute.Value.Arrow Damage %" , 200 , 100 , 1000);

			TDrawSpeed = BUILDER.comment("Enable Draw Speed Attribute").define("Miscellaneous.Ring Of Divinity.Attribute.Toggle.Draw Speed" , true);
			DRAW_SPEED = BUILDER.comment("Draw Speed").defineInRange("Miscellaneous.Ring Of Divinity.Attribute.Value.Draw Speed %" , 200 , 100 , 1000);

			//================================================ Orb of Magic

			TSPELL_RESIST = BUILDER.comment("Enable Spell Resist Attribute").define("Miscellaneous.Divine Orb.Attribute.Toggle.Spell Resist" , true);
			SPELL_RESIST = BUILDER.comment("Spell Resist").defineInRange("Miscellaneous.Divine Orb.Attribute.Value.Spell Resist %" , 200 , 100 , 1000);

			TSPELL_POWER = BUILDER.comment("Enable Spell Power Attribute").define("Miscellaneous.Divine Orb.Attribute.Toggle.Spell Power" , true);
			SPELL_POWER = BUILDER.comment("Spell Power").defineInRange("Miscellaneous.Divine Orb.Attribute.Value.Spell Power %" , 200 , 100 , 1000);

			TMANA_REGEN = BUILDER.comment("Enable Mana Regen Attribute").define("Miscellaneous.Divine Orb.Attribute.Toggle.Mana Regen" , true);
			MANA_REGEN = BUILDER.comment("Mana Regen").defineInRange("Miscellaneous.Divine Orb.Attribute.Value.Mana Regen %" , 200 , 100 , 1000);

			TMAX_MANA = BUILDER.comment("Enable Max Mana Attribute").define("Miscellaneous.Divine Orb.Attribute.Toggle.Max Mana" , true);
			MAX_MANA = BUILDER.comment("Max Mana").defineInRange("Miscellaneous.Divine Orb.Attribute.Value.Max Mana %" , 200 , 100 , 1000);

			TCOOLDOWN_REDUCTION = BUILDER.comment("Enable Cool-Down Reduction Attribute").define("Miscellaneous.Divine Orb.Attribute.Toggle.Cool-Down Reduction" , true);
			COOLDOWN_REDUCTION = BUILDER.comment("Cool-Down Reduction").defineInRange("Miscellaneous.Divine Orb.Attribute.Value.Cool-Down Reduction %" , 200 , 100 , 1000);

			TCAST_TIME_REDUCTION = BUILDER.comment("Enable Cast-Time Reduction Attribute").define("Miscellaneous.Divine Orb.Attribute.Toggle.Cast-Time Reduction" , true);
			CAST_TIME_REDUCTION = BUILDER.comment("Cast-Time Reduction").defineInRange("Miscellaneous.Divine Orb.Attribute.Value.Cast-Time Reduction %" , 200 , 100 , 1000);
			builder.pop();

		}
	}

}