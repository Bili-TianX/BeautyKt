package top.bilitianx.backend.requests

import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import top.bilitianx.backend.*
import top.bilitianx.backend.schemas.Tag
import top.bilitianx.backend.utils.*


fun Network.getTags() = channelFlow {
    val html = client.get {
        url {
            protocol = PROTOCOL
            host = HOST
            pathSegments = listOf("ku", "tag")
        }
    }.html()

    html.selectXpath("""//div[@class="main"]//li""").forEach {
        launch {
            val a = it.findElementByXPath("a")!!
            val span = it.findElementByXPath("span")!!

            send(
                Tag(
                    Url(a["href"]).encodedPath.findNumber()!!,
                    a.text(),
                    span.text().findNumber()!!
                )
            )
        }
    }
}
