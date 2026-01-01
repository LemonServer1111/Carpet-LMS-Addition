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
package cn.nm.lms.carpetlmsaddition.lib

import cn.nm.lms.carpetlmsaddition.CarpetLMSAdditionMod
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import net.fabricmc.loader.api.FabricLoader
import java.nio.file.Files
import java.nio.file.Path
import java.util.UUID

object PlayerConfig {
    private val gson: Gson = GsonBuilder().setPrettyPrinting().create()
    private val file: Path = FabricLoader.getInstance().configDir.resolve("${CarpetLMSAdditionMod.MOD_ID}.json")
    private const val PLAYER_CONFIG_KEY = "playerConfig"
    private var root: JsonObject? = null

    fun get(
        playerUUID: UUID,
        configName: String,
    ): String? {
        val r = ensureLoaded()

        val allConfig = r.getAsJsonObject(PLAYER_CONFIG_KEY) ?: return null
        val perConfig = allConfig.getAsJsonObject(configName) ?: return null

        return perConfig.get(playerUUID.toString())?.asString
    }

    fun set(
        playerUUID: UUID,
        configName: String,
        value: String,
    ) {
        val r = ensureLoaded()

        val allConfig = r.getAsJsonObject(PLAYER_CONFIG_KEY) ?: JsonObject()
        val perConfig = allConfig.getAsJsonObject(configName) ?: JsonObject()

        perConfig.addProperty(playerUUID.toString(), value)
        allConfig.add(configName, perConfig)
        r.add(PLAYER_CONFIG_KEY, allConfig)

        Files.createDirectories(file.parent)
        Files.newBufferedWriter(file).use {
            gson.toJson(r, it)
        }
    }

    private fun ensureLoaded(): JsonObject {
        if (root != null) return root!!
        root =
            if (Files.exists(file)) {
                Files.newBufferedReader(file).use {
                    JsonParser.parseReader(it).asJsonObject
                }
            } else {
                JsonObject()
            }
        return root!!
    }
}
