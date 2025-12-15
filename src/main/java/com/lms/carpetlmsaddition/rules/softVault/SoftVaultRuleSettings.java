package com.lms.carpetlmsaddition.rules.softVault;

import static carpet.api.settings.RuleCategory.SURVIVAL;

import carpet.api.settings.Rule;
import com.lms.carpetlmsaddition.lib.RuleSupport;

public class SoftVaultRuleSettings {
  @Rule(categories = {RuleSupport.LMS, SURVIVAL})
  public static boolean softVault = false;

  private SoftVaultRuleSettings() {}
}
