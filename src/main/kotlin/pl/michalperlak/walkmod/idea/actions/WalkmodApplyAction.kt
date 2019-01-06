package pl.michalperlak.walkmod.idea.actions

import pl.michalperlak.walkmod.idea.exec.WalkmodProxy

class WalkmodApplyAction : WalkmodExecAction("apply", "Execute walkmod apply command") {
    override fun execute(walkmodProxy: WalkmodProxy) {
        walkmodProxy.apply()
    }
}