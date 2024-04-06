package nk.divineartifacts.client.handler;

import nk.divineartifacts.config.Config;

public class ToggleHelper {
	public static boolean toggleMagnet() {
		return Config.toggleMagnet.get();
	}
	public static boolean toggleExplode() {
		return Config.toggleExplode.get();
	}
	public static boolean toggleShield() {
		return Config.toggleShield.get();
	}
	public static boolean toggleBlockBreak() {
		return Config.toggleBlockBreak.get();
	}
	public static boolean toggleExtraDrops() {
		return Config.toggleExtraDrops.get();
	}
}
