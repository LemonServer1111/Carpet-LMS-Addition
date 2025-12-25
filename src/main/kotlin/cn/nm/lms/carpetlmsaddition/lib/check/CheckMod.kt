package cn.nm.lms.carpetlmsaddition.lib.check

import net.fabricmc.loader.api.FabricLoader
import net.fabricmc.loader.api.VersionParsingException
import net.fabricmc.loader.api.metadata.version.VersionPredicate

object CheckMod {
    fun checkMod(
        modid: String,
        version: String,
    ): Boolean {
        val container =
            FabricLoader
                .getInstance()
                .getModContainer(modid)
                .orElse(null)
                ?: return false

        return try {
            val predicate = VersionPredicate.parse(version)
            predicate.test(container.metadata.version)
        } catch (_: VersionParsingException) {
            false
        }
    }
}
