package com.xiaoyue.celestial_core.content.client.render;

import net.minecraft.client.gui.GuiGraphics;

/**
 * This class references class RenderElement from Create
 * Date: 2025-4-3
 */
public abstract class RenderElement {

    public static final RenderElement EMPTY = new RenderElement() {
        @Override
        public void render(GuiGraphics graphics) {
        }
    };

    public static RenderElement of(RenderContext context) {
        return new SimpleRenderElement(context);
    }

    protected int width = 16, height = 16;
    protected float x = 0, y = 0, z = 0;
    protected float alpha = 1f;

    @SuppressWarnings("unchecked")
    public <T extends RenderElement> T at(float x, float y) {
        this.x = x;
        this.y = y;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends RenderElement> T at(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends RenderElement> T withBounds(int width, int height) {
        this.width = width;
        this.height = height;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends RenderElement> T withAlpha(float alpha) {
        this.alpha = alpha;
        return (T) this;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public abstract void render(GuiGraphics graphics);

    public void render(GuiGraphics graphics, int x, int y) {
        this.at(x, y).render(graphics);
    }

    public static class SimpleRenderElement extends RenderElement {

        private final RenderContext context;

        public SimpleRenderElement(RenderContext context) {
            this.context = context;
        }

        @Override
        public void render(GuiGraphics graphics) {
            context.render(graphics, (int) x, (int) y);
        }
    }
}
