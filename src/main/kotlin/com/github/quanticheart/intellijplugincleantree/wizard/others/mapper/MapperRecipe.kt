package com.github.quanticheart.intellijplugincleantree.wizard.others.mapper

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.domain.repository.cleanArchRepositorySimple
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.domain.useCases.cleanArchUseCaseSimple
import java.util.*

fun RecipeExecutor.mapperRecipe(
    moduleData: ModuleTemplateData,
    classInputName: String,
    classOutputName: String,
    packageName: String
) {

    val (_, srcOut, _) = moduleData

    val fileName = "${classInputName}To${classOutputName}"

    /**
     * Domain
     */
    val repository = mapperTemplate(
        featurePackage = packageName,
        featureName = fileName,
        class1 = classInputName,
        class2 = classOutputName
    )
    save(
        repository,
        srcOut.resolve("${fileName}.kt")
    )

}
