package com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.recipes.domain

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.domain.repository.cleanArchRepositorySimple
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.domain.useCases.cleanArchUseCaseSimple
import java.util.*

fun RecipeExecutor.domainRecipe(
    moduleData: ModuleTemplateData,
    featureName: String,
    packageName: String
) {

    val (_, srcOut, _) = moduleData

    /**
     * Packages
     */
    val featurePackage = "${packageName}.${featureName.toLowerCase()}"

    //domain
    val repositoryName = "${featureName}Repository"
    val useCaseName = "${featureName}UseCase"

    /**
     * Paths
     */
    val basePath = featureName.toLowerCase(Locale.getDefault())

    //domain
    val domainRepositoryPath = "$basePath/repositories"
    val domainRepositoryPackagePath = "$featurePackage.repositories"

    val domainUseCasesPath = "$basePath/useCases"
    val domainUseCasesPackagePath = "$featurePackage.useCases"

    /**
     * Create Files
     *
     */

    /**
     * Domain
     */
    val repository = cleanArchRepositorySimple(
        featurePackage = domainRepositoryPackagePath,
        featureName = featureName,
        repositoryName = repositoryName
    )
    save(
        repository,
        srcOut.resolve("$domainRepositoryPath/${repositoryName}.kt")
    )

    val useCases = cleanArchUseCaseSimple(
        featurePackage = domainUseCasesPackagePath,
        featureName = featureName,
        useCaseName = useCaseName,
        repositoryPackagePath = domainRepositoryPackagePath,
        repositoryName = repositoryName
    )
    save(
        useCases,
        srcOut.resolve("$domainUseCasesPath/${useCaseName}.kt")
    )
}
