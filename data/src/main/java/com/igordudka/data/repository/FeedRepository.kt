package com.igordudka.data.repository

import com.igordudka.data.network.api.MainApi
import java.util.HashMap

class FeedRepository
    (private val mainApi: MainApi)
{
        suspend fun search(token: String) = mainApi.search(token)
    suspend fun sendTicketToOwner(token: String, body: HashMap<String, String>) = mainApi.sendTicketToOwner(token, body)
}