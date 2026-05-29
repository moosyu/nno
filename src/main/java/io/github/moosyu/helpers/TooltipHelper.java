package io.github.moosyu.helpers;

import io.github.moosyu.attributes.ModAttributes;
import io.github.moosyu.items.ItemTypes;
import io.github.moosyu.rarities.RarityTypes;
import io.github.moosyu.registers.DataComponentRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.List;

public class TooltipHelper {
    public static void displayHoverText(ItemStack stack, List<Component> tooltipComponents) {
        RarityTypes itemRarity = stack.get(DataComponentRegistry.RARITY.get());
        ItemTypes itemType = stack.get(DataComponentRegistry.ITEM_TYPE.get());
        if (itemRarity == null) itemRarity = RarityTypes.COMMON;
        if (itemType == null) itemType = ItemTypes.ITEM;

        tooltipComponents.add(Component.translatable(stack.getDescriptionId()).withColor(itemRarity.getColor()));
        ItemAttributeModifiers modifiers = stack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);

        for (ItemAttributeModifiers.Entry entry : modifiers.modifiers()) {
            Holder<Attribute> attributeHolder = entry.attribute();
            AttributeModifier modifier = entry.modifier();

            tooltipComponents.add(Component.translatable(attributeHolder.value().getDescriptionId()).append(Component.literal(": ")).withStyle(ChatFormatting.GRAY)
                    .append(Component.literal("+" + (int) modifier.amount()).withStyle((ModAttributes.fromAttribute(attributeHolder.value()).offensive) ? ChatFormatting.RED : ChatFormatting.GREEN))
            );
        }

        tooltipComponents.add(Component.empty());
        tooltipComponents.add(Component.literal(Component.translatable("rarity.nno." + itemRarity.name().toLowerCase()).getString().toUpperCase()).append(Component.literal(" ")).append(Component.literal(itemType.name())).withColor(itemRarity.getColor()).withStyle(ChatFormatting.BOLD));
    }
}
