package pl.michalperlak.walkmod.idea.launch

import com.intellij.diagnostic.logging.LogConfigurationPanel
import com.intellij.execution.ExecutionBundle
import com.intellij.execution.Executor
import com.intellij.execution.JavaRunConfigurationExtensionManager
import com.intellij.execution.configurations.*
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.module.Module
import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.options.SettingsEditorGroup
import com.intellij.openapi.project.Project
import org.jdom.Element
import pl.michalperlak.walkmod.idea.launch.ui.WalkmodSettingsEditor

class WalkmodRunConfiguration(
    name: String,
    project: Project,
    configurationFactory: ConfigurationFactory
) : ModuleBasedConfiguration<JavaRunConfigurationModule, Element>(
    name,
    JavaRunConfigurationModule(project, true),
    configurationFactory
) {
    override fun getConfigurationEditor(): SettingsEditor<out RunConfiguration> {
        val group = SettingsEditorGroup<WalkmodRunConfiguration>()
        group.addEditor(
            ExecutionBundle.message("run.configuration.configuration.tab.title"),
            WalkmodSettingsEditor()
        )
        JavaRunConfigurationExtensionManager.instance.appendEditors(this, group)
        group.addEditor(ExecutionBundle.message("logs.tab.title"), LogConfigurationPanel())
        return group
    }

    override fun getState(executor: Executor, environment: ExecutionEnvironment): RunProfileState? = null

    override fun getValidModules(): Collection<Module> = ModuleManager.getInstance(project).modules.toList()
}
