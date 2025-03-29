package com.example.nextdrive.domain

import androidx.lifecycle.LifecycleCoroutineScope
import com.example.nextdrive.data.supabase
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


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