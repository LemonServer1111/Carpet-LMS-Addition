package cn.nm.lms.carpetlmsaddition.rules

import carpet.api.settings.Rule
import carpet.api.settings.RuleCategory

object ZombifiedPiglinSpawnFix {
    @Rule(
        categories = [LMSRuleCategory.LMS, RuleCategory.SURVIVAL, RuleCategory.BUGFIX],
    )
    @JvmField
    var zombifiedPiglinSpawnFix: Boolean = false
}
