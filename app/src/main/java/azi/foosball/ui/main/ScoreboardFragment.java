package azi.foosball.ui.main;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import azi.foosball.api.FoosballApi;
import azi.foosball.R;
import azi.foosball.api.RetrofitBuilder;
import azi.foosball.model.PlayerStatisticsDto;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScoreboardFragment extends Fragment {

	@BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
	@BindView(R.id.recycler_view) RecyclerView recyclerView;

	static ScoreboardFragment newInstance() {

		return new ScoreboardFragment();
	}

	@Override
	public View onCreateView(
		@NonNull LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {

		View root = inflater.inflate(R.layout.f_scoreboard, container, false);

		ButterKnife.bind(this, root);

		swipeRefreshLayout.setOnRefreshListener(() -> {

			FoosballApi api = (FoosballApi) RetrofitBuilder.createApiService(FoosballApi.class);
			api.getStatistics().enqueue(new Callback<List<PlayerStatisticsDto>>() {

				@Override
				public void onResponse(Call<List<PlayerStatisticsDto>> call, Response<List<PlayerStatisticsDto>> response) {

					if (response.isSuccessful()) {
						List<PlayerStatisticsDto> body = response.body();

						getActivity().runOnUiThread(() -> updateItems(body));
					}
					swipeRefreshLayout.setRefreshing(false);
				}

				@Override
				public void onFailure(Call<List<PlayerStatisticsDto>> call, Throwable t) {

					swipeRefreshLayout.setRefreshing(false);
				}
			});
		});

		initRecyclerView();

		return root;
	}

	private void initRecyclerView() {

		ScoreboardAdapter scoreboardAdapter = new ScoreboardAdapter(new ArrayList<>(), this.getContext());

		recyclerView.setAdapter(scoreboardAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
	}

	public void updateItems(List<PlayerStatisticsDto> response) {

		ScoreboardAdapter adapter = (ScoreboardAdapter) recyclerView.getAdapter();

		adapter.update(response);
	}
}