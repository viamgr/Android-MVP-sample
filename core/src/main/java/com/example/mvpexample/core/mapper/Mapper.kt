package com.example.mvpexample.core.mapper

interface Mapper<E, D> {
    fun map(type: E): D
}
