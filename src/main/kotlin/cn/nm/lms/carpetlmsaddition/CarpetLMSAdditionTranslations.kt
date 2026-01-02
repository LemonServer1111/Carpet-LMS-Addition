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

import com.google.gson.Gson
import net.fabricmc.loader.api.FabricLoader
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.Collections

object CarpetLMSAdditionTranslations {
    private val translationStorage: MutableMap<String, MutableMap<String, String>> = linkedMapOf()

    private const val DEFAULT_LANG = "en_us"
    private const val RESOURCE_DIR = "assets/carpetlmsaddition/lang"
    private val gson = Gson()

    fun loadTranslations() {
        val languages: List<String> =
            try {
                readJson("$RESOURCE_DIR/meta/languages.json")
            } catch (e: Exception) {
                CarpetLMSAdditionMod.LOGGER.error("Failed to read language list", e)
                return
            }

        for (lang in languages) {
            translationStorage.computeIfAbsent(lang) {
                loadTranslationFile(lang).toMutableMap()
            }
        }
    }

    private fun loadTranslationFile(lang: String): Map<String, String> =
        try {
            readJson("$RESOURCE_DIR/$lang.json")
        } catch (e: Exception) {
            val msg = "Failed to load translation of language $lang"
            CarpetLMSAdditionMod.LOGGER.error(msg, e)
            if (FabricLoader.getInstance().isDevelopmentEnvironment) {
                throw RuntimeException(msg, e)
            }
            Collections.emptyMap()
        }

    private fun getTranslations(lang: String): Map<String, String> = translationStorage[lang] ?: emptyMap()

    fun translations(lang: String): Map<String, String> {
        val result = HashMap<String, String>()

        // fallback
        result.putAll(getTranslations(DEFAULT_LANG))

        if (lang != DEFAULT_LANG) {
            result.putAll(getTranslations(lang))
        }

        return result
    }

    private inline fun <reified T> readJson(path: String): T {
        val stream =
            javaClass.classLoader.getResourceAsStream(path)
                ?: throw IllegalStateException("Missing resource: $path")

        stream.use {
            InputStreamReader(it, StandardCharsets.UTF_8).use { reader ->
                return gson.fromJson(reader, T::class.java)
            }
        }
    }
}
