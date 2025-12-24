package cn.nm.lms.carpetlmsaddition

import cn.nm.lms.carpetlmsaddition.rules.craftableSponges.CraftableSpongesRecipe
import cn.nm.lms.carpetlmsaddition.rules.renewableEnchantedGoldenApples.RenewableEnchantedGoldenApplesRecipe
import cn.nm.lms.carpetlmsaddition.rules.renewableelytra.RenewableElytraRecipe
import net.minecraft.recipe.SpecialCraftingRecipe.SpecialRecipeSerializer
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object CarpetLMSAdditionRecipes {
    @JvmField
    val CRAFTABLE_SPONGES: SpecialRecipeSerializer<CraftableSpongesRecipe> =
        SpecialRecipeSerializer { CraftableSpongesRecipe() }
    val RENEWABLE_ELYTRA: SpecialRecipeSerializer<RenewableElytraRecipe> =
        SpecialRecipeSerializer { RenewableElytraRecipe() }
    val RENEWABLE_ENCHANTED_GOLDEN_APPLES: SpecialRecipeSerializer<RenewableEnchantedGoldenApplesRecipe> =
        SpecialRecipeSerializer { RenewableEnchantedGoldenApplesRecipe() }

    fun register() {
        mapOf(
            "renewableelytra" to RENEWABLE_ELYTRA,
            "renewableenchantedgoldenapples" to RENEWABLE_ENCHANTED_GOLDEN_APPLES,
            "craftablesponges" to CRAFTABLE_SPONGES,
        ).forEach { (path, serializer) ->
            Registry.register(
                Registries.RECIPE_SERIALIZER,
                Identifier.of(CarpetLMSAdditionMod.MOD_ID, path),
                serializer,
            )
        }
    }
}
