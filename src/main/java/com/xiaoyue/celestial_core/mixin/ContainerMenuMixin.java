package com.xiaoyue.celestial_core.mixin;

import com.xiaoyue.celestial_core.events.MenuSlotClickEvent;
import com.xiaoyue.celestial_core.utils.CCUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractContainerMenu.class)
public class ContainerMenuMixin {

    @Inject(at = @At("HEAD"), method = "doClick", cancellable = true)
    public void celestial_core$do_click(int pSlotId, int pButton, ClickType pClickType, Player pPlayer, CallbackInfo ci) {
        AbstractContainerMenu menu = (AbstractContainerMenu) (Object) this;
        if (!pClickType.equals(ClickType.PICKUP) || pSlotId < 0) {
            return;
        }
        Slot slot = menu.getSlot(pSlotId);
        var event = new MenuSlotClickEvent(slot, pPlayer, menu, pButton);
        if (celestialCore$canUse(menu, slot, pPlayer)) {
            if (CCUtils.fireEvent(event).isCanceled()) {
                ci.cancel();
            }
        }
    }

    @Unique
    public boolean celestialCore$canUse(AbstractContainerMenu menu, Slot slot, Player player) {
        return !(slot instanceof ResultSlot) && slot.allowModification(player) && menu.canTakeItemForPickAll(menu.getCarried(), slot) && slot.isActive();
    }
}
