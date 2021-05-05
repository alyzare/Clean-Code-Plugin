package clean_code.action

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
        var arr1 = arrayOf("repositories","${root}_repository_impl.dart")
        var arr2 = arrayOf("data_sources","${root}_remote_data_source.dart","${root}_local_data_source.dart")
        var arr3 = arrayOf("models","${root}_model.dart")
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
                arr1,
                arr2, arr3
            )
        }
        arr1= arrayOf("repositories","${root}_repository.dart")
        arr2 = arrayOf("use_cases","${root}_use_case.dart")
        arr3 = arrayOf("entities","${root}.dart")
        Generator.createFolder(
            project, folder,
            "domain",
            arr1,
            arr2, arr3
        )
        arr1= arrayOf("view_model","${root}_view_model.dart","${root}_state.dart")
        arr2= arrayOf("pages","${root}_page.dart")
        arr3 = arrayOf("widgets",)
        Generator.createFolder(
            project, folder,
            "presentation",
            arr1,
            arr2, arr3
        )
    }
}