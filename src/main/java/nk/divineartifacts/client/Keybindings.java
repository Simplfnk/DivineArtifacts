package nk.divineartifacts.client;


import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import nk.divineartifacts.DivineArtifacts;

public final class Keybindings {
    public static final Keybindings INSTANCE = new Keybindings();
    private Keybindings(){}
    public static final String CATEGORY = "key.categories." + DivineArtifacts.MODID;
    public  final KeyMapping magnetKey = new KeyMapping(
            "magnet." + DivineArtifacts.MODID + ".toggle.key",
            KeyConflictContext.UNIVERSAL,
            InputConstants.getKey(InputConstants.KEY_H,-1),
            CATEGORY
    );
    public  final KeyMapping explodedKey = new KeyMapping(
            "explode" + DivineArtifacts.MODID + ".toggle.key",
            KeyConflictContext.UNIVERSAL,
            InputConstants.getKey(InputConstants.KEY_T,-1),
            CATEGORY
    );
}
