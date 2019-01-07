package pl.michalperlak.walkmod.idea.exec

import org.walkmod.WalkModFacade
import java.nio.file.Path

class EmbeddedWalkmodExecutor(walkmodConfig: WalkmodConfig) : WalkmodProxy {
    private val walkmodFacade: WalkModFacade = WalkModFacade(walkmodConfig.toOptions())

    override fun check(vararg chains: String): List<Path> = walkmodFacade.check(*chains).map { it.toPath() }

    override fun apply(vararg chains: String): List<Path> = walkmodFacade.apply(*chains).map { it.toPath() }

    override fun patch(vararg chains: String): List<Path> = walkmodFacade.patch(*chains).map { it.toPath() }

    override fun install() = walkmodFacade.install()
}