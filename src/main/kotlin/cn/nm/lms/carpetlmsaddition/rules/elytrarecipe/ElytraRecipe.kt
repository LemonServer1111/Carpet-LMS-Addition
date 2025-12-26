package cn.nm.lms.carpetlmsaddition.rules.elytrarecipe

import carpet.api.settings.Rule
import carpet.api.settings.RuleCategory
import cn.nm.lms.carpetlmsaddition.rules.LMSRuleCategory

object ElytraRecipe {
    @Rule(
        categories = [LMSRuleCategory.LMS, RuleCategory.SURVIVAL],
    )
    @JvmField
    var elytraRecipe: Boolean = false
}
