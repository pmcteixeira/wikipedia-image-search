package com.pmcteixeira.wikipediaimagesearch.backend;

import com.pmcteixeira.wikipediaimagesearch.backend.pojos.WikiImgsResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created with Android Studio.
 * User: pedroteixeira pmcteixeira@gmail.com
 * Date: 21/01/15
 * Time: 12:28
 */
public interface WikipediaImagesClient {

	@GET("/api.php?action=query&prop=pageimages&format=json&piprop=thumbnail&pithumbsize=96&pilimit=50&generator=prefixsearch")
	void getImages(@Query("gpssearch") String query, @Query("gpslimit") int limit, Callback<WikiImgsResponse> callback);

}
