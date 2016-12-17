package com.song.bisshop.model;

import android.content.Context;

import com.song.bisshop.db.DBManager;
import com.song.bisshop.http.RetrofitHttp;
import com.song.bisshop.model.bean.BannerDataModel;
import com.song.bisshop.model.bean.ProductDataModel;
import com.song.bisshop.model.bean.RecommendContentModel;

import java.util.List;

import okhttp3.OkHttpClient;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author：Anumbrella
 * Date：16/5/31 下午12:40
 */
public class RecommendModel {

    public static void getProductsDataFromNet(Subscriber<List<ProductDataModel>> subscriber) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        RetrofitHttp.getRetrofit(builder.build()).getProducts("getProducts")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }


    /**
     * 返回一个被观察者对象
     *
     * @param context
     * @return
     */
    public static Observable<List<RecommendContentModel>> getProductsFromDB(final Context context) {
        return Observable.create(new Observable.OnSubscribe<List<RecommendContentModel>>() {
            @Override
            public void call(Subscriber<? super List<RecommendContentModel>> subscriber) {
                //订阅者回调行为
                subscriber.onNext(DBManager.getManager(context).getRecommendContentsFromDB());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    public static void getRecommendBanners(Subscriber<List<BannerDataModel>> subscriber) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        RetrofitHttp.getRetrofit(builder.build()).getBanners("getBanners")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


}
