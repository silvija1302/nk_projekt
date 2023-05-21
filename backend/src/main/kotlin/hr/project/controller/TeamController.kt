package hr.project.controller

import hr.project.model.Team
import hr.project.service.impl.PlayerServiceImpl
import hr.project.service.impl.TeamServiceImpl
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.*
import io.micronaut.http.annotation.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.SQLException
import javax.validation.ValidationException

@Controller("/api/team")
class TeamController (
    private var teamService: TeamServiceImpl,
    private var playerService: PlayerServiceImpl
){

    @Get("/all")
    suspend fun getAllTeams(): HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(teamService.getAll())
        } catch (e: NoSuchElementException) {
            notFound<Team>().body(mapOf("message" to "Team not found."))
        }
    }

    @Get("/{id}")
    suspend fun getTeamById(
        @PathVariable id: Int
    ):  HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(teamService.getById(id).toDto().also { it.players = playerService.getPlayerFromTeam(it.id) })
        } catch (e: NoSuchElementException) {
            notFound<Team>().body(mapOf("message" to "Team not found."))
        } catch (e: ValidationException) {
            badRequest<Team>().body(mapOf("message" to e.message))
        } catch (e: Exception) {
            badRequest<Team>().body(mapOf("message" to  "Server Error."))
        }

    }

    @Post("/create")
    suspend fun createTeam(
        @Body team: Team
    ): HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(teamService.create(team))
        } catch (e: SQLException) {
            badRequest<Team>().body(mapOf("message" to "Team not created."))
        } catch (e: ValidationException) {
            badRequest<Team>().body(mapOf("message" to e.message))
        }
    }

    @Put("/update")
    suspend fun updateTeam(
        @Body team: Team
    ): HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(teamService.update(team))
        } catch (e: SQLException) {
            badRequest<Team>().body(mapOf("message" to "Team not updated."))
        }
    }

    @Delete("/delete/{id}")
    suspend fun deleteTeam(
        @PathVariable id: Int
    ): HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(teamService.delete(id))
        } catch (e: SQLException) {
            badRequest<Team>().body(mapOf("message" to "Team not deleted."))
        }
    }
}