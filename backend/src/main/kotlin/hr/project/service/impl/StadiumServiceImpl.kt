package hr.project.service.impl

import hr.project.model.Stadium
import hr.project.service.BasicService

class StadiumServiceImpl: BasicService<Stadium> {
    override suspend fun getAll(): List<Stadium> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: Int): Stadium {
        TODO("Not yet implemented")
    }

    override suspend fun create(t: Stadium): Stadium {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun update(t: Stadium): Stadium {
        TODO("Not yet implemented")
    }
}