package nk.divineartifacts.client.handler;

import nk.divineartifacts.config.ToggleAbilities;

import static nk.divineartifacts.config.ServerConfig.*;

;

public class ToggleHelper {
	public static boolean toggleMagnet() {
		if (!ToggleAbilities.ClientSpec.isLoaded()) return true;
		return ToggleAbilities.toggleAttractorMagnet.get();
	}
	public static boolean toggleAoeDamage() {
		if (!ToggleAbilities.ClientSpec.isLoaded()) return true;
		return ToggleAbilities.toggleAoeDamage.get();
	}
	public static boolean toggleShield() {
		if (!ToggleAbilities.ClientSpec.isLoaded()) return true;
		return ToggleAbilities.toggleShield.get();
	}
	public static boolean toggleBlockBreak() {
		if (!ToggleAbilities.ClientSpec.isLoaded()) return true;
		return ToggleAbilities.toggleBlockBreak.get();
	}
	public static boolean toggleExtraDrops() {
		if (!ToggleAbilities.ClientSpec.isLoaded()) return true;
		return ToggleAbilities.toggleExtraDrops.get();
	}
	public static boolean toggleHudElements() {
		if (!ToggleAbilities.ClientSpec.isLoaded()) return true;
		return ToggleAbilities.toggleHudElements.get();
	}
	public static boolean togGaiaBlessing() {
		if (!ToggleAbilities.ClientSpec.isLoaded()) return true;
		return ToggleAbilities.togGaiaBlessing.get();
	}
	public static boolean TogSunShield() {
		if (!ToggleAbilities.ClientSpec.isLoaded()) return true;
		return ToggleAbilities.TogSunShield.get();
	}
	//=================
	public static boolean toggleDivineRing() {
		if (!serverConfig.isLoaded()) return true;
		return configDivineRing.get();
	}

	public static boolean toggleOrbOfMagic() {
		if (!serverConfig.isLoaded()) return true;
		return configOrbOfMagic.get();
	}

	public static boolean toggleTattack() {
		if (!serverConfig.isLoaded()) return true;
		return Tattack.get();
	}

	public static boolean toggleTArmor() {
		if (!serverConfig.isLoaded()) return true;
		return TArmor.get();
	}

	public static boolean toggleTArmorToughness() {
		if (!serverConfig.isLoaded()) return true;
		return TArmorToughness.get();
	}

	public static boolean toggleTMAXHealth() {
		if (!serverConfig.isLoaded()) return true;
		return TMAXHealth.get();
	}

	public static boolean toggleTKnockbackResistance() {
		if (!serverConfig.isLoaded()) return true;
		return TKnockbackResistance.get();
	}

	public static boolean toggleTStepHeight() {
		if (!serverConfig.isLoaded()) return true;
		return TStepHeight.get();
	}

	public static boolean toggleTBlockReach() {
		if (!serverConfig.isLoaded()) return true;
		return TBlockReach.get();
	}

	public static boolean toggleTEntityReach() {
		if (!serverConfig.isLoaded()) return true;
		return TEntityReach.get();
	}

	public static boolean toggleTMovementSpeed() {
		if (!serverConfig.isLoaded()) return true;
		return TMovementSpeed.get();
	}

	public static boolean toggleTSwimSpeed() {
		if (!serverConfig.isLoaded()) return true;
		return TSwimSpeed.get();
	}

	public static boolean toggleTFlyingSpeed() {
		if (!serverConfig.isLoaded()) return true;
		return TFlyingSpeed.get();
	}

	public static boolean toggleTLuck() {
		if (!serverConfig.isLoaded()) return true;
		return TLuck.get();
	}

	public static boolean toggleTCritChance() {
		if (!serverConfig.isLoaded()) return true;
		return TCritChance.get();
	}

	public static boolean toggleTArmorPierce() {
		if (!serverConfig.isLoaded()) return true;
		return TArmorPierce.get();
	}

	public static boolean toggleTArmorShred() {
		if (!serverConfig.isLoaded()) return true;
		return TArmorShred.get();
	}

	public static boolean toggleTArrowVelocity() {
		if (!serverConfig.isLoaded()) return true;
		return TArrowVelocity.get();
	}

	public static boolean toggleTArrowDamage() {
		if (!serverConfig.isLoaded()) return true;
		return TArrowDamage.get();
	}

	public static boolean toggleTDrawSpeed() {
		if (!serverConfig.isLoaded()) return true;
		return TDrawSpeed.get();
	}

	public static int getMagnetRange() {
		if (!serverConfig.isLoaded()) return 100;
		return MagnetRange.get();
	}

	public static int getMaxHeart() {
		if (!serverConfig.isLoaded()) return 100;
		return MaxHeart.get();
	}

	public static int getRegenerateHealth() {
		if (!serverConfig.isLoaded()) return 100;
		return RegenerateHealth.get();
	}

	public static int getLuck() {
		if (!serverConfig.isLoaded()) return 100;
		return Luck.get();
	}

	public static int getFireResistance() {
		if (!serverConfig.isLoaded()) return 100;
		return FireResistance.get();
	}

	public static int getAttackDamage() {
		if (!serverConfig.isLoaded()) return 100;
		return ATTACK_DAMAGE.get();
	}

	public static int getArmor() {
		if (!serverConfig.isLoaded()) return 100;
		return ARMOR.get();
	}

	public static int getArmorToughness() {
		if (!serverConfig.isLoaded()) return 100;
		return ARMOR_TOUGHNESS.get();
	}

	public static int getKnockbackResistance() {
		if (!serverConfig.isLoaded()) return 100;
		return KNOCKBACK_RESISTANCE.get();
	}

	public static int getStepHeight() {
		if (!serverConfig.isLoaded()) return 100;
		return STEP_HEIGHT.get();
	}

	public static int getBlockReach() {
		if (!serverConfig.isLoaded()) return 100;
		return BLOCK_REACH.get();
	}

	public static int getEntityReach() {
		if (!serverConfig.isLoaded()) return 100;
		return ENTITY_REACH.get();
	}

	public static int getMovementSpeed() {
		if (!serverConfig.isLoaded()) return 100;
		return MOVEMENT_SPEED.get();
	}

	public static int getSwimSpeed() {
		if (!serverConfig.isLoaded()) return 100;
		return SWIM_SPEED.get();
	}

	public static int getFlyingSpeed() {
		if (!serverConfig.isLoaded()) return 100;
		return FLYING_SPEED.get();
	}

	public static int getCritChance() {
		if (!serverConfig.isLoaded()) return 100;
		return CRIT_CHANCE.get();
	}

	public static int getArmorPierce() {
		if (!serverConfig.isLoaded()) return 100;
		return ARMOR_PIERCE.get();
	}

	public static int getArmorShred() {
		if (!serverConfig.isLoaded()) return 100;
		return ARMOR_SHRED.get();
	}

	public static int getArrowVelocity() {
		if (!serverConfig.isLoaded()) return 100;
		return ARROW_VELOCITY.get();
	}

	public static int getArrowDamage() {
		if (!serverConfig.isLoaded()) return 100;
		return ARROW_DAMAGE.get();
	}

	public static int getDrawSpeed() {
		if (!serverConfig.isLoaded()) return 100;
		return DRAW_SPEED.get();
	}

	public static int getWaterBreathing() {
		if (!serverConfig.isLoaded()) return 100;
		return WATER_BREATHING.get();
	}

	public static int getNightVision() {
		if (!serverConfig.isLoaded()) return 100;
		return NIGHT_VISION.get();
	}

	public static int getSaturation() {
		if (!serverConfig.isLoaded()) return 100;
		return SATURATION.get();
	}

	public static int getAoeDamage() {
		if (!serverConfig.isLoaded()) return 100;
		return AoeDamage.get();
	}

	public static int getExtraDrops() {
		if (!serverConfig.isLoaded()) return 100;
		return ExtraDrops.get();
	}

	public static boolean toggleSpellResist() {
		if (!serverConfig.isLoaded()) return true;
		return TSPELL_RESIST.get();
	}

	public static boolean toggleSpellPower() {
		if (!serverConfig.isLoaded()) return true;
		return TSPELL_POWER.get();
	}

	public static boolean toggleManaRegen() {
		if (!serverConfig.isLoaded()) return true;
		return TMANA_REGEN.get();
	}

	public static boolean toggleMaxMana() {
		if (!serverConfig.isLoaded()) return true;
		return TMAX_MANA.get();
	}

	public static boolean toggleCooldownReduction() {
		if (!serverConfig.isLoaded()) return true;
		return TCOOLDOWN_REDUCTION.get();
	}

	public static boolean toggleCastTimeReduction() {
		if (!serverConfig.isLoaded()) return true;
		return TCAST_TIME_REDUCTION.get();
	}

	public static int getSpellResist() {
		if (!serverConfig.isLoaded()) return 100;
		return SPELL_RESIST.get();
	}

	public static int getSpellPower() {
		if (!serverConfig.isLoaded()) return 100;
		return SPELL_POWER.get();
	}

	public static int getManaRegen() {
		if (!serverConfig.isLoaded()) return 100;
		return MANA_REGEN.get();
	}

	public static int getMaxMana() {
		if (!serverConfig.isLoaded()) return 100;
		return MAX_MANA.get();
	}

	public static int getCooldownReduction() {
		if (!serverConfig.isLoaded()) return 100;
		return COOLDOWN_REDUCTION.get();
	}

	public static int getCastTimeReduction() {
		if (!serverConfig.isLoaded()) return 100;
		return CAST_TIME_REDUCTION.get();
	}

	public static boolean toggleEldritchTablet() {
		if (!serverConfig.isLoaded()) return true;
		return TogEldritchTablet.get();
	}

	public static boolean toggleElSpellPower() {
		if (!serverConfig.isLoaded()) return true;
		return TogElSpellPower.get();
	}

	public static int getElSpellPower() {
		if (!serverConfig.isLoaded()) return 100;
		return ElSpellPower.get();
	}

	public static boolean toggleElSpellResist() {
		if (!serverConfig.isLoaded()) return true;
		return TogElSPellResist.get();
	}

	public static int getElSpellResist() {
		if (!serverConfig.isLoaded()) return 100;
		return ElSPellResist.get();
	}

	public static boolean toggleElMaxMana() {
		if (!serverConfig.isLoaded()) return true;
		return TogElMaxMana.get();
	}

	public static int getElMaxMana() {
		if (!serverConfig.isLoaded()) return 100;
		return ElMaxMana.get();
	}

	public static boolean toggleBloodTablet() {
		if (!serverConfig.isLoaded()) return true;
		return TogBloodTablet.get();
	}

	public static boolean toggleBldTabBloodMagicResist() {
		if (!serverConfig.isLoaded()) return true;
		return TogBldTabBloodMagicResist.get();
	}

	public static int getBldTabBloodMagicResist() {
		if (!serverConfig.isLoaded()) return 100;
		return ValBldTabBloodMagicResist.get();
	}

	public static boolean toggleBldBloodSpellPower() {
		if (!serverConfig.isLoaded()) return true;
		return TogBldBloodSpellPower.get();
	}

	public static int getBldTabBloodSpellPower() {
		if (!serverConfig.isLoaded()) return 100;
		return ValBldTabBloodSpellPower.get();
	}

	public static boolean toggleBldTabCastTime() {
		if (!serverConfig.isLoaded()) return true;
		return TogBldTabCastTime.get();
	}

	public static int getBldTabCastTime() {
		if (!serverConfig.isLoaded()) return 100;
		return ValBldTabCastTime.get();
	}

	public static boolean toggleBldTabCoolDown() {
		if (!serverConfig.isLoaded()) return true;
		return TogBldTabCoolDown.get();
	}

	public static int getBldTabCoolDown() {
		if (!serverConfig.isLoaded()) return 100;
		return ValBldTabCoolDown.get();
	}

	public static boolean toggleDeadKingHeart() {
		if (!serverConfig.isLoaded()) return true;
		return TogDeadKingHeart.get();
	}

	public static boolean toggleMaxHearts() {
		if (!serverConfig.isLoaded()) return true;
		return TogMaxHearts.get();
	}

	public static boolean TogRegeneration() {
		if (!serverConfig.isLoaded()) return true;
		return TogRegeneration.get();
	}

	public static boolean toggleBloodSpell() {
		if (!serverConfig.isLoaded()) return true;
		return TogBloodSpell.get();
	}

	public static boolean toggleHolySpells() {
		if (!serverConfig.isLoaded()) return true;
		return TogHolySpells.get();
	}

	public static boolean toggleHolyMagic() {
		if (!serverConfig.isLoaded()) return true;
		return TogHolyMagic.get();
	}

	public static int getKingHeartMaxHearts() {
		if (!serverConfig.isLoaded()) return 100;
		return ValKingHeartMaxHearts.get();
	}

	public static int getKingHeartBloodSpell() {
		if (!serverConfig.isLoaded()) return 100;
		return ValKingHeartBloodSpell.get();
	}

	public static int getKingHeartHolySpells() {
		if (!serverConfig.isLoaded()) return 100;
		return ValKingHeartHolySpells.get();
	}

	public static int getKingHeartHolyMagic() {
		if (!serverConfig.isLoaded()) return 100;
		return ValKingHeartHolyMagic.get();
	}

	public static int getKingHeartHealAmount() {
		if (!serverConfig.isLoaded()) return 100;
		return ValKingHeartHealAmount.get();
	}

	public static int getKingHeartManaCost() {
		if (!serverConfig.isLoaded()) return 100;
		return ValKingHeartManaCost.get();
	}

	public static int getKingHeartTickDelay() {
		if (!serverConfig.isLoaded()) return 100;
		return ValKingHeartTickDelay.get();
	}

	public static boolean toggleHolyTablet() {
		if (!serverConfig.isLoaded()) return true;
		return TogHolyTablet.get();
	}

	public static boolean toggleHlyTabHollyMagicResist() {
		if (!serverConfig.isLoaded()) return true;
		return TogHlyTabHollyMagicResist.get();
	}

	public static int getHlyTabHollyMagicResist() {
		if (!serverConfig.isLoaded()) return 100;
		return ValHlyTabHollyMagicResist.get();
	}

	public static boolean toggleHlyTabHollyMagicSpellPower() {
		if (!serverConfig.isLoaded()) return true;
		return TogHlyTabHollyMagicSpellPower.get();
	}

	public static int getHlyTabHollyMagicSpellPower() {
		if (!serverConfig.isLoaded()) return 100;
		return ValHlyTabHollyMagicSpellPower.get();
	}

	public static boolean toggleSunShieldAbilities() {
		if (!serverConfig.isLoaded()) return true;
		return TogSunShieldAbilities.get();
	}

	public static boolean toggleHlyTabManaRegen() {
		if (!serverConfig.isLoaded()) return true;
		return TogHlyTabManaRegen.get();
	}

	public static int getHlyTabManaRegen() {
		if (!serverConfig.isLoaded()) return 100;
		return ValHlyTabManaRegen.get();
	}

	public static boolean toggleHlyTabEvocationMagicResist() {
		if (!serverConfig.isLoaded()) return true;
		return TogHlyTabEvocationMagicResist.get();
	}

	public static int getHlyTabEvocationMagicResist() {
		if (!serverConfig.isLoaded()) return 100;
		return ValHlyTabEvocationMagicResist.get();
	}

	public static int getHlyTabSunFireRange() {
		if (!serverConfig.isLoaded()) return 100;
		return ValHlyTabSunFireRange.get();
	}

	public static int getHlyTabSunKnockRange() {
		if (!serverConfig.isLoaded()) return 100;
		return ValHlyTabSunKnockRange.get();
	}

	public static int getHlyTabSunKnockChance() {
		if (!serverConfig.isLoaded()) return 100;
		return ValHlyTabSunKnockChance.get();
	}

	public static int getHlyTabSunKnockCost() {
		if (!serverConfig.isLoaded()) return 100;
		return ValHlyTabSunKnockCost.get();
	}

	public static int getHlyTabSunFireCost() {
		if (!serverConfig.isLoaded()) return 100;
		return ValHlyTabSunFireCost.get();
	}

	public static int getHlyTabFireDuration() {
		if (!serverConfig.isLoaded()) return 100;
		return ValHlyTabFireDuration.get();
	}

	public static boolean toggleEnderTablet() {
		if (!serverConfig.isLoaded()) return true;
		return TogEnderTablet.get();
	}

	public static boolean toggleEndTabEnderManNoAgro() {
		if (!serverConfig.isLoaded()) return true;
		return TogEndTabEnderManNoAgro.get();
	}

	public static boolean toggleEndTabEnderMagicResist() {
		if (!serverConfig.isLoaded()) return true;
		return TogEndTabEnderMagicResist.get();
	}

	public static boolean toggleEndTabEnderMagicSpellPower() {
		if (!serverConfig.isLoaded()) return true;
		return TogEndTabEnderMagicSpellPower.get();
	}

	public static boolean toggleEndTabSpellResist() {
		if (!serverConfig.isLoaded()) return true;
		return TogEndTabSpellResist.get();
	}

	public static int getEndTabEnderMagicResist() {
		if (!serverConfig.isLoaded()) return 100;
		return ValEndTabEnderMagicResist.get();
	}

	public static int getEndTabEnderMagicSpellPower() {
		if (!serverConfig.isLoaded()) return 100;
		return ValEndTabEnderMagicSpellPower.get();
	}

	public static int getEndTabSpellResist() {
		if (!serverConfig.isLoaded()) return 100;
		return ValEndTabSpellResist.get();
	}

	public static boolean toggleCrystal() {
		if (!serverConfig.isLoaded()) return true;
		return togCrystal.get();
	}

	public static boolean toggleGreatAttractor() {
		if (!serverConfig.isLoaded()) return true;
		return TogGreatAttractor.get();
	}

	public static int getGreatAttractorRange() {
		if (!serverConfig.isLoaded()) return 100;
		return ValGreatAttractorRang.get();
	}

	public static int getGreatAttractorManaCost() {
		if (!serverConfig.isLoaded()) return 100;
		return ValGreatAttractorManaCost.get();
	}

	public static int getGreatAttractorTickDelay() {
		if (!serverConfig.isLoaded()) return 100;
		return ValGreatAttractorTickDelay.get();
	}

	public static boolean toggleNatureTablet() {
		if (!serverConfig.isLoaded()) return true;
		return TogNatureTablet.get();
	}

	public static boolean toggleNatureMagicResist() {
		if (!serverConfig.isLoaded()) return true;
		return TogNatureMagicResist.get();
	}

	public static boolean toggleNatureSpellPower() {
		if (!serverConfig.isLoaded()) return true;
		return TogNatureSpellPower.get();
	}

	public static boolean toggleNatureFireResist() {
		if (!serverConfig.isLoaded()) return true;
		return TogNatureFireResist.get();
	}

	public static boolean toggleNaturePoisonImmunity() {
		if (!serverConfig.isLoaded()) return true;
		return TogNaturePoisonImmunity.get();
	}

	public static boolean toggleNaturePlantGrowth() {
		if (!serverConfig.isLoaded()) return true;
		return TogNaturePlantGrowth.get();
	}

	public static int getNatureMagicResist() {
		if (!serverConfig.isLoaded()) return 100;
		return ValNatureMagicResist.get();
	}

	public static int getNatureSpellPower() {
		if (!serverConfig.isLoaded()) return 100;
		return ValNatureSpellPower.get();
	}

	public static int getNatureFireResist() {
		if (!serverConfig.isLoaded()) return 100;
		return ValNatureFireResist.get();
	}

	public static int getNatureGrowthCost() {
		if (!serverConfig.isLoaded()) return 100;
		return ValNatureGrowthCost.get();
	}

	public static int getNatureGrowthTickDelay() {
		if (!serverConfig.isLoaded()) return 100;
		return ValNatureGrowthTickDelay.get();
	}

	public static int getNatureGrowthRange() {
		if (!serverConfig.isLoaded()) return 100;
		return ValNatureGrowthRang.get();
	}

	public static int getNaturePoisonImmunityCost() {
		if (!serverConfig.isLoaded()) return 100;
		return ValNaturePoisonImmunityCost.get();
	}

	public static boolean toggleFireTablet() {
		if (!serverConfig.isLoaded()) return true;
		return TogFireTablet.get();
	}

	public static boolean toggleFirTabFireMagicResist() {
		if (!serverConfig.isLoaded()) return true;
		return TogFirTabFireMagicResist.get();
	}

	public static boolean toggleFirTabFireSpellPower() {
		if (!serverConfig.isLoaded()) return true;
		return TogFirTabFireSpellPower.get();
	}

	public static boolean toggleFirTabCoolDonReduction() {
		if (!serverConfig.isLoaded()) return true;
		return TogFirTabCoolDonReduction.get();
	}

	public static boolean toggleFirTabIceMagicResist() {
		if (!serverConfig.isLoaded()) return true;
		return TogFirTabIceMagicResist.get();
	}

	public static boolean toggleFirTabFireImmunity() {
		if (!serverConfig.isLoaded()) return true;
		return TogFirTabFireImmunity.get();
	}

	public static boolean toggleFirTabLavaSpeed() {
		if (!serverConfig.isLoaded()) return true;
		return TogFirTabLavaSpeed.get();
	}

	public static boolean toggleFirTabMiningSpeed() {
		if (!serverConfig.isLoaded()) return true;
		return TogFirTabMiningSpeed.get();
	}

	public static boolean toggleFirTabLavaVision() {
		if (!serverConfig.isLoaded()) return true;
		return TogFirTabLavaVision.get();
	}

	public static boolean toggleFirTabExtraFirePowerSpell() {
		if (!serverConfig.isLoaded()) return true;
		return TogFirTabExtraFirePowerSpell.get();
	}

	public static int getFirTabFireMagicResist() {
		if (!serverConfig.isLoaded()) return 100;
		return ValFirTabFireMagicResist.get();
	}

	public static int getFirTabFireSpellPower() {
		if (!serverConfig.isLoaded()) return 100;
		return ValFirTabFireSpellPower.get();
	}

	public static int getFirTabCoolDonReduction() {
		if (!serverConfig.isLoaded()) return 100;
		return ValFirTabCoolDonReduction.get();
	}

	public static int getFirTabIceMagicResist() {
		if (!serverConfig.isLoaded()) return 100;
		return valFirTabIceMagicResist.get();
	}

	public static int getFirTabExtraFirePowerSpell() {
		if (!serverConfig.isLoaded()) return 100;
		return valFirTabExtraFirePowerSpell.get();
	}

	public static boolean toggleIceTablet() {
		if (!serverConfig.isLoaded()) return true;
		return TogIceTablet.get();
	}

	public static boolean toggleIceTabIceMagicResist() {
		if (!serverConfig.isLoaded()) return true;
		return TogIceTabICE_MAGIC_RESIST.get();
	}

	public static boolean toggleIceTabIceSpellPower() {
		if (!serverConfig.isLoaded()) return true;
		return TogIceTabICE_SPELL_POWER.get();
	}

	public static boolean toggleIceTabMaxMana() {
		if (!serverConfig.isLoaded()) return true;
		return TogIceTabMAX_MANA.get();
	}

	public static boolean toggleIceTabNatureMagicResist() {
		if (!serverConfig.isLoaded()) return true;
		return TogIceTabNATURE_MAGIC_RESIST.get();
	}

	public static boolean toggleIceTabExtraIceSpellPower() {
		if (!serverConfig.isLoaded()) return true;
		return TogIceTabExtraIceSpellPower.get();
	}

	public static boolean toggleIceTabFreezingImmunity() {
		if (!serverConfig.isLoaded()) return true;
		return TogIceTabFreezingImmunity.get();
	}

	public static boolean toggleIceTabWaterVision() {
		if (!serverConfig.isLoaded()) return true;
		return TogIceTabWaterVision.get();
	}

	public static boolean toggleIceTabIceSpeed() {
		if (!serverConfig.isLoaded()) return true;
		return TogIceTabIceSpeed.get();
	}

	public static boolean toggleIceTabIceMiningSpeed() {
		if (!serverConfig.isLoaded()) return true;
		return TogIceTabIceMiningSpeed.get();
	}

	public static int getIceTabIceMagicResist() {
		if (!serverConfig.isLoaded()) return 100;
		return valIceTabICE_MAGIC_RESIST.get();
	}

	public static int getIceTabIceSpellPower() {
		if (!serverConfig.isLoaded()) return 100;
		return valIceTabICE_SPELL_POWER.get();
	}

	public static int getIceTabMaxMana() {
		if (!serverConfig.isLoaded()) return 100;
		return valIceTabMAX_MANA.get();
	}

	public static int getIceTabNatureMagicResist() {
		if (!serverConfig.isLoaded()) return 100;
		return valIceTabNATURE_MAGIC_RESIST.get();
	}

	public static int getIceTabExtraIceSpellPower() {
		if (!serverConfig.isLoaded()) return 100;
		return valIceTabExtraIceSpellPower.get();
	}

	public static void setToggleAttractorMagnet(boolean bol) {
		ToggleAbilities.toggleAttractorMagnet.set(bol);
		ToggleAbilities.ClientSpec.save();
	}
	public static void setToggleAoeDamage(boolean bol) {
		ToggleAbilities.toggleAoeDamage.set(bol);
		ToggleAbilities.ClientSpec.save();
	}
	public static void setTogGaiaBlessing(boolean bol) {
		ToggleAbilities.togGaiaBlessing.set(bol);
		ToggleAbilities.ClientSpec.save();
	}
	public static void setToggleShield(boolean bol) {
		ToggleAbilities.toggleShield.set(bol);
		ToggleAbilities.ClientSpec.save();
	}
	public static void setTogSunShield(boolean bol) {
		ToggleAbilities.TogSunShield.set(bol);
		ToggleAbilities.ClientSpec.save();
	}
	public static void setToggleBlockBreak(boolean bol) {
		ToggleAbilities.toggleBlockBreak.set(bol);
		ToggleAbilities.ClientSpec.save();
	}
	public static void setToggleExtraDrops(boolean bol) {
		ToggleAbilities.toggleExtraDrops.set(bol);
		ToggleAbilities.ClientSpec.save();
	}

}
