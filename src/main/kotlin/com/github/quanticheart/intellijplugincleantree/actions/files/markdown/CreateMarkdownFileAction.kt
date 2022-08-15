package com.github.quanticheart.intellijplugincleantree.actions.files.markdown

import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.InputValidatorEx
import com.intellij.psi.PsiDirectory
import com.intellij.util.PlatformIcons
import org.jetbrains.annotations.NotNull

class CreateMarkdownFileAction : CreateFileFromTemplateAction("Markdown", "Creates new markdown file", PlatformIcons.FILE_ICON) {

    override fun buildDialog(project: Project, directory: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
        builder
            .setTitle("Markdown")
            .addKind("Markdown", PlatformIcons.CLASS_ICON, "Markdown")
            .setValidator(object : InputValidatorEx {
                override fun checkInput(inputString: String?): Boolean {
                    return getErrorText(inputString) == null
                }

                override fun canClose(inputString: String?): Boolean {
                    return true
                }

                override fun getErrorText(inputString: String?): String? {
                    if (inputString.isNullOrBlank()) {
                        return "Name can't be empty"
                    } else if (!inputString.matches("[a-zA-Z_][a-zA-Z0-9_]*".toRegex())) {
                        return "Invalid name"
                    }
                    return null
                }
            })
    }

    override fun getActionName(
        directory: PsiDirectory?,
        @NotNull newName: String, templateName: String?
    ): String {
        return "Create my file: $newName"
    }
}