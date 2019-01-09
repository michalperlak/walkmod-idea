package pl.michalperlak.walkmod.idea.actions

import pl.michalperlak.walkmod.idea.launch.WalkmodCommand
import pl.michalperlak.walkmod.idea.launch.WalkmodLauncher

class WalkmodInstallAction : WalkmodExecAction("install", "Execute walkmod install command") {
    override fun execute(walkmodLauncher: WalkmodLauncher) = walkmodLauncher.runCommand(WalkmodCommand.INSTALL)
}