package pl.michalperlak.walkmod.idea.settings

import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.io.exists
import pl.michalperlak.walkmod.idea.utils.getWalkmodExec

class WalkmodHomeChooserDescriptor : FileChooserDescriptor(false, true, false, false, false, false) {

    override fun isFileSelectable(file: VirtualFile): Boolean {
        if (!super.isFileSelectable(file)) {
            return false
        }
        val walkmodExec = getWalkmodExec(file.path)
        return walkmodExec.exists()
    }
}