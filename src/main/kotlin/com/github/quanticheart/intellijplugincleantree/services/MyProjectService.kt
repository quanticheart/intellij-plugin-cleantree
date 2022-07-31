package com.github.quanticheart.intellijplugincleantree.services

import com.intellij.openapi.project.Project
import com.github.quanticheart.intellijplugincleantree.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
