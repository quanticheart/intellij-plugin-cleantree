package com.github.quanticheart.intellijplugincleantree.actions.stackOverflow

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys

class OpenStackAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        BrowserUtil.browse("https://stackoverflow.com/")
    }

    override fun update(e: AnActionEvent) {
    }
}