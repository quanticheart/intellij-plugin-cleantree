package com.github.quanticheart.intellijplugincleantree.wizard.others.mapper

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.domain.repository.cleanArchRepositorySimple
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.domain.useCases.cleanArchUseCaseSimple
import java.util.*

fun RecipeExecutor.mapperGenericRecipe(
    moduleData: ModuleTemplateData,
    packageName: String
) {

    val (_, srcOut, _) = moduleData

    val fileName = "${classInputName}To${classOutputName}"

    /**
     * Domain
     */
    val repository = mapperGenericTemplate(
        featurePackage = packageName
    )
    save(
        repository,
        srcOut.resolve("${fileName}.kt")
    )

}
