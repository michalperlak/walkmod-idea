package pl.michalperlak.walkmod.idea.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DefaultActionGroup
import pl.michalperlak.walkmod.idea.utils.WalkmodIcons

class WalkmodActionsGroup : DefaultActionGroup() {
    override fun update(e: AnActionEvent) {
        e.presentation.icon = WalkmodIcons.MainIcon
    }
}