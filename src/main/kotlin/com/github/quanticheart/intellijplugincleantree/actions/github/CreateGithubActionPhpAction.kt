package com.github.quanticheart.intellijplugincleantree.actions.github

import com.github.quanticheart.intellijplugincleantree.utils.createFile
import com.github.quanticheart.intellijplugincleantree.utils.createSubDirectory
import com.github.quanticheart.intellijplugincleantree.utils.getDirectory
import com.github.quanticheart.intellijplugincleantree.utils.verifyFileExits
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.psi.PsiDirectory

const val fileNameDeployFTP = "main"
const val templateNameDeployFTP = "GithubActionWebFtp"
const val templateNameTelegramAlert = "GithubActionTelegramAlert"

class CreateGithubActionPhpAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val d = DialogActionSelectWrapper(object : OnGithubActionSelected {
            override fun action(selected: TypeGithubActions) {
                when (selected) {
                    TypeGithubActions.WEB_FTP_DEPLOY -> createFileAction(e, fileNameDeployFTP)
                    TypeGithubActions.TELEGRAM_ALERT -> createFileAction(e, templateNameTelegramAlert)
                }
            }
        })
        d.showAndGet()
    }

    private fun createFileAction(e: AnActionEvent, fileTemplateName: String) {
        WriteCommandAction.runWriteCommandAction(e.project) {
            val psiDirectory = e.getDirectory()
            val gitFolder = psiDirectory?.createSubDirectory(".github")
            val subDirectory = gitFolder?.createSubDirectory("workflows")

            subDirectory?.verifyFileExits("$fileTemplateName.yml") {
                subDirectory.createFile(fileTemplateName, templateNameDeployFTP)
            }
        }
    }
}