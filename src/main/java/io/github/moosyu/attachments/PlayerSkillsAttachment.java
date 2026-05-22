package io.github.moosyu.attachments;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

import java.util.HashMap;
import java.util.Map;

public class PlayerSkillsAttachment {
    private static final int[] SKILL_LEVEL_TABLE = {50, 175, 375, 675, 1175, 1925, 2925, 4425, 6425, 9925};
    private final float[] skillExp = new float[Skill.values().length];
    private float playerExp;

    // you ever write a word so much it doesnt even feel real anymore
    public enum Skill {
        MINING,
        COMBAT,
        FARMING,
        FISHING,
        FORAGING
    }

    public PlayerSkillsAttachment(float miningExp, float combatExp, float farmingExp, float fishingExp, float foragingExp, float playerExp) {
        skillExp[Skill.MINING.ordinal()] = miningExp;
        skillExp[Skill.COMBAT.ordinal()] = combatExp;
        skillExp[Skill.FARMING.ordinal()] = farmingExp;
        skillExp[Skill.FISHING.ordinal()] = fishingExp;
        skillExp[Skill.FORAGING.ordinal()] = foragingExp;
        this.playerExp = playerExp;
    }

    public void transferSkills(PlayerSkillsAttachment other) {
        System.arraycopy(other.skillExp, 0, this.skillExp, 0, skillExp.length);
        this.playerExp = other.playerExp;
    }

    public float getExp(Skill skill) {
        return skillExp[skill.ordinal()];
    }

    public void addExp(Skill skill, float amount) {
        skillExp[skill.ordinal()] += amount;
    }

    public int getLevel(float exp) {
        int level = 0;
        while (level < SKILL_LEVEL_TABLE.length && exp >= SKILL_LEVEL_TABLE[level]) {
            level++;
        }
        return level;
    }

    public static final Codec<PlayerSkillsAttachment> RECORD_CODEC = Codec.unboundedMap(Codec.STRING, Codec.FLOAT).xmap(map -> {
                PlayerSkillsAttachment obj = new PlayerSkillsAttachment(0, 0, 0, 0, 0, 0);
                for (var entry : map.entrySet()) {
                    Skill skill = Skill.valueOf(entry.getKey());
                    obj.skillExp[skill.ordinal()] = entry.getValue();
                }
                return obj;
            },
            obj -> {
                Map<String, Float> map = new HashMap<>();
                for (Skill skill : Skill.values()) {
                    map.put(skill.name(), obj.getExp(skill));
                }
                return map;
            }
    );

    // remember to properly sync this
    public static final StreamCodec<RegistryFriendlyByteBuf, PlayerSkillsAttachment> STREAM_CODEC = StreamCodec.of((buf, obj) -> {
                for (Skill skill : Skill.values()) {
                    buf.writeFloat(obj.getExp(skill));
                }
                buf.writeFloat(obj.playerExp);
            },
            buf -> {
                float mining = buf.readFloat();
                float combat = buf.readFloat();
                float farming = buf.readFloat();
                float fishing = buf.readFloat();
                float foraging = buf.readFloat();
                float playerExp = buf.readFloat();
                return new PlayerSkillsAttachment(mining, combat, farming, fishing, foraging, playerExp);
            }
    );
}