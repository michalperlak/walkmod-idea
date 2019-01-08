package pl.michalperlak.walkmod.idea.launch

import com.intellij.diagnostic.logging.LogConfigurationPanel
import com.intellij.execution.CommonJavaRunConfigurationParameters
import com.intellij.execution.ExecutionBundle
import com.intellij.execution.Executor
import com.intellij.execution.JavaRunConfigurationExtensionManager
import com.intellij.execution.configurations.*
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.execution.util.JavaParametersUtil
import com.intellij.execution.util.ProgramParametersUtil
import com.intellij.openapi.module.Module
import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.options.SettingsEditorGroup
import com.intellij.openapi.project.Project
import org.jdom.Element
import pl.michalperlak.walkmod.idea.launch.ui.WalkmodSettingsEditor
import pl.michalperlak.walkmod.idea.settings.WalkmodPluginConfig
import pl.michalperlak.walkmod.idea.utils.getWalkmodExec
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

class WalkmodRunConfiguration(
    name: String,
    project: Project,
    configurationFactory: ConfigurationFactory
) : ModuleBasedConfiguration<JavaRunConfigurationModule, Element>(
    name,
    JavaRunConfigurationModule(project, true),
    configurationFactory
), CommonJavaRunConfigurationParameters {
    private val state = JavaRunConfigurationState()

    override fun getConfigurationEditor(): SettingsEditor<out RunConfiguration> {
        val group = SettingsEditorGroup<WalkmodRunConfiguration>()
        group.addEditor(
            ExecutionBundle.message("run.configuration.configuration.tab.title"),
            WalkmodSettingsEditor(project)
        )
        JavaRunConfigurationExtensionManager.instance.appendEditors(this, group)
        group.addEditor(ExecutionBundle.message("logs.tab.title"), LogConfigurationPanel())
        return group
    }

    override fun checkConfiguration() {
        JavaParametersUtil.checkAlternativeJRE(this)
        ProgramParametersUtil.checkWorkingDirectoryExist(this, project, null)
        JavaRunConfigurationExtensionManager.checkConfigurationIsValid(this)
        val walkmodExec = getWalkmodExec(getWalkmodHome())
        if (Files.notExists(walkmodExec)) {
            throw RuntimeConfigurationException("Walkmod home is not valid!")
        }
    }

    override fun getState(executor: Executor, environment: ExecutionEnvironment): RunProfileState =
        WalkmodCommandLineState(Paths.get(getWalkmodHome()), this, environment)

    override fun getValidModules(): Collection<Module> = ModuleManager.getInstance(project).modules.toList()

    override fun getRunClass(): String = "org.walkmod.WalkModDispatcher"

    override fun getPackage(): String = "org.walkmod"

    override fun setEnvs(envs: Map<String, String>) {
        state.envs = envs
    }

    override fun getEnvs(): Map<String, String> = Collections.unmodifiableMap(state.envs)

    override fun setAlternativeJrePath(path: String?) {
        state.alternativeJrePath = path ?: ""
    }

    override fun getAlternativeJrePath(): String? = state.alternativeJrePath

    override fun setProgramParameters(value: String?) {
        state.programParameters = value ?: ""
    }

    override fun getProgramParameters(): String? = state.programParameters

    override fun setVMParameters(value: String?) {
        state.vmParameters = value ?: ""
    }

    override fun getVMParameters(): String = state.vmParameters

    override fun setAlternativeJrePathEnabled(enabled: Boolean) {
        state.alternativeJrePathEnabled = enabled
    }

    override fun isAlternativeJrePathEnabled(): Boolean = state.alternativeJrePathEnabled

    override fun getWorkingDirectory(): String = state.workingDir

    override fun setWorkingDirectory(value: String?) {
        state.workingDir = value ?: ""
    }

    override fun isPassParentEnvs(): Boolean = state.passParentEnvs

    override fun setPassParentEnvs(passParentEnvs: Boolean) {
        state.passParentEnvs = passParentEnvs
    }

    private fun getWalkmodHome(): String {
        val walkmodPluginConfig = WalkmodPluginConfig.getCurrent()
        return walkmodPluginConfig.walkmodHome
    }

    private class JavaRunConfigurationState {
        var envs: Map<String, String> = LinkedHashMap()
        var alternativeJrePath: String = ""
        var alternativeJrePathEnabled: Boolean = false
        var vmParameters: String = ""
        var programParameters: String = ""
        var passParentEnvs = true
        var workingDir: String = ""
    }
}
