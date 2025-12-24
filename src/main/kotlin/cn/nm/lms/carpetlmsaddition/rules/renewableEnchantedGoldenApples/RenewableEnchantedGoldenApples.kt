package cn.nm.lms.carpetlmsaddition.rules.renewableEnchantedGoldenApples

import carpet.api.settings.Rule
import carpet.api.settings.RuleCategory
import cn.nm.lms.carpetlmsaddition.rules.LMSRuleCategory

object RenewableEnchantedGoldenApples {
    @Rule(
        categories = [LMSRuleCategory.LMS, RuleCategory.SURVIVAL],
    )
    @JvmField
    var renewableEnchantedGoldenApples: Boolean = false
}
