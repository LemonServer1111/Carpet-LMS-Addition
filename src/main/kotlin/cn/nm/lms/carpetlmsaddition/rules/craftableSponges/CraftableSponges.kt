package cn.nm.lms.carpetlmsaddition.rules.craftableSponges

import carpet.api.settings.Rule
import carpet.api.settings.RuleCategory
import cn.nm.lms.carpetlmsaddition.rules.LMSRuleCategory

object CraftableSponges {
    @Rule(
        categories = [LMSRuleCategory.LMS, RuleCategory.SURVIVAL],
    )
    @JvmField
    var craftableSponges: Boolean = false
}
