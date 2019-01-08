package pl.michalperlak.walkmod.idea.exec

import java.nio.file.Path
import java.nio.file.Paths

class WalkmodCmdExecutor(walkmodHomePath: String) : WalkmodProxy {
    private val walkmodHome = Paths.get(walkmodHomePath)

    override fun check(vararg chains: String): List<Path> = executeCommand("check", chains)

    override fun apply(vararg chains: String): List<Path> = executeCommand("apply", chains)

    override fun patch(vararg chains: String): List<Path> = executeCommand("patch", chains)

    override fun install() {
        executeCommand("install", emptyArray())
    }

    private fun executeCommand(command: String, chains: Array<out String>): List<Path> {
        val chainsArg = chains.joinToString(separator = " ")
        val args = "$command $chainsArg"
        TODO()
    }
}