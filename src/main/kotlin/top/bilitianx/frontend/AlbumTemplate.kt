package top.bilitianx.frontend

import io.ktor.server.html.*
import kotlinx.html.*
import top.bilitianx.backend.schemas.Album

class AlbumTemplate(private val album: Album) : Template<FlowContent> {
    override fun FlowContent.apply() {
        div {
            a(href = "/frontend/photos/${album.tagID}/${album.id}") {
                p { +album.name }
            }
            p {
                span { +album.date }
                span { +" | " }
                span { +"${album.count}" }
            }
            a(href = "/frontend/download/${album.tagID}/${album.id}") {
                p { +"下载" }
            }
            img(alt = album.name, src = album.cover) { }
        }
    }
}
