package pl.michalperlak.walkmod.idea.utils

import com.intellij.util.io.exists
import org.jdesktop.swingx.util.OS
import java.nio.file.Path
import java.nio.file.Paths

fun getWalkmodExec(walkmodHome: String): Path {
    val walkmodBin = Paths.get(walkmodHome, "bin")
    return if (OS.isWindows()) {
        walkmodBin.resolve("walkmod.bat")
    } else {
        val exec = walkmodBin.resolve("walkmod.sh")
        if (exec.exists()) exec else walkmodBin.resolve("walkmod")
    }
}