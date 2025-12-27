package cn.nm.lms.carpetlmsaddition.rules.enchantedgoldenapplerecipe

import cn.nm.lms.carpetlmsaddition.CarpetLMSAdditionRecipes
import cn.nm.lms.carpetlmsaddition.lib.recipe.ShapedRecipe
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.CraftingBookCategory
import net.minecraft.world.item.crafting.CustomRecipe
import net.minecraft.world.item.crafting.RecipeSerializer

class CraftableEnchantedGoldenApple :
    ShapedRecipe(
        category = CraftingBookCategory.MISC,
        enabled = { EnchantedGoldenAppleRecipe.enchantedGoldenAppleRecipe },
        width = 3,
        height = 3,
        key =
            listOf(
                Items.GOLD_BLOCK,
                Items.GOLD_BLOCK,
                Items.GOLD_BLOCK,
                Items.GOLD_BLOCK,
                Items.GOLDEN_APPLE,
                Items.GOLD_BLOCK,
                Items.GOLD_BLOCK,
                Items.GOLD_BLOCK,
                Items.GOLD_BLOCK,
            ),
        resultItem = Items.ENCHANTED_GOLDEN_APPLE,
    ) {
    override val serializer0: RecipeSerializer<out CustomRecipe> =
        CarpetLMSAdditionRecipes.CRAFTABLE_ENCHANTED_GOLDEN_APPLE
}
