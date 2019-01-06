package pl.michalperlak.walkmod.idea.launch.ui

import com.intellij.application.options.ModuleDescriptionsComboBox
import com.intellij.execution.ui.ConfigurationModuleSelector
import com.intellij.execution.ui.DefaultJreSelector
import com.intellij.execution.ui.JrePathEditor
import com.intellij.execution.ui.ShortenCommandLineModeCombo
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBTextField
import pl.michalperlak.walkmod.idea.launch.WalkmodRunConfiguration
import pl.michalperlak.walkmod.idea.ui.AbstractGridBagPanel
import java.awt.FlowLayout
import javax.swing.JComponent
import javax.swing.JPanel

class WalkmodSettingsEditor(project: Project) : SettingsEditor<WalkmodRunConfiguration>() {
    private val editorPanel by lazy { WalkmodSettingsEditorPanel(project) }

    override fun resetEditorFrom(runConfiguration: WalkmodRunConfiguration) {
        //TODO
    }

    override fun createEditor(): JComponent = editorPanel

    override fun applyEditorTo(runConfiguration: WalkmodRunConfiguration) {
        editorPanel.moduleSelector.applyTo(runConfiguration)
        //TODO
    }

    inner class WalkmodSettingsEditorPanel(project: Project) : AbstractGridBagPanel<WalkmodSettingsEditorPanel>() {
        private val modulesComboBox = ModuleDescriptionsComboBox().apply { setAllModulesFromProject(project) }
        private val commandComboBox = ComboBox<String>(arrayOf("apply", "check", "install")) //TODO
        private val installationComboBox = ComboBox<String>(arrayOf("EMBEDDED")) //TODO
        private val workingDir = TextFieldWithBrowseButton(JBTextField(project.basePath))
        private val offlineCheckBox = JBCheckBox("Offline")
        private val stacktraceCheckBox = JBCheckBox("Stacktrace")
        private val jreSelector = JrePathEditor()
        private val shortedCommandLineCombo = ShortenCommandLineModeCombo(project, jreSelector, modulesComboBox)
        val moduleSelector = ConfigurationModuleSelector(project, modulesComboBox)

        init {
            var yIndex = 1
            addElement(createLabeled(modulesComboBox, "Module"), gridx = 0, gridy = yIndex++)
            addElement(createLabeled(commandComboBox, "Command"), gridx = 0, gridy = yIndex++)
            addElement(createLabeled(installationComboBox, "Installation"), gridx = 0, gridy = yIndex++)
            addElement(createLabeled(workingDir, "Working dir"), gridx = 0, gridy = yIndex++)
            addElement(createOptionsComponent(), gridx = 0, gridy = yIndex++)
            addElement(jreSelector, gridx = 0, gridy = yIndex++)
            addElement(createLabeled(shortedCommandLineCombo, "Shorten command line"), gridx = 0, gridy = yIndex++)
            addElement(JPanel(), gridx = 0, gridy = yIndex, weighty = 1.0)

            jreSelector.setDefaultJreSelector(DefaultJreSelector.fromModuleDependencies(modulesComboBox, false))
        }

        private fun createOptionsComponent(): JComponent {
            val panel = JPanel(FlowLayout(FlowLayout.LEFT))
            panel.add(offlineCheckBox)
            panel.add(stacktraceCheckBox)
            return createLabeled(panel, "Options")
        }
    }
}