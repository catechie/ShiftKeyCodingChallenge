package com.shiftkey.codingchallenge

import android.os.Build
import androidx.annotation.RequiresApi
import com.shiftkey.data.ShiftData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

object ShiftService {
    private val retrofit: Retrofit? = null
    private const val BASE_URL = "https://staging-app.shiftkey.com"
    val service = if (retrofit == null){
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ShiftApi::class.java)
    } else retrofit.create(ShiftApi::class.java)
}

interface ShiftApi {
    @RequiresApi(Build.VERSION_CODES.O)
    @GET("api/v2/available_shifts")
    suspend fun getShiftData(
        @Header("Accept") accept: String = "application/json",
        @Query("address") address: String = "Dallas, TX",
        @Query("type") type: String = "list",
        @Query("start") start: String? = DateUtil(0),
        @Query("end") end: String? = DateUtil(1),
    ): Response<ShiftData>
}

@RequiresApi(Build.VERSION_CODES.O)
fun DateUtil(numberOfDays: Int): String? {
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val cal = Calendar.getInstance()
    System.out.println("Current Date: " + sdf.format(cal.time))
    //Adding x Day to the current date
    cal.add(Calendar.DAY_OF_MONTH, numberOfDays)
    //Date after adding one day to the current date
    return sdf.format(cal.time)
}