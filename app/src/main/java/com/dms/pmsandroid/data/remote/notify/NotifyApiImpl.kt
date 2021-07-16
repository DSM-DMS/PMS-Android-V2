package com.dms.pmsandroid.data.remote.notify

import com.dms.pmsandroid.data.remote.ApiProvider
import com.dms.pmsandroid.feature.notify.model.GalleryDetailResponse
import com.dms.pmsandroid.feature.notify.model.GalleryListModel
import com.dms.pmsandroid.feature.notify.model.NoticeDetailModel
import com.dms.pmsandroid.feature.notify.model.NoticeListModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import retrofit2.http.Header

class NotifyApiImpl {
    private fun provideNotifyApi(): NotifyApi =
        ApiProvider.jiWooRetrofitBuilder.create(NotifyApi::class.java)

    fun getNoticeList(
        accessToken: String,
        page: Int,
        size: Int
    ): @NonNull Single<Response<List<NoticeListModel>>> =
        provideNotifyApi().getNoticeList(accessToken, page, size)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getNoticeDetail(
        accessToken: String, id: Int
    ): @NonNull Single<Response<NoticeDetailModel>> =
        provideNotifyApi().getNoticeDetail(accessToken, id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getHomeNoticeList(
        accessToken: String,
        page: Int,
        size: Int
    ): @NonNull Single<Response<List<NoticeListModel>>> =
        provideNotifyApi().getHomeNotice(accessToken, page, size)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getGalleryList(
        page: Int,
        size: Int
    ): @NonNull Single<Response<GalleryListModel>> =
        provideNotifyApi().getGallery(page, size)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getGalleryDetail(
        id: Int
    ): @NonNull Single<Response<GalleryDetailResponse>> = provideNotifyApi().getGalleryDetail(id)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
}