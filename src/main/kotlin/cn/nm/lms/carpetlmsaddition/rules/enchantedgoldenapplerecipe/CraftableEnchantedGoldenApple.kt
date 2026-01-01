/*
 * Copyright (C) 2025  Carpet-LMS-Addition contributors
 * https://github.com/Citrus-Union/Carpet-LMS-Addition

 * Carpet LMS Addition is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.

 * Carpet LMS Addition is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with Carpet LMS Addition.  If not, see <https://www.gnu.org/licenses/>.
 */
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
