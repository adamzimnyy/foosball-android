package azi.foosball;

import static azi.foosball.RetrofitBuilder.createApiService;

import java.io.IOException;
import java.util.List;

import azi.foosball.model.MatchForm;
import azi.foosball.model.PlayerStatisticsDto;
import retrofit2.Response;


class RestApiClient {

	RestResponse saveMatch(MatchForm form) throws IOException {

		FoosballApi api = (FoosballApi) createApiService(FoosballApi.class);

		Response<Void> execute = api.saveMatch(form).execute();

		return new RestResponse(execute.code(), execute.message(), execute.isSuccessful());
	}

	List<PlayerStatisticsDto> getStatistics() throws IOException {

		FoosballApi api = (FoosballApi) createApiService(FoosballApi.class);

		return api.getStatistics().execute().body();
	}

	void wakeUp() {

		FoosballApi api = (FoosballApi) createApiService(FoosballApi.class);

		api.wakeUp().enqueue(new DoNothingCallback());
	}
}
