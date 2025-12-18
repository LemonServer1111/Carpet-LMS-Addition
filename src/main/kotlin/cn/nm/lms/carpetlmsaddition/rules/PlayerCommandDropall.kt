package cn.nm.lms.carpetlmsaddition.rules

import carpet.api.settings.Rule
import carpet.api.settings.RuleCategory
import carpet.api.settings.Validators

object PlayerCommandDropall {
    @Rule(
        categories = [LMSRuleCategory.LMS, RuleCategory.SURVIVAL, RuleCategory.COMMAND],
        validators = [Validators.CommandLevel::class],
    )
    @JvmField
    var playerCommandDropall: String = "false"
}
