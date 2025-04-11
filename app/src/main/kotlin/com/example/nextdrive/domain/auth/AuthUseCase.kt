package com.example.nextdrive.domain.auth

import com.example.nextdrive.domain.supabase.supabase
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.user.UserSession
import io.github.jan.supabase.storage.storage

import com.example.nextdrive.data.UserProfile
import io.github.jan.supabase.postgrest.from


class AuthUseCase {
    val db = supabase

    suspend fun signUp(email: String, password: String): Result<Any> {
        return try {
            supabase.auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            Result.success("Вы успешно зарегистрировались!")
        } catch (e: Exception) {
            Result.failure(Exception("Возникла проблема с регистрацией, попробуйте еще раз."))
        }
    }

    suspend fun createProfile(data: UserProfile): UserProfile? {
        return try {
            supabase
                .from("profiles")
                .insert(data) {
                    select()
                }
                .decodeSingleOrNull()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun signIn(email: String, password: String): Result<String> {
        return try {
            supabase.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            Result.success("Вы успешно вошли в аккаунт.")
        } catch (e: Exception) {
            Result.failure(Exception("Возникла проблема со входом в приложение, попробуйте еще раз."))
        }
    }

    suspend fun getCurrentSession(): UserSession? {
        return try {
            supabase.auth.currentSessionOrNull()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun logout(): Result<Any> {
        return try {
            supabase.auth.signOut()
            Result.success("Вы успешно вышли из аккаунта.")
        } catch (e: Exception) {
            Result.failure(Exception("Возникла проблема с выходом из приложения, попробуйте еще раз."))
        }
    }

}