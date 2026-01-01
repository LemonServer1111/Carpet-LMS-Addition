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

/*
TODO: Rewrite this file
 */

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.yaml.snakeyaml.LoaderOptions
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.SafeConstructor
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.Collections
import java.util.concurrent.ConcurrentHashMap

object CarpetLMSAdditionTranslations {
    private val LOGGER: Logger =
        LoggerFactory.getLogger(CarpetLMSAdditionTranslations::class.java)

    private val YAML: Yaml =
        Yaml(SafeConstructor(LoaderOptions()))

    private val CACHE: MutableMap<String, Map<String, String>> =
        ConcurrentHashMap()

    fun getTranslation(lang: String): Map<String, String> = CACHE.computeIfAbsent(lang) { loadTranslation(it) }

    private fun loadTranslation(lang: String): Map<String, String> {
        val path = "assets/carpetlmsaddition/lang/%s.yml".format(lang)

        try {
            val stream: InputStream? =
                CarpetLMSAdditionTranslations::class.java.classLoader.getResourceAsStream(path)

            val reader: InputStreamReader? =
                if (stream == null) {
                    null
                } else {
                    InputStreamReader(stream, StandardCharsets.UTF_8)
                }

            reader.use {
                if (it == null) {
                    return Collections.emptyMap()
                }

                val data: Any? = YAML.load(it)
                if (data !is Map<*, *>) {
                    return Collections.emptyMap()
                }

                val flat: MutableMap<String, String> = HashMap()
                flatten(data, "", flat)
                return flat
            }
        } catch (e: Exception) {
            LOGGER.warn("Failed to load translations for {}", lang, e)
            return Collections.emptyMap()
        }
    }

    private fun flatten(
        node: Any?,
        prefix: String,
        out: MutableMap<String, String>,
    ) {
        if (node is Map<*, *>) {
            for ((k, v) in node) {
                val key = k as? String ?: continue
                val childPrefix =
                    if (prefix.isEmpty()) key else "$prefix.$key"
                flatten(v, childPrefix, out)
            }
            return
        }

        if (node is List<*>) {
            for (i in node.indices) {
                val childPrefix =
                    if (prefix.isEmpty()) i.toString() else "$prefix.$i"
                flatten(node[i], childPrefix, out)
            }
            return
        }

        if (prefix.isNotEmpty() && node != null) {
            out[prefix] = node.toString()
        }
    }
}
