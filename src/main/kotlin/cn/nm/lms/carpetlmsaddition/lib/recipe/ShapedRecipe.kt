package cn.nm.lms.carpetlmsaddition.lib.recipe

import net.minecraft.core.HolderLookup
import net.minecraft.core.NonNullList
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.CraftingBookCategory
import net.minecraft.world.item.crafting.CraftingInput
import net.minecraft.world.item.crafting.CustomRecipe
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.PlacementInfo
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.item.crafting.display.RecipeDisplay
import net.minecraft.world.item.crafting.display.ShapedCraftingRecipeDisplay
import net.minecraft.world.item.crafting.display.SlotDisplay
import net.minecraft.world.level.Level
import java.util.Optional

abstract class ShapedRecipe(
    category: CraftingBookCategory,
    private val enabled: () -> Boolean,
    private val width: Int,
    private val height: Int,
    private val key: List<Item?>,
    private val resultItem: Item,
    private val resultCount: Int = 1,
    private val remainder: (CraftingInput) -> NonNullList<ItemStack> =
        { input -> NonNullList.withSize(input.size(), ItemStack.EMPTY) },
) : CustomRecipe(category) {
    override fun matches(
        input: CraftingInput,
        world: Level,
    ): Boolean {
        if (!enabled()) return false
        if (input.width() != width || input.height() != height) return false

        for (y in 0 until input.height()) {
            for (x in 0 until input.width()) {
                val expect = key[y * width + x]
                val stack = input.getItem(x, y)

                if (expect == null) {
                    if (!stack.isEmpty) return false
                } else {
                    if (stack.isEmpty || !stack.`is`(expect)) return false
                }
            }
        }
        return true
    }

    override fun assemble(
        recipeInput: CraftingInput,
        registries: HolderLookup.Provider,
    ): ItemStack = ItemStack(resultItem, resultCount).also { it.damageValue = 0 }

    override fun getRemainingItems(input: CraftingInput): NonNullList<ItemStack> =
        if (enabled()) {
            remainder(input)
        } else {
            NonNullList.withSize(input.size(), ItemStack.EMPTY)
        }

    override fun isSpecial(): Boolean = false

    override fun display(): List<RecipeDisplay> {
        if (!enabled()) return emptyList()

        val empty = SlotDisplay.Empty.INSTANCE
        val ingredients =
            key.map {
                if (it == null) empty else SlotDisplay.ItemSlotDisplay(it)
            }

        return listOf(
            ShapedCraftingRecipeDisplay(
                width,
                height,
                ingredients,
                SlotDisplay.ItemSlotDisplay(resultItem),
                SlotDisplay.ItemSlotDisplay(Items.CRAFTING_TABLE),
            ),
        )
    }

    override fun placementInfo(): PlacementInfo {
        if (!enabled()) return PlacementInfo.NOT_PLACEABLE
        val slots =
            key.map {
                if (it == null) {
                    Optional.empty()
                } else {
                    Optional.of(Ingredient.of(it))
                }
            }
        return PlacementInfo.createFromOptionals(slots)
    }

    abstract val serializer0: RecipeSerializer<out CustomRecipe>

    override fun getSerializer(): RecipeSerializer<out CustomRecipe> = serializer0
}
