package pl.michalperlak.walkmod.idea.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@State(
    name = "WalkmodSettings",
    storages = [(Storage("walkmod-plugin.xml"))]
)
class WalkmodSettings : PersistentStateComponent<WalkmodSettings.State> {
    var myState = State()

    override fun getState(): WalkmodSettings.State = myState

    override fun loadState(state: WalkmodSettings.State) {
        myState.walkmodHome = state.walkmodHome
    }

    fun apply(newPluginConfig: WalkmodPluginConfig) {
        myState.walkmodHome = newPluginConfig.walkmodHome
    }

    fun getConfig() = WalkmodPluginConfig(
        walkmodHome = myState.walkmodHome
    )

    companion object {
        fun getInstance(): WalkmodSettings = ServiceManager.getService(WalkmodSettings::class.java)
    }

    class State {
        var isEmbedded: Boolean = true
        var walkmodHome: String = ""
        var isOffline: Boolean = false
    }
}