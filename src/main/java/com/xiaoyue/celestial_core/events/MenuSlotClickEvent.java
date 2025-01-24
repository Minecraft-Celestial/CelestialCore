package com.xiaoyue.celestial_core.events;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class MenuSlotClickEvent extends Event {

    private final Slot slot;
    private final Player player;
    private final AbstractContainerMenu menu;
    private final int actionId;

    public MenuSlotClickEvent(Slot slot, Player player, AbstractContainerMenu menu, int actionId) {
        this.slot = slot;
        this.player = player;
        this.menu = menu;
        this.actionId = actionId;
    }

    public Slot getSlot() {
        return slot;
    }

    public ItemStack getSlotItem() {
        return slot.getItem();
    }

    public Player getPlayer() {
        return player;
    }

    public AbstractContainerMenu getMenu() {
        return menu;
    }

    public ItemStack getHeldItem() {
        return menu.getCarried();
    }

    public int getActionId() {
        return actionId;
    }

    public ClickAction getAction() {
        return actionId == 0 ? ClickAction.PRIMARY : ClickAction.SECONDARY;
    }
}
