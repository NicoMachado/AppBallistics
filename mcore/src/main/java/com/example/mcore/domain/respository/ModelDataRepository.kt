package com.example.mcore.domain.respository

import com.example.mcore.domain.models.ModelData

interface ModelDataRepository {
    fun getModelData(): ModelData

    fun addModelData(modelData: ModelData)
}