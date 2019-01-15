package pl.michalperlak.walkmod.idea.settings

data class WalkmodPluginConfig(
    val walkmodHome: String = ""
) {
    companion object {
        fun getCurrent(): WalkmodPluginConfig {
            val settings = WalkmodSettings.getInstance()
            return settings.getConfig()
        }
    }
}