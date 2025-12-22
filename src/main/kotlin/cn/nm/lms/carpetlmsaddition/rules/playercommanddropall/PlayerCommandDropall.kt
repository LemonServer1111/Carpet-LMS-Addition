package cn.nm.lms.carpetlmsaddition.rules.playercommanddropall

import carpet.api.settings.Rule
import carpet.api.settings.RuleCategory
import carpet.api.settings.Validators
import cn.nm.lms.carpetlmsaddition.rules.LMSRuleCategory

object PlayerCommandDropall {
    @Rule(
        categories = [LMSRuleCategory.LMS, RuleCategory.SURVIVAL, RuleCategory.COMMAND],
        validators = [Validators.CommandLevel::class],
    )
    @JvmField
    var playerCommandDropall: String = "false"
}
