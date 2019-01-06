package pl.michalperlak.walkmod.idea.exec

import pl.michalperlak.walkmod.idea.settings.WalkmodPluginConfig
import java.io.File

interface WalkmodProxy {
    fun check(vararg chains: String): List<File>

    fun apply(vararg chains: String): List<File>

    fun patch(vararg chains: String): List<File>

    fun install()

    companion object {
        fun create(walkmodConfig: WalkmodConfig): WalkmodProxy {
            val walkmodPluginConfig = WalkmodPluginConfig.getCurrent()
            return if (walkmodPluginConfig.isEmbedded) {
                EmbeddedWalkmodExecutor(walkmodConfig)
            } else {
                WalkmodCmdExecutor(walkmodPluginConfig.walkmodHome)
            }
        }
    }
}