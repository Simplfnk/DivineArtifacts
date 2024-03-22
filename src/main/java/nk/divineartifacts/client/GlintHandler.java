package nk.divineartifacts.client;


import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.ItemStack;
import nk.divineartifacts.item.DivineOrbBase;
import nk.divineartifacts.item.DivineRingBase;

public class GlintHandler {

    private static final ThreadLocal< ItemStack > stack = new ThreadLocal<>();

    public static void setStack( ItemStack value ) {
        stack.set( value );
    }

    public static RenderType getGlintTranslucent() {
        GlintRenderTypes color = getColor( stack.get() );
        return color == null ? RenderType.glintTranslucent() : color.translucent;
    }

    public static RenderType getGlint() {
        GlintRenderTypes color = getColor( stack.get() );
        return color == null ? RenderType.glint() : color.glint;
    }

    public static RenderType getGlintDirect() {
        GlintRenderTypes color = getColor( stack.get() );
        return color == null ? RenderType.glintDirect() : color.direct;
    }

    private static GlintRenderTypes getColor( ItemStack stack ) {
        if ( stack == null )
            return null;


        if ( stack.getItem() instanceof DivineRingBase item )

            return item.glintType;

        if ( stack.getItem() instanceof DivineOrbBase item )

            return item.glintType;

        return null;
    }


}
