package cn.nm.lms.carpetlmsaddition

import cn.nm.lms.carpetlmsaddition.rules.elytrarecipe.CraftableElytra
import cn.nm.lms.carpetlmsaddition.rules.enchantedgoldenapplerecipe.CraftableEnchantedGoldenApple
import cn.nm.lms.carpetlmsaddition.rules.spongerecipe.CraftableSponge
import net.minecraft.recipe.SpecialCraftingRecipe.SpecialRecipeSerializer
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object CarpetLMSAdditionRecipes {
    @JvmField
    val CRAFTABLE_ELYTRA: SpecialRecipeSerializer<CraftableElytra> =
        SpecialRecipeSerializer { CraftableElytra() }
    val CRAFTABLE_ENCHANTED_GOLDEN_APPLE: SpecialRecipeSerializer<CraftableEnchantedGoldenApple> =
        SpecialRecipeSerializer { CraftableEnchantedGoldenApple() }
    val CRAFTABLE_SPONGE: SpecialRecipeSerializer<CraftableSponge> =
        SpecialRecipeSerializer { CraftableSponge() }

    fun register() {
        mapOf(
            "craftableelytra" to CRAFTABLE_ELYTRA,
            "craftableenchantedgoldenapple" to CRAFTABLE_ENCHANTED_GOLDEN_APPLE,
            "craftablesponge" to CRAFTABLE_SPONGE,
        ).forEach { (path, serializer) ->
            Registry.register(
                Registries.RECIPE_SERIALIZER,
                Identifier.of(CarpetLMSAdditionMod.MOD_ID, path),
                serializer,
            )
        }
    }
}
