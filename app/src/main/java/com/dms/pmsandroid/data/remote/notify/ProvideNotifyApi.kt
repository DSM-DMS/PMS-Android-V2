package com.dms.pmsandroid.data.remote.notify

import com.dms.pmsandroid.data.remote.PotatoChipApi
import com.dms.pmsandroid.presentation.feature.notify.model.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class ProvideNotifyApi(api: PotatoChipApi) {

    private val notifyApi = api.retrofit.create(NotifyApi::class.java)

    fun getNoticeList(
        accessToken: String,
        page: Int,
        size: Int
    ): @NonNull Single<Response<NoticeResponseModel>> =
        notifyApi.getNoticeList(accessToken, page, size)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getNoticeDetail(
        accessToken: String, id: Int
    ): @NonNull Single<Response<NoticeDetailModel>> =
        notifyApi.getNoticeDetail(accessToken, id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun searchNotice(
        accessToken: String, keyword: String
    ): @NonNull Single<Response<List<NoticeListModel>>> =
        notifyApi.searchNotice(accessToken, keyword)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getHomeNoticeList(
        accessToken: String,
        page: Int,
        size: Int
    ): @NonNull Single<Response<NoticeResponseModel>> =
        notifyApi.getHomeNotice(accessToken, page, size)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun searchHome(
        accessToken: String, keyword: String
    ): @NonNull Single<Response<List<NoticeListModel>>> =
        notifyApi.searchHome(accessToken, keyword)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getGalleryList(
        page: Int,
        size: Int
    ): @NonNull Single<Response<GalleryListModel>> =
        notifyApi.getGallery(page, size)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getGalleryDetail(
        id: Int
    ): @NonNull Single<Response<GalleryDetailResponse>> =
        notifyApi.getGalleryDetail(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getReComments(
        accessToken: String,
        id: Int
    ): @NonNull Single<Response<List<CommentModel>>> =
        notifyApi.getReComments(accessToken, id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun postComment(
        accessToken: String,
        noticeId: Int,
        body: CommentRequestModel
    ): @NonNull Single<Response<Void>> =
        notifyApi.postComment(accessToken, noticeId, body)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}