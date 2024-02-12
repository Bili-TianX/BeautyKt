package top.bilitianx.frontend

import io.ktor.server.html.*
import kotlinx.html.*

class PhotosPage(private val images: Iterable<String>) : Template<HTML> {
    override fun HTML.apply() {
        head {
            link(rel = "stylesheet", href = "/frontend/styles.css", type = "text/css")
        }
        body {
            div("list") {
                images.forEach {
                    div {
                        img(src = it) {}
                    }
                }
            }
        }
    }
}
