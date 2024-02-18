package com.xiaoyue.celestial_core.events;

import com.xiaoyue.celestial_core.content.damage.CDamageTypeTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.xiaoyue.celestial_core.celestial_core.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CGatherData {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        boolean included = event.includeServer();
        var provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        generator.addProvider(included, new CDamageTypeTags(output, provider, helper));
    }
}
