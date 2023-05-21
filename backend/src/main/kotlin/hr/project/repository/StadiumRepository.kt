package hr.project.repository

import hr.project.model.Stadium
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface StadiumRepository: CrudRepository<Stadium, Long> {
    override fun findAll(): List<Stadium>
    fun findById(id: Int): Stadium
    fun save(stadium: Stadium): Stadium
    fun update(stadium: Stadium): Stadium
    fun deleteById(id: Int)
}