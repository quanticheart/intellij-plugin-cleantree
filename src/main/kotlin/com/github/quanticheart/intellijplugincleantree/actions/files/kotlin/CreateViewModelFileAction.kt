package com.github.quanticheart.intellijplugincleantree.actions.files.kotlin

import com.github.quanticheart.intellijplugincleantree.dialog.DialogNameActionWrapper
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class CreateViewModelFileAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        e.project?.let {
            DialogNameActionWrapper("TEST") {
                print(it)
            }.showAndGet()
        }
    }
}