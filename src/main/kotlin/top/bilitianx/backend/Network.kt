package top.bilitianx.backend

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.http.*

internal val PROTOCOL = URLProtocol.HTTPS
internal const val HOST = "www.hh12345.cc"

object Network : AutoCloseable {
    internal val client: HttpClient = HttpClient(CIO) {
        BrowserUserAgent()

        engine {
            proxy = null
        }
    }

    override fun close() = client.close()
}
