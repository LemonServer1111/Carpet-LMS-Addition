package cn.nm.lms.carpetlmsaddition.mixin;

import cn.nm.lms.carpetlmsaddition.rules.PearlIgnoreEntityCollision;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Projectile.class)
public abstract class PearlIgnoreEntityCollisionMixin {
  @WrapWithCondition(
      method = "hitTargetOrDeflectSelf",
      at =
          @At(
              value = "INVOKE",
              target =
                  "Lnet/minecraft/world/entity/projectile/Projectile;onHit(Lnet/minecraft/world/phys/HitResult;)V"))
  private boolean skipCollisionIfEntityHit(Projectile self, HitResult hitResult) {
    return !(self instanceof ThrownEnderpearl)
        || hitResult.getType() != HitResult.Type.ENTITY
        || !PearlIgnoreEntityCollision.pearlIgnoreEntityCollision;
  }
}
