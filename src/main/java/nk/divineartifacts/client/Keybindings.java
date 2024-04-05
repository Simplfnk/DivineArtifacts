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
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_NUMPAD1,-1),
            CATEGORY
    );
    public  final KeyMapping explodedKey = new KeyMapping(
            "explode." + DivineArtifacts.MODID + ".toggle.key",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_NUMPAD2,-1),
            CATEGORY
    );
    public  final KeyMapping shieldKey = new KeyMapping(
            "shield." + DivineArtifacts.MODID + ".toggle.key",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_NUMPAD3,-1),
            CATEGORY
    );
}
