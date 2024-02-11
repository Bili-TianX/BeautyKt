package top.bilitianx.backend.schemas

import kotlinx.serialization.Serializable

@Serializable
data class Album(
    val tagID: Long,
    val id: Long,
    val name: String,

    val cover: String,
    val date: String,
    val count: Long
)
