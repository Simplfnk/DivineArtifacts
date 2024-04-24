package nk.divineartifacts.client;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.ItemStack;
import nk.divineartifacts.item.DivineOrbBase;
import nk.divineartifacts.item.DivineRingBase;

import java.util.HashMap;

public class GlintHandler {

    private static final ThreadLocal<ItemStack> stack = new ThreadLocal<>();

    public static final HashMap<GlintRenderTypes, RenderType> glintMap = new HashMap<>();
    public static final HashMap<GlintRenderTypes, RenderType> directMap = new HashMap<>();
    public static final HashMap<GlintRenderTypes, RenderType> translucentMap = new HashMap<>();

    static {
        for (GlintRenderTypes glint : GlintRenderTypes.values()) {
            RenderType glintType = GlintRenderType.buildGlintRenderType(glint.name);
            glintMap.put(glint, glintType);

            RenderType directType = GlintRenderType.buildGlintDirectRenderType(glint.name);
            directMap.put(glint, directType);

            RenderType translucentType = GlintRenderType.buildGlintTranslucentRenderType(glint.name);
            translucentMap.put(glint, translucentType);
        }
    }

    public static void setStack(ItemStack value) {
        stack.set(value);
    }

    public static RenderType getGlintTranslucent() {
        GlintRenderTypes color = getColor(stack.get());
        return color == null ? RenderType.glintTranslucent() : translucentMap.get(color);
    }

    public static RenderType getGlint() {
        GlintRenderTypes color = getColor(stack.get());
        return color == null ? RenderType.glint() : glintMap.get(color);
    }

    public static RenderType getGlintDirect() {
        GlintRenderTypes color = getColor(stack.get());
        return color == null ? RenderType.glintDirect() : directMap.get(color);
    }

    private static GlintRenderTypes getColor(ItemStack stack) {
        if(stack == null || !(stack.getItem() instanceof DivineRingBase item)) return null;
        return item.glintType;
    }
    private static GlintRenderTypes getColor2(ItemStack stack) {
        if(stack == null || !(stack.getItem() instanceof DivineOrbBase item)) return null;
        return item.glintType;
    }

}