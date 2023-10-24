package com.example.androidworkshop.di

import com.example.androidworkshop.repo.CricketAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

//    @Singleton
//    @Provides
//    private fun provideOkHttpClient () : OkHttpClient {
//        val builder = OkHttpClient()
//            .newBuilder()
//
//        val requestInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//        builder.addNetworkInterceptor(requestInterceptor)
//
//        return builder.build()
//    }

    @Singleton
    @Provides
    fun providesSevayuAPI(retrofit: Retrofit): CricketAPI {
        return retrofit.create(CricketAPI::class.java)
    }

}
