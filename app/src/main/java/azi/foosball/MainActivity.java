package azi.foosball;

import static azi.foosball.Team.BLUE;
import static azi.foosball.Team.RED;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;
import azi.foosball.model.MatchForm;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

	@BindView(R.id.mainLayout) LinearLayout mainLayout;

	@BindView(R.id.redWinsButton) Button redWinsButton;
	@BindView(R.id.blueWinsButton) Button blueWinsButton;

	private Map<Player, ToggleButtons> buttonsMap = new HashMap<>();
	private Queue<ToggleButton> redQueue = new LinkedList<>();
	private Queue<ToggleButton> blueQueue = new LinkedList<>();
	private List<Player> redTeam = new ArrayList<>();
	private List<Player> blueTeam = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		createPlayerButtons();;
	}

	@OnClick(R.id.blueWinsButton)
	void saveBlueWin() {

		saveMatch(BLUE);
	}

	@OnClick(R.id.redWinsButton)
	void saveRedWin() {

		saveMatch(RED);
	}

	private void saveMatch(Team winner) {

		MatchForm form = new MatchForm();
		form.setWinner(winner);
		form.setRedTeam(redTeam);
		form.setBlueTeam(blueTeam);

		List<ToggleButton> collect = buttonsMap.values().stream()
			.map(ToggleButtons::getButtons)
			.flatMap(Collection::stream)
			.collect(Collectors.toList());

		new SaveMatchAsyncTask(collect, this).execute(form);
	}

	private void createPlayerButtons() {

		Arrays.stream(Player.values()).forEach(player -> mainLayout.addView(createPlayerView(player)));
	}

	@SuppressWarnings("ConstantConditions")
	private View createPlayerView(Player player) {

		View view = getLayoutInflater().inflate(R.layout.team_selection_view, null, false);

		ToggleButton blueButton = view.findViewById(R.id.blueTeamButton);
		blueButton.setText(player.getName());
		blueButton.setTextOn(player.getName());
		blueButton.setTextOff(player.getName());

		ToggleButton redButton = view.findViewById(R.id.redTeamButton);
		redButton.setText(player.getName());
		redButton.setTextOn(player.getName());
		redButton.setTextOff(player.getName());

		blueButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
			if (isChecked) {
				redButton.setChecked(false);

				if (blueQueue.size() == 2) {
					blueQueue.poll().setChecked(false);
					blueTeam.remove(player);
				}

				blueQueue.add(blueButton);

				blueTeam.add(player);
			}

			if (!isChecked) {
				blueQueue.remove(blueButton);
				blueTeam.remove(player);
			}
		});

		redButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
			if (isChecked) {
				blueButton.setChecked(false);

				if (redQueue.size() == 2) {
					redQueue.poll().setChecked(false);
					redTeam.remove(player);
				}

				redQueue.add(redButton);
				redTeam.add(player);
			}

			if (!isChecked) {
				redQueue.remove(redButton);
				redTeam.remove(player);
			}
		});

		buttonsMap.put(player, new ToggleButtons(blueButton, redButton));

		return view;
	}

	class ToggleButtons {

		ToggleButton blue;
		ToggleButton red;

		ToggleButtons(ToggleButton blue, ToggleButton red) {

			this.blue = blue;
			this.red = red;
		}

		List<ToggleButton> getButtons() {

			return Arrays.asList(blue, red);
		}
	}
}
