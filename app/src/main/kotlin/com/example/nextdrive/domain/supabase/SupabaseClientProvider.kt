package com.example.nextdrive.domain.supabase

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage


var supabaseData: Supabase = Supabase(
    supabaseUrl = "https://qldqvllrlqwtlaeqxmnc.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InFsZHF2bGxybHF3dGxhZXF4bW5jIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDMxMDc4OTksImV4cCI6MjA1ODY4Mzg5OX0.6ld-SCMv5r1Gs9oEuFdMeLc_doCC0qzxu6plrSMo5Gc",
    schemeAuth = "auth"
)

val supabase = createSupabaseClient(
    supabaseUrl = supabaseData.supabaseUrl,
    supabaseKey = supabaseData.supabaseKey
) {
    install(Auth) {
        host = supabaseData.supabaseUrl
        scheme = supabaseData.schemeAuth
    }
    install(Postgrest)
    install(Storage)
}
