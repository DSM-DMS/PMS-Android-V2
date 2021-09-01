package com.dms.pmsandroid.data.remote.notify

import com.dms.pmsandroid.feature.notify.model.*
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.*

interface NotifyApi {
    @GET("/notice")
    fun getNoticeList(
        @Header("Authorization") accessToken: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Single<Response<NoticeResponseModel>>

    @GET("/notice/{notice_id}")
    fun getNoticeDetail(
        @Header("Authorization") accessToken: String,
        @Path("notice_id") id: Int
    ): Single<Response<NoticeDetailModel>>

    @GET("/notice/search")
    fun searchNotice(
        @Header("Authorization") accessToken: String,
        @Query("q") keyWord: String,
        @Query("page") page: Int
    ): Single<Response<NoticeResponseModel>>

    @GET("/notice/news")
    fun getHomeNotice(
        @Header("Authorization") accessToken: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Single<Response<NoticeResponseModel>>

    @GET("/notice/news/search")
    fun searchHome(
        @Header("Authorization") accessToken: String,
        @Query("q") keyWord: String,
        @Query("page") page: Int
    ): Single<Response<NoticeResponseModel>>

    @GET("/gallery")
    fun getGallery(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Single<Response<GalleryListModel>>

    @GET("/gallery/{gallery_id}")
    fun getGalleryDetail(
        @Path("gallery_id") galleryId: Int
    ): Single<Response<GalleryDetailResponse>>

    @GET("/notice/{comment_id}/comment")
    fun getReComments(
        @Header("Authorization") accessToken: String,
        @Path("comment_id") id: Int
    ): Single<Response<List<CommentModel>>>

    @POST("/notice/{notice_id}/comment")
    fun postComment(
        @Header("Authorization") accessToken: String,
        @Path("notice_id")noticeId:Int,
        @Body body: CommentRequestModel
    ): Single<Response<Void>>
}