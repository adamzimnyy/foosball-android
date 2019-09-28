package azi.foosball;

import java.util.List;

import azi.foosball.model.MatchForm;
import azi.foosball.model.PlayerStatisticsDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface FoosballApi {

	@GET("stats")
	Call<List<PlayerStatisticsDto>> getStatistics();

	@POST("match")
	Call<Void> saveMatch(@Body MatchForm match);
}
