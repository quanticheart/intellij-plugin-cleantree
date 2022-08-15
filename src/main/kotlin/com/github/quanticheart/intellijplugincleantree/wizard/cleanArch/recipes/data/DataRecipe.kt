package com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.recipes.data

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.data.*
import java.util.*

fun RecipeExecutor.dataRecipe(
    moduleData: ModuleTemplateData,
    featureName: String,
    packageName: String
) {

    val (_, srcOut, _) = moduleData

    /**
     * Packages
     */
    val featurePackage = "${packageName}.${featureName.toLowerCase()}"

    //data
    val repositoryImplName = "${featureName}RepositoryImpl"
    val endPointsName = "${featureName}EndPoints"

    /**
     * Paths
     */
    val basePath = featureName.toLowerCase(Locale.getDefault())

    /**
     * Create Files
     */

    /**
     * Data
     */
    val repositoryImpl = cleanArchRepositoryImplSimple(
        featurePackage = featurePackage,
        endPointsName = endPointsName,
        repositoryImplName = repositoryImplName
    )
    save(
        repositoryImpl,
        srcOut.resolve("$basePath/${repositoryImplName}.kt")
    )

    val endpoints = cleanArchEndPointsSimple(
        featurePackage = featurePackage,
        endPointsName = endPointsName
    )
    save(
        endpoints,
        srcOut.resolve("$basePath/${endPointsName}.kt")
    )
}
