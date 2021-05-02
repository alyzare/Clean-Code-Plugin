package ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import javax.swing.JCheckBox
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextField

/**
* Creates a [DialogWrapper] to get the feature name
*/
class FeatureDialog(project: Project?) :
    DialogWrapper(project) {
    private var contentPanel: JPanel? = null
    private var blocNameTextField: JTextField? = null

    /**
    * @return feature name
    */
    fun getName(): String? = blocNameTextField?.text

    /**
    * @return split data sources
    */

    override fun createCenterPanel(): JComponent? {
        return contentPanel
    }

    /**
    * Sets focus on the text field
    */
    override fun getPreferredFocusedComponent(): JComponent? {
        return blocNameTextField
    }

    init {
        init()
        title = "Clean-Architecture Generator"
    }
}