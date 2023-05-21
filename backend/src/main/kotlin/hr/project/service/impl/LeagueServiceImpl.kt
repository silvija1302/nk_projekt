package hr.project.service.impl

import hr.project.model.League
import hr.project.service.BasicService
import jakarta.inject.Singleton

@Singleton
class LeagueServiceImpl: BasicService<League> {
    override suspend fun getAll(): List<League> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: Int): League {
        TODO("Not yet implemented")
    }

    override suspend fun create(t: League): League {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun update(t: League): League {
        TODO("Not yet implemented")
    }
}