package hr.project.controller
/*
import hr.project.model.Coach
import hr.project.service.impl.CoachServiceImpl
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.*
import io.micronaut.http.annotation.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.SQLException
import javax.validation.ValidationException

@Controller("/api/coach")
class CoachController (
    var coachService: CoachServiceImpl
){

    @Get("/all")
    suspend fun getAllCoaches(): HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(coachService.getAll())
        } catch (e: NoSuchElementException) {
            notFound<Coach>().body(mapOf("message" to "Coach not found."))
        }
    }

    @Get("/{id}")
    suspend fun getCoachById(
        @PathVariable id: Int
    ):  HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(coachService.getById(id))
        } catch (e: NoSuchElementException) {
            notFound<Coach>().body(mapOf("message" to "Coach not found."))
        } catch (e: ValidationException) {
            badRequest<Coach>().body(mapOf("message" to e.message))
        } catch (e: Exception) {
            badRequest<Coach>().body(mapOf("message" to  "Server Error."))
        }

    }

    @Post("/create")
    suspend fun createCoach(
        @Body coach: Coach
    ): HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(coachService.create(coach))
        } catch (e: SQLException) {
            badRequest<Coach>().body(mapOf("message" to "Coach not created."))
        }
    }

    @Put("/update")
    suspend fun updateCoach(
        @Body coach: Coach
    ): HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(coachService.update(coach))
        } catch (e: SQLException) {
            badRequest<Coach>().body(mapOf("message" to "Coach not updated."))
        }
    }

    @Delete("/delete/{id}")
    suspend fun deleteCoach(
        @PathVariable id: Int
    ): HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(coachService.delete(id))
        } catch (e: SQLException) {
            badRequest<Coach>().body(mapOf("message" to "Coach not deleted."))
        }
    }
}
*/