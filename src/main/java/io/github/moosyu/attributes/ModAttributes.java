package io.github.moosyu.attributes;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.neoforge.registries.DeferredHolder;

public enum ModAttributes {
    HEALTH("health", "❤", 100.0, 0.0, 2147483647.0, 0xFFFC3A3A, AttributeTypes.IMPORTANT),
    STRENGTH("strength", "❁", 0.0, 0.0, 4096.0, 0xFFFC3A3A, AttributeTypes.IMPORTANT),
    CRITICAL_CHANCE("critical_chance", "☣", 30.0, 0.0, 2048.0, 0xFF3535CC, AttributeTypes.IMPORTANT),
    CRITICAL_DAMAGE("critical_damage", "☠", 50.0, 0.0, 4096.0, 0xFF3535CC, AttributeTypes.IMPORTANT),
    MANA("mana", "✎", 100.0, 0.0, 131072.0, 0xFF55D5FF, AttributeTypes.IMPORTANT),
    SWEEP("sweep", "∮", 0.0, 0.0, 1024.0, 0xFF00AA00, AttributeTypes.VISIBLE),
    FORAGING_FORTUNE("foraging_fortune", "☘", 0.0, 0.0, 1024.0, 0xFFFFAA00, AttributeTypes.IMPORTANT),
    DAMAGE("damage", 0.0, 0.0, 2147483647.0),
    HEALTH_REGEN("health_regen", "❣", 100.0, 0.0, 2048.0, 0xFFFC3A3A, AttributeTypes.VISIBLE);

    public final String id;
    public final String symbol;
    public final double def, min, max;
    public final int color;
    public final AttributeTypes type;
    public DeferredHolder<Attribute, Attribute> holder;

    ModAttributes(String id, String symbol, double def, double min, double max, int color, AttributeTypes type) {
        this.id = id;
        this.symbol = symbol;
        this.def = def;
        this.min = min;
        this.max = max;
        this.color = color;
        this.type = type;
    }

    // for attributes like damage that don't need to be displayed
    ModAttributes(String id, double def, double min, double max) {
        this(id, "", def, min, max, 0x00000000, AttributeTypes.INVISIBLE);
    }

    public String getTranslationKey() {
        return "attribute.name.nno." + id;
    }
}

