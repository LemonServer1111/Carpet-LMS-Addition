package cn.nm.lms.carpetlmsaddition.mixin.playercommanddropall;

import carpet.commands.PlayerCommand;
import carpet.helpers.EntityPlayerActionPack;
import carpet.utils.CommandHelper;
import cn.nm.lms.carpetlmsaddition.rules.playercommanddropall.DropAllActionExtension;
import cn.nm.lms.carpetlmsaddition.rules.playercommanddropall.PlayerCommandDropall;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import java.util.function.Consumer;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PlayerCommand.class, remap = false)
public abstract class PlayerCommandDropAllMixin {

  @Inject(method = "register", at = @At("TAIL"))
  private static void lms$registerDropAll(
      CommandDispatcher<ServerCommandSource> dispatcher,
      CommandRegistryAccess ctx,
      CallbackInfo ci) {
    CommandNode<ServerCommandSource> rootRaw = dispatcher.getRoot().getChild("player");
    if (!(rootRaw instanceof LiteralCommandNode<ServerCommandSource> root)) return;

    CommandNode<ServerCommandSource> playerArg = root.getChild("player");
    if (playerArg == null) {
      return;
    }

    if (playerArg.getChild("dropall") == null) {
      playerArg.addChild(cmd().build());
    }
  }

  @Invoker(value = "manipulate", remap = false)
  private static int manipulate(
      CommandContext<ServerCommandSource> context, Consumer<EntityPlayerActionPack> op) {
    throw new AssertionError();
  }

  @Unique
  private static LiteralArgumentBuilder<ServerCommandSource> cmd() {
    return CommandManager.literal("dropall")
        .requires(
            src -> CommandHelper.canUseCommand(src, PlayerCommandDropall.playerCommandDropall))
        .executes(PlayerCommandDropAllMixin::once)
        .then(CommandManager.literal("once").executes(PlayerCommandDropAllMixin::once))
        .then(CommandManager.literal("continuous").executes(PlayerCommandDropAllMixin::continuous))
        .then(
            CommandManager.literal("interval")
                .then(
                    CommandManager.argument("ticks", IntegerArgumentType.integer(1))
                        .executes(PlayerCommandDropAllMixin::interval)));
  }

  @Unique
  private static void markAndStart(
      EntityPlayerActionPack pack, EntityPlayerActionPack.Action action) {
    ((DropAllActionExtension) action).carpetlmsaddition$setDropAll(true);
    pack.start(EntityPlayerActionPack.ActionType.DROP_STACK, action);
  }

  @Unique
  private static int once(CommandContext<ServerCommandSource> ctx) {
    return manipulate(ctx, pack -> pack.drop(-2, true));
  }

  @Unique
  private static int continuous(CommandContext<ServerCommandSource> ctx) {
    return manipulate(ctx, pack -> markAndStart(pack, EntityPlayerActionPack.Action.continuous()));
  }

  @Unique
  private static int interval(CommandContext<ServerCommandSource> ctx) {
    int t = IntegerArgumentType.getInteger(ctx, "ticks");
    return manipulate(ctx, pack -> markAndStart(pack, EntityPlayerActionPack.Action.interval(t)));
  }
}
