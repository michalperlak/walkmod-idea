package pl.michalperlak.walkmod.idea.settings

import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.io.exists
import org.jdesktop.swingx.util.OS
import java.nio.file.Paths

class WalkmodHomeChooserDescriptor : FileChooserDescriptor(false, true, false, false, false, false) {

    override fun isFileSelectable(file: VirtualFile): Boolean {
        if (!super.isFileSelectable(file)) {
            return false
        }
        val walkmodBin = Paths.get(file.path, "bin")
        val walkmodExec = if (OS.isWindows()) {
            walkmodBin.resolve("walkmod.bat")
        } else {
            walkmodBin.resolve("walkmod.sh")
        }
        return walkmodExec.exists()
    }
}