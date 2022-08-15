package com.github.quanticheart.intellijplugincleantree.actions.github

import com.github.quanticheart.intellijplugincleantree.utils.createFile
import com.github.quanticheart.intellijplugincleantree.utils.getDirectory
import com.github.quanticheart.intellijplugincleantree.utils.verifyFileExits
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.command.WriteCommandAction

class CreateGitIgnoreFileAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        WriteCommandAction.runWriteCommandAction(e.project) {
            val psiDirectory = e.getDirectory()
            psiDirectory?.verifyFileExits(".gitignore") {
                psiDirectory.createFile("", "gitignore")
            }
        }
    }
}