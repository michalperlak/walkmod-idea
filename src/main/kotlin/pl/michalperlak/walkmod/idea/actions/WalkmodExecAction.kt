package pl.michalperlak.walkmod.idea.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import pl.michalperlak.walkmod.idea.exec.WalkmodConfig
import pl.michalperlak.walkmod.idea.exec.WalkmodProxy
import pl.michalperlak.walkmod.idea.utils.WalkmodIcons
import java.nio.file.Paths

abstract class WalkmodExecAction(
    text: String,
    description: String
) : AnAction(text, description, WalkmodIcons.MainIcon) {

    override fun actionPerformed(event: AnActionEvent) {
        val project = event.getRequiredData(PlatformDataKeys.PROJECT)
        val walkmodConfig = WalkmodConfig(executionDirectory = Paths.get(project.basePath))
        val walkmodProxy = WalkmodProxy.create(walkmodConfig)
        execute(walkmodProxy)
    }

    abstract fun execute(walkmodProxy: WalkmodProxy)
}