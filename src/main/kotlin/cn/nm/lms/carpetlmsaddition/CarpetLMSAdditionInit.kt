package cn.nm.lms.carpetlmsaddition

import cn.nm.lms.carpetlmsaddition.rules.lowhealthspectator.LowHealthSpectatorListener

object CarpetLMSAdditionInit {
    fun initall() {
        CarpetLMSAdditionRecipes.register()
        LowHealthSpectatorListener.init()
    }
}
