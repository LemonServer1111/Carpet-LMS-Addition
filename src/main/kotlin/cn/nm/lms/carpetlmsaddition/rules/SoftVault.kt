package cn.nm.lms.carpetlmsaddition.rules

import carpet.api.settings.Rule
import carpet.api.settings.RuleCategory

object SoftVault {
    @Rule(
        categories = [LMSRuleCategory.LMS, RuleCategory.SURVIVAL],
    )
    @JvmField
    var softVault: Boolean = false
}
