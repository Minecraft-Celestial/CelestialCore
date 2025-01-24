package com.xiaoyue.celestial_core.content.generic;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public abstract class SimpleMenuItem extends Item {
    public SimpleMenuItem(Properties pProperties) {
        super(pProperties);
    }

    @NotNull
    public abstract AbstractContainerMenu getSimpleMenu(int id, Inventory inv, Player player, ItemStack stack);

    @NotNull
    public abstract SimpleContainer getSimpleInv(ItemStack stack);

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (pLevel.isClientSide()) {
            return InteractionResultHolder.fail(stack);
        }
        if (pPlayer instanceof ServerPlayer serverPlayer) {
            if (serverPlayer.isCrouching()) {
                this.onShiftUse(pLevel, serverPlayer, stack);
            } else {
                this.openSimpleMenu(serverPlayer, stack);
            }
            return InteractionResultHolder.success(stack);
        }
        return InteractionResultHolder.fail(stack);
    }

    public void onShiftUse(Level level, ServerPlayer player, ItemStack stack) {
    }

    public void openSimpleMenu(ServerPlayer serverPlayer, ItemStack stack) {
        serverPlayer.openMenu(new SimpleMenuProvider((id, inv, player) -> {
            if (stack.getItem() instanceof SimpleMenuItem item) {
                return item.getSimpleMenu(id, inv, player, stack);
            }
            return null;
        }, stack.getHoverName()));
    }
}
