package cn.nm.lms.carpetlmsaddition.mixin;

import cn.nm.lms.carpetlmsaddition.rules.AllayHealInterval;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.passive.AllayEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AllayEntity.class)
public abstract class AllayHealIntervalMixin {
  @ModifyExpressionValue(
      method = "tickMovement",
      at = @At(value = "CONSTANT", args = "intValue=10"))
  private int carpetlmsaddition$changeAllayHealInterval(int original) {
    int interval = AllayHealInterval.allayHealInterval;
    return interval <= 0 ? Integer.MAX_VALUE : interval;
  }
}
