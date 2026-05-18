package io.github.moosyu.events;

import io.github.moosyu.registers.AttributesRegistry;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import static io.github.moosyu.NNO.MODID;

public class LivingDamageHandler {
    @EventBusSubscriber(modid = MODID)
    public static class EventHandler {
        @SubscribeEvent
        public static void onLivingDamage(LivingDamageEvent.Pre event) {
            // todo: this currently blows up my computers and doesnt work so maybe i should fix that
            if (event.getEntity() instanceof Player player) {
                // disabling the default damage as there's it cant be cancelled
                event.setNewDamage(0.0f);

                double mobDamage = event.getEntity().getAttribute(AttributesRegistry.DAMAGE).getBaseValue();
                double playerHealth = player.getAttribute(AttributesRegistry.HEALTH).getBaseValue();

                if (playerHealth - mobDamage > 0.0d) {
                    player.hurt(event.getSource(), 0.0f);
                    player.getAttribute(AttributesRegistry.HEALTH).setBaseValue(playerHealth - mobDamage);
                } else {
                    player.kill();
                }
            } else if (event.getSource().is(DamageTypeTags.IS_FIRE)) {
                // seems to be required to allow players to damage enemies on fire
                // todo: make it so this doesnt go batshit when the entity is in lava
                event.getEntity().invulnerableTime = 0;
            }
        }
    }
}
