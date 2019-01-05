package pl.michalperlak.walkmod.idea.settings

import com.intellij.openapi.options.SearchableConfigurable
import pl.michalperlak.walkmod.idea.settings.ui.WalkmodSettingsUI
import javax.swing.JComponent

internal class WalkmodConfigurable : SearchableConfigurable {
    private val settingsUI by lazy { WalkmodSettingsUI() }

    override fun getId(): String = "Walkmod"

    override fun getDisplayName(): String = "Walkmod"

    override fun isModified(): Boolean = settingsUI.isModified()

    override fun apply() {
        val settings = WalkmodSettings.getInstance()
        settings.apply(settingsUI.createConfig())
    }

    override fun createComponent(): JComponent? = settingsUI.createComponent()
}