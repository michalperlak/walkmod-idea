package pl.michalperlak.walkmod.idea.exec

import java.io.File
import java.nio.file.Paths

class WalkmodCmdExecutor(walkmodHomePath: String) : WalkmodProxy {
    private val walkmodHome = Paths.get(walkmodHomePath)

    override fun check(vararg chains: String): List<File> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun apply(vararg chains: String): List<File> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun patch(vararg chains: String): List<File> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun install() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}