package hr.project.service.impl

import hr.project.model.Coach
import hr.project.repository.CoachRepository
import hr.project.service.BasicService
import jakarta.inject.Singleton

@Singleton
class CoachServiceImpl(
    var coachRepository: CoachRepository
): BasicService<Coach> {
    override suspend fun getAll(): List<Coach> = coachRepository.findAll()

    override suspend fun getById(id: Int): Coach {
        TODO("Not yet implemented")
    }

    override suspend fun create(t: Coach): Coach {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun update(t: Coach): Coach {
        TODO("Not yet implemented")
    }
}