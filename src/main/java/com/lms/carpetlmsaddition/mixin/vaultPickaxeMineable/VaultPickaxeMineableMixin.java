package com.lms.carpetlmsaddition.mixin.vaultPickaxeMineable;

import com.lms.carpetlmsaddition.rules.fragileVaults.FragileVaultHelper;
import com.lms.carpetlmsaddition.rules.vaultPickaxeMineable.VaultPickaxeMineableRuleSettings;
import net.minecraft.core.HolderSet;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class VaultPickaxeMineableMixin {
    @Shadow
    public abstract Block getBlock();

    @Inject(method = "is(Lnet/minecraft/core/HolderSet;)Z", at = @At("HEAD"), cancellable = true)
    private void lms$letPickaxesMineVaults(
            HolderSet<Block> holderSet, CallbackInfoReturnable<Boolean> cir) {
        if (!VaultPickaxeMineableRuleSettings.vaultPickaxeMineable) {
            return;
        }

        if (holderSet instanceof HolderSet.Named<Block> named
                && named.key().equals(BlockTags.MINEABLE_WITH_PICKAXE)
                && FragileVaultHelper.isVault(this.getBlock())) {
            cir.setReturnValue(true);
        }
    }
}
