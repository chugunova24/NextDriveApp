package com.example.nextdrive.domain.authentication.usecase

import com.example.nextdrive.common.supabase.supabase
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.gotrue.user.UserSession
//import timber.log.Timber
//import com.example.nextdrive.common.supabase.supabase
//import io.github.jan.supabase.auth.auth
//import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.user.UserInfo
import io.github.jan.supabase.auth.user.Identity


class AuthUseCase {

    suspend fun signUp(email: String, password: String) {
        try {
            val user = supabase.auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
        } catch (e: Exception) {
            throw Exception("AuthUseCase.signUp: " +  e.printStackTrace())
        }
    }

    suspend fun signIn(email: String, password: String) {
        try {
            val result = supabase.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
        } catch (e: Exception) {
            throw Exception("AuthUseCase.signIn: " +  e.printStackTrace())
        }
    }

}