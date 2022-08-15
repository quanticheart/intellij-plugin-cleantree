package com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.recipes.clean

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.data.*
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.di.cleanArchDI
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.domain.model.cleanArchModelRequest
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.domain.model.cleanArchModelResponse
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.domain.repository.cleanArchRepository
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.domain.useCases.cleanArchUseCase
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.res.layout.mvvmXml
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.ui.cleanArchActivity
import com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.ui.cleanArchViewModel
import java.util.*

fun RecipeExecutor.cleanArchRecipe(
    moduleData: ModuleTemplateData,
    featureName: String,
    layoutName: String,
    packageName: String
) {

    val (projectData, srcOut, resOut) = moduleData

    /**
     * Packages
     */
    val applicationPackage = projectData.applicationPackage
    val featurePackage = "${packageName}.${featureName.toLowerCase()}"

//    val project = DataManager.getInstance().dataContext.getData(DataConstants.PROJECT) as Project
//    val virtualFiles: Array<VirtualFile> = ProjectRootManager.getInstance(project).contentSourceRoots
//    val srcPath = virtualFiles.first { it.path.contains("${applicationPackage}/src/main/java") }
//    val resPath = virtualFiles.first { it.path.contains("${applicationPackage}/src/main/res") }
//    val testSrcPath = virtualFiles.first { it.path.contains("${applicationPackage}/src/test/java") }

//    addAllKotlinDependencies(moduleData)
//    val format = SimpleDateFormat("yyyy/MM/dd")
//    val date = format.format(Date())
//
//    val path = srcOut.absolutePath.replace("java", "kotlin")
//    val srcKotlinDir = File(path)
//    save(
//        someFragmentViewModel(entityName, layoutName),
//        srcKotlinDir.resolve("${entityName}ViewModel.kt")
//    )

    //ui
    val activityName = "${featureName}Activity"
    val activityViewBindingName = "Activity${featureName}Binding"
    val viewModelName = "${featureName}ViewModel"

    //domain
    val repositoryName = "${featureName}Repository"
    val useCaseName = "${featureName}UseCase"

    val requestModelName = "Request${featureName}Status"
    val responseModelName = "${featureName}Result"

    //data
    val repositoryImplName = "${featureName}RepositoryImpl"
    val endPointsName = "${featureName}EndPoints"

    val requestDataModelName = "${featureName}Request"
    val responseDataModelName = "${featureName}Response"

    val requestDataMapperName = "${featureName}StatusToRequest"
    val responseDataMapperName = "ResponseTo${featureName}Result"

    //di
    val diName = "${featureName}DI"

    /**
     * Paths
     */
    val basePath = featureName.toLowerCase(Locale.getDefault())
    //ui
    val uiPath = "$basePath/ui"
    val uiPackagePath = "$featurePackage.ui"

    //domain
    val domainRepositoryPath = "$basePath/domain/repositories"
    val domainRepositoryPackagePath = "$featurePackage.domain.repositories"

    val domainModelPath = "$basePath/domain/models"
    val domainModelPackagePath = "$featurePackage.domain.models"

    val domainUseCasesPath = "$basePath/domain/useCases"
    val domainUseCasesPackagePath = "$featurePackage.domain.useCases"

    //data
    val dataRepositoryImplPath = "$basePath/data"
    val dataRepositoryImplPackagePath = "$featurePackage.data"

    val domainDataRequestModelPath = "$basePath/data/requests"
    val domainDataRequestModelPackagePath = "$featurePackage.data.requests"

    val domainDataResponseModelPath = "$basePath/data/responses"
    val domainDataResponseModelPackagePath = "$featurePackage.data.responses"

    val domainDataMapperPath = "$basePath/data/mappers/request"
    val domainDataMapperPackagePath = "$featurePackage.data.mappers.request"

    val domainDataMapperResponsePath = "$basePath/data/mappers/response"
    val domainDataMapperResponsePackagePath = "$featurePackage.data.mappers.response"

    /**
     * Create Files
     *
     * Layout
     */
    val xml = mvvmXml()
    save(xml, resOut.resolve("layout/$layoutName.xml"))

    /**
     * UI
     */
    val activity = cleanArchActivity(
        applicationPackage = applicationPackage,
        featurePackage = uiPackagePath,
        activityName = activityName,
        viewBindingName = activityViewBindingName,
        viewModelName = viewModelName
    )
    save(activity, srcOut.resolve("$uiPath/${activityName}.kt"))

    val viewModel = cleanArchViewModel(
        featurePackage = uiPackagePath,
        viewModelName = viewModelName,
        useCaseNamePackage = domainUseCasesPackagePath,
        useCaseName = useCaseName,
        modelPackagePackage = domainModelPackagePath,
        modelName = requestModelName
    )
    save(viewModel, srcOut.resolve("$uiPath/${viewModelName}.kt"))

    /**
     * Domain
     */
    val repository = cleanArchRepository(
        featurePackage = domainRepositoryPackagePath,
        featureName = featureName,
        repositoryName = repositoryName,
        domainModelPackage = domainModelPackagePath,
        domainModelRequestName = requestModelName,
        domainModelResponseName = responseModelName
    )
    save(
        repository,
        srcOut.resolve("$domainRepositoryPath/${repositoryName}.kt")
    )

    val useCases = cleanArchUseCase(
        featurePackage = domainUseCasesPackagePath,
        featureName = featureName,
        useCaseName = useCaseName,
        repositoryPackagePath = domainRepositoryPackagePath,
        repositoryName = repositoryName,
        domainModelPackage = domainModelPackagePath,
        domainModelRequestName = requestModelName
    )
    save(
        useCases,
        srcOut.resolve("$domainUseCasesPath/${useCaseName}.kt")
    )

    val modelRequestCases = cleanArchModelRequest(
        featurePackage = domainModelPackagePath,
        modelRequestName = requestModelName
    )
    save(
        modelRequestCases,
        srcOut.resolve("$domainModelPath/${requestModelName}.kt")
    )

    val modelResponseCases = cleanArchModelResponse(
        featurePackage = domainModelPackagePath,
        modelResponseName = responseModelName
    )
    save(
        modelResponseCases,
        srcOut.resolve("$domainModelPath/${responseModelName}.kt")
    )

    /**
     * Data
     */
    val repositoryImpl = cleanArchRepositoryImpl(
        featurePackage = dataRepositoryImplPackagePath,
        featureName = featureName,
        repositoryName = repositoryName,
        endPointsName = endPointsName,
        repositoryImplName = repositoryImplName,
        importRepositoryPackage = domainRepositoryPackagePath,
        domainModelsPackage = domainModelPackagePath,
        domainModelRequestName = requestModelName,
        domainModelResponseName = responseModelName,
        dataMapperRequestPackage = domainDataMapperPackagePath,
        dataMapperRequestName = requestDataMapperName,
        dataMapperResponsePackage = domainDataMapperResponsePackagePath,
        dataMapperResponseName = responseDataMapperName
    )
    save(
        repositoryImpl,
        srcOut.resolve("$dataRepositoryImplPath/${repositoryImplName}.kt")
    )

    val endpoints = cleanArchEndPoints(
        featurePackage = dataRepositoryImplPackagePath,
        endPointsName = endPointsName,
        dataRequestPackage = domainDataRequestModelPackagePath,
        dataRequestName = requestDataModelName,
        dataResponsePackage = domainDataResponseModelPackagePath,
        dataResponseName = responseDataModelName
    )
    save(
        endpoints,
        srcOut.resolve("$dataRepositoryImplPath/${endPointsName}.kt")
    )

    // request
    val modelDataRequestCases = cleanArchDataModelRequest(
        featurePackage = domainDataRequestModelPackagePath,
        modelRequestName = requestDataModelName
    )
    save(
        modelDataRequestCases,
        srcOut.resolve("$domainDataRequestModelPath/${requestDataModelName}.kt")
    )

    //response
    val modelDataResponseCases = cleanArchDataModelResponse(
        featurePackage = domainDataResponseModelPackagePath,
        modelResponseName = responseDataModelName
    )
    save(
        modelDataResponseCases,
        srcOut.resolve("$domainDataResponseModelPath/${responseDataModelName}.kt")
    )

    // Mappers
    val mapperDataRequestCases = cleanArchDataMapperRequest(
        featurePackage = domainDataMapperPackagePath,
        mapperRequestName = requestDataMapperName,
        dataRequestPackage = domainDataRequestModelPackagePath,
        requestName = requestDataModelName,
        modelRequestPackage = domainModelPackagePath,
        requestModelName = requestModelName
    )
    save(
        mapperDataRequestCases,
        srcOut.resolve("$domainDataMapperPath/${requestDataMapperName}.kt")
    )

    val mapperDataResponseCases = cleanArchDataMapperResponse(
        featurePackage = domainDataMapperResponsePackagePath,
        mapperResponseName = responseDataMapperName,
        dataResponsePackage = domainDataResponseModelPackagePath,
        responseName = responseDataModelName,
        modelResponsePackage = domainModelPackagePath,
        responseModelName = responseModelName
    )
    save(
        mapperDataResponseCases,
        srcOut.resolve("$domainDataMapperResponsePath/${responseDataMapperName}.kt")
    )

    /**
     * DI
     */
    val di = cleanArchDI(
        featurePackage = featurePackage,
        featureName = featureName,
        endPointsNane = endPointsName,
        repositoryNane = repositoryName,
        repositoryImplNane = repositoryImplName,
        useCaseName = useCaseName,
        viewModelName = viewModelName,
        dataPackage = dataRepositoryImplPackagePath,
        repositoryPackage = domainRepositoryPackagePath,
        useCasesPackage = domainUseCasesPackagePath,
        uiPackage = uiPackagePath
    )
    save(
        di, srcOut.resolve("$basePath/${diName}.kt")
    )
}
