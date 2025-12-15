package com.lms.carpetlmsaddition.rules.vaultPickaxeMineable;

import static carpet.api.settings.RuleCategory.SURVIVAL;

import carpet.api.settings.Rule;
import com.lms.carpetlmsaddition.lib.RuleSupport;

public final class VaultPickaxeMineableRuleSettings {
  @Rule(categories = {RuleSupport.LMS, SURVIVAL})
  public static boolean vaultPickaxeMineable = false;

  private VaultPickaxeMineableRuleSettings() {}
}
