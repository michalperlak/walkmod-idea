package pl.michalperlak.walkmod.idea.launch

import com.intellij.execution.ProgramRunnerUtil
import com.intellij.execution.RunManager
import com.intellij.execution.executors.DefaultRunExecutor
import com.intellij.openapi.module.Module

class WalkmodLauncher(private val module: Module) {
    fun runCommand(command: WalkmodCommand) {
        val executor = DefaultRunExecutor.getRunExecutorInstance()
        val runManager = RunManager.getInstance(module.project)
        val config = runManager.createConfiguration(command.id, WalkmodRunConfigurationType::class.java)
        ProgramRunnerUtil.executeConfiguration(config, executor)
    }
}