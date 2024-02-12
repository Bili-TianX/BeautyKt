package top.bilitianx.frontend

import io.ktor.server.html.*
import kotlinx.html.*
import top.bilitianx.backend.schemas.Album

class AlbumsPage(private val albums: Iterable<Album>) : Template<HTML> {
    override fun HTML.apply() {
        head {
            link(rel = "stylesheet", href = "/frontend/styles.css", type = "text/css")
        }
        body {
            div("list") {
                albums.forEach { album ->
                    insert(AlbumTemplate(album)) {}
                }
            }
        }
    }
}
