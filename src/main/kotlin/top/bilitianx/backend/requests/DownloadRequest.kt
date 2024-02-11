package top.bilitianx.backend.requests

import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.*
import top.bilitianx.backend.Network
import java.nio.file.Path
import kotlin.io.path.writeBytes

suspend fun Network.downloadRequest(url: String, path: Path) {
    val bytes = client.get(url).readBytes()

    withContext(Dispatchers.IO) {
        path.writeBytes(bytes)
    }
}
