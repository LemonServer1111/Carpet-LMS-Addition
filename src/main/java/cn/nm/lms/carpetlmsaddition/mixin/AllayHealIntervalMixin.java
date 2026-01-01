/*
 * Copyright (C) 2025  Carpet-LMS-Addition contributors
 * https://github.com/Citrus-Union/Carpet-LMS-Addition

 * Carpet LMS Addition is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.

 * Carpet LMS Addition is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with Carpet LMS Addition.  If not, see <https://www.gnu.org/licenses/>.
 */
package cn.nm.lms.carpetlmsaddition.mixin;

import cn.nm.lms.carpetlmsaddition.rules.AllayHealInterval;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.animal.allay.Allay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Allay.class)
public abstract class AllayHealIntervalMixin {
  @ModifyExpressionValue(method = "aiStep", at = @At(value = "CONSTANT", args = "intValue=10"))
  private int changeAllayHealInterval(int original) {
    int interval = AllayHealInterval.allayHealInterval;
    return interval <= 0 ? Integer.MAX_VALUE : interval;
  }
}
