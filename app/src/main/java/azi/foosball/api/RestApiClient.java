package azi.foosball.api;

import static azi.foosball.api.RetrofitBuilder.createApiService;

import java.io.IOException;
import java.util.List;

import azi.foosball.model.MatchDto;
import azi.foosball.model.MatchForm;
import azi.foosball.model.PlayerStatisticsDto;
import retrofit2.Response;


public class RestApiClient {

	public RestResponse saveMatch(MatchForm form) throws IOException {

		FoosballApi api = (FoosballApi) createApiService(FoosballApi.class);

		Response<Void> execute = api.saveMatch(form).execute();

		return new RestResponse(execute.code(), execute.message(), execute.isSuccessful());
	}

	public List<MatchDto> getAllMatches() throws IOException {

		FoosballApi api = (FoosballApi) createApiService(FoosballApi.class);

		return api.getAllMatches().execute().body();
	}

	public List<PlayerStatisticsDto> getStatistics() throws IOException {

		FoosballApi api = (FoosballApi) createApiService(FoosballApi.class);

		return api.getStatistics().execute().body();
	}

	public void wakeUp() {

		FoosballApi api = (FoosballApi) createApiService(FoosballApi.class);

		api.wakeUp().enqueue(new DoNothingCallback());
	}
}
