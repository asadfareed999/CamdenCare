package com.camdencare.app.networking

import com.camdencare.app.networking.responsemodels.ResponseLogin
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpointClient {

    companion object {
        const val API_V = "api/v4/"

        const val LOGIN = "${API_V}patients/verify_patient_by_contact_no/"
        const val QUERY_MRN = "mrn"
        const val QUERY_PHONE1 = "phone1"
    }

    @GET(LOGIN)
    fun login(
        @Query(QUERY_MRN) mrn: String,
        @Query(QUERY_PHONE1) phone1: String
    ): Call<ResponseLogin>
}