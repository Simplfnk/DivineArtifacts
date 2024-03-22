package nk.divineartifacts.config;


import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;


public class Config {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    private static final General GENERAL = new General(BUILDER);
    public static final ForgeConfigSpec spec = BUILDER.build();

    //Config Values




    public static BooleanValue configDivineArtifacts;



    public static class General {
        public General(final ForgeConfigSpec.Builder builder) {
            builder.push("Divine Artifacts");
            builder.push("Enable/Disable Artifacts");
            configDivineArtifacts = builder.define("itemGroup.divineartifacts", true);
            builder.pop();

        }
    }

}