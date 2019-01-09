package pl.michalperlak.walkmod.idea.actions

import pl.michalperlak.walkmod.idea.launch.WalkmodCommand
import pl.michalperlak.walkmod.idea.launch.WalkmodLauncher

class WalkmodCheckAction : WalkmodExecAction("check", "Execute walkmod check command") {
    override fun execute(walkmodLauncher: WalkmodLauncher) {
        walkmodLauncher.runCommand(WalkmodCommand.CHECK)
    }
}