package top.bilitianx.frontend

import io.ktor.server.html.*
import kotlinx.html.*
import top.bilitianx.backend.schemas.Tag

class TagsPage(private val tags: Iterable<Tag>) : Template<HTML> {
    override fun HTML.apply() {
        head {
            link(rel = "stylesheet", href = "/frontend/styles.css", type = "text/css")
        }
        body {
            div {
                tags.forEach { tag ->
                    insert(TagTemplate(tag)) { }
                }
            }
        }
    }
}
