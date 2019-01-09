package pl.michalperlak.walkmod.idea.actions

import pl.michalperlak.walkmod.idea.launch.WalkmodCommand
import pl.michalperlak.walkmod.idea.launch.WalkmodLauncher

class WalkmodApplyAction : WalkmodExecAction("apply", "Execute walkmod apply command") {
    override fun execute(walkmodLauncher: WalkmodLauncher) {
        walkmodLauncher.runCommand(WalkmodCommand.APPLY)
    }
}