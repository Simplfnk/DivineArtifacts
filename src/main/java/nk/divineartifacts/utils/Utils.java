package nk.divineartifacts.utils;

import net.minecraft.world.entity.LivingEntity;
import nk.divineartifacts.item.DivineOrbBase;
import nk.divineartifacts.item.DivineRingBase;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import java.util.Optional;

public class Utils {

    public static boolean isRingEquipped(DivineRingBase ring, LivingEntity player) {
        return getFirstCurio(ring, player) != null;
    }

    public static ItemStack getFirstCurio(DivineRingBase ring, LivingEntity player) {
        if(!ring.isEnabled) return null;

        Optional<ICuriosItemHandler> inventory = CuriosApi.getCuriosInventory(player).resolve();
        if(inventory.isEmpty()) return null;

        Optional<SlotResult> curio = inventory.get().findFirstCurio(ring);
        return curio.map(SlotResult::stack).orElse(null);
    }
    public static boolean isRingEquipped(DivineOrbBase ring, Player player) {
        return getFirstCurio(ring, player) != null;
    }

    public static ItemStack getFirstCurio(DivineOrbBase ring, Player player) {
        if(!ring.isEnabled) return null;

        Optional<ICuriosItemHandler> inventory = CuriosApi.getCuriosInventory(player).resolve();
        if(inventory.isEmpty()) return null;

        Optional<SlotResult> curio = inventory.get().findFirstCurio(ring);
        return curio.map(SlotResult::stack).orElse(null);
    }


}
