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
package cn.nm.lms.carpetlmsaddition

import cn.nm.lms.carpetlmsaddition.rules.elytrarecipe.CraftableElytra
import cn.nm.lms.carpetlmsaddition.rules.enchantedgoldenapplerecipe.CraftableEnchantedGoldenApple
import cn.nm.lms.carpetlmsaddition.rules.spongerecipe.CraftableSponge
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.crafting.CustomRecipe.Serializer

object CarpetLMSAdditionRecipes {
    @JvmField
    val CRAFTABLE_ELYTRA: Serializer<CraftableElytra> =
        Serializer { CraftableElytra() }
    val CRAFTABLE_ENCHANTED_GOLDEN_APPLE: Serializer<CraftableEnchantedGoldenApple> =
        Serializer { CraftableEnchantedGoldenApple() }
    val CRAFTABLE_SPONGE: Serializer<CraftableSponge> =
        Serializer { CraftableSponge() }

    fun register() {
        mapOf(
            "craftableelytra" to CRAFTABLE_ELYTRA,
            "craftableenchantedgoldenapple" to CRAFTABLE_ENCHANTED_GOLDEN_APPLE,
            "craftablesponge" to CRAFTABLE_SPONGE,
        ).forEach { (path, serializer) ->
            Registry.register(
                BuiltInRegistries.RECIPE_SERIALIZER,
                ResourceLocation.fromNamespaceAndPath(CarpetLMSAdditionMod.MOD_ID, path),
                serializer,
            )
        }
    }
}
