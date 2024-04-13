package nk.divineartifacts.config;


import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;


public class Config {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    private static final General GENERAL = new General(BUILDER);
    public static final ForgeConfigSpec spec = BUILDER.build();
    public static BooleanValue toggleMagnet;
    public static BooleanValue toggleAioDamage;
    public static BooleanValue toggleShield;
    public static BooleanValue toggleBlockBreak;
    public static BooleanValue toggleExtraDrops;
    public static BooleanValue toggleHudElements;
    private static final boolean magState = Config.toggleMagnet.get() != null;
    private static final boolean expState = Config.toggleAioDamage.get() != null;
    private static final boolean shiState = Config.toggleShield.get() != null;
    private static final boolean breakState = Config.toggleBlockBreak.get() != null;
    private static final boolean extraLoot = Config.toggleExtraDrops.get() != null;
    private static final boolean hudState = Config.toggleHudElements.get() != null;

    public static BooleanValue configDivineArtifacts;


    public static class General {
        public General(final ForgeConfigSpec.Builder builder) {
            builder.push("Divine Artifacts");
            builder.push("Enable/Disable Artifacts");
            configDivineArtifacts = builder.define("Items.Rings", true);
            toggleMagnet = BUILDER.comment("On/Off Magnet state")
                    .define("State.MagnetState", magState);
            toggleAioDamage = BUILDER.comment("On/Off AIO Damage state")
                    .define("State.AIO State", expState);
            toggleShield = BUILDER.comment("On/Off Shield state")
                    .define("State. Shield State", shiState);
            toggleBlockBreak = BUILDER.comment("On/Off Block Break state")
                    .define("State.Block Break State", breakState);
            toggleExtraDrops = BUILDER.comment("On/Off Extra Drops state")
                    .define("State.Extra Drops", extraLoot);
            toggleHudElements = BUILDER.comment("On/Off Toggle Hud elements state")
                    .define("State.Hud Elements", hudState);
            builder.pop();

        }
    }

}