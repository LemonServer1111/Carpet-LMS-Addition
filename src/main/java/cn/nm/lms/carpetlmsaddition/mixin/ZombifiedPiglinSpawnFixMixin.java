package cn.nm.lms.carpetlmsaddition.mixin;

import static cn.nm.lms.carpetlmsaddition.rules.ZombifiedPiglinSpawnFix.zombifiedPiglinSpawnFix;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(NetherPortalBlock.class)
public abstract class ZombifiedPiglinSpawnFixMixin {
  @Unique
  private static boolean carpetlmsaddition$passesNaturalCollisionChecks(
      EntityType<?> type, ServerLevel world, BlockPos spawnPos, EntitySpawnReason reason) {
    double x = spawnPos.getX() + 0.5D;
    double y = spawnPos.getY();
    double z = spawnPos.getZ() + 0.5D;
    AABB spawnBox = type.getSpawnAABB(x, y, z);
    if (!world.noCollision(spawnBox)) {
      return false;
    }
    Entity entity = type.create(world, reason);
    if (!(entity instanceof Mob mob)) {
      return true;
    }
    mob.snapTo(x, y, z, 0.0F, 0.0F);
    return mob.checkSpawnObstruction(world);
  }

  @WrapOperation(
      method = "randomTick",
      at =
          @At(
              value = "INVOKE",
              target =
                  "Lnet/minecraft/world/entity/EntityType;spawn(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/EntitySpawnReason;)Lnet/minecraft/world/entity/Entity;"))
  private Entity carpetlmsaddition$onlyIfClear(
      EntityType<?> type,
      ServerLevel world,
      BlockPos spawnPos,
      EntitySpawnReason reason,
      Operation<Entity> origin) {
    if (zombifiedPiglinSpawnFix
        && !carpetlmsaddition$passesNaturalCollisionChecks(type, world, spawnPos, reason)) {
      return null;
    }
    return origin.call(type, world, spawnPos, reason);
  }
}
