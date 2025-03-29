package com.example.nextdrive.data

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest


var host_url = "https://qldqvllrlqwtlaeqxmnc.supabase.co"
var anon_key = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InFsZHF2bGxybHF3dGxhZXF4bW5jIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDMxMDc4OTksImV4cCI6MjA1ODY4Mzg5OX0.6ld-SCMv5r1Gs9oEuFdMeLc_doCC0qzxu6plrSMo5Gc"


val supabase = createSupabaseClient(
    supabaseUrl = host_url,
    supabaseKey = anon_key
) {
    install(Auth) {
        host = host_url
        scheme = "auth"
    }
    install(Postgrest)
}
