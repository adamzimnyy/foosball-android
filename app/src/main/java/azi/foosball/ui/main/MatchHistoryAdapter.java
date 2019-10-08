package azi.foosball.ui.main;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import azi.foosball.DateUtils;
import azi.foosball.R;
import azi.foosball.model.MatchDto;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MatchHistoryAdapter extends RecyclerView.Adapter<MatchHistoryAdapter.MatchHistoryViewHolder> {

	private List<MatchDto> matches;
	private Context context;

	MatchHistoryAdapter(List<MatchDto> items, Context context) {

		this.matches = items;
		this.context = context;
	}

	@NonNull
	@Override
	public MatchHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.match_history_item, parent, false);

		return new MatchHistoryViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull MatchHistoryViewHolder holder, int position) {

		if (matches.isEmpty())
			return;
		MatchDto playerStatisticsDto = matches.get(position);
		holder.update(playerStatisticsDto);
	}

	@Override
	public int getItemCount() {

		return matches.size();
	}

	public void update(List<MatchDto> list) {

		this.matches = list;
		notifyDataSetChanged();
	}

	class MatchHistoryViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.blue_team_1)  TextView blueTeam1;
		@BindView(R.id.blue_team_2)TextView blueTeam2;
		@BindView(R.id.red_team_1)TextView redTeam1;
		@BindView(R.id.red_team_2)	TextView redTeam2;
		@BindView(R.id.winner)	TextView winner;
		@BindView(R.id.date)	TextView date;

		MatchHistoryViewHolder(@NonNull View itemView) {

			super(itemView);

			ButterKnife.bind(this, itemView);
		}

		void update(MatchDto matchDto) {

			redTeam1.setText(matchDto.getRedTeam().get(0).getName());
			redTeam2.setText(matchDto.getRedTeam().get(1).getName());
			
			blueTeam1.setText(matchDto.getBlueTeam().get(0).getName());
			blueTeam2.setText(matchDto.getBlueTeam().get(1).getName());
			
			date.setText(DateUtils.formatRelative(matchDto.getDate()));
			
			winner.setText(matchDto.getWinner().toString());
		}
	}
}
