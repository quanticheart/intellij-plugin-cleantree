package com.github.quanticheart.intellijplugincleantree.actions.files.test

import com.intellij.ide.fileTemplates.FileTemplate
import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.fileTemplates.actions.CreateFromTemplateAction
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class CreateTest4FileAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val fileTemplateManager = FileTemplateManager.getInstance()
        val templates: Array<FileTemplate> = fileTemplateManager.allTemplates
        for (ft in templates) {
            if (ft.name == "Singleton") {
                val action: AnAction = CreateFromTemplateAction(ft)
                action.actionPerformed(e)
            }
        }
    }
}