package azi.foosball.api;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitBuilder {

	private static final String BASE_URL = "https://foosball-unity.herokuapp.com/";

	public static Object createApiService(Class<?> clas) {

		return build(BASE_URL)
			.create(clas);
	}

	static Object createApiService(Class<?> clas, String url) {

		return build(url).create(clas);
	}

	private static Retrofit build(String url) {

	

		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {

			@Override
			public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {

				return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			}
		}).create();

		OkHttpClient client = new OkHttpClient.Builder()
			.readTimeout(10, TimeUnit.SECONDS)
			.connectTimeout(10, TimeUnit.SECONDS)
			.build();

		return new Retrofit.Builder()
			.client(client)
			.baseUrl(url)
			.addConverterFactory(GsonConverterFactory.create(gson))
			.build();
	}
}