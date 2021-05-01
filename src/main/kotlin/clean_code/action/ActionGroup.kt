package clean_code.action

import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.actionSystem.DefaultActionGroup


/**
 * Defines the ActionGroup to be only visible when parent is a [PSI_ELEMENT]
 */
class ActionGroup : DefaultActionGroup() {
//    override fun update(event: AnActionEvent) {
//        // Enable/disable depending on whether user is editing
//        val psi = event.getData(CommonDataKeys.PSI_ELEMENT)
//        //event.presentation.isEnabled = project != null
//        // Always make visible.
//        event.presentation.isVisible = psi != null
//        // Take this opportunity to set an icon for the menu entry.
//        event.presentation.icon = AllIcons.Actions.NewFolder
//    }

    private lateinit var dataContext:DataContext

    override fun update(e: AnActionEvent) {
        e.dataContext.let {
            this.dataContext = it
            val presentation = e.presentation
            presentation.isEnabled = true
            presentation.icon = AllIcons.Actions.NewFolder
        }
    }
}