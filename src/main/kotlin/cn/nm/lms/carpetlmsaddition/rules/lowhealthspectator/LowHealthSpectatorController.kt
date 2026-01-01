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
package cn.nm.lms.carpetlmsaddition.rules.lowhealthspectator

import cn.nm.lms.carpetlmsaddition.CarpetLMSAdditionMod
import cn.nm.lms.carpetlmsaddition.lib.PlayerConfig
import cn.nm.lms.carpetlmsaddition.lib.check.CheckMod
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.level.GameType
import java.util.UUID

object LowHealthSpectatorController {
    private val cooldownMap = mutableMapOf<UUID, Long>()

    fun init() {
        ServerLivingEntityEvents.AFTER_DAMAGE.register(
            ServerLivingEntityEvents.AfterDamage { entity, _, _, _, _ ->
                val player = entity as? ServerPlayer ?: return@AfterDamage
                val world = entity.level()
                val now = world.gameTime
                val playerUUID = player.uuid
                val last = cooldownMap[playerUUID] ?: (now - LowHealthSpectatorCooldown.lowHealthSpectatorCooldown)
                val hp = player.health

                if (
                    isEnabled(playerUUID) &&
                    now - last >= LowHealthSpectatorCooldown.lowHealthSpectatorCooldown &&
                    !player.isSpectator &&
                    hp > 0f &&
                    hp <= LowHealthSpectatorThreshold.lowHealthSpectatorThreshold.toFloat()
                ) {
                    when (LowHealthSpectatorMethod.lowHealthSpectatorMethod) {
                        "vanilla" -> {
                            player.setGameMode(GameType.SPECTATOR)
                        }

                        "mcdreforged" -> {
                            CarpetLMSAdditionMod.LOGGER.info(
                                "<{}> !!spec",
                                player.name.string,
                            )
                        }

                        "carpet-org-addition" -> {
                            if (CheckMod.checkMod("carpet-org-addition", ">=1.38.0")) {
                                val server = world.server
                                val commandManager = server.commands
                                server.execute {
                                    commandManager.performPrefixedCommand(
                                        player.createCommandSourceStack(),
                                        "spectator",
                                    )
                                }
                            } else {
                                CarpetLMSAdditionMod.LOGGER.warn("Carpet Org Addition (>=1.38.0) not installed")
                            }
                        }

                        "kick" -> {
                            player.connection.disconnect(Component.nullToEmpty("Kicked by Carpet LMS Addition"))
                        }

                        else -> {
                            CarpetLMSAdditionMod.LOGGER.warn(
                                "Unknown lowHealthSpectatorMethod: {}",
                                LowHealthSpectatorMethod.lowHealthSpectatorMethod,
                            )
                        }
                    }
                    cooldownMap[player.uuid] = now
                }
            },
        )
    }

    private fun isEnabled(playerUUID: UUID): Boolean =
        when (LowHealthSpectator.lowHealthSpectator) {
            "true" -> true
            "false" -> false
            "custom" -> PlayerConfig.get(playerUUID, "lowHealthSpectator")?.toBoolean() ?: false
            else -> false
        }
}
