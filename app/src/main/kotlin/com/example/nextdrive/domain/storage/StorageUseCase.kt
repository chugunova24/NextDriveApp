package com.example.nextdrive.domain.storage

import com.example.nextdrive.domain.supabase.supabase
import io.github.jan.supabase.storage.storage


class StorageUseCase {

    suspend fun uploadUserAvatar(userId: String, imageBytes: ByteArray) {
        try {
            val fileName = "$userId/avatar.jpg"
            val bucket = supabase.storage.from("profiles")
            bucket.upload(fileName, imageBytes) {
                upsert = true
            }
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getUserAvatar(userId: String): ByteArray? {
        return try {
            val fileName = "$userId/avatar.jpg"
            supabase.storage.from("profiles").downloadAuthenticated(path = fileName)
        } catch (e: Exception) {
            null
        }
    }


}