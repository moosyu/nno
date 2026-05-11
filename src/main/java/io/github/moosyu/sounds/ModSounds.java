package io.github.moosyu.sounds;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import java.util.Random;

public class ModSounds {
    public static void playerExperienceSound(Player player) {
        Random rand = new Random();
        // Not 100% about this just yet. Slightly randomising the pitch helped, but I may add some random delay to the pitch sometimes to emulate
        // Hypixel's shitting 5tps servers?
        player.playNotifySound(SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.NEUTRAL, 0.1f, 2.0f - rand.nextFloat(0.3f));

    }
}
