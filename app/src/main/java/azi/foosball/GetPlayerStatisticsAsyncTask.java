package azi.foosball;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

import android.os.AsyncTask;
import azi.foosball.api.RestApiClient;
import azi.foosball.model.PlayerStatisticsDto;
import azi.foosball.ui.main.ScoreboardFragment;


public class GetPlayerStatisticsAsyncTask extends AsyncTask<Void, Void, List<PlayerStatisticsDto>> {

	private final WeakReference<ScoreboardFragment> weakActivity;


	public GetPlayerStatisticsAsyncTask(ScoreboardFragment myActivity) {

		this.weakActivity = new WeakReference<>(myActivity);
	}

	@Override
	protected List<PlayerStatisticsDto> doInBackground(Void... voids) {

		RestApiClient api = new RestApiClient();

		try {
			return api.getStatistics();
		}
		catch (IOException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	protected void onPostExecute(List<PlayerStatisticsDto> response) {

		weakActivity.get().updateItems(response);
	}
}