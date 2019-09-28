package azi.foosball;

import java.io.IOException;
import java.util.List;

import azi.foosball.model.MatchForm;
import azi.foosball.model.PlayerStatisticsDto;
import retrofit2.Response;


class RestApiClient {

	RestResponse saveMatch(MatchForm form) throws IOException {

		FoosballApi api = (FoosballApi) RetrofitBuilder.getService(FoosballApi.class);

		Response<Void> execute = api.saveMatch(form).execute();

		return new RestResponse(execute.code(), execute.message(), execute.isSuccessful());
	}

	List<PlayerStatisticsDto> getStatistics() throws IOException {

		FoosballApi api = (FoosballApi) RetrofitBuilder.getService(FoosballApi.class);

		return api.getStatistics().execute().body();
	}
}
