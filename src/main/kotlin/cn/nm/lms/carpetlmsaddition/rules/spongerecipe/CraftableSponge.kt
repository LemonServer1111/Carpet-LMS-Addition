package cn.nm.lms.carpetlmsaddition.rules.spongerecipe

import cn.nm.lms.carpetlmsaddition.CarpetLMSAdditionRecipes
import cn.nm.lms.carpetlmsaddition.lib.recipe.ShapedRecipe
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.CraftingBookCategory
import net.minecraft.world.item.crafting.CustomRecipe
import net.minecraft.world.item.crafting.RecipeSerializer

class CraftableSponge :
    ShapedRecipe(
        category = CraftingBookCategory.MISC,
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
    override val serializer0: RecipeSerializer<out CustomRecipe> =
        CarpetLMSAdditionRecipes.CRAFTABLE_SPONGE
}
