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
        myState.isEmbedded = state.isEmbedded
        myState.walkmodHome = state.walkmodHome
        myState.isOffline = state.isOffline
    }

    fun apply(newConfig: WalkmodConfig) {
        myState.isEmbedded = newConfig.isEmbedded
        myState.walkmodHome = newConfig.walkmodHome
        myState.isOffline = newConfig.isOffline
    }

    fun getConfig() = WalkmodConfig(
        isEmbedded = myState.isEmbedded,
        walkmodHome = myState.walkmodHome,
        isOffline = myState.isOffline
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