package com.example.mvpexample.core.mapper

interface BiMapper<E, D> {

    fun mapFromEntity(type: E): D

    fun mapToEntity(type: D): E
}
