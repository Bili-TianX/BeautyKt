package top.bilitianx.routes

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.testing.*
import top.bilitianx.module
import kotlin.test.Test

class BackendRoutesKtTest {

    @Test
    fun testGetBackendTags() = testApplication {
        application {
            module()
        }
        client.get("/backend/tags").apply {
            println(this.bodyAsText())
        }
    }
}
