package top.bilitianx.backend.requests

import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import top.bilitianx.backend.*
import top.bilitianx.backend.schemas.Album
import top.bilitianx.backend.utils.*

fun Network.getAlbums(tagID: Long, index: Long = 1L) = channelFlow {
    val html = client.get {
        url {
            protocol = PROTOCOL
            host = HOST
            pathSegments = listOf("ku", "$tagID", "list_${tagID}_${index}.html")
        }
    }.html()

    html.selectXpath("""//div[@class="list"]/div""").forEach { element ->
        launch {
            val img = element.findElementByXPath("div/a/img")!!
            val url = Url(element.findElementByXPath("a")!!["href"])
            val (newTagID, id) = url.encodedPath.findNumbers().toList()

            send(
                Album(
                    tagID = newTagID!!,
                    id = id!!,
                    name = img["alt"],
                    cover = img["src"],
                    date = element.findElementByXPath("""div/span[@class="time"]""")!!.text(),
                    count = element.findElementByXPath("""div/span[@class="click"]""")!!.text().findNumber()!!,
                )
            )
        }
    }
}
