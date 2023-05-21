package hr.project

import hr.project.model.*
import hr.project.service.impl.TeamServiceImpl
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import reactor.kotlin.core.publisher.toMono
import java.util.*

@MicronautTest
class BackendTest(
) {

    @Inject
    lateinit var application: EmbeddedApplication<*>

    @Test
    fun testItWorks() {
        Assertions.assertTrue(application.isRunning)
    }

    private val teamService: TeamServiceImpl = mock(TeamServiceImpl::class.java)

    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    suspend fun `get team`() {
        `when`(teamService.getById(1)).thenReturn(
            Team(
                1,
                "gnk dinamo zagreb",
            "dzg",
                Stadium(1, "Maksimir", 60000, "Zagreb"),
                Coach(1, "Igor", "Biscan", Date(1978, 5,4 ), "Zagreb", Nationality(1, "Croatia"), null),
                League(1, "SuperSport Hrvatska nogometna liga")
            )
        )
        var response = client.exchange(
            HttpRequest.GET<Any>("/api/team/1"),
           Team::class.java
        ).toMono().block()!!

        assertEquals(HttpStatus.OK, response.status())
        assertEquals(1, response.body().id)
    }

}
