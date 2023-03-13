package com.example.test_task_compose_2.data.retrofit.model

import com.example.test_task_compose_2.ui.model.ListsUserUi
import com.google.gson.annotations.SerializedName

data class ListsUserApi(
    val login: String? = null,
    val id: Int? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @SerializedName("node_id")
    val nodeId: String? = null
)

fun ListsUserApi.listsUserApiToUi(): ListsUserUi = ListsUserUi(
    login = this.login ?: "",
    id = this.id ?: 0,
    avatarUrl = this.avatarUrl ?: "",
    nodeId = this.nodeId ?: ""
)
