package cn.nm.lms.carpetlmsaddition.mixin.playercommanddropall;

import carpet.fakes.ServerPlayerInterface;
import carpet.helpers.EntityPlayerActionPack;
import carpet.utils.CommandHelper;
import cn.nm.lms.carpetlmsaddition.rules.playercommanddropall.DropAllActionExtension;
import cn.nm.lms.carpetlmsaddition.rules.playercommanddropall.PlayerCommandDropall;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityPlayerActionPack.Action.class)
public class EntityPlayerActionPackActionMixin implements DropAllActionExtension {
  @Unique private boolean lms$dropAll;

  @Unique
  public void setDropAll(boolean v) {
    this.lms$dropAll = v;
  }

  @Unique
  public boolean isDropAll() {
    return this.lms$dropAll;
  }

  @WrapOperation(
      method = "tick",
      at =
          @At(
              value = "INVOKE",
              target =
                  "Lcarpet/helpers/EntityPlayerActionPack$ActionType;execute(Lnet/minecraft/server/level/ServerPlayer;Lcarpet/helpers/EntityPlayerActionPack$Action;)Z"),
      remap = false)
  private boolean lms$wrapExecute(
      EntityPlayerActionPack.ActionType type,
      net.minecraft.server.level.ServerPlayer player,
      EntityPlayerActionPack.Action action,
      Operation<Boolean> original,
      EntityPlayerActionPack pack) {
    if (type == EntityPlayerActionPack.ActionType.DROP_STACK && isDropAll()) {
      if (!CommandHelper.canUseCommand(
          player.createCommandSourceStack(), PlayerCommandDropall.playerCommandDropall)) {
        return false;
      }
      EntityPlayerActionPack actionPack = ((ServerPlayerInterface) player).getActionPack();
      actionPack.drop(-2, true);
      return false;
    }

    return original.call(type, player, action);
  }
}
