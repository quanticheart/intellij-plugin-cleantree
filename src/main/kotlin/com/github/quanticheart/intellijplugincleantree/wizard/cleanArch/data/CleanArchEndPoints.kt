package com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.data

fun cleanArchEndPoints(
    featurePackage: String,
    endPointsName: String,
    dataRequestPackage: String,
    dataRequestName: String,
    dataResponsePackage: String,
    dataResponseName: String
) = """
package $featurePackage

import $dataRequestPackage.$dataRequestName
import $dataResponsePackage.$dataResponseName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface $endPointsName {
    @POST("auth/example/")
    suspend fun requestStatus(@Body request: $dataRequestName): Response<$dataResponseName>
}
"""
