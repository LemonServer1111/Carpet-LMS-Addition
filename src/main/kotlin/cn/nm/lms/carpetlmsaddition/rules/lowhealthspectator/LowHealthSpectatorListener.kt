package cn.nm.lms.carpetlmsaddition.rules.lowhealthspectator

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.world.GameMode

object LowHealthSpectatorListener {
    fun init() {
        ServerLivingEntityEvents.AFTER_DAMAGE.register(
            ServerLivingEntityEvents.AfterDamage { entity, _, _, _, _ ->
                val player = entity as? ServerPlayerEntity ?: return@AfterDamage
                val hp = player.health
                if (
                    hp > 0f &&
                    hp <= LowHealthSpectator.lowHealthSpectator.toFloat() &&
                    !player.isSpectator
                ) {
                    player.changeGameMode(GameMode.SPECTATOR)
                }
            },
        )
    }
}
