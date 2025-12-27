package cn.nm.lms.carpetlmsaddition.rules.commandLMS

import carpet.utils.CommandHelper
import cn.nm.lms.carpetlmsaddition.lib.PlayerConfig
import com.mojang.brigadier.arguments.StringArgumentType
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer

object CommandLMS {
    @JvmField
    val ALL_CONFIG: Map<String, Set<String>> =
        mapOf(
            "lowHealthSpectator" to setOf("true", "false"),
        )

    private fun configList(): Set<String> = ALL_CONFIG.keys

    private fun valuesOf(config: String): Set<String> = ALL_CONFIG[config] ?: emptySet()

    private fun hasConfig(config: String) = configList().contains(config)

    private fun hasValue(
        config: String,
        value: String,
    ) = hasConfig(config) && valuesOf(config).contains(value)

    private fun canUse(
        src: CommandSourceStack,
        target: ServerPlayer,
    ): Boolean {
        val self = src.player
        val isSelf = (self != null && self.uuid == target.uuid)
        val perm = if (isSelf) CommandLMSSelf.commandLMSSelf else CommandLMSOthers.commandLMSOthers
        return CommandHelper.canUseCommand(src, perm)
    }

    fun register() {
        CommandRegistrationCallback.EVENT.register(
            CommandRegistrationCallback { dispatcher, _, _ ->
                dispatcher.register(
                    Commands
                        .literal("lms")
                        .then(
                            Commands
                                .argument("player", EntityArgument.player())
                                .then(
                                    Commands
                                        .argument("config", StringArgumentType.word())
                                        .suggests { _, builder ->
                                            configList().forEach { builder.suggest(it) }
                                            builder.buildFuture()
                                        }.executes { ctx ->
                                            val src = ctx.source
                                            val target = EntityArgument.getPlayer(ctx, "player")
                                            val config = StringArgumentType.getString(ctx, "config")
                                            if (!hasConfig(config)) {
                                                src.sendFailure(Component.literal("Unknown config: $config"))
                                                return@executes 0
                                            }
                                            if (!canUse(src, target)) {
                                                src.sendFailure(Component.literal("No permission"))
                                                return@executes 0
                                            }
                                            val raw = PlayerConfig.get(target.uuid, config)
                                            src.sendSuccess(
                                                { Component.literal("[Carpet LMS Addition] $config = ${raw ?: "null"}") },
                                                false,
                                            )
                                            1
                                        }.then(
                                            Commands
                                                .argument("value", StringArgumentType.word())
                                                .suggests { ctx, builder ->
                                                    val config = StringArgumentType.getString(ctx, "config")
                                                    valuesOf(config).forEach { builder.suggest(it) }
                                                    builder.buildFuture()
                                                }.executes { ctx ->
                                                    val src = ctx.source
                                                    val target = EntityArgument.getPlayer(ctx, "player")
                                                    val config = StringArgumentType.getString(ctx, "config")
                                                    val value = StringArgumentType.getString(ctx, "value")
                                                    if (!hasValue(config, value)) {
                                                        src.sendFailure(Component.literal("Unknown config or value: $config $value"))
                                                        return@executes 0
                                                    }
                                                    if (!canUse(src, target)) {
                                                        src.sendFailure(Component.literal("No permission"))
                                                        return@executes 0
                                                    }
                                                    PlayerConfig.set(target.uuid, config, value)
                                                    src.sendSuccess(
                                                        { Component.literal("[Carpet LMS Addition] $config = $value") },
                                                        false,
                                                    )
                                                    1
                                                },
                                        ),
                                ),
                        ),
                )
            },
        )
    }
}
