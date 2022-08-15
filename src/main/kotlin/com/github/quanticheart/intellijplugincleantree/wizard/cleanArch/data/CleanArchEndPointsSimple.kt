package com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.data

fun cleanArchEndPointsSimple(
    featurePackage: String,
    endPointsName: String
) = """
package $featurePackage

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface $endPointsName {
    @GET("auth/example/")
    suspend fun requestStatus(): Response<Boolean>
}
"""
