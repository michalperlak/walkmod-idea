package pl.michalperlak.walkmod.idea.actions

import pl.michalperlak.walkmod.idea.exec.WalkmodProxy

class WalkmodCheckAction : WalkmodExecAction("check", "Execute walkmod check command") {
    override fun execute(walkmodProxy: WalkmodProxy) {
        walkmodProxy.check()
    }
}