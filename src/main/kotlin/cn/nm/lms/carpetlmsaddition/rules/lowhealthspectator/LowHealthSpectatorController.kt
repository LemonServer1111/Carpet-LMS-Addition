package cn.nm.lms.carpetlmsaddition.rules.lowhealthspectator

import cn.nm.lms.carpetlmsaddition.CarpetLMSAdditionMod
import cn.nm.lms.carpetlmsaddition.lib.PlayerConfig
import cn.nm.lms.carpetlmsaddition.lib.check.CheckMod
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
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
                                player.displayName?.string,
                            )
                        }

                        "carpet-org-addition" -> {
                            if (CheckMod.checkMod("carpet-org-addition", ">=1.38.0")) {
                                val server = (world as ServerLevel).server
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
