package com.example.nextdrive.domain.supabase


data class Supabase(
    val supabaseUrl: String,
    val supabaseKey: String,
    val schemeAuth: String
)