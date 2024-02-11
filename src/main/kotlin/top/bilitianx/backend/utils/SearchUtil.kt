package top.bilitianx.backend.utils

private val numberRegex = Regex("""\d+""")

fun String.findNumber() =
    numberRegex.find(this)?.value?.toLongOrNull()

fun String.findNumbers() =
    numberRegex.findAll(this).map { it.value.toLongOrNull() }
