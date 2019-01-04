package pl.michalperlak.walkmod.idea.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@State(
    name = "WalkmodSettings",
    storages = [Storage("walkmod-plugin.xml")]
)
class WalkmodSettings : PersistentStateComponent<WalkmodSettings> {
    var config = WalkmodConfig()
        private set

    override fun getState(): WalkmodSettings = this

    override fun loadState(state: WalkmodSettings) {
        config = state.config
    }

    fun apply(newConfig: WalkmodConfig) {
        config = newConfig
    }

    companion object {
        fun getInstance(): WalkmodSettings? = ServiceManager.getService(WalkmodSettings::class.java)
    }
}