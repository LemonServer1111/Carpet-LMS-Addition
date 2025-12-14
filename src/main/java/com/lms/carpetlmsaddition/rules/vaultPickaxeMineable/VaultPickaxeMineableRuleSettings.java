package com.lms.carpetlmsaddition.rules.vaultPickaxeMineable;

import carpet.api.settings.Rule;
import com.lms.carpetlmsaddition.lib.RuleSupport;

import static carpet.api.settings.RuleCategory.SURVIVAL;

public final class VaultPickaxeMineableRuleSettings {
    @Rule(categories = {RuleSupport.LMS, SURVIVAL})
    public static boolean vaultPickaxeMineable = false;

    private VaultPickaxeMineableRuleSettings() {
    }
}
