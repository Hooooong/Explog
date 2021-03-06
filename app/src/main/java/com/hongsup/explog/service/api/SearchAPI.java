package com.hongsup.explog.service.api;

import com.hongsup.explog.data.post.PostCover;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 정인섭 on 2017-12-13.
 */

public interface SearchAPI {

    @FormUrlEncoded
    @POST("/post/search/")
    Observable<Response<List<PostCover>>> getSearchResult(@Field("word")String word);
}
