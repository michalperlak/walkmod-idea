package pl.michalperlak.walkmod.idea.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import pl.michalperlak.walkmod.idea.launch.WalkmodLauncher
import pl.michalperlak.walkmod.idea.utils.WalkmodIcons

abstract class WalkmodExecAction(
    text: String,
    description: String
) : AnAction(text, description, WalkmodIcons.MainIcon) {

    override fun actionPerformed(event: AnActionEvent) {
        val module = event.getRequiredData(LangDataKeys.MODULE)
        val walkmodProxy = WalkmodLauncher(module)
        execute(walkmodProxy)
    }

    abstract fun execute(walkmodLauncher: WalkmodLauncher)
}