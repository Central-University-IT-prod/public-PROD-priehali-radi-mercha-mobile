package com.igordudka.data.network.api

import com.igordudka.data.model.InviteResponse
import com.igordudka.data.network.model.GetMembersReponse
import com.igordudka.data.network.model.GetTeamResponse
import com.igordudka.data.network.model.ProfileResponse
import com.igordudka.data.network.model.RegisterResponse
import com.igordudka.data.network.model.TicketForOwnerResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.nio.channels.AsynchronousByteChannel

interface MainApi {

    @Headers(
        "Content-Type: application/json"
    )
    @POST("auth/register")
    suspend fun registerUser(
        @Body body: HashMap<String, String>
    ) : RegisterResponse

    @Headers(
        "Content-Type: application/json"
    )
    @POST("auth/login")
    suspend fun loginUser(
        @Body body: HashMap<String, String>
    ) : RegisterResponse

    @Headers(
        "Content-Type: application/json"
    )
    @PATCH("profile")
    suspend fun getProfileInfo(
        @Header("Authorization") token: String,
        @Body body: Body = Body()
    ) : ProfileResponse

    @Headers(
        "Content-Type: application/json"
    )
    @GET("profile/{id}")
    suspend fun getProfile(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ) : ProfileResponse

    @Headers(
        "Content-Type: application/json"
    )
    @POST("team/create")
    suspend fun createTeam(
        @Header("Authorization") token: String,
        @Body body: HashMap<String, String>
    ) : RegisterResponse

    @Headers(
        "Content-Type: application/json"
    )
    @GET("team")
    suspend fun getTeam(
        @Header("Authorization") token: String,
        @Query("team_id") teamId: String
    ) : GetTeamResponse

    @Headers(
        "Content-Type: application/json"
    )
    @PATCH("profile")
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Body body: HashMap<String, Any>
    ) : ProfileResponse

    @Headers(
        "Content-Type: application/json"
    )
    @POST("vacancy")
    suspend fun addVacancy(
        @Header("Authorization") token: String,
        @Body body: HashMap<String, String>
    ) : RegisterResponse

    @Headers(
        "Content-Type: application/json"
    )
    @GET("members/team_id")
    suspend fun getTeamId(
        @Header("Authorization") token: String,
    ) : RegisterResponse

    @Headers(
        "Content-Type: application/json"
    )
    @POST("members/add")
    suspend fun addMember(
        @Header("Authorization") token: String,
        @Body body: HashMap<String, String>
    ) : RegisterResponse

    @Headers(
        "Content-Type: application/json"
    )
    @GET("search")
    suspend fun search(
        @Header("Authorization") token: String,
    ) : List<Map<String, String>>

    @Headers(
        "Content-Type: application/json"
    )
    @POST("ticket")
    suspend fun sendTicketToOwner(
        @Header("Authorization") token: String,
        @Body body: HashMap<String, String>
    ) : RegisterResponse

    @Headers(
        "Content-Type: application/json"
    )
    @GET("ticket")
    suspend fun getTicketsForOwner(
        @Header("Authorization") token: String,
        @Query("team_id") teamId: String
    ) : TicketForOwnerResponse

    @Headers(
        "Content-Type: application/json"
    )
    @GET("members")
    suspend fun getMembers(
        @Header("Authorization") token: String,
        @Query("team_id") teamId: String
    ) : GetMembersReponse

    @Headers(
        "Content-Type: application/json"
    )
    @PATCH("team")
    suspend fun editTeam(
        @Header("Authorization") token: String,
        @Body body: HashMap<String, String>
    ) : GetTeamResponse

    @Headers(
        "Content-Type: application/json"
    )
    @POST("members/kick")
    suspend fun kickMember(
        @Header("Authorization") token: String,
        @Body body: HashMap<String, String>
    ) : RegisterResponse


    @Headers(
        "Content-Type: application/json"
    )
    @POST("ticket/delete")
    suspend fun ticketDelete(
        @Header("Authorization") token: String,
        @Body body: HashMap<String, String>
    ) : RegisterResponse

    @Headers(
        "Content-Type: application/json"
    )
    @GET("ticket")
    suspend fun getInvites(
        @Header("Authorization") token: String,
    ) : InviteResponse
}