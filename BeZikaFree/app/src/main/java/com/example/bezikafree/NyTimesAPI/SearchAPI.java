package com.example.bezikafree.NyTimesAPI;

import com.example.bezikafree.keys.keys;
import com.example.bezikafree.models.NewsWireResults;
import com.example.bezikafree.models.ZikaArticleSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Billy on 6/11/16.
 */
public interface SearchAPI {

    @GET("{source}/{section}/720.json?&api-key=" + keys.newsWireKey)
    Call<NewsWireResults> listNewsWireResults(@Path("source") String source,
                                              @Path("section") String section,
                                              @Query("limit") int limit);

    @GET("articlesearch.json?&sort=newest&api-key=" + keys.nyTimesFullSearchQueryKey)
    Call<ZikaArticleSearch> listArticleSearchDocs(@Query("q") String q);

}
