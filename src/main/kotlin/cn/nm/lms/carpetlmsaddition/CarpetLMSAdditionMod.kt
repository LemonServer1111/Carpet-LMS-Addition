/*
 * Copyright (C) 2025  Carpet-LMS-Addition contributors
 * https://github.com/Citrus-Union/Carpet-LMS-Addition

 * Carpet LMS Addition is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.

 * Carpet LMS Addition is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with Carpet LMS Addition.  If not, see <https://www.gnu.org/licenses/>.
 */
package cn.nm.lms.carpetlmsaddition

import carpet.CarpetExtension
import carpet.CarpetServer
import cn.nm.lms.carpetlmsaddition.rules.RulesBootstrap
import net.fabricmc.api.ModInitializer
import net.fabricmc.loader.api.FabricLoader
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object CarpetLMSAdditionMod : ModInitializer, CarpetExtension {
    const val MOD_ID: String = "carpet-lms-addition"

    private val MOD_CONTAINER by lazy(LazyThreadSafetyMode.PUBLICATION) {
        FabricLoader
            .getInstance()
            .getModContainer(MOD_ID)
            .orElseThrow { RuntimeException("Mod not found: $MOD_ID") }
    }

    @JvmStatic
    val version: String
        get() = MOD_CONTAINER.metadata.version.friendlyString

    @JvmStatic
    val MOD_NAME: String
        get() = MOD_CONTAINER.metadata.name

    @JvmField
    val LOGGER: Logger = LogManager.getLogger(MOD_NAME)

    override fun onInitialize() {
        LOGGER.info("$MOD_NAME version $version")
        CarpetServer.manageExtension(this)
        CarpetLMSAdditionInit.initAll()
    }

    override fun version(): String = version

    override fun onGameStarted() {
        RulesBootstrap.registerAll()
    }

    override fun canHasTranslations(lang: String): Map<String, String> = CarpetLMSAdditionTranslations.translations(lang)
}
