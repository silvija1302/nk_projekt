package hr.project.controller

import hr.project.model.League
import hr.project.service.impl.LeagueServiceImpl
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.*
import io.micronaut.http.annotation.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.SQLException
import javax.validation.ValidationException

@Controller("/api/league")
class LeagueController (
    var leagueService: LeagueServiceImpl
){

    @Get("/all")
    suspend fun getAllLeagues(): HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(leagueService.getAll())
        } catch (e: NoSuchElementException) {
            notFound<League>().body(mapOf("message" to "League not found."))
        }
    }

    @Get("/{id}")
    suspend fun getLeagueById(
        @PathVariable id: Int
    ):  HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(leagueService.getById(id))
        } catch (e: NoSuchElementException) {
            notFound<League>().body(mapOf("message" to "League not found."))
        } catch (e: ValidationException) {
            badRequest<League>().body(mapOf("message" to e.message))
        } catch (e: Exception) {
            badRequest<League>().body(mapOf("message" to  "Server Error."))
        }

    }

    @Post("/create")
    suspend fun createLeague(
        @Body league: League
    ): HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(leagueService.create(league))
        } catch (e: SQLException) {
            badRequest<League>().body(mapOf("message" to "League not created."))
        }
    }

    @Put("/update")
    suspend fun updateLeague(
        @Body league: League
    ): HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(leagueService.update(league))
        } catch (e: SQLException) {
            badRequest<League>().body(mapOf("message" to "League not updated."))
        }
    }

    @Delete("/delete/{id}")
    suspend fun deleteLeague(
        @PathVariable id: Int
    ): HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(leagueService.delete(id))
        } catch (e: SQLException) {
            badRequest<League>().body(mapOf("message" to "League not deleted."))
        }
    }
}