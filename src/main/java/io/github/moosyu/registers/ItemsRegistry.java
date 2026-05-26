package io.github.moosyu.registers;

import io.github.moosyu.attributes.ModAttributes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static io.github.moosyu.NNO.MODID;
import static io.github.moosyu.registers.ArmorMaterialRegister.LEAFLET_ARMOR_MATERIAL;
import static io.github.moosyu.registers.BlocksRegistry.EXAMPLE_BLOCK;

public class ItemsRegistry {
    // Create a Deferred Register to hold Items which will all be registered under the "nno" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    // Creates a new BlockItem with the id "nno:example_block", combining the namespace and path
    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", EXAMPLE_BLOCK);

    public static final DeferredItem<AxeItem> MERCENARY_AXE = ITEMS.register("mercenary_axe", () -> new AxeItem(Tiers.IRON, new Item.Properties().attributes(
                    ItemAttributeModifiers.builder().add(ModAttributes.DAMAGE.holder,
                            new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MODID, "mercenary_axe_damage"), 70, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.MAINHAND).add(ModAttributes.STRENGTH.holder,
                            new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MODID, "mercenary_axe_strength"), 20, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.MAINHAND
                    ).build()
            ))
    );
    // todo: make it so the foraging fortune only applies in certain areas (the park, not the galatea)
    public static final DeferredItem<AxeItem> TREECAPITATOR = ITEMS.register("treecapitator", () -> new AxeItem(Tiers.GOLD, new Item.Properties().attributes(
                    ItemAttributeModifiers.builder().add(ModAttributes.SWEEP.holder,
                            new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MODID, "treecapitator_sweep"), 25, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.MAINHAND).add(ModAttributes.FORAGING_FORTUNE.holder,
                            new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MODID, "treecapitator_foraging_fortune"), 100, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.MAINHAND
                    ).build()
            ))
    );

    // todo: make it so the foraging fortune only applies in certain areas (the park, not the galatea)
    public static final DeferredItem<AxeItem> SPRUCE_AXE = ITEMS.register("spruce_axe", () -> new AxeItem(Tiers.WOOD, new Item.Properties().attributes(
                    ItemAttributeModifiers.builder().add(ModAttributes.SWEEP.holder,
                            new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MODID, "spruce_axe_sweep"), 4, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.MAINHAND).add(ModAttributes.FORAGING_FORTUNE.holder,
                            new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MODID, "spruce_axe_foraging_fortune"), 50, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.MAINHAND
                    ).build()
            ))
    );

    public static final DeferredItem<AxeItem> SERIOUSLY_DAMAGED_AXE = ITEMS.register("seriously_damaged_axe", () -> new AxeItem(Tiers.WOOD, new Item.Properties().attributes(
                    ItemAttributeModifiers.builder().add(ModAttributes.SWEEP.holder,
                            new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MODID, "seriously_damaged_axe_sweep"), 7, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.MAINHAND).add(ModAttributes.FORAGING_FORTUNE.holder,
                            new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MODID, "seriously_damaged_axe_foraging_fortune"), 5, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.MAINHAND
                    ).build()
            ))
    );

    public static final DeferredItem<AxeItem> DECENT_AXE = ITEMS.register("decent_axe", () -> new AxeItem(Tiers.WOOD, new Item.Properties().attributes(
                    ItemAttributeModifiers.builder().add(ModAttributes.SWEEP.holder,
                            new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MODID, "decent_axe_sweep"), 24, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.MAINHAND).add(ModAttributes.FORAGING_FORTUNE.holder,
                            new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MODID, "decent_axe_foraging_fortune"), 5, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.MAINHAND
                    ).build()
            ))
    );

    public static final DeferredItem<AxeItem> FIG_HEW = ITEMS.register("fig_hew", () -> new AxeItem(Tiers.WOOD, new Item.Properties().attributes(
                    ItemAttributeModifiers.builder().add(ModAttributes.SWEEP.holder,
                            new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MODID, "fig_hew_sweep"), 7, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.MAINHAND).add(ModAttributes.FORAGING_FORTUNE.holder,
                            new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MODID, "fig_hew_foraging_fortune"), 20, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.MAINHAND
                    ).build()
            ))
    );

    public static final DeferredItem<AxeItem> FIGSTONE_SPLITTER = ITEMS.register("figstone_splitter", () -> new AxeItem(Tiers.WOOD, new Item.Properties().attributes(
                    ItemAttributeModifiers.builder().add(ModAttributes.SWEEP.holder,
                            new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MODID, "figstone_splitter_sweep"), 24, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.MAINHAND).add(ModAttributes.FORAGING_FORTUNE.holder,
                            new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MODID, "figstone_splitter_foraging_fortune"), 35, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.MAINHAND
                    ).build()
            ))
    );

    public static final DeferredItem<Item> LEAFLET_HELMET = ITEMS.registerItem("leaflet_helmet", props -> new ArmorItem(
            LEAFLET_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().attributes(
            ItemAttributeModifiers.builder().add(ModAttributes.HEALTH.holder,
            new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MODID, "leaflet_helmet_health"), 70, AttributeModifier.Operation.ADD_VALUE),
            EquipmentSlotGroup.HEAD).add(ModAttributes.FORAGING_FORTUNE.holder,
            new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MODID, "leaflet_helmet_foraging_fortune"), 3, AttributeModifier.Operation.ADD_VALUE),
            EquipmentSlotGroup.HEAD).build()))
    );

    public static final DeferredItem<Item> LEAFLET_CHESTPLATE = ITEMS.registerItem("leaflet_chestplate", props -> new ArmorItem(
            LEAFLET_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().attributes(
            ItemAttributeModifiers.builder().add(ModAttributes.HEALTH.holder,
                    new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MODID, "leaflet_chestplate_health"), 80, AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.CHEST).add(ModAttributes.FORAGING_FORTUNE.holder,
                    new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MODID, "leaflet_chestplate_foraging_fortune"), 3, AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.CHEST).build()))
    );

    public static final DeferredItem<Item> LEAFLET_LEGGINGS = ITEMS.registerItem("leaflet_leggings", props -> new ArmorItem(
            LEAFLET_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().attributes(
            ItemAttributeModifiers.builder().add(ModAttributes.HEALTH.holder,
                    new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MODID, "leaflet_leggings_health"), 20, AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.LEGS).add(ModAttributes.FORAGING_FORTUNE.holder,
                    new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MODID, "leaflet_leggings_foraging_fortune"), 3, AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.LEGS).build()))
    );

    public static final DeferredItem<Item> LEAFLET_BOOTS = ITEMS.registerItem("leaflet_boots", props -> new ArmorItem(
            LEAFLET_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().attributes(
            ItemAttributeModifiers.builder().add(ModAttributes.HEALTH.holder,
                    new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MODID, "leaflet_boots_health"), 25, AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.FEET).add(ModAttributes.FORAGING_FORTUNE.holder,
                    new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MODID, "leaflet_boots_foraging_fortune"), 3, AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.FEET).build()))
    );
}
