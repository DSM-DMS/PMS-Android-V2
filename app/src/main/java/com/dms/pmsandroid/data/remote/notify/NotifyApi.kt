package com.dms.pmsandroid.data.remote.notify

import com.dms.pmsandroid.feature.notify.model.GalleryListModel
import com.dms.pmsandroid.feature.notify.model.NoticeListModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NotifyApi {
    @GET("/notice")
    fun getNoticeList(
        @Header("Authorization") accessToken: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Single<Response<List<NoticeListModel>>>

    @GET("/notice/news")
    fun getHomeNotice(
        @Header("Authorization") accessToken: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Single<Response<List<NoticeListModel>>>

    @GET("gallery")
    fun getGallery(
        @Query("page")page:Int,
        @Query("size")size:Int
    ): Single<Response<GalleryListModel>>
}