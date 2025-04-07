package com.xiaoyue.celestial_core.content.client.render;

import com.mojang.blaze3d.platform.Lighting;

/**
 * This class references class ILightingSettings from Create
 * Date: 2025-4-3
 */
public interface ILightingSettings {

    void applyLighting();

    ILightingSettings DEFAULT_3D = Lighting::setupFor3DItems;
    ILightingSettings DEFAULT_FLAT = Lighting::setupForFlatItems;

}
