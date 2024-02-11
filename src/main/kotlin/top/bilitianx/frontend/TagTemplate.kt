package top.bilitianx.frontend

import io.ktor.server.html.*
import kotlinx.html.*
import top.bilitianx.backend.schemas.Tag

class TagTemplate(private val tag: Tag) : Template<FlowContent> {
    override fun FlowContent.apply() {
        div {
            p { +tag.name }
            p { +"数量：${tag.count}" }
            a(href = "/frontend/albums/${tag.id}/1") {
                +"Url"
            }
        }
    }
}
