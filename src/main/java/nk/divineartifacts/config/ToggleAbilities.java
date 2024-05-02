package nk.divineartifacts.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ToggleAbilities {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	private static final clientConfig client_config = new clientConfig(BUILDER);
	public static final ForgeConfigSpec ClientSpec = BUILDER.build();

	public static ForgeConfigSpec.BooleanValue toggleMagnet;
	public static ForgeConfigSpec.BooleanValue toggleAoeDamage;
	public static ForgeConfigSpec.BooleanValue toggleShield;
	public static ForgeConfigSpec.BooleanValue toggleBlockBreak;
	public static ForgeConfigSpec.BooleanValue toggleExtraDrops;
	public static ForgeConfigSpec.BooleanValue toggleHudElements;
	public static class clientConfig {
		public clientConfig(final ForgeConfigSpec.Builder builder) {
			builder.push("Divine Artifacts");
			builder.push("Enable/Disable Artifacts");
			toggleMagnet = BUILDER.comment("On/Off Magnet state").define("State.MagnetState" , true);
			toggleAoeDamage = BUILDER.comment("On/Off AOE Damage state").define("State.AOE State" , true);
			toggleShield = BUILDER.comment("On/Off Shield state").define("State. Shield State" , true);
			toggleBlockBreak = BUILDER.comment("On/Off Block Break state").define("State.Block Break State" , true);
			toggleExtraDrops = BUILDER.comment("On/Off Extra Drops state").define("State.Extra Drops" , true);
			toggleHudElements = BUILDER.comment("On/Off Toggle Hud elements state").define("State.Hud Elements" , true);
			builder.pop();
		}
	}
}