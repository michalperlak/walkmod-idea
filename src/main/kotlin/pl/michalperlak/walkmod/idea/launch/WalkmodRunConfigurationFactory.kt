package pl.michalperlak.walkmod.idea.launch

import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.openapi.project.Project

class WalkmodRunConfigurationFactory(configurationType: ConfigurationType) : ConfigurationFactory(configurationType) {
    override fun createTemplateConfiguration(project: Project): RunConfiguration {
        return WalkmodRunConfiguration("Walkmod", project, this)
    }

    override fun getName(): String {
        return "Walkmod Configuration Factory"
    }
}
