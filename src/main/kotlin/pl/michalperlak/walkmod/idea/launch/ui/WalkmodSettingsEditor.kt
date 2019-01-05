package pl.michalperlak.walkmod.idea.launch.ui

import com.intellij.openapi.options.SettingsEditor
import com.intellij.ui.components.JBPanel
import pl.michalperlak.walkmod.idea.launch.WalkmodRunConfiguration
import javax.swing.JComponent

class WalkmodSettingsEditor : SettingsEditor<WalkmodRunConfiguration>() {
    override fun resetEditorFrom(runConfiguration: WalkmodRunConfiguration) {
    }

    override fun createEditor(): JComponent = WalkmodSettingsEditorPanel()

    override fun applyEditorTo(runConfiguration: WalkmodRunConfiguration) {
    }

    inner class WalkmodSettingsEditorPanel : JBPanel<WalkmodSettingsEditorPanel>() {

    }
}