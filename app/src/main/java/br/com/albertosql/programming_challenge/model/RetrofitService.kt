package br.com.albertosql.programming_challenge.model

import br.com.albertosql.programming_challenge.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}