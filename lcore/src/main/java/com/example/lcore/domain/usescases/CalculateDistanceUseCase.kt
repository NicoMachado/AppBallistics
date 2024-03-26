package com.example.lcore.domain.usescases

import android.location.Location
import com.example.lcore.domain.AppBallisticException

class CalculateDistanceUseCase {
    operator fun invoke(latFrom: Double, longFrom: Double, latTo: Double, longTo: Double): Float {
        val results = FloatArray(1)

        try {
            Location.distanceBetween(
                latFrom,
                longFrom,
                latTo,
                longTo,
                results
            )

        } catch (e: Exception) {
            throw  AppBallisticException(e.localizedMessage?:"Unknown Error")
        }
        return results[0]
    }
}