package bloc.action

import bloc.generator.CubitGenerator
import bloc.generator.CubitGeneratorFactory
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.application.ApplicationManager.getApplication
import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.project.Project
import com.intellij.psi.*

class GenerateCubitAction : GenerateBlocDialog.Listener,DefaultActionGroup() {

    private lateinit var dataContext: DataContext

    override fun actionPerformed(e: AnActionEvent) {
        val dialog = GenerateBlocDialog(this)
        dialog.show()
    }

    override fun onGenerateBlocClicked(name: String?, useEquatable: Boolean) {
        name?.let { name ->
            val generators = CubitGeneratorFactory.getCubitGenerators(name, useEquatable)
            generate(generators)
        }
    }

    override fun update(e: AnActionEvent) {
        e.dataContext.let {
            this.dataContext = it
            val presentation = e.presentation
            presentation.isEnabled = true
        }
    }

    protected fun generate(mainSourceGenerators: List<CubitGenerator>) {
        val project = CommonDataKeys.PROJECT.getData(dataContext)
        val view = LangDataKeys.IDE_VIEW.getData(dataContext)
        val directory = view?.orChooseDirectory
        getApplication().runWriteAction {
            CommandProcessor.getInstance().executeCommand(
                project,
                {
                    mainSourceGenerators.forEach { createSourceFile(project!!, it, directory!!) }
                },
                "Generate a new Cubit",
                null
            )
        }
    }

    private fun createSourceFile(project: Project, generator: CubitGenerator, directory: PsiDirectory) {
        val fileName = generator.fileName()
        val existingPsiFile = directory.findFile(fileName)
        if (existingPsiFile != null) {
            val document = PsiDocumentManager.getInstance(project).getDocument(existingPsiFile)
            document?.insertString(document.textLength, "\n" + generator.generate())
            return
        }
        val psiFile = PsiFileFactory.getInstance(project)
            .createFileFromText(fileName, generator.generate())
        directory.add(psiFile)
    }
}