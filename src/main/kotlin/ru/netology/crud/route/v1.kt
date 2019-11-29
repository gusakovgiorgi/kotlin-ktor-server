package ru.netology.crud.route

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.features.NotFoundException
import io.ktor.features.ParameterConversionException
import io.ktor.request.receive
import io.ktor.request.receiveText
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.util.pipeline.PipelineContext
import org.kodein.di.generic.instance
import org.kodein.di.ktor.kodein
import ru.netology.crud.dto.PostRequestDto
import ru.netology.crud.dto.PostResponseDto
import ru.netology.crud.model.PostModel
import ru.netology.crud.repository.PostRepository

fun Routing.v1() {
    val repo by kodein().instance<PostRepository>()

    route("/api/v1/posts") {
        get {
            log()
            val response = repo.getAll().map { PostResponseDto.fromModel(it) }
            call.respond(response)
        }
        get("/{id}") {
            log()
            val id = call.parameters["id"]?.toLongOrNull() ?: throw ParameterConversionException("id", "Long")
            val model = repo.getById(id) ?: throw NotFoundException()
            val response = PostResponseDto.fromModel(model)
            call.respond(response)
        }
        post {
            log()
            val input = call.receive<PostRequestDto>()
            val model = PostModel(id = input.id, author = input.author, content = input.content)
            val response = PostResponseDto.fromModel(repo.save(model))
            call.respond(response)
        }
        delete("/{id}") {
            TODO()
        }
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.log() {
    println(call.toString())
    println("parameters: ${call.parameters}")
    println("body: ${call.receiveText()}")
}