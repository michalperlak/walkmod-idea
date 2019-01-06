package pl.michalperlak.walkmod.idea.exec

import org.walkmod.WalkModFacade
import java.io.File

class EmbeddedWalkmodExecutor(walkmodConfig: WalkmodConfig) : WalkmodProxy {
    private val walkmodFacade: WalkModFacade = WalkModFacade(walkmodConfig.toOptions())

    override fun check(vararg chains: String): List<File> = walkmodFacade.check(*chains)

    override fun apply(vararg chains: String): List<File> = walkmodFacade.apply(*chains)

    override fun patch(vararg chains: String): List<File> = walkmodFacade.patch(*chains)

    override fun install() = walkmodFacade.install()
}