package pl.michalperlak.walkmod.idea.settings.ui

import com.intellij.openapi.ui.TextComponentAccessor
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBTextField
import pl.michalperlak.walkmod.idea.settings.WalkmodHomeChooserDescriptor
import pl.michalperlak.walkmod.idea.settings.WalkmodPluginConfig
import pl.michalperlak.walkmod.idea.ui.AbstractGridBagPanel
import java.awt.GridBagConstraints
import javax.swing.JComponent
import javax.swing.JPanel

class WalkmodSettingsUI {
    private val panel by lazy { WalkmodSettingsPanel(WalkmodPluginConfig.getCurrent()) }

    fun createConfig(): WalkmodPluginConfig = WalkmodPluginConfig(
        isEmbedded = panel.useEmbeddedCheckbox.isSelected,
        walkmodHome = panel.walkmodHomeField.text,
        isOffline = panel.offlineCheckbox.isSelected
    )

    fun isModified(): Boolean = WalkmodPluginConfig.getCurrent() != createConfig()

    fun createComponent(): JComponent = panel

    inner class WalkmodSettingsPanel(pluginConfig: WalkmodPluginConfig) : AbstractGridBagPanel<WalkmodSettingsPanel>() {
        val useEmbeddedCheckbox = JBCheckBox("Use embedded version")
        val walkmodHomeField = TextFieldWithBrowseButton(JBTextField(DEFAULT_TEXT_FIELD_SIZE))
        val offlineCheckbox = JBCheckBox("Offline")

        init {
            addElement(useEmbeddedCheckbox, gridx = 0, gridy = 1)
            val walkmodHomePanel = createLabeled(walkmodHomeField, "Walkmod home")
            addElement(walkmodHomePanel, gridx = 0, gridy = 2, weightx = 0.0, anchor = GridBagConstraints.NORTHWEST)
            addElement(offlineCheckbox, gridx = 0, gridy = 3)
            addElement(JPanel(), gridx = 0, gridy = 4, weightx = 1.0, weighty = 1.0)
            configureFileChooser()
            registerListeners()
            initValues(pluginConfig)
        }

        private fun configureFileChooser() {
            val fileChooserDescriptor = WalkmodHomeChooserDescriptor()
            walkmodHomeField.addBrowseFolderListener(
                "",
                "Walkmod home",
                null,
                fileChooserDescriptor,
                TextComponentAccessor.TEXT_FIELD_WHOLE_TEXT
            )
        }

        private fun registerListeners() {
            useEmbeddedCheckbox.addActionListener {
                walkmodHomeField.isEnabled = !useEmbeddedCheckbox.isSelected
            }
        }

        private fun initValues(pluginConfig: WalkmodPluginConfig) {
            useEmbeddedCheckbox.isSelected = pluginConfig.isEmbedded
            walkmodHomeField.isEnabled = !pluginConfig.isEmbedded
            walkmodHomeField.text = pluginConfig.walkmodHome
            offlineCheckbox.isSelected = pluginConfig.isOffline
        }
    }
}

