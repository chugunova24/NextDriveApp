package com.example.nextdrive.domain.car

import com.example.nextdrive.data.Car
import com.example.nextdrive.domain.supabase.supabase
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns


//interface CarRepository {
//    suspend fun getAllCars(): List<Car>
//    suspend fun getCarById(id: String): Car?
//    suspend fun searchCars(brand: String): List<Car>
//}

class CarRepository {
    suspend fun getAllCars(): List<Car> {
        return supabase.from("cars")
            .select()
            .decodeList<Car>()
    }

    suspend fun searchCars(query: String): List<Car> {
        return supabase.from("cars").select {
            filter {
                or {
                    ilike("brand", "%${query}%")
                    ilike("model", "%${query}%")
                    ilike("type", "%${query}%")
                }
            }
        }.decodeList<Car>()
    }
}