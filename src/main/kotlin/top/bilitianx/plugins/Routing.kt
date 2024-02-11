package top.bilitianx.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import top.bilitianx.routes.*

fun Application.configureRouting() {
    routing {
        frontendRouting()
        cssRouting()
        backendRouting()
    }
}
