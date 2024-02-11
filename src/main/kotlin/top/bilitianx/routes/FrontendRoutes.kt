package top.bilitianx.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.toList
import kotlinx.css.*
import kotlinx.html.*
import top.bilitianx.backend.Network
import top.bilitianx.backend.requests.*
import top.bilitianx.backend.utils.tryCreateDirectory
import top.bilitianx.frontend.*
import java.awt.Desktop
import java.util.*
import kotlin.io.path.Path
import kotlin.time.measureTime


fun Route.frontendRouting() {
    route("/frontend") {
        get("/tags") {
            call.respondHtmlTemplate(
                TagsPage(
                    Network.getTags().toList()
                )
            ) {}
        }

        get("/albums/{tagID?}/{index?}") {
            call.respondHtmlTemplate(
                AlbumsPage(
                    Network.getAlbums(
                        call.parameters["tagID"]!!.toLong(),
                        call.parameters["index"]!!.toLong()
                    ).toList()
                )
            ) {}
        }

        get("/photos/{tagID?}/{id?}") {
            call.respondHtmlTemplate(
                PhotosPage(
                    Network.getPhotos(
                        call.parameters["tagID"]!!.toLong(),
                        call.parameters["id"]!!.toLong()
                    ).toList()
                )
            ) {}
        }

        get("/download/{tagID?}/{id?}") {
            val path = Path("images", "${UUID.randomUUID()}").tryCreateDirectory()

            val time = measureTime {
                launch(Dispatchers.Default) {
                    Network.getPhotos(
                        call.parameters["tagID"]!!.toLong(),
                        call.parameters["id"]!!.toLong()
                    ).collect {
                        launch { Network.downloadRequest(it, path.resolve("${UUID.randomUUID()}.jpg")) }
                    }
                }.join()
            }

            Desktop.getDesktop().open(path.toFile())

            call.respondHtml {
                body {
                    h1 {
                        +"下载耗时：$time"
                    }
                }
            }
        }
    }
}

private suspend inline fun ApplicationCall.respondCss(builder: CSSBuilder.() -> Unit) {
    this.respondText(CSSBuilder().apply(builder).toString(), ContentType.Text.CSS)
}

fun Route.cssRouting() {
    route("/frontend") {
        get("/styles.css") {
            call.respondCss {
                div {
                    borderColor = Color.black
                    borderRadius = 10.px
                    borderWidth = 3.px
                    borderStyle = BorderStyle.solid
                }
            }
        }
    }
}
