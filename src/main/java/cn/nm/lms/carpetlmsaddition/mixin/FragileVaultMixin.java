package cn.nm.lms.carpetlmsaddition.mixin;

import cn.nm.lms.carpetlmsaddition.rules.FragileVault;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ExplosionDamageCalculator.class)
public abstract class FragileVaultMixin {
  @ModifyReturnValue(method = "getBlockExplosionResistance", at = @At("RETURN"))
  private Optional<Float> carpetlmsaddition$vaultBlastResistanceTo3(
      Optional<Float> original,
      Explosion explosion,
      BlockGetter world,
      BlockPos pos,
      BlockState blockState,
      FluidState fluidState) {
    if (FragileVault.fragileVault && blockState.is(Blocks.VAULT)) {
      return Optional.of(3.0F);
    }

    return original;
  }
}
