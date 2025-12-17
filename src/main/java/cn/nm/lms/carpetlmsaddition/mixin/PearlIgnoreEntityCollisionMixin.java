package cn.nm.lms.carpetlmsaddition.mixin;

import cn.nm.lms.carpetlmsaddition.rules.PearlIgnoreEntityCollision;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ProjectileEntity.class)
public abstract class PearlIgnoreEntityCollisionMixin {
  @WrapWithCondition(
      method = "hitOrDeflect",
      at =
          @At(
              value = "INVOKE",
              target =
                  "Lnet/minecraft/entity/projectile/ProjectileEntity;onCollision(Lnet/minecraft/util/hit/HitResult;)V"))
  private boolean carpetlmsaddition$skipCollisionIfEntityHit(
      ProjectileEntity self, HitResult hitResult) {
    return !(self instanceof EnderPearlEntity)
        || hitResult.getType() != HitResult.Type.ENTITY
        || !PearlIgnoreEntityCollision.pearlIgnoreEntityCollision;
  }
}
