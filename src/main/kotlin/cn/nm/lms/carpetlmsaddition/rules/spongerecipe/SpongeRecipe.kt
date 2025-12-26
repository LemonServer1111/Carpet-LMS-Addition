package cn.nm.lms.carpetlmsaddition.rules.spongerecipe

import carpet.api.settings.Rule
import carpet.api.settings.RuleCategory
import cn.nm.lms.carpetlmsaddition.rules.LMSRuleCategory

object SpongeRecipe {
    @Rule(
        categories = [LMSRuleCategory.LMS, RuleCategory.SURVIVAL],
    )
    @JvmField
    var spongeRecipe: Boolean = false
}
