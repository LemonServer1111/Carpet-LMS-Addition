package cn.nm.lms.carpetlmsaddition.rules.craftableSponges

import cn.nm.lms.carpetlmsaddition.CarpetLMSAdditionRecipes
import cn.nm.lms.carpetlmsaddition.lib.recipe.ShapedRecipe
import net.minecraft.item.Items
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.SpecialCraftingRecipe
import net.minecraft.recipe.book.CraftingRecipeCategory

class CraftableSpongesRecipe :
    ShapedRecipe(
        category = CraftingRecipeCategory.MISC,
        enabled = { CraftableSponges.craftableSponges },
        width = 3,
        height = 3,
        key =
            listOf(
                Items.SLIME_BLOCK,
                Items.HAY_BLOCK,
                Items.SLIME_BLOCK,
                Items.HAY_BLOCK,
                Items.SLIME_BLOCK,
                Items.HAY_BLOCK,
                Items.SLIME_BLOCK,
                Items.HAY_BLOCK,
                Items.SLIME_BLOCK,
            ),
        resultItem = Items.SPONGE,
    ) {
    override val serializer0: RecipeSerializer<out SpecialCraftingRecipe> =
        CarpetLMSAdditionRecipes.CRAFTABLE_SPONGES
}
