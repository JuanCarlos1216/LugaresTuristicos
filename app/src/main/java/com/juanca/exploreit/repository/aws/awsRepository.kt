package com.juanca.exploreit.repository.aws

import com.juanca.exploreit.models.Places
import com.juanca.exploreit.models.PlacesDto
import com.juanca.exploreit.utils.OperationResult

interface awsRepository {
    suspend fun getPlaces(): OperationResult<PlacesDto>
    suspend fun savePlaces(places: List<Places>): OperationResult<String>
}