package cn.nm.lms.carpetlmsaddition.rules

import carpet.CarpetServer

object RulesBootstrap {
    fun registerAll() {
        listOf(
            ExampleRule::class.java,
        ).forEach { settingsClass ->
            CarpetServer.settingsManager.parseSettingsClass(settingsClass)
        }
    }
}
