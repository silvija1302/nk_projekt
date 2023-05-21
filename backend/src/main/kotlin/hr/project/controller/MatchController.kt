package hr.project.controller
/*
import hr.project.model.Match
import hr.project.service.impl.MatchServiceImpl
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.*
import io.micronaut.http.annotation.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.SQLException
import javax.validation.ValidationException

@Controller("/api/match")
class MatchController (
    var matchService: MatchServiceImpl
){

    @Get("/all")
    suspend fun getAllMatches(): HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(matchService.getAll())
        } catch (e: NoSuchElementException) {
            notFound<Match>().body(mapOf("message" to "Match not found."))
        }
    }

    @Get("/{id}")
    suspend fun getMatchById(
        @PathVariable id: Int
    ):  HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(matchService.getById(id))
        } catch (e: NoSuchElementException) {
            notFound<Match>().body(mapOf("message" to "Match not found."))
        } catch (e: ValidationException) {
            badRequest<Match>().body(mapOf("message" to e.message))
        } catch (e: Exception) {
            badRequest<Match>().body(mapOf("message" to  "Server Error."))
        }

    }

    @Post("/create")
    suspend fun createMatch(
        @Body match: Match
    ): HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(matchService.create(match))
        } catch (e: SQLException) {
            badRequest<Match>().body(mapOf("message" to "Match not created."))
        }
    }

    @Put("/update")
    suspend fun updateMatch(
        @Body match: Match
    ): HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(matchService.update(match))
        } catch (e: SQLException) {
            badRequest<Match>().body(mapOf("message" to "Match not updated."))
        }
    }

    @Delete("/delete/{id}")
    suspend fun deleteMatch(
        @PathVariable id: Int
    ): HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(matchService.delete(id))
        } catch (e: SQLException) {
            badRequest<Match>().body(mapOf("message" to "Match not deleted."))
        }
    }
}
*/