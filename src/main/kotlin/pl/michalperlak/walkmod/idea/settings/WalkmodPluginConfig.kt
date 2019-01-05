package pl.michalperlak.walkmod.idea.settings

data class WalkmodPluginConfig(
    val isEmbedded: Boolean = true,
    val walkmodHome: String = "",
    val isOffline: Boolean = false
) {
    companion object {
        fun getCurrent(): WalkmodPluginConfig {
            val settings = WalkmodSettings.getInstance()
            return settings.getConfig()
        }
    }
}