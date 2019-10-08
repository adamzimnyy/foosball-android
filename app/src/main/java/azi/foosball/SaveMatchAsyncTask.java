package azi.foosball;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import android.os.AsyncTask;
import android.widget.Toast;
import android.widget.ToggleButton;
import azi.foosball.api.RestApiClient;
import azi.foosball.api.RestResponse;
import azi.foosball.model.MatchForm;
import azi.foosball.ui.main.SaveMatchFragment;


public class SaveMatchAsyncTask extends AsyncTask<MatchForm, Void, RestResponse> {

	private final WeakReference<SaveMatchFragment> weakActivity;

	private List<ToggleButton> buttons;

	public SaveMatchAsyncTask(List<ToggleButton> buttons, SaveMatchFragment myActivity) {

		this.buttons = buttons;
		this.weakActivity = new WeakReference<>(myActivity);
	}

	@Override
	protected void onPreExecute() {

		weakActivity.get().showProgress();
	}

	@Override
	protected RestResponse doInBackground(MatchForm... forms) {

		RestApiClient api = new RestApiClient();

		try {
			return api.saveMatch(forms[0]);
		}
		catch (IOException e) {
			return new RestResponse(null, e.getMessage(), false);
		}
	}

	@Override
	protected void onPostExecute(RestResponse response) {

		weakActivity.get().hideProgress();

		if (response.isSuccessful()) {
			buttons.forEach(button -> button.setChecked(false));
			Toast.makeText(weakActivity.get().getContext(), "Saved!", Toast.LENGTH_SHORT).show();
		}
		else {
			Toast.makeText(weakActivity.get().getContext(), "Failed! " + response.getCode() + "  " + response.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
}