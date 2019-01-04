package pl.michalperlak.walkmod.idea.settings

import java.nio.file.Path

data class WalkmodConfig(
    val isEmbedded: Boolean = true,
    val walkmodHome: Path? = null,
    val isOffline: Boolean = false
) {
    companion object {
        fun getCurrent(): WalkmodConfig {
            val settings = WalkmodSettings.getInstance()
            return settings?.config ?: WalkmodConfig()
        }
    }
}