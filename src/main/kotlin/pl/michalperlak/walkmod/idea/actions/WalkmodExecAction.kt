package pl.michalperlak.walkmod.idea.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import pl.michalperlak.walkmod.idea.launch.WalkmodLauncher
import pl.michalperlak.walkmod.idea.utils.WalkmodIcons

abstract class WalkmodExecAction(
    text: String,
    description: String
) : AnAction(text, description, WalkmodIcons.MainIcon) {

    override fun actionPerformed(event: AnActionEvent) {
        val project = event.getRequiredData(PlatformDataKeys.PROJECT)
        val walkmodProxy = WalkmodLauncher(project)
        execute(walkmodProxy)
    }

    abstract fun execute(walkmodLauncher: WalkmodLauncher)
}