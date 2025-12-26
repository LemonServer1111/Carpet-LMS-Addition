package cn.nm.lms.carpetlmsaddition.rules.spongerecipe

import cn.nm.lms.carpetlmsaddition.CarpetLMSAdditionRecipes
import cn.nm.lms.carpetlmsaddition.lib.recipe.ShapedRecipe
import net.minecraft.item.Items
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.SpecialCraftingRecipe
import net.minecraft.recipe.book.CraftingRecipeCategory

class CraftableSponge :
    ShapedRecipe(
        category = CraftingRecipeCategory.MISC,
        enabled = { SpongeRecipe.spongeRecipe },
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
        CarpetLMSAdditionRecipes.CRAFTABLE_SPONGE
}
