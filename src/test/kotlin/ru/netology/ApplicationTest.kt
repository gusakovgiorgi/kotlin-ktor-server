package ru.netology

import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.contentType
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    private val jsonContentType = ContentType.Application.Json
    @Test
    fun testGetAll() {

        withTestApplication({ module() }) {
            handleRequest(HttpMethod.Get, "/api/v1/posts").run {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(jsonContentType, response.contentType())
            }
        }
    }
}
