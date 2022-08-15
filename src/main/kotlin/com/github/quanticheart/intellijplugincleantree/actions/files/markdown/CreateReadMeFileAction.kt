package com.github.quanticheart.intellijplugincleantree.actions.files.markdown

import com.github.quanticheart.intellijplugincleantree.utils.createFile
import com.github.quanticheart.intellijplugincleantree.utils.getDirectory
import com.intellij.openapi.actionSystem.*


class CreateReadMeFileAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val psiDirectory = e.getDirectory()
        psiDirectory?.createFile("README", "ReadME")
    }
}