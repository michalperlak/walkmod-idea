package pl.michalperlak.walkmod.idea.actions

import pl.michalperlak.walkmod.idea.exec.WalkmodProxy

class WalkmodInstallAction : WalkmodExecAction("install", "Execute walkmod install command") {
    override fun execute(walkmodProxy: WalkmodProxy) = walkmodProxy.install()
}