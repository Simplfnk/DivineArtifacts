package nk.divineartifacts.utils;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import nk.divineartifacts.item.ItemBaseClass;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import java.util.Optional;

public class Utils {

    public static boolean isRingEquipped(ItemBaseClass ring, LivingEntity player) {
        return getFirstCurio( ring , player) != null;
    }

    public static ItemStack getFirstCurio(ItemBaseClass ring, LivingEntity player) {
        if(!ring.isEnabled.get()) return null;

        Optional<ICuriosItemHandler> inventory = CuriosApi.getCuriosInventory(player).resolve();
        if(inventory.isEmpty()) return null;

        Optional<SlotResult> curio = inventory.get().findFirstCurio(ring);
        return curio.map(SlotResult::stack).orElse(null);
    }

}
