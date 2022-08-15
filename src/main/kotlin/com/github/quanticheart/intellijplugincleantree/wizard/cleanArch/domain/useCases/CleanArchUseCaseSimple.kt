package com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.domain.useCases

fun cleanArchUseCaseSimple(
    featurePackage: String,
    featureName: String,
    useCaseName: String,
    repositoryPackagePath: String,
    repositoryName: String
    ) = """
package $featurePackage

import $repositoryPackagePath.$repositoryName

class $useCaseName(private val repository: $repositoryName) {
    suspend operator fun invoke() = repository.get$featureName()
}
"""
