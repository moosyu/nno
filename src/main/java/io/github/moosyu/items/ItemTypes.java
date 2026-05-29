package io.github.moosyu.items;

import com.mojang.serialization.Codec;

public enum ItemTypes {
    ITEM,
    SWORD,
    AXE,
    BATTLE_AXE,
    BOW,
    HELMET,
    CHESTPLATE,
    LEGGINGS,
    BOOTS,
    STAFF;

    private final String;

    RarityTypes(int color) {
        this.color = color;
    }

    public static final Codec<ItemTypes> CODEC = Codec.STRING.xmap(ItemTypes::valueOf, ItemTypes::name);
}
