package top.bilitianx.backend.utils

import io.ktor.client.statement.*
import org.jsoup.Jsoup
import org.jsoup.nodes.*
import java.nio.charset.Charset

internal val GBK = Charset.forName("GBK")

suspend fun HttpResponse.html(): Document =
    Jsoup.parse(bodyAsText(GBK))

fun Element.findElementByXPath(xpath: String): Element? = selectXpath(xpath).first()

operator fun Element.get(key: String): String = attr(key)
