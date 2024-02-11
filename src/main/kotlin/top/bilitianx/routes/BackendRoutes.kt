package top.bilitianx.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.flow.toList
import top.bilitianx.backend.Network
import top.bilitianx.backend.requests.*

fun Route.backendRouting() {
    route("/backend") {
        get("/tags") {
            call.respond(Network.getTags().toList())
        }

        get("/albums/{tagID?}/{index?}") {
            call.respond(
                Network.getAlbums(
                    call.parameters["tagID"]!!.toLong(),
                    call.parameters["index"]!!.toLong()
                )
            )
        }

        get("/photos/{tagID?}/{id?}") {
            call.respond(
                Network.getPhotos(
                    call.parameters["tagID"]!!.toLong(),
                    call.parameters["id"]!!.toLong()
                )
            )
        }
    }
}
