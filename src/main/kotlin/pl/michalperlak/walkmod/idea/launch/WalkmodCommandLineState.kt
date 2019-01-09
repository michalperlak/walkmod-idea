package pl.michalperlak.walkmod.idea.launch

import com.intellij.execution.application.BaseJavaApplicationCommandLineState
import com.intellij.execution.configurations.JavaParameters
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.execution.util.JavaParametersUtil
import java.nio.file.Files
import java.nio.file.Path

class WalkmodCommandLineState(
    private val walkmodHome: Path,
    runConfiguration: WalkmodRunConfiguration,
    executionEnvironment: ExecutionEnvironment
) : BaseJavaApplicationCommandLineState<WalkmodRunConfiguration>(executionEnvironment, runConfiguration) {

    override fun createJavaParameters(): JavaParameters {
        val params = JavaParameters()
        val jreHome = if (myConfiguration.isAlternativeJrePathEnabled) myConfiguration.alternativeJrePath else null
        params.jdk = JavaParametersUtil.createProjectJdk(myConfiguration.project, jreHome)
        params.mainClass = WalkmodLaunchConstants.MAIN_CLASS
        setupJavaParameters(params)
        setupClassPath(params)
        return params
    }

    private fun setupClassPath(params: JavaParameters) {
        val classPath = params.classPath
        val entries = getConfigFiles() + getLibFiles()
        entries.forEach { classPath.add(it.toFile()) }
    }

    private fun getConfigFiles(): List<Path> {
        val configDir = walkmodHome.resolve("config")
        return Files.newDirectoryStream(configDir).use { it.toList() }
    }

    private fun getLibFiles(): List<Path> {
        val configDir = walkmodHome.resolve("lib")
        return Files.newDirectoryStream(configDir).use { it.toList() }
    }
}