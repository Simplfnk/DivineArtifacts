package nk.divineartifacts.config;


import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;


public class Config {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    private static final General GENERAL = new General(BUILDER);
    public static final ForgeConfigSpec spec = BUILDER.build();
    public static BooleanValue toggleMagnet;
    public static BooleanValue toggleExplode;
    public static BooleanValue toggleShield;
    public static BooleanValue toggleBlockBreak;
    private static final boolean magState = Config.toggleMagnet.get() != null;
    private static final boolean expState = Config.toggleExplode.get() != null;
    private static final boolean shiState = Config.toggleShield.get() != null;
    private static final boolean breakState = Config.toggleBlockBreak.get() != null;

    public static BooleanValue configDivineArtifacts;


    public static class General {
        public General(final ForgeConfigSpec.Builder builder) {
            builder.push("Divine Artifacts");
            builder.push("Enable/Disable Artifacts");
            configDivineArtifacts = builder.define("itemGroup.divineartifacts", true);
            toggleMagnet = BUILDER.comment("On/Off Magnet state")
                    .define("MagnetState", magState);
            toggleExplode = BUILDER.comment("On/Off Explode state")
                    .define("ExplodeState", expState);
            toggleShield = BUILDER.comment("On/Off Shield state")
                    .define("ShieldState", shiState);
            toggleBlockBreak = BUILDER.comment("On/Off Block Break state")
                    .define("BlockBreakState", breakState);
            builder.pop();

        }
    }

}