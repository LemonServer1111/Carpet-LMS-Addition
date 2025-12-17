package cn.nm.lms.carpetlmsaddition.rules

import carpet.api.settings.Rule

object ExampleRule {
    @Rule(
        categories = ["TEST"],
    )
    @JvmField
    var exampleRule: Boolean = false
}
