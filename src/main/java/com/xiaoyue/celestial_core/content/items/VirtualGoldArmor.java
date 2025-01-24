package com.xiaoyue.celestial_core.content.items;

import com.xiaoyue.celestial_core.content.client.armor.CCModelLayers;
import com.xiaoyue.celestial_core.content.client.armor.VGArmorModel;
import dev.xkmc.l2damagetracker.contents.materials.api.ArmorConfig;
import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraArmorConfig;
import dev.xkmc.l2damagetracker.contents.materials.generic.GenericArmorItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class VirtualGoldArmor extends GenericArmorItem {
    public VirtualGoldArmor(ArmorMaterial material, Type slot, Properties prop, ExtraArmorConfig config) {
        super(material, slot, prop, config);
    }

    public static final ArmorConfig ARMOR_GEN = new ArmorConfig((mat, slot, prop) -> new VirtualGoldArmor(mat.getArmorMaterial(), slot, prop, mat.getExtraArmorConfig()));

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot slot, HumanoidModel<?> original) {
                EntityModelSet set = Minecraft.getInstance().getEntityModels();
                ModelPart layer = set.bakeLayer(slot == EquipmentSlot.LEGS ? CCModelLayers.VIRTUAL_GOLD_ARMOR_INNER_LAYER : CCModelLayers.VIRTUAL_GOLD_ARMOR_OUTER_LAYER);
                return new VGArmorModel(layer);
            }
        });
    }
}
