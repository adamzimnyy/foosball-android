package azi.foosball;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


class RetrofitBuilder {

	private static final String BASE_URL = "https://foosball-unity.herokuapp.com/";

	static Object createApiService(Class<?> clas) {

		return build(BASE_URL)
			.create(clas);
	}

	static Object createApiService(Class<?> clas, String url) {

		return build(url).create(clas);
	}

	private static Retrofit build(String url) {

		OkHttpClient client = new OkHttpClient.Builder().build();

		return new Retrofit.Builder()
			.client(client)
			.baseUrl(url)
			.addConverterFactory(GsonConverterFactory.create())
			.build();
	}
}