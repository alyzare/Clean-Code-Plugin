package generator

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import ui.Notifier
import java.io.IOException


interface Generator {
    companion object {
        /**
        * Creates a [parent] folder and its [children] in a given [folder].
        * [project] is needed for the notifications if there is an error or a warning situation.
        * @return null if an error occurred or the a map of all virtual files created
        */
        fun createFolder(
            project: Project,
            folder: VirtualFile,
            parent: String,
            vararg children: Array<String>
        ): Map<String, VirtualFile>? {
            try {
                for (child in folder.children) {
                    if (child.name == parent) {
                        Notifier.warning(project, "Directory [$parent] already exists")
                        return null
                    }
                }
                val mapOfFolder = mutableMapOf<String, VirtualFile>()
                mapOfFolder[parent] = folder.createChildDirectory(folder, parent)
                for (child in children) {
                    mapOfFolder[child[0]] =
                        mapOfFolder[parent]?.createChildDirectory(mapOfFolder[parent], child[0]) ?: throw IOException()
                    for(i in 1 until child.size)
                        mapOfFolder[child[i]] =
                            mapOfFolder[child[0]]?.createChildData(mapOfFolder[child[0]],child[i])?:throw IOException()
                }
                return mapOfFolder
            } catch (e: IOException) {
                Notifier.warning(project, "Couldn't create $parent directory")
                e.printStackTrace()
                return null
            }
        }
    }
}