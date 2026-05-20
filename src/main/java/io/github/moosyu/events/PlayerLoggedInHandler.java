package io.github.moosyu.events;

import io.github.moosyu.attachments.PlayerStatsAttachment;
import io.github.moosyu.registers.AttributesRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import static io.github.moosyu.NNO.MODID;
import static io.github.moosyu.registers.AttachmentRegistry.PLAYER_STATS;

public class PlayerLoggedInHandler {
    @EventBusSubscriber(modid = MODID)
    public static class EventHandler {
        @SubscribeEvent
        public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
            if (event.getEntity() instanceof ServerPlayer player) {
                PlayerStatsAttachment stats = player.getData(PLAYER_STATS.get());
                player.syncData(PLAYER_STATS.get());
            }
        }
    }
}
