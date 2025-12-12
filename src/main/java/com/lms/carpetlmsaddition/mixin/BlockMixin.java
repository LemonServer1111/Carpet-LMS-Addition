package com.lms.carpetlmsaddition.mixin;

import com.lms.carpetlmsaddition.CarpetLmsSettings;
import com.lms.carpetlmsaddition.FragileVaultHelper;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public abstract class BlockMixin {
  @Inject(method = "getExplosionResistance", at = @At("HEAD"), cancellable = true)
  private void lms$weakenVaults(CallbackInfoReturnable<Float> cir) {
    if (CarpetLmsSettings.fragileVaults && FragileVaultHelper.isVault((Block) (Object) this)) {
      cir.setReturnValue(FragileVaultHelper.BEACON_BLAST_RESISTANCE);
    }
  }
}
