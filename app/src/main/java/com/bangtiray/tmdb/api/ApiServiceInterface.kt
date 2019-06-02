package com.bangtiray.tmdb.api

import com.bangtiray.tmdb.models.NowPlayingModel
import com.bangtiray.tmdb.models.UpcomingModel
import com.bangtiray.tmdb.util.Constant
import io.reactivex.Observable
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceInterface {
    @GET("movie/now_playing")
    fun nowPlaying(@Query("language") lang: String): Observable<NowPlayingModel>

    @GET("movie/upcoming")
    fun upcoming(@Query("language") lang: String): Observable<NowPlayingModel>

    @GET("movie/{movie_id}")
    fun movieId(@Path("movie_id") movieId: String, @Query("language") lang: String): Observable<NowPlayingModel>

    @GET("search/movie")
    fun searchMovie(@Query("query") keyMovie: String, @Query("language") lang: String): Observable<NowPlayingModel>

    companion object Factory {
        fun create(): ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .client(client())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build()
            return retrofit.create(ApiServiceInterface::class.java)
        }

        private fun client(): OkHttpClient {
            val builder = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    var request = chain.request()
                    var httpUrl: HttpUrl = request.url()
                        .newBuilder()
                        .addQueryParameter("api_key", Constant.CLIENT_KEY)
                        .build()

                    val builder = request.newBuilder()
                        .url(httpUrl)


                    request = builder.build()
                    chain.proceed(request)
                }
            return builder.build()
        }
    }
}

