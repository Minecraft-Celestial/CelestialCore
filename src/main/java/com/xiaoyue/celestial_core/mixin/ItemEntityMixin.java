package com.xiaoyue.celestial_core.mixin;

import com.xiaoyue.celestial_core.content.recipes.TransformationRecipe;
import com.xiaoyue.celestial_core.register.CCRecipes;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {

    @Shadow public abstract void setItem(ItemStack p_32046_);

    @Inject(at = @At("RETURN"), method = "tick")
    public void celestial_core$tick$recipe(CallbackInfo ci) {
        ItemEntity entity = (ItemEntity) (Object) this;
        Level level = entity.level();
        if (entity.isRemoved() || level.isClientSide()) return;
        TransformationRecipe.Inv inv = new TransformationRecipe.Inv();
        level.getRecipeManager().getRecipeFor(CCRecipes.RT_TRANSFORMATION.get(), inv ,level).ifPresent(recipe -> {
            List<BlockPos> states = List.of(entity.getOnPos(), entity.getOnPos().below());
            BlockPos current = null;
            if (level.getBlockState(states.get(0)).is(recipe.stat)) {
                current = states.get(0);
            } else if (level.getBlockState(states.get(1)).is(recipe.stat)) {
                current = states.get(1);
            }
            if (current == null) return;
            List<ItemEntity> itemEntities = level.getEntitiesOfClass(ItemEntity.class, entity.getBoundingBox().inflate(1));
            List<Ingredient> missInputs = new ArrayList<>(recipe.inputs);
            Set<ItemEntity> selectedEntities = new ReferenceOpenHashSet<>(missInputs.size());
            for (ItemEntity itemEntity : itemEntities) {
                ItemStack other = itemEntity.getItem();
                if (other.isEmpty()) continue;
                for (var it = missInputs.iterator(); it.hasNext();) {
                    Ingredient ing = it.next();
                    if (ing.test(other)) {
                        selectedEntities.add(itemEntity);
                        it.remove();
                        break;
                    }
                }
            }
            if (missInputs.isEmpty()) {
                int i = 0;
                for (var selected : selectedEntities) {
                    inv.setItem(i++, selected.getItem().split(1));
                    if (selected.getItem().getCount() <= 0) {
                        selected.discard();
                    }
                }
                ItemStack output = recipe.assemble(inv, level.registryAccess());
                EntityUtils.spawnItem(level, entity.getOnPos(), output);
                if (recipe.after != null) {
                    level.setBlock(current, recipe.after.defaultBlockState(), 2);
                }
            }
        });
    }
}
