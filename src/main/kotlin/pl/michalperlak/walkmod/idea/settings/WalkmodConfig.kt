package pl.michalperlak.walkmod.idea.settings

import java.nio.file.Path

data class WalkmodConfig(
    val isEmbedded: Boolean,
    val walkmodHome: Path,
    val isOffline: Boolean
) {
    companion object {
        fun getDefault(): WalkmodConfig = TODO()
    }
}