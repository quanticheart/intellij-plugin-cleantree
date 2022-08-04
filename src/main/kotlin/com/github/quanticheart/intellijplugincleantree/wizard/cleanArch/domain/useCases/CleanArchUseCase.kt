package com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.domain.useCases

fun cleanArchUseCase(
    featurePackage: String,
    featureName: String,
    useCaseName: String,
    repositoryPackagePath: String,
    repositoryName: String,
    domainModelPackage: String,
    domainModelRequestName: String,
    ) = """
package $featurePackage

import $domainModelPackage.$domainModelRequestName
import $repositoryPackagePath.$repositoryName

class $useCaseName(private val repository: $repositoryName) {
    suspend operator fun invoke(requestStatus: $domainModelRequestName) = repository.get$featureName(requestStatus)
}
"""
