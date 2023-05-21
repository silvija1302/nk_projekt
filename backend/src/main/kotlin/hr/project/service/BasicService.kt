package hr.project.service

interface BasicService<T> {
    suspend fun getAll(): List<T>
    suspend fun getById(id: Int): T
    suspend fun create(t: T): T
    suspend fun update(t: T): T
    suspend fun delete(id: Int): Boolean
}