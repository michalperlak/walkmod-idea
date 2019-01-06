package pl.michalperlak.walkmod.idea.exec

import org.walkmod.Options
import org.walkmod.OptionsBuilder
import java.nio.file.Path

data class WalkmodConfig(
    val executionDirectory: Path,
    val printErrors: Boolean = false,
    val offline: Boolean = false,
    val verbose: Boolean = false,
    val path: String? = null,
    val includes: List<String>? = null,
    val excludes: List<String>? = null,
    val dynamicArgs: Map<String, Any>? = null
) {
    fun toOptions(): Options = OptionsBuilder
        .options()
        .printErrors(printErrors)
        .offline(offline)
        .verbose(verbose)
        .path(path)
        .includes(includes)
        .excludes(excludes)
        .dynamicArgs(dynamicArgs)
        .executionDirectory(executionDirectory.toFile())
        .build()
}