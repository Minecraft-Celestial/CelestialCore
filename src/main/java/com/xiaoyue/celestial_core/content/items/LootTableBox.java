package com.xiaoyue.celestial_core.content.items;

import com.xiaoyue.celestial_core.data.CCLangData;
import com.xiaoyue.celestial_core.utils.ItemUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LootTableBox extends Item {

    public final ResourceLocation lootTable;
    public final boolean needFishing;

    public LootTableBox(Properties pProperties, ResourceLocation lootTable) {
        super(pProperties);
        this.lootTable = lootTable;
        this.needFishing = true;
    }

    public LootTableBox(Properties pProperties, ResourceLocation lootTable, boolean needFishing) {
        super(pProperties);
        this.lootTable = lootTable;
        this.needFishing = needFishing;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
        String id = ItemUtils.getPathAfterSlash(lootTable.getPath());
        MutableComponent info = ItemUtils.getUSNameInfo(id).withStyle(ChatFormatting.AQUA);
        list.add(CCLangData.LOOT_BOX_INFO.get(info).withStyle(ChatFormatting.GRAY));
        if (this.needFishing) {
            //list.add(CCLangData.BIOMES_FISHING.get().withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack box = pPlayer.getItemInHand(pUsedHand);
        if (pPlayer instanceof ServerPlayer serverPlayer) {
            List<ItemStack> stacks = ItemUtils.getDefaultLootStacks(lootTable, serverPlayer);
            for (ItemStack stack : stacks) {
                serverPlayer.addItem(stack);
            }
            ItemUtils.toShrink(box, serverPlayer);
            return InteractionResultHolder.consume(box);
        }
        return InteractionResultHolder.fail(box);
    }
}
