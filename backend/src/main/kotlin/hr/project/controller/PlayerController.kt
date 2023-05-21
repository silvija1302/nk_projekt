package hr.project.controller

import hr.project.model.Player
import hr.project.service.impl.PlayerServiceImpl
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.*
import io.micronaut.http.annotation.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.SQLException
import javax.validation.ValidationException

@Controller("/api/player")
class PlayerController (
    var playerService: PlayerServiceImpl
){

    @Get("/all")
    suspend fun getAllPlayers(): HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(playerService.getAll())
        } catch (e: NoSuchElementException) {
            notFound<Player>().body(mapOf("message" to "Player not found."))
        }
    }

    @Get("/{id}")
    suspend fun getPlayerById(
        @PathVariable id: Int
    ):  HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(playerService.getById(id))
        } catch (e: NoSuchElementException) {
            notFound<Player>().body(mapOf("message" to "Player not found."))
        } catch (e: ValidationException) {
            badRequest<Player>().body(mapOf("message" to e.message))
        } catch (e: Exception) {
            badRequest<Player>().body(mapOf("message" to  "Server Error."))
        }

    }

    @Post("/create")
    suspend fun createPlayer(
        @Body player: Player
    ): HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(playerService.create(player))
        } catch (e: SQLException) {
            badRequest<Player>().body(mapOf("message" to "Player not created."))
        }
    }

    @Put("/update")
    suspend fun updatePlayer(
        @Body player: Player
    ): HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(playerService.update(player))
        } catch (e: SQLException) {
            badRequest<Player>().body(mapOf("message" to "Player not updated."))
        }
    }

    @Delete("/delete/{id}")
    suspend fun deletePlayer(
        @PathVariable id: Int
    ): HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(playerService.delete(id))
        } catch (e: SQLException) {
            badRequest<Player>().body(mapOf("message" to "Player not deleted."))
        }
    }
}