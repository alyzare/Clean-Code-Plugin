package clean_code.action

import bloc.action.GenerateCubitAction
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.command.WriteCommandAction
import generator.Generator
import ui.FeatureDialog

class ActionGenerateCleanCode : AnAction() {

  //  var cubit : GenerateCubitAction = GenerateCubitAction()
    override fun actionPerformed(actionEvent: AnActionEvent) {
        val dialog = FeatureDialog(actionEvent.project)
        if (dialog.showAndGet()) {
            generate(actionEvent.dataContext, dialog.getName())
        }
    }
    private fun generate(dataContext: DataContext, root: String?) {
        val project = CommonDataKeys.PROJECT.getData(dataContext) ?: return
        val selected = PlatformDataKeys.VIRTUAL_FILE.getData(dataContext) ?: return
        var folder = if (selected.isDirectory) selected else selected.parent
      //  cubit.onGenerateBlocClicked(root , true)
        WriteCommandAction.runWriteCommandAction(project) {
            if (root != null && root.isNotBlank()) {
                val result = Generator.createFolder(
                    project, folder, root
                ) ?: return@runWriteCommandAction
                folder = result[root]
            }
                Generator.createFolder(
                    project, folder,
                    "data",
                    "repositories", "data_sources", "models"
                )
            }
            Generator.createFolder(
                project, folder,
                "domain",
                "repositories", "use_cases", "entities"
            )
            Generator.createFolder(
                project, folder,
                "presentation",
                "view_model", "pages", "widgets"
            )
        }
    }