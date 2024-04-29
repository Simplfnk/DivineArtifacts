package nk.divineartifacts.client.handler;

import nk.divineartifacts.config.ToggleAbilities;

public class ToggleHelper {
	public static boolean toggleMagnet() {
		return ToggleAbilities.toggleMagnet.get();
	}
	public static boolean toggleAoeDamage() {
		return ToggleAbilities.toggleAoeDamage.get();
	}
	public static boolean toggleShield() {
		return ToggleAbilities.toggleShield.get();
	}
	public static boolean toggleBlockBreak() {
		return ToggleAbilities.toggleBlockBreak.get();
	}
	public static boolean toggleExtraDrops() {
		return ToggleAbilities.toggleExtraDrops.get();
	}
	public static boolean toggleHudElements() {
		return ToggleAbilities.toggleHudElements.get();
	}
}
