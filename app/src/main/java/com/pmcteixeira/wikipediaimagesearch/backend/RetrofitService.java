package com.pmcteixeira.wikipediaimagesearch.backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.pmcteixeira.wikipediaimagesearch.backend.pojos.Page;
import com.pmcteixeira.wikipediaimagesearch.backend.pojos.Pages;
import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created with Android Studio.
 * User: pedroteixeira pmcteixeira@gmail.com
 * Date: 21/01/15
 * Time: 11:21
 */
public class RetrofitService {

	String API_URL = "https://en.wikipedia.org/w/";

	public RetrofitService() {
	}

	public <S> S  createService(Class<S> serviceClass) {

		Gson gson = new GsonBuilder()
						.excludeFieldsWithoutExposeAnnotation()
						.registerTypeAdapter(Pages.class, new PagesDeserializer())
						.create();

		RestAdapter.Builder builder = new RestAdapter.Builder()
				.setEndpoint(API_URL)
				//.setLogLevel(RestAdapter.LogLevel.FULL)
				.setConverter(new GsonConverter(gson))
				.setClient(new OkClient(new OkHttpClient()));

		RestAdapter adapter = builder.build();

		return adapter.create(serviceClass);
	}

	private class PagesDeserializer implements JsonDeserializer<Pages> {
		@Override
		public Pages deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
				throws JsonParseException {

			Gson gson = new Gson();
			JsonObject jsonObject = je.getAsJsonObject();

			// Iterate entrySet to get Page objects.
			Pages pages = new Pages();
			List<Page> pageList = new ArrayList<>();
			Iterable<Map.Entry<String,JsonElement>> entries = jsonObject.entrySet();
			for (Map.Entry<String, JsonElement> entry : entries) {
				Page page = gson.fromJson(entry.getValue(), Page.class);
				pageList.add(page);
			}

			pages.setPages(pageList);
			return pages;
		}
	}
}
