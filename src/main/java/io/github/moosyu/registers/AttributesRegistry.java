package io.github.moosyu.registers;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static io.github.moosyu.NNO.MODID;

public class AttributesRegistry {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, MODID);

    public static final DeferredHolder<Attribute, Attribute> SWEEP = ATTRIBUTES.register("sweep", () -> new RangedAttribute("attribute.name.nno.sweep", 0.0D, 0.0D, 1024.0D).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> FORAGING_FORTUNE = ATTRIBUTES.register("foraging_fortune", () -> new RangedAttribute("attribute.name.nno.foraging_fortune", 0.0D, 0.0D, 1024.0D).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> HEALTH = ATTRIBUTES.register("health", () -> new RangedAttribute("attribute.name.nno.health", 0.0D, 0.0D, 2147483647.0D).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> DAMAGE = ATTRIBUTES.register("damage", () -> new RangedAttribute("attribute.name.nno.damage", 0.0D, 0.0D, 2147483647.0D).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> STRENGTH = ATTRIBUTES.register("strength", () -> new RangedAttribute("attribute.name.nno.strength", 0.0D, 0.0D, 4096.0D).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> CRITICAL_DAMAGE = ATTRIBUTES.register("critical_damage", () -> new RangedAttribute("attribute.name.nno.critical_damage", 0.0D, 0.0D, 4096.0D).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> CRITICAL_CHANCE = ATTRIBUTES.register("critical_chance", () -> new RangedAttribute("attribute.name.nno.critical_chance", 0.0D, 0.0D, 2048.0D).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> MANA = ATTRIBUTES.register("mana", () -> new RangedAttribute("attribute.name.nno.mana", 0.0D, 0.0D, 131072.0D).setSyncable(true));
}