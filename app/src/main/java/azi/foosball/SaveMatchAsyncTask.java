package azi.foosball;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import android.os.AsyncTask;
import android.widget.Toast;
import android.widget.ToggleButton;
import azi.foosball.model.MatchForm;


public class SaveMatchAsyncTask extends AsyncTask<MatchForm, Void, RestResponse> {

	private final WeakReference<MainActivity> weakActivity;

	private List<ToggleButton> buttons;

	SaveMatchAsyncTask(List<ToggleButton> buttons, MainActivity myActivity) {

		this.buttons = buttons;
		this.weakActivity = new WeakReference<>(myActivity);
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

		if (response.isSuccessful()) {
			buttons.forEach(button -> button.setChecked(false));
			Toast.makeText(weakActivity.get(), "Saved!", Toast.LENGTH_SHORT).show();
		}
		else {
			Toast.makeText(weakActivity.get(), "Failed! " + response.getCode() + "  " + response.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
}