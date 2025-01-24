package com.xiaoyue.celestial_core.content.generic;

import com.xiaoyue.celestial_core.utils.ItemUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class DurableFoodItem extends Item {
    public final ItemStack foodBase;

    public DurableFoodItem(Properties pProperties) {
        super(pProperties);
        this.foodBase = this.getDefaultInstance();
    }

    public DurableFoodItem(Properties pProperties, Item foodBase) {
        super(pProperties);
        this.foodBase = foodBase.getDefaultInstance();
    }

    public DurableFoodItem(Properties pProperties, ItemStack foodBase) {
        super(pProperties);
        this.foodBase = foodBase;
    }

    public boolean isDrinkable() {
        return false;
    }

    public boolean isFast() {
        return false;
    }

    public boolean canShrink() {
        return false;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return isDrinkable() ? UseAnim.DRINK : UseAnim.EAT;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return isFast() ? 16 : 32;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (ItemUtils.getDurability(stack) > 1 && !canShrink()) {
            pPlayer.startUsingItem(pUsedHand);
            return InteractionResultHolder.consume(stack);
        } else if (canShrink()) {
            pPlayer.startUsingItem(pUsedHand);
            return InteractionResultHolder.consume(stack);
        }
        return InteractionResultHolder.fail(stack);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        eatFood(pLevel, pLivingEntity);
        ItemUtils.directDamage(pStack, 1);
        this.onConsume(pStack, pLivingEntity);
        return pStack;
    }

    public void eatFood(Level pLevel, LivingEntity pLivingEntity) {
        if (foodBase.isEdible()) {
            var prop = foodBase.getFoodProperties(pLivingEntity);
            if (pLivingEntity instanceof Player player) {
                player.getFoodData().eat(prop.getNutrition(), prop.getSaturationModifier());
            }
            for (var ins : prop.getEffects()) {
                if (!pLevel.isClientSide() && ins.getFirst() != null && pLevel.random.nextFloat() < ins.getSecond()) {
                    pLivingEntity.addEffect(new MobEffectInstance(ins.getFirst()));
                }
            }
        }
    }


        protected void onConsume(ItemStack stack, LivingEntity entity) {
    }
}
