package pl.michalperlak.walkmod.idea.launch

import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import pl.michalperlak.walkmod.idea.utils.WalkmodIcons
import javax.swing.Icon

class WalkmodRunConfigurationType : ConfigurationType {
    override fun getIcon(): Icon = WalkmodIcons.MainIcon

    override fun getConfigurationTypeDescription(): String = "Walkmod execution configuration"

    override fun getId(): String = "walkmod.runconf"

    override fun getDisplayName(): String = "Walkmod"

    override fun getConfigurationFactories(): Array<ConfigurationFactory> =
        arrayOf(WalkmodRunConfigurationFactory(this))
}