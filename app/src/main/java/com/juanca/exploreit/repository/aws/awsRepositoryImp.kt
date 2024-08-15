package com.juanca.exploreit.repository.aws

import com.juanca.exploreit.data.local.AppDataBase
import com.juanca.exploreit.data.remote.Api
import com.juanca.exploreit.models.Places
import com.juanca.exploreit.models.PlacesDto
import com.juanca.exploreit.utils.OperationResult

class awsRepositoryImp: awsRepository {
    override suspend fun getPlaces(): OperationResult<PlacesDto> {
        return try {

            val response = Api.build().getInfoPlaces()
            if (response.isSuccessful) {
                OperationResult.Complete(response.body())
            } else {
                OperationResult.Failure(java.lang.Exception(response.body()?.description))
            }

        } catch (e: Exception) {
            OperationResult.Failure(e)
        }
    }

    override suspend fun savePlaces(places: List<Places>): OperationResult<String> {
        return try {

           places.forEach {
               AppDataBase.instance?.placesDao()?.savePlaces(it)
            }
            OperationResult.Complete("Success")

        } catch (e: Exception) {
            OperationResult.Failure(e)
        }
    }
}