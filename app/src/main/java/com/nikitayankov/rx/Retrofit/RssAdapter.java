package com.nikitayankov.rx.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RssAdapter {
    @GET("news/{category}/rss")
    Call<Feed> GetItems(@Path("category") String category);
}
