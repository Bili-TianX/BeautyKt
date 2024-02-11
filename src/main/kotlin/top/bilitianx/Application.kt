package top.bilitianx

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import top.bilitianx.plugins.*

fun main() {
    println("http://127.0.0.1:5000/frontend/tags")
    embeddedServer(
        Netty,
        port = 5000,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    configureTemplating()
    configureRouting()
    configureSerialization()
}
