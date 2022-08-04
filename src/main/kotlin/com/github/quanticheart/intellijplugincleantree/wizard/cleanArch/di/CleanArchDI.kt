package com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.di

fun cleanArchDI(
    featurePackage: String,
    featureName: String,
    endPointsNane: String,
    repositoryNane: String,
    repositoryImplNane: String,
    useCaseName: String,
    viewModelName: String,
    dataPackage: String,
    repositoryPackage: String,
    useCasesPackage: String,
    uiPackage: String

) = """
package $featurePackage

import $dataPackage.$endPointsNane
import $dataPackage.$repositoryImplNane
import $repositoryPackage.$repositoryNane
import $useCasesPackage.$useCaseName
import $uiPackage.$viewModelName
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val dataModules = module {
    factory<$endPointsNane> { Api.create(context = get()) }
    factory<$repositoryNane> { $repositoryImplNane(get()) }
}

private val domainModules = module {
    factory { $useCaseName(get()) }
}

private val viewModelModules = module {
    viewModel { $viewModelName(get()) }
}

internal val ${featureName}Module = listOf(
    dataModules,
    domainModules,
    viewModelModules
)
"""
