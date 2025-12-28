package cn.nm.lms.carpetlmsaddition.mixin;

import cn.nm.lms.carpetlmsaddition.rules.SoftVault;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockStateBase.class)
public abstract class SoftVaultMixin {
  @Shadow
  public abstract Block getBlock();

  @ModifyReturnValue(method = "getDestroySpeed", at = @At("RETURN"))
  private float vaultHardnessTo3(float original, BlockGetter world, BlockPos pos) {
    if (SoftVault.softVault && this.getBlock() == Blocks.VAULT) {
      return 3.0F;
    }

    return original;
  }
}
