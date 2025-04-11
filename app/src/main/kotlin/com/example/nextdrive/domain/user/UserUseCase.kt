package com.example.nextdrive.domain.user

import android.provider.ContactsContract.Profile
import com.example.nextdrive.domain.supabase.supabase
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.user.UserSession
import io.github.jan.supabase.postgrest.from

import kotlinx.serialization.Serializable
import io.github.jan.supabase.storage.storage
import io.github.jan.supabase.storage.FileUploadResponse
import io.ktor.http.Url

import com.example.nextdrive.data.UserProfile


class UserUseCase() {

    suspend fun getCurrentSession(): UserSession? {
        return try {
            supabase.auth.currentSessionOrNull()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getUserProfile(userId: String): UserProfile {
        return try {
            val result = supabase.from("profiles")
                .select {
                    filter {
                        eq("user_id", userId)
                    }
                }
                .decodeSingleOrNull<UserProfile>()
            result!!
        } catch (e: Exception) {
            UserProfile()
        }
    }

//    suspend fun uploadUserAvatar(userId: String, imageBytes: ByteArray): Any {
//        val fileName = "$userId.jpg"
//        return try {
//            val response: FileUploadResponse = supabase.storage.from("avatars").upload(
//                path = fileName,
//                data = imageBytes,
//    //                upsert = true
//            )
//            response
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }

//    suspend fun getUserAvatar(userId: String): ByteArray? {
//        val fileName = "$userId.jpg"
//        return try {
//            val avatar: ByteArray =  supabase.storage.from("avatars").downloadAuthenticated(path = fileName)
//            avatar
//        } catch (e: Exception) {
//            e.printStackTrace()
//            null
//        }
//    }


}