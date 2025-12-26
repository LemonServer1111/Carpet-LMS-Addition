package cn.nm.lms.carpetlmsaddition.rules.enchantedgoldenapplerecipe

import cn.nm.lms.carpetlmsaddition.CarpetLMSAdditionRecipes
import cn.nm.lms.carpetlmsaddition.lib.recipe.ShapedRecipe
import net.minecraft.item.Items
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.SpecialCraftingRecipe
import net.minecraft.recipe.book.CraftingRecipeCategory

class CraftableEnchantedGoldenApple :
    ShapedRecipe(
        category = CraftingRecipeCategory.MISC,
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
    override val serializer0: RecipeSerializer<out SpecialCraftingRecipe> =
        CarpetLMSAdditionRecipes.CRAFTABLE_ENCHANTED_GOLDEN_APPLE
}
