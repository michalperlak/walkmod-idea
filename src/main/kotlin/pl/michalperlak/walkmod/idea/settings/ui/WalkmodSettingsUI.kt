package pl.michalperlak.walkmod.idea.settings.ui

import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.components.JBTextField
import pl.michalperlak.walkmod.idea.settings.WalkmodConfig
import java.awt.Component
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.JComponent
import javax.swing.JPanel

class WalkmodSettingsUI {
    private val panel by lazy { WalkmodSettingsPanel(WalkmodConfig.getCurrent()) }

    fun createConfig(): WalkmodConfig {
        // TODO
        return WalkmodConfig.getCurrent()
    }

    fun isModified(): Boolean = false
    fun createComponent(): JComponent = panel

    inner class WalkmodSettingsPanel(config: WalkmodConfig) : JBPanel<WalkmodSettingsPanel>() {
        private val useEmbeddedCheckbox = JBCheckBox("Use embedded version")
        private val walkmodHomeField = TextFieldWithBrowseButton(JBTextField(TEXT_FIELD_SIZE))
        private val offlineCheckbox = JBCheckBox("Offline")

        init {
            layout = GridBagLayout()
            addElement(useEmbeddedCheckbox, gridx = 0, gridy = 1)
            val walkmodHomeLabel = JBLabel("Walkmod home")
            addElement(walkmodHomeLabel, gridx = 0, gridy = 2, weightx = 0.0)
            addElement(walkmodHomeField, gridx = 1, gridy = 2)
            addElement(offlineCheckbox, gridx = 0, gridy = 3)
            addElement(JPanel(), gridx = 0, gridy = 4, weightx = 1.0, weighty = 1.0)
            initValues(config)
        }

        private fun addElement(
            element: Component,
            gridx: Int = 0,
            gridy: Int = 0,
            weightx: Double = 1.0,
            weighty: Double = 0.0
        ) {
            val constraints = GridBagConstraints()
            constraints.fill = GridBagConstraints.HORIZONTAL
            constraints.gridx = gridx
            constraints.gridy = gridy
            constraints.weightx = weightx
            constraints.weighty = weighty
            constraints.insets = DEFAULT_INSETS
            constraints.anchor = GridBagConstraints.NORTH
            add(element, constraints)
        }

        private fun initValues(config: WalkmodConfig) {
            useEmbeddedCheckbox.isSelected = config.isEmbedded
            walkmodHomeField.isEnabled = !config.isEmbedded
            walkmodHomeField.text = config.walkmodHome?.toString() ?: ""
            offlineCheckbox.isSelected = config.isOffline
        }
    }
}

private const val TEXT_FIELD_SIZE = 20
private val DEFAULT_INSETS = Insets(5, 5, 5, 5)