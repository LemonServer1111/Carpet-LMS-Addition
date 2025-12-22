package cn.nm.lms.carpetlmsaddition.mixin;

import static cn.nm.lms.carpetlmsaddition.rules.ZombifiedPiglinSpawnFix.zombifiedPiglinSpawnFix;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.SpawnHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(NetherPortalBlock.class)
public abstract class ZombifiedPiglinSpawnFixMixin {
  @WrapOperation(
      method = "randomTick",
      at =
          @At(
              value = "INVOKE",
              target =
                  "Lnet/minecraft/entity/EntityType;spawn(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/SpawnReason;)Lnet/minecraft/entity/Entity;"))
  private Entity carpetlmsaddition$onlyIfClear(
      EntityType<?> type,
      ServerWorld world,
      BlockPos spawnPos,
      SpawnReason reason,
      Operation<Entity> origin) {
    BlockPos headPos = spawnPos.up();
    if (!SpawnHelper.isClearForSpawn(
            world, headPos, world.getBlockState(headPos), world.getFluidState(headPos), type)
        && zombifiedPiglinSpawnFix) {
      return null;
    }
    return origin.call(type, world, spawnPos, reason);
  }
}
