package azi.foosball.api;

import retrofit2.Call;
import retrofit2.Response;


public class DoNothingCallback implements retrofit2.Callback<Void> {

	@Override
	public void onResponse(Call<Void> call, Response<Void> response) {
		//do nothing
	}

	@Override
	public void onFailure(Call<Void> call, Throwable t) {
		//do nothing
	}
}
