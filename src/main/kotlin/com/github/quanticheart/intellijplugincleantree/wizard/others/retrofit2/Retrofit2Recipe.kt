package com.github.quanticheart.intellijplugincleantree.wizard.others.retrofit2

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.domain.repository.cleanArchRepositorySimple
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.domain.useCases.cleanArchUseCaseSimple
import java.util.*

fun RecipeExecutor.retrofit2Recipe(
    moduleData: ModuleTemplateData,
    packageName: String
) {

    val (projectData, srcOut, _) = moduleData

    val applicationPackage = projectData.applicationPackage
    val fileName = "ConnectionBase"

    val repository = retrofitTemplate(
        applicationPackage= applicationPackage,
        featurePackage = packageName,
        featureName = fileName
    )
    save(
        repository,
        srcOut.resolve("${fileName}.kt")
    )

}
