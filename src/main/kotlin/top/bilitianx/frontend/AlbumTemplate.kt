package top.bilitianx.frontend

import io.ktor.server.html.*
import kotlinx.html.*
import top.bilitianx.backend.schemas.Album

class AlbumTemplate(private val album: Album) : Template<FlowContent> {
    override fun FlowContent.apply() {
        div {
            p { +album.name }
            p { +"数量：${album.count}" }
            p { +"日期：${album.date}" }
            a(href = "/frontend/photos/${album.tagID}/${album.id}") { +"Url" }
            a(href = "/frontend/download/${album.tagID}/${album.id}") { +"Download" }
            img(alt = album.name, src = album.cover) { }
        }
    }
}
