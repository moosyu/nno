package io.github.moosyu.sounds;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

public class ExperienceSounds {
    public static void playerExperienceSound(Player player) {
        player.playNotifySound(SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.NEUTRAL, 0.7f, 2.0f);
    }
}
