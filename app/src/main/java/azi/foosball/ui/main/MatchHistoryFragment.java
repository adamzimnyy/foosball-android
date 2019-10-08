package azi.foosball.ui.main;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import azi.foosball.R;
import azi.foosball.api.FoosballApi;
import azi.foosball.api.RetrofitBuilder;
import azi.foosball.model.MatchDto;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MatchHistoryFragment extends Fragment {

	@BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
	@BindView(R.id.recycler_view) RecyclerView recyclerView;

	static MatchHistoryFragment newInstance() {

		return new MatchHistoryFragment();
	}

	@Override
	public View onCreateView(
		@NonNull LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {

		View root = inflater.inflate(R.layout.f_match_history, container, false);

		ButterKnife.bind(this, root);

		swipeRefreshLayout.setOnRefreshListener(() -> {

			FoosballApi api = (FoosballApi) RetrofitBuilder.createApiService(FoosballApi.class);
			api.getAllMatches().enqueue(new Callback<List<MatchDto>>() {

				@Override
				public void onResponse(Call<List<MatchDto>> call, Response<List<MatchDto>> response) {

					if (response.isSuccessful()) {
						List<MatchDto> body = response.body();
						getActivity().runOnUiThread(() -> updateItems(body));

					}

					swipeRefreshLayout.setRefreshing(false);
				}

				@Override
				public void onFailure(Call<List<MatchDto>> call, Throwable t) {

					Log.d("ddd", "onResponse: "+t.getLocalizedMessage());
					swipeRefreshLayout.setRefreshing(false);
				}
			});
		});

		initRecyclerView();

		return root;
	}

	private void initRecyclerView() {

		MatchHistoryAdapter scoreboardAdapter = new MatchHistoryAdapter(new ArrayList<>(), this.getContext());

		recyclerView.setAdapter(scoreboardAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
	}

	public void updateItems(List<MatchDto> response) {

		MatchHistoryAdapter adapter = (MatchHistoryAdapter) recyclerView.getAdapter();

		adapter.update(response);
	}
}
