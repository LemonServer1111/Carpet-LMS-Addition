package cn.nm.lms.carpetlmsaddition

import cn.nm.lms.carpetlmsaddition.rules.elytrarecipe.CraftableElytra
import cn.nm.lms.carpetlmsaddition.rules.enchantedgoldenapplerecipe.CraftableEnchantedGoldenApple
import cn.nm.lms.carpetlmsaddition.rules.spongerecipe.CraftableSponge
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.Identifier
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
                Identifier.fromNamespaceAndPath(CarpetLMSAdditionMod.MOD_ID, path),
                serializer,
            )
        }
    }
}
