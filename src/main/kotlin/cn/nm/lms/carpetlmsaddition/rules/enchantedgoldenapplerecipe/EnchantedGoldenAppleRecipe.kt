package cn.nm.lms.carpetlmsaddition.rules.enchantedgoldenapplerecipe

import carpet.api.settings.Rule
import carpet.api.settings.RuleCategory
import cn.nm.lms.carpetlmsaddition.rules.LMSRuleCategory

object EnchantedGoldenAppleRecipe {
    @Rule(
        categories = [LMSRuleCategory.LMS, RuleCategory.SURVIVAL],
    )
    @JvmField
    var enchantedGoldenAppleRecipe: Boolean = false
}
