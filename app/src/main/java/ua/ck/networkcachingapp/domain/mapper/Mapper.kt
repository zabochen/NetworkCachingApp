package ua.ck.networkcachingapp.domain.mapper

interface Mapper<in I, out U> {
    fun map(data: I): U
}