package com.example.domain.core

interface DomainMapper<T, DomainModel> {
    fun mapFromDomainModel(model: T): DomainModel
    fun mapToDomainModel(domainModel: DomainModel): T
}