package hr.project.controller

import hr.project.model.Training
import hr.project.service.impl.TrainingServiceImpl
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.*
import io.micronaut.http.annotation.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.SQLException
import javax.validation.ValidationException

@Controller("/api/training")
class TrainingController(
    var trainingService: TrainingServiceImpl
) {
    @Get("/all")
    suspend fun getAllTrainings(): HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(trainingService.getAll())
        } catch (e: NoSuchElementException) {
            notFound<Training>().body(mapOf("message" to "Training not found."))
        }
    }

    @Get("/{id}")
    suspend fun getTrainingById(
        @PathVariable id: Int
    ):  HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(trainingService.getById(id))
        } catch (e: NoSuchElementException) {
            notFound<Training>().body(mapOf("message" to "Training not found."))
        } catch (e: ValidationException) {
            badRequest<Training>().body(mapOf("message" to e.message))
        } catch (e: Exception) {
            badRequest<Training>().body(mapOf("message" to  "Server Error."))
        }

    }

    @Post("/create")
    suspend fun createTraining(
        @Body training: Training
    ): HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(trainingService.create(training))
        } catch (e: SQLException) {
            badRequest<Training>().body(mapOf("message" to "Training not created."))
        }
    }

    @Put("/update")
    suspend fun updateTraining(
        @Body training: Training
    ): HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(trainingService.update(training))
        } catch (e: SQLException) {
            badRequest<Training>().body(mapOf("message" to "Training not updated."))
        }
    }

    @Delete("/delete/{id}")
    suspend fun deleteTraining(
        @PathVariable id: Int
    ): HttpResponse<out Any> = withContext(Dispatchers.IO) {
        try {
            ok(trainingService.delete(id))
        } catch (e: SQLException) {
            badRequest<Training>().body(mapOf("message" to "Training not deleted."))
        }
    }
}