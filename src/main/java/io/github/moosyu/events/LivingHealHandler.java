package io.github.moosyu.events;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;

import static io.github.moosyu.NNO.MODID;

// disabling vanilla healing to use custom logic
public class LivingHealHandler {
    @EventBusSubscriber(modid = MODID)
    public static class EventHandler {
        @SubscribeEvent
        public static void onLivingHeal(LivingHealEvent event) {
            event.setCanceled(true);
        }
    }
}