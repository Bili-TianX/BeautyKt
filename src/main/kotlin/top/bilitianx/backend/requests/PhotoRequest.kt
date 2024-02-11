package top.bilitianx.backend.requests

import io.ktor.client.request.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.jsoup.nodes.Element
import top.bilitianx.backend.*
import top.bilitianx.backend.utils.*

private fun getImages(html: Element) = channelFlow {
    html.selectXpath("""//div[@class="content"]/img""").forEach {
        launch {
            send(it["src"])
        }
    }
}.buffer()

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun Network.getPhotos(tagID: Long, id: Long) = channelFlow {
    val firstHtml = client.get {
        url {
            protocol = PROTOCOL
            host = HOST
            pathSegments = listOf("ku", "$tagID", "${id}.html")
        }
    }.html()

    send(getImages(firstHtml))

    val count = firstHtml.findElementByXPath("""//div[@class="page-list"]/ul/a[1]""")!!.text().findNumber()!!

    (2..count).forEach { index ->
        launch {
            val html = client.get {
                url {
                    protocol = PROTOCOL
                    host = HOST
                    pathSegments = listOf("ku", "$tagID", "${id}_${index}.html")
                }
            }.html()

            send(getImages(html))
        }
    }
}.flattenMerge().buffer()
