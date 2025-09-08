package com.xiaoyue.celestial_core.content.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.xiaoyue.celestial_core.utils.CCUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.model.data.ModelData;
import org.joml.Matrix4f;

import javax.annotation.Nullable;
import java.util.List;

/**
 * This class references class GuiGameElement from Create
 * Date: 2025-4-3
 */
public class GuiRenderHandler {

    public static ILightingSettings BLOCK_LIGHTING = CustomLightingSettings.builder()
            .firstLightRotation(-30, 45)
            .secondLightRotation(0, 65)
            .build();

    public static void renderInfoFromMouse(GuiGraphics graphics, List<Component> info, double mx, double my, double x, double y, double ex, double ey, double ox, double oy) {
        Font font = Minecraft.getInstance().font;
        if (mx > x && mx < x + ex && my > y && my < y + ey) {
            graphics.renderComponentTooltip(font, info, (int) (mx + ox), (int) (my + oy));
        }
    }

    public static void renderInfoFromMouse(GuiGraphics graphics, List<Component> info, double mx, double my, double x, double y, double ex, double ey) {
        renderInfoFromMouse(graphics, info, mx, my, x, y, ex, ey, 0, 0);
    }

    public static GuiRenderBuilder of(ItemStack stack) {
        return new GuiItemRenderBuilder(stack);
    }

    public static GuiRenderBuilder of(ItemLike itemProvider) {
        return new GuiItemRenderBuilder(itemProvider);
    }

    public static GuiRenderBuilder of(BlockState state) {
        if (state.getRenderShape() != RenderShape.MODEL && state.getFluidState().isEmpty()) {
            return of(state.getBlock());
        }
        if (state.getBlock() instanceof StairBlock) {
            state = state.setValue(StairBlock.FACING, state.getValue(StairBlock.FACING).getOpposite());
        }
        return new GuiBlockStateRenderBuilder(state);
    }

    public static GuiRenderBuilder of(Fluid fluid) {
        return new GuiBlockStateRenderBuilder(fluid.defaultFluidState().createLegacyBlock().setValue(LiquidBlock.LEVEL, 0));
    }

    public static abstract class GuiRenderBuilder extends RenderElement {
        protected double xLocal, yLocal, zLocal;
        protected double xRot, yRot, zRot;
        protected double scale = 1;
        protected int color = 0xFFFFFF;
        protected Vec3 rotationOffset = Vec3.ZERO;
        protected ILightingSettings customLighting = null;

        public GuiRenderBuilder atLocal(double x, double y, double z) {
            this.xLocal = x;
            this.yLocal = y;
            this.zLocal = z;
            return this;
        }

        public GuiRenderBuilder rotate(double xRot, double yRot, double zRot) {
            this.xRot = xRot;
            this.yRot = yRot;
            this.zRot = zRot;
            return this;
        }

        public GuiRenderBuilder rotateBlock(double xRot, double yRot, double zRot) {
            return this.rotate(xRot, yRot, zRot).withRotationOffset(CCUtils.getCenterOf(BlockPos.ZERO));
        }

        public GuiRenderBuilder scale(double scale) {
            this.scale = scale;
            return this;
        }

        public GuiRenderBuilder color(int color) {
            this.color = color;
            return this;
        }

        public GuiRenderBuilder withRotationOffset(Vec3 offset) {
            this.rotationOffset = offset;
            return this;
        }

        public GuiRenderBuilder lighting(ILightingSettings lighting) {
            customLighting = lighting;
            return this;
        }

        protected void prepareMatrix(PoseStack matrixStack) {
            matrixStack.pushPose();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.enableDepthTest();
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            prepareLighting(matrixStack);
        }

        protected void transformMatrix(PoseStack matrixStack) {
            matrixStack.translate(x + 3, y + 13, z);
            matrixStack.scale((float) scale, (float) scale, (float) scale);
            matrixStack.translate(xLocal, yLocal, zLocal);
            matrixStack.mulPoseMatrix(new Matrix4f().scaling(1, -1, 1));
            matrixStack.translate(rotationOffset.x, rotationOffset.y, rotationOffset.z);
            matrixStack.mulPose(Axis.ZP.rotationDegrees((float) zRot));
            matrixStack.mulPose(Axis.XP.rotationDegrees((float) xRot));
            matrixStack.mulPose(Axis.YP.rotationDegrees((float) yRot));
            matrixStack.translate(-rotationOffset.x, -rotationOffset.y, -rotationOffset.z);
        }

        protected void cleanUpMatrix(PoseStack matrixStack) {
            matrixStack.popPose();
            cleanUpLighting(matrixStack);
        }

        protected void prepareLighting(PoseStack matrixStack) {
            if (customLighting != null) {
                customLighting.applyLighting();
            } else {
                Lighting.setupFor3DItems();
            }
        }

        protected void cleanUpLighting(PoseStack matrixStack) {
            if (customLighting != null) {
                Lighting.setupFor3DItems();
            }
        }
    }

    private static class GuiBlockModelRenderBuilder extends GuiRenderBuilder {

        protected BakedModel blockModel;
        protected BlockState blockState;
        private final ModelData modelData;

        public GuiBlockModelRenderBuilder(BakedModel model, @Nullable BlockState blockState) {
            this.blockState = blockState == null ? Blocks.AIR.defaultBlockState() : blockState;
            this.blockModel = model;
            this.modelData = ModelData.EMPTY;
        }

        @Override
        public void render(GuiGraphics graphics) {
            PoseStack matrixStack = graphics.pose();
            prepareMatrix(matrixStack);
            Minecraft mc = Minecraft.getInstance();
            BlockRenderDispatcher blockRenderer = mc.getBlockRenderer();
            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
            RenderType renderType = blockState.getBlock() == Blocks.AIR ? Sheets.translucentCullBlockSheet() : ItemBlockRenderTypes.getRenderType(blockState, true);
            VertexConsumer vb = buffer.getBuffer(renderType);
            transformMatrix(matrixStack);
            RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);
            renderModel(blockRenderer, buffer, renderType, vb, matrixStack);
            cleanUpMatrix(matrixStack);
        }

        protected void renderModel(BlockRenderDispatcher blockRenderer, MultiBufferSource.BufferSource buffer, RenderType renderType, VertexConsumer vb, PoseStack ms) {
            Minecraft mc = Minecraft.getInstance();
            int color = mc.getBlockColors().getColor(blockState, mc.level, mc.cameraEntity != null ? mc.cameraEntity.blockPosition() : null, 0);
            float r = color == -1 ? (this.color >> 16 & 0xff) / 255f : color;
            float g = color == -1 ? (this.color >> 8 & 0xff) / 255f : color;
            float b = color == -1 ? (this.color & 0xff) / 255f : color;
            blockRenderer.getModelRenderer().renderModel(ms.last(), vb, blockState, blockModel, r, g, b, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, modelData, null);
            buffer.endBatch();
        }
    }

    public static class GuiBlockStateRenderBuilder extends GuiBlockModelRenderBuilder {

        public GuiBlockStateRenderBuilder(BlockState blockstate) {
            super(Minecraft.getInstance().getBlockRenderer().getBlockModel(blockstate), blockstate);
        }

        @Override
        protected void renderModel(BlockRenderDispatcher blockRenderer, MultiBufferSource.BufferSource buffer, RenderType renderType, VertexConsumer vb, PoseStack ms) {
            if (blockState.getBlock() instanceof FireBlock) {
                Lighting.setupForFlatItems();
                blockRenderer.renderSingleBlock(blockState, ms, buffer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY);
                buffer.endBatch();
                Lighting.setupFor3DItems();
                return;
            }
            super.renderModel(blockRenderer, buffer, renderType, vb, ms);
            if (blockState.getFluidState().isEmpty()) {
                return;
            }
            float min = 0.001F, max = 0.999F;
            FluidRenderer.renderFluidBox(blockState.getFluidState(), min, min, min, max, max * 0.8888889F, max, buffer, ms, LightTexture.FULL_BRIGHT, false);
            buffer.endBatch();
        }
    }

    public static class GuiItemRenderBuilder extends GuiRenderBuilder {

        private final ItemStack stack;

        public GuiItemRenderBuilder(ItemStack stack) {
            this.stack = stack;
            scale = 10;
        }

        public GuiItemRenderBuilder(ItemLike provider) {
            this(provider.asItem().getDefaultInstance());
        }

        @Override
        public void render(GuiGraphics graphics) {
            PoseStack matrixStack = graphics.pose();
            prepareMatrix(matrixStack);
            transformMatrix(matrixStack);
            renderItemIntoGUI(matrixStack, stack, customLighting == null);
            cleanUpMatrix(matrixStack);
        }

        protected void transformMatrix(PoseStack matrixStack) {
            matrixStack.translate(x, y, z);
            matrixStack.translate(xLocal * scale, yLocal * scale, zLocal * scale);
            matrixStack.mulPoseMatrix(new Matrix4f().scaling(1, -1, 1));
        }

        public static void renderItemIntoGUI(PoseStack matrixStack, ItemStack stack, boolean useDefaultLighting) {
            ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
            BakedModel bakedModel = renderer.getModel(stack, null, null, 0);
            Minecraft.getInstance().getTextureManager().getTexture(InventoryMenu.BLOCK_ATLAS).setFilter(false, false);
            RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);
            RenderSystem.enableBlend();
            RenderSystem.enableCull();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            matrixStack.pushPose();
            matrixStack.translate(0, 0, 100.0F);
            matrixStack.translate(8.0F, -8.0F, 0.0F);
            matrixStack.scale(16.0F, 16.0F, 16.0F);
            MultiBufferSource.BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();
            boolean flatLighting = !bakedModel.usesBlockLight();
            if (useDefaultLighting && flatLighting) {
                Lighting.setupForFlatItems();
            }
            renderer.render(stack, ItemDisplayContext.GUI, false, matrixStack, buffer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, bakedModel);
            RenderSystem.disableDepthTest();
            buffer.endBatch();
            RenderSystem.enableDepthTest();
            if (useDefaultLighting && flatLighting) {
                Lighting.setupFor3DItems();
            }
            matrixStack.popPose();
        }

        @Override
        public GuiRenderBuilder lighting(ILightingSettings lighting) {
            return this;
        }

    }
}
