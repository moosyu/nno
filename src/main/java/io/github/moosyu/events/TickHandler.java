package io.github.moosyu.events;

import io.github.moosyu.helpers.ModHelpers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class TickHandler {
    @EventBusSubscriber(modid = "nno")
    public static class EventHandler {
        @SubscribeEvent
        public static void onPlayerTick(PlayerTickEvent.Post event) {
            Player player = event.getEntity();
            // Remember to delete this one later if this is used for anything but bobbing indicator
            if (!player.level().isClientSide()) {
                return;
            }

            if (!(player.fishing instanceof FishingHook)) {
                return;
            }

            int test = player.fishing.nibble;
            if (test > 0) {
                ModHelpers.broadcastMessage(String.valueOf(test));
            }
            // uhh idk ill figure this out later
        }
    }
}
