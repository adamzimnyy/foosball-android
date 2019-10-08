package azi.foosball;

import java.util.Arrays;
import java.util.Objects;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PasswordActivity extends AppCompatActivity {

	@BindView(R.id.passwordText)
	EditText passwordText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password);

		ButterKnife.bind(this);

		checkPassword();
	}

	private void checkPassword() {

		SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

		if (sharedPref.contains("password")) {
			validatePassword(sharedPref.getString("password", null));
		}
	}

	@OnClick(R.id.loginButton)
	public void login() {

		validatePassword(passwordText.getText().toString());
	}

	private void validatePassword(String password) {

		if (Arrays.stream(Player.values()).map(Enum::name).anyMatch(name -> Objects.equals(name, password))) {

			IntentHelper.startActivityIntent(this, Main3Activity.class);

			SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
			preferences.edit().putString("password", password).apply();

			this.finish();
		}
	}
}
