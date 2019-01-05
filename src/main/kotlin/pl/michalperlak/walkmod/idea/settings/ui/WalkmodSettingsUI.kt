package pl.michalperlak.walkmod.idea.settings.ui

import com.intellij.openapi.ui.TextComponentAccessor
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.components.JBTextField
import pl.michalperlak.walkmod.idea.settings.WalkmodConfig
import pl.michalperlak.walkmod.idea.settings.WalkmodHomeChooserDescriptor
import java.awt.Component
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.BoxLayout
import javax.swing.JComponent
import javax.swing.JPanel

class WalkmodSettingsUI {
    private val panel by lazy { WalkmodSettingsPanel(WalkmodConfig.getCurrent()) }

    fun createConfig(): WalkmodConfig = WalkmodConfig(
        isEmbedded = panel.useEmbeddedCheckbox.isSelected,
        walkmodHome = panel.walkmodHomeField.text,
        isOffline = panel.offlineCheckbox.isSelected
    )

    fun isModified(): Boolean = WalkmodConfig.getCurrent() != createConfig()

    fun createComponent(): JComponent = panel

    inner class WalkmodSettingsPanel(config: WalkmodConfig) : JBPanel<WalkmodSettingsPanel>() {
        val useEmbeddedCheckbox = JBCheckBox("Use embedded version")
        val walkmodHomeField = TextFieldWithBrowseButton(JBTextField(TEXT_FIELD_SIZE))
        val offlineCheckbox = JBCheckBox("Offline")

        init {
            layout = GridBagLayout()
            addElement(useEmbeddedCheckbox, gridx = 0, gridy = 1)
            val walkmodHomePanel = createWalkmodHomePanel()
            addElement(walkmodHomePanel, gridx = 0, gridy = 2, weightx = 0.0, anchor = GridBagConstraints.NORTHWEST)
            addElement(offlineCheckbox, gridx = 0, gridy = 3)
            addElement(JPanel(), gridx = 0, gridy = 4, weightx = 1.0, weighty = 1.0)
            configureFileChooser()
            registerListeners()
            initValues(config)
        }

        private fun addElement(
            element: Component,
            gridx: Int = 0,
            gridy: Int = 0,
            weightx: Double = 1.0,
            weighty: Double = 0.0,
            anchor: Int = GridBagConstraints.NORTH
        ) {
            val constraints = GridBagConstraints()
            constraints.fill = GridBagConstraints.HORIZONTAL
            constraints.gridx = gridx
            constraints.gridy = gridy
            constraints.weightx = weightx
            constraints.weighty = weighty
            constraints.insets = DEFAULT_INSETS
            constraints.anchor = anchor
            add(element, constraints)
        }

        private fun createWalkmodHomePanel(): JPanel {
            val walkmodHomePanel = JPanel()
            val walkmodHomeLayout = BoxLayout(walkmodHomePanel, BoxLayout.X_AXIS)
            walkmodHomePanel.layout = walkmodHomeLayout
            walkmodHomePanel.add(JBLabel("Walkmod home"))
            walkmodHomePanel.add(walkmodHomeField)
            return walkmodHomePanel
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

        private fun initValues(config: WalkmodConfig) {
            useEmbeddedCheckbox.isSelected = config.isEmbedded
            walkmodHomeField.isEnabled = !config.isEmbedded
            walkmodHomeField.text = config.walkmodHome
            offlineCheckbox.isSelected = config.isOffline
        }
    }
}

private const val TEXT_FIELD_SIZE = 20
private val DEFAULT_INSETS = Insets(5, 5, 5, 5)