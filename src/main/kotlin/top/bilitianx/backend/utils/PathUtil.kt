package top.bilitianx.backend.utils

import java.nio.file.*
import kotlin.io.path.*

fun String.asValidPath(): Path {
    return Path(replace("""[<>:"/\\|?*]""".toRegex(), "_"))
}

fun Path.tryCreateDirectory(): Path {
    try {
        createDirectory()
    } catch (_: FileAlreadyExistsException) {
    }

    return this
}
