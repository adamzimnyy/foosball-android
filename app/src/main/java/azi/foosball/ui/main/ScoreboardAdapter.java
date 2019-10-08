package azi.foosball.ui.main;

import java.util.List;

import com.timqi.sectorprogressview.ColorfulRingProgressView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import azi.foosball.R;
import azi.foosball.model.PlayerStatisticsDto;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class ScoreboardAdapter extends RecyclerView.Adapter<ScoreboardAdapter.ScoreboardViewHolder> {

	private List<PlayerStatisticsDto> playerStatistics;
	private Context context;

	public ScoreboardAdapter(List<PlayerStatisticsDto> items, Context context) {

		this.playerStatistics = items;
		this.context = context;
	}

	@NonNull
	@Override
	public ScoreboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.scoreboard_item, parent, false);

		return new ScoreboardViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ScoreboardViewHolder holder, int position) {

		if (playerStatistics.isEmpty())
			return;
		PlayerStatisticsDto playerStatisticsDto = playerStatistics.get(position);
		holder.update(playerStatisticsDto);
	}

	@Override
	public int getItemCount() {

		return playerStatistics.size();
	}

	public void update(List<PlayerStatisticsDto> list) {

		this.playerStatistics = list;
		notifyDataSetChanged();
	}

	class ScoreboardViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.victories_text) TextView victoriesText;
		@BindView(R.id.losses_text) TextView lossesText;
		@BindView(R.id.player_name) TextView playerNameText;
		@BindView(R.id.percentage_text) TextView percetageText;
		@BindView(R.id.percentage_view) ColorfulRingProgressView winRate;
		@BindView(R.id.image_view) CircleImageView circleImageView;

		ScoreboardViewHolder(@NonNull View itemView) {

			super(itemView);

			ButterKnife.bind(this, itemView);
		}

		void update(PlayerStatisticsDto playerStatisticsDto) {

			playerNameText.setText(String.valueOf(playerStatisticsDto.getPlayer().getName()));

			victoriesText.setText(String.valueOf(playerStatisticsDto.getWinRates().getTotalVictories()));
			lossesText.setText(String.valueOf(playerStatisticsDto.getWinRates().getTotalMatches() - playerStatisticsDto.getWinRates().getTotalVictories()));

			winRate.setPercent(playerStatisticsDto.getWinRates().getWinPercent() * 100f);
			percetageText.setText(String.valueOf((int) (playerStatisticsDto.getWinRates().getWinPercent() * 100f)));
			int image = playerStatisticsDto.getPlayer().getImage();

			circleImageView.setImageDrawable(context.getResources().getDrawable(image));
		}
	}
}
