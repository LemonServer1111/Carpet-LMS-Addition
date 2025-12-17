package cn.nm.lms.carpetlmsaddition.rules

import carpet.api.settings.Rule
import carpet.api.settings.RuleCategory
import carpet.api.settings.Validators

object AllayHealInterval {
    @Rule(
        categories = [LMSRuleCategory.LMS, RuleCategory.SURVIVAL],
        validators = [Validators.NonNegativeNumber::class],
    )
    @JvmField
    var allayHealInterval: Int = 10
}
