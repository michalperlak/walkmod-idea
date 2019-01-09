package pl.michalperlak.walkmod.idea.launch.ui

import com.intellij.application.options.ModuleDescriptionsComboBox
import com.intellij.execution.ShortenCommandLine
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
import pl.michalperlak.walkmod.idea.launch.WalkmodCommand
import pl.michalperlak.walkmod.idea.launch.WalkmodRunConfiguration
import pl.michalperlak.walkmod.idea.ui.AbstractGridBagPanel
import java.awt.FlowLayout
import javax.swing.JComponent
import javax.swing.JPanel

class WalkmodSettingsEditor(project: Project) : SettingsEditor<WalkmodRunConfiguration>() {
    private val modulesComboBox = ModuleDescriptionsComboBox().apply { setAllModulesFromProject(project) }
    private val commandComboBox = ComboBox<WalkmodCommand>(WalkmodCommand.values())
    private val installationComboBox = ComboBox<String>(arrayOf("EMBEDDED")) //TODO
    private val workingDir = TextFieldWithBrowseButton(JBTextField(project.basePath))
    private val offlineCheckBox = JBCheckBox("Offline")
    private val stacktraceCheckBox = JBCheckBox("Stacktrace")
    private val jreSelector = JrePathEditor()
    private val shortedCommandLineCombo = ShortenCommandLineModeCombo(project, jreSelector, modulesComboBox)
    private val moduleSelector = ConfigurationModuleSelector(project, modulesComboBox)
    private val editorPanel by lazy { WalkmodSettingsEditorPanel() }

    override fun resetEditorFrom(runConfiguration: WalkmodRunConfiguration) {
        moduleSelector.reset(runConfiguration)
        jreSelector.setPathOrName(runConfiguration.alternativeJrePath, runConfiguration.isAlternativeJrePathEnabled)
        workingDir.text = runConfiguration.workingDirectory
        shortedCommandLineCombo.selectedItem = runConfiguration.shortenCommandLineMode
        commandComboBox.selectedItem = runConfiguration.walkmodCommand
    }

    override fun createEditor(): JComponent = editorPanel

    override fun applyEditorTo(runConfiguration: WalkmodRunConfiguration) {
        moduleSelector.applyTo(runConfiguration)
        runConfiguration.setModule(moduleSelector.module)
        runConfiguration.alternativeJrePath = jreSelector.jrePathOrName
        runConfiguration.isAlternativeJrePathEnabled = jreSelector.isAlternativeJreSelected
        runConfiguration.workingDirectory = workingDir.text
        runConfiguration.shortenCommandLineMode = shortedCommandLineCombo.selectedItem ?: ShortenCommandLine.NONE
        runConfiguration.walkmodCommand = commandComboBox.selectedItem as WalkmodCommand
    }

    inner class WalkmodSettingsEditorPanel : AbstractGridBagPanel<WalkmodSettingsEditorPanel>() {
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