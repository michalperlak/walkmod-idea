package pl.michalperlak.walkmod.idea.settings

data class WalkmodConfig(
    val isEmbedded: Boolean = true,
    val walkmodHome: String = "",
    val isOffline: Boolean = false
) {
    companion object {
        fun getCurrent(): WalkmodConfig {
            val settings = WalkmodSettings.getInstance()
            return settings.getConfig()
        }
    }
}