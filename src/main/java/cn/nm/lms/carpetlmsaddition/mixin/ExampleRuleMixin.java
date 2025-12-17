package cn.nm.lms.carpetlmsaddition.mixin;

import cn.nm.lms.carpetlmsaddition.rules.ExampleRule;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.block.AbstractBlock.AbstractBlockState;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractBlockState.class)
public abstract class ExampleRuleMixin {
  @Shadow
  public abstract Block getBlock();

  @ModifyReturnValue(method = "getHardness", at = @At("RETURN"))
  private float carpetlmsaddition$stoneHardnessTo100(
      float original, BlockView world, BlockPos pos) {
    AbstractBlockState state = (AbstractBlockState) (Object) this;
    if (ExampleRule.exampleRule && this.getBlock() == Blocks.STONE) {
      return 100.0f;
    }

    return original;
  }
}
